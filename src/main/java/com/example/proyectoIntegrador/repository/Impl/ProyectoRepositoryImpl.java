package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.RegisterClient;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.repository.ProyectoRepository;
import com.example.proyectoIntegrador.utils.ClienteRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
public class ProyectoRepositoryImpl implements ProyectoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProyectoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Integer> registerUser(RegisterDTO registerDTO) {

        try {

            String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, email, fecha_registro, estado_cliente, username) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            return Optional.of(jdbcTemplate.update(sql,
                    registerDTO.getNombre(),
                    registerDTO.getApellido(),
                    registerDTO.getDireccion(),
                    registerDTO.getTelefono(),
                    registerDTO.getEmail(),
                    registerDTO.getFechaRegistro(),
                    registerDTO.getEstadoCliente(),
                    registerDTO.getUsername()
            ));

        } catch (Exception e) {
            log.debug("No se pudo registar el usuario: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<RegisterClient> getInfoUser(RegisterDTO registerDTO) {

        log.debug("Obtener informacion del cliente");

        try {

            String sql = """
                    SELECT * FROM clientes
                    """;
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ClienteRowMapper()));

        } catch (Exception e) {
            log.debug("No se pudo registar el usuario: {}", e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Optional<Integer> getIdUser(String userName) {
        log.debug("Obtener informacion del cliente");

        try {
            String sql = """
                    SELECT cliente_id FROM clientes WHERE username = ?
                    """;

            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Integer.class, userName));

        } catch (Exception e) {
            log.debug("No se pudo registar el usuario: {}", e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<Integer> setSessiones(int codeSession, int userId) {

        log.debug("start guardar session");
        try {
            String sql = """
                    INSERT INTO sesiones (usuario_id, token_sesion, fecha_inicio, estado) VALUES (?, ?, CURRENT_TIMESTAMP, ?)
                    """;

            return Optional.of(jdbcTemplate.update(sql,
                    userId,
                    codeSession,
                    "ACTIVA"
            ));

        } catch (Exception e) {
            log.debug("No se pudo guardar session: {}", e.getMessage());
            return Optional.empty();
        }

    }
}
