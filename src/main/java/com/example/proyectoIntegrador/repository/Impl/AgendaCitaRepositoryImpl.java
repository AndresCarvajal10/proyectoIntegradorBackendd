package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
                    SELECT agenda_cita_id, usuario_id, empleado_id, fecha_inicio, estado, descripcion, direccion, telefono
                    FROM agenda_cita
                    WHERE usuario_id = ?
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                AgendaCita data = new AgendaCita();
                data.setAgendaCitaId(rs.getInt("agenda_cita_id"));
                data.setUsuarioId(rs.getInt("usuario_id"));
                data.setEmpleadoId(rs.getInt("empleado_id"));
                data.setEstado(rs.getString("estado"));
                data.setDescripcion(rs.getString("descripcion"));
                data.setDireccion(rs.getString("direccion"));
                data.setTelefono(rs.getString("telefono"));
                data.setFechaInicio(obtenerFechaEnviar(String.valueOf(rs.getInt("fecha_inicio"))));
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


    public int insertAgendaCita(AgendaCita agendaCita) {

        log.debug("Guardar cita");

        String sql = """
                INSERT INTO agenda_cita (usuario_id, empleado_id, fecha_inicio, estado, descripcion, direccion, telefono)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        // Insertamos la agenda cita y retornamos el ID de la agenda creada
        return jdbcTemplate.update(sql, ps -> {
            ps.setInt(1, agendaCita.getUsuarioId());
            ps.setInt(2, agendaCita.getEmpleadoId());
            ps.setInt(3, Integer.parseInt(agendaCita.getFechaInicio()));
            ps.setString(4, agendaCita.getEstado());
            ps.setString(5, agendaCita.getDescripcion());
            ps.setString(6, agendaCita.getDireccion());
            ps.setString(7, agendaCita.getTelefono());
        });
    }

    public String obtenerFechaEnviar(String fecha) {

        if (fecha != null && !fecha.isEmpty()) {

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Convertir a LocalDate
            LocalDate date = LocalDate.parse(fecha, inputFormatter);

            // Convertir a String con el formato deseadO
            return date.format(outputFormatter);

        } else {
            // Obtener la fecha de hoy
            LocalDate today = LocalDate.now();

            // Crear un formateador para el nuevo formato
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Cambiar a formato yyyyMMdd

            // Formatear la fecha en el nuevo formato
            return today.format(formatter);
        }

    }

}
