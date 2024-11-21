package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                    SELECT agenda_cita_id, usuario_id, empleado_id, cita_id, fecha_inicio, estado, descripcion, direccion, telefono
                    FROM agenda_cita
                    WHERE usuario_id = ?
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                AgendaCita data = new AgendaCita();
                data.setAgendaCitaId(rs.getInt("agenda_cita_id"));
                data.setUsuarioId(rs.getInt("usuario_id"));
                data.setEmpleadoId(rs.getInt("empleado_id"));
                data.setCitaId(rs.getInt("cita_id"));
                data.setFechaInicio(rs.getTimestamp("fecha_inicio"));
                data.setEstado(rs.getString("estado"));
                data.setDescripcion(rs.getString("descripcion"));
                data.setDireccion(rs.getString("direccion"));
                data.setTelefono(rs.getInt("telefono"));
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
}
