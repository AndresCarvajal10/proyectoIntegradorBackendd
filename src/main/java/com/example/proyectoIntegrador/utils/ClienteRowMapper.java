package com.example.proyectoIntegrador.utils;

import com.example.proyectoIntegrador.models.RegisterClient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<RegisterClient> {

    @Override
    public RegisterClient mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegisterClient cliente = new RegisterClient();
        cliente.setClienteId(rs.getInt("cliente_id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setEmail(rs.getString("email"));
        cliente.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        cliente.setEstadoCliente(rs.getString("estado_cliente"));
        return cliente;
    }

}
