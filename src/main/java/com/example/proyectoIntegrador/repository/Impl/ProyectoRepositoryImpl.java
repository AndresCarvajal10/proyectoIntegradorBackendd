package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.RegisterClient;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.utils.ClienteRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.proyectoIntegrador.repository.ProyectoRepository;

import java.util.Optional;

@Repository
@Slf4j
public class ProyectoRepositoryImpl implements ProyectoRepository{

    private final JdbcTemplate jdbcTemplate;

    public ProyectoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Integer> registerUser(RegisterDTO registerDTO) {
        String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, email, fecha_registro, estado_cliente) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return Optional.of(jdbcTemplate.update(sql,
                registerDTO.getNombre(),
                registerDTO.getApellido(),
                registerDTO.getDireccion(),
                registerDTO.getTelefono(),
                registerDTO.getEmail(),
                registerDTO.getFechaRegistro(),
                registerDTO.getEstadoCliente()
        ));
    }

    @Override
    public Optional<RegisterClient> getInfoUser(RegisterDTO registerDTO) {

        log.debug("Obtener informacion del cliente");

        String sql = """
                SELECT * FROM clientes
                """;
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ClienteRowMapper()));
    }
}
