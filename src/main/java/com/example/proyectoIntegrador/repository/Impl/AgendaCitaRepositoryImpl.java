package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class AgendaCitaRepositoryImpl implements AgendaCitaRepository {

    private final JdbcTemplate jdbcTemplate;

    public AgendaCitaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<AgendaCita>> getListAgenda(int idClient) {
        log.debug("Obtener informacion de las citas agendadas");

        try {
            // Construye la consulta SQL
            String sql = """
                    SELECT ac.agenda_id, c.descripcion, c.fecha , c.hora, ec.descripcion as estado_cita 
                    FROM agenda_cita ac 
                    left join cita c on c.cita_id = ac.cita_id 
                    left join estado_cita ec on ec.estado_id  = ac.estado_id 
                    WHERE cliente_id = ?  
                    order by c.fecha desc, c.hora desc 
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                AgendaCita data = new AgendaCita();
                data.setAgendaCitaId(rs.getInt("agenda_id"));
                data.setDescripcion(rs.getString("descripcion"));
                data.setFechaInicio(rs.getString("fecha"));
                data.setHoraInicio(rs.getString("hora"));
                data.setEstado(rs.getString("estado_cita"));
                return data;
            }, idClient);

            if (!empresas.isEmpty()) {
                return Optional.of(empresas);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            log.error("There was a problem listar citas agendas: " + e.getMessage());
            return Optional.empty();
        }
    }


    @Transactional
    public Optional<Integer> insertAgendaCita(String description, String fecha, String hora, Integer estadoId, Integer clienteId, Integer medicoId) {
        log.debug("Guardar insertAgendaCita");

        // SQL para insertar la cita
        String sqlCita = """
                    INSERT INTO cita (descripcion, fecha, hora)
                    VALUES (?, ?, ?)
                """;

        // SQL para insertar en agenda_cita
        String sqlAgendaCita = """
                    INSERT INTO agenda_cita (estado_id, cliente_id, cita_id, medico_id)
                    VALUES (?, ?, ?, ?)
                """;

        try {
            // KeyHolder para capturar el ID generado de la cita
            KeyHolder keyHolder = new GeneratedKeyHolder();

            int rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sqlCita, new String[]{"cita_id"});
                ps.setString(1, description);
                ps.setDate(2, Date.valueOf(obtenerFechaEnviar(fecha)));
                ps.setString(3, hora);
                return ps;
            }, keyHolder);

            if (rowsAffected > 0) {
                Number citaId = keyHolder.getKey();
                if (citaId == null) throw new IllegalStateException("No se pudo obtener el ID de la cita");

                // Ahora insertamos en agenda_cita usando el cita_id generado
                int agendaRows = jdbcTemplate.update(sqlAgendaCita,
                        estadoId, clienteId, citaId.intValue(), medicoId);

                return (agendaRows > 0) ? Optional.of(citaId.intValue()) : Optional.empty();
            }

            return Optional.empty();
        } catch (Exception e) {
            log.error("Error al guardar la cita y agenda: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> actualizarFechaHoraCita(Integer citaId, String nuevaFecha, String nuevaHora, Integer medicoId) {
        log.debug("Actualizando fecha/hora para cita {}", citaId);

        // Verificar disponibilidad
        String sqlVerificar = """
                    SELECT COUNT(*)
                    FROM agenda_cita ac
                    JOIN cita c ON ac.cita_id = c.cita_id
                    WHERE c.fecha = ? AND c.hora = ? AND ac.medico_id = ? AND c.cita_id != ?
                """;

        try {

            Integer count = jdbcTemplate.queryForObject(sqlVerificar,
                    Integer.class, nuevaFecha, nuevaHora, medicoId, citaId);

            if (count > 0) {
                log.warn("Ya existe una cita para el médico {} en {} {}", medicoId, nuevaFecha, nuevaHora);
                return Optional.empty(); // Ya hay una cita en esa fecha/hora
            }

        } catch (Exception e) {
            log.error("Error. No se pudo validar disponibilidad de la cita", e);
            throw new RuntimeException("Error. No se pudo validar disponibilidad de la cita", e);
        }


        // Actualizar cita
        String sqlActualizar = """
                    UPDATE cita
                    SET fecha = ?, hora = ?
                    WHERE cita_id = ?
                """;

        try {
            int rows = jdbcTemplate.update(sqlActualizar, nuevaFecha, nuevaHora, citaId);
            return rows > 0 ? Optional.of(true) : Optional.empty();

        } catch (Exception e) {
            log.error("Error. No se pudo actualizar fecha y hora de la cita", e);
            throw new RuntimeException("Error. No se pudo actualizar fecha y hora de la cita", e);
        }

    }


    public Optional<Boolean> cancelarCita(Integer citaId) {
        log.debug("Cancelando lógicamente la cita {}", citaId);

        try {
            String sqlCancelar = """
                        UPDATE agenda_cita
                        SET estado_id = 3
                        WHERE cita_id = ?
                    """;

            int rows = jdbcTemplate.update(sqlCancelar, citaId);
            return rows > 0 ? Optional.of(true) : Optional.empty();

        } catch (Exception e) {
            log.error("Error al cancelar la cita {}: {}", citaId, e.getMessage(), e);
            return Optional.empty();
        }
    }


    @Override
    public Optional<Boolean> validateClient(int idClient) {

        log.debug("validate client");

        String sql = """
                SELECT COUNT(*) FROM cliente WHERE cliente_id = ? 
                """;

        try {
            var resp = jdbcTemplate.queryForObject(sql, Integer.class, idClient);
            return resp > 0 ? Optional.of(true) : Optional.empty();
        } catch (Exception e) {
            log.error("Error al validar cliente: " + e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> validateVeterinarian(int idVeterinarian) {
        log.debug("validate client");

        String sql = """
                SELECT COUNT(*) FROM medico WHERE medico_id = ? 
                """;

        try {
            var resp = jdbcTemplate.queryForObject(sql, Integer.class, idVeterinarian);
            return resp > 0 ? Optional.of(true) : Optional.empty();
        } catch (Exception e) {
            log.error("Error al validar cliente: " + e.getMessage());
            return Optional.empty();
        }
    }


    public Optional<Boolean> hayDisponibilidadCita(LocalDate fechaDeseada, LocalTime horaDeseada) {

        log.debug("start hayDisponibilidadCita");

        LocalTime horaInicio = horaDeseada.minusMinutes(30);
        LocalTime horaFin = horaDeseada.plusMinutes(30);

        String sql = """
                SELECT COUNT(*) FROM cita WHERE fecha = ? AND hora BETWEEN ? AND ?
                """;

        try {
            Integer cantidad = jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    Date.valueOf(fechaDeseada),
                    Time.valueOf(horaInicio),
                    Time.valueOf(horaFin)
            );

            return cantidad == 0 ? Optional.of(true) : Optional.empty(); // true = disponible

        } catch (Exception e) {
            log.error("Error al hayDisponibilidadCita: {}", e.getMessage());
            return Optional.empty();
        }

    }


    public LocalDate obtenerFechaEnviar(String fecha) {

        if (fecha != null && !fecha.isEmpty()) {

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir a LocalDate
            LocalDate date = LocalDate.parse(fecha, inputFormatter);

            // Convertir a String con el formato deseadO
            return date;

        } else {
            // Obtener la fecha de hoy
            LocalDate today = LocalDate.now();

            // Crear un formateador para el nuevo formato
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Cambiar a formato yyyyMMdd

            // Formatear la fecha en el nuevo formato
            return LocalDate.parse(today.format(formatter), formatter);
        }

    }

}
