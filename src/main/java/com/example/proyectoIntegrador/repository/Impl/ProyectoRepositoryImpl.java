package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.InfoUser;
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
        log.debug("start registerUser");

        try {

            String sql = "INSERT INTO cliente (nombre, apellido, direccion, telefono, email, fecha_registro, estado_cliente, username) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            return Optional.of(jdbcTemplate.update(sql,
                    registerDTO.getNombre(),
                    registerDTO.getApellido(),
                    registerDTO.getDireccion(),
                    registerDTO.getTelefono(),
                    registerDTO.getEmail(),
                    registerDTO.getFechaRegistro(),
                    true,
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
                    SELECT nombre, apellido, direccion, telefono, email, fecha_registro, estado_cliente, username, rol_id FROM cliente
                    """;
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ClienteRowMapper()));

        } catch (Exception e) {
            log.debug("No se pudo encontrar el usuario: {}", e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Optional<Integer> getIdUser(String userName) {
        log.debug("Obtener informacion del cliente");

        try {
            String sql = """
                    SELECT cliente_id FROM cliente WHERE username = ?
                    """;

            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Integer.class, userName));

        } catch (Exception e) {
            log.debug("No se pudo encontro usuario con ese username: {}", e.getMessage());
            return Optional.empty();
        }

    }


    @Override
    public Optional<InfoUser> validateUser(String username) {

        log.debug("Obtener informacion del cliente");

        try {

            String sql = """
                    SELECT cliente_id, nombre, apellido, direccion, telefono, email, fecha_registro, estado_cliente, username FROM cliente
                    WHERE username = ?
                    """;
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                InfoUser data = new InfoUser();
                data.setIdClient(rs.getString("cliente_id"));
                data.setName(rs.getString("nombre"));
                data.setLastName(rs.getString("apellido"));
                data.setAddres(rs.getString("direccion"));
                data.setPhone(rs.getString("telefono"));
                data.setEmail(rs.getString("email"));
                data.setUsername(rs.getString("username"));
                return data;
            }, username));

        } catch (Exception e) {
            log.debug("No se pudo validar el usuario: {}", e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> validatePassword(String idClient, String password) {
        log.debug("start validatePassword");
        int id = Integer.parseInt(idClient);

        String sql = """
                SELECT EXISTS (
                    SELECT 1
                    FROM user_credentials
                    WHERE usuario_id = ? AND password_hash = ?
                )
                """;

        try {
            // Ejecuta la consulta y devuelve el resultado
            Boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, id, password);

            // Retorna el resultado envuelto en un Optional
            return Optional.of(exists);

        } catch (Exception e) {
            log.error("Error validando la contraseña para el usuario {}: {}", idClient, e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Optional<Integer> savePasswordUser(int idClient, String password) {

        log.debug("start guardar password");
        try {
            String sql = """
                    INSERT INTO user_credentials (usuario_id, password_hash) VALUES (?, ?)
                    """;

            return Optional.of(jdbcTemplate.update(sql,
                    idClient,
                    password
            ));

        } catch (Exception e) {
            log.debug("No se pudo guardar la contraseña: {}", e.getMessage());
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
