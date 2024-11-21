package com.example.proyectoIntegrador.utils;

import com.example.proyectoIntegrador.models.AgendaCita;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AgendaCitaRowMapper implements RowMapper<AgendaCita> {

    @Override
    public AgendaCita mapRow(ResultSet rs, int rowNum) throws SQLException {
        AgendaCita agendaCita = new AgendaCita();

        agendaCita.setAgendaCitaId(rs.getInt("agenda_cita_id"));
        agendaCita.setUsuarioId(rs.getInt("usuario_id"));
        agendaCita.setEmpleadoId(rs.getInt("empleado_id"));
        agendaCita.setCitaId(rs.getInt("cita_id"));

        Timestamp fechaInicio = rs.getTimestamp("fecha_inicio");
        if (fechaInicio != null) {
            agendaCita.setFechaInicio(fechaInicio);
        }

        agendaCita.setEstado(rs.getString("estado"));
        agendaCita.setDescripcion(rs.getString("descripcion"));
        agendaCita.setDireccion(rs.getString("direccion"));

        int telefono = rs.getInt("telefono");
        if (!rs.wasNull()) {
            agendaCita.setTelefono(telefono);
        }

        return agendaCita;
    }
}