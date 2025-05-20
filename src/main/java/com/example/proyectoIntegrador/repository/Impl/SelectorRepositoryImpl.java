package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.selectors.SelectorEstado;
import com.example.proyectoIntegrador.models.selectors.SelectorRaza;
import com.example.proyectoIntegrador.models.selectors.SelectorRols;
import com.example.proyectoIntegrador.models.selectors.SelectorVeterinarian;
import com.example.proyectoIntegrador.repository.SelectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Slf4j
@RequiredArgsConstructor
public class SelectorRepositoryImpl implements SelectorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<SelectorEstado>> getSelectorEstado() {
        log.debug("Obtener getSelectorEstado");

        try {
            // Construye la consulta SQL
            String sql = """
                    SELECT estado_id, descripcion
                    FROM public.estado_cita 
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                SelectorEstado data = new SelectorEstado();
                data.setIdEstado(rs.getInt("estado_id"));
                data.setDescripcion(rs.getString("descripcion"));
                return data;
            });

            if (!empresas.isEmpty()) {
                return Optional.of(empresas);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            log.error("There was a problem listar estados: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<SelectorVeterinarian>> getSelectorVeterinarian() {
        log.debug("Obtener getSelectorVeterinarian");

        try {
            // Construye la consulta SQL
            String sql = """
                    SELECT m.medico_id, p.nombre, p.apellido, r.descripcion as rolDescription 
                    FROM medico m
                    left join personal p on p.personal_id = m.identificacion_medico 
                    left join rol r on r.rol_id = p.rol_id 
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                SelectorVeterinarian data = new SelectorVeterinarian();
                data.setIdVeterinarian(rs.getInt("medico_id"));
                data.setNombre(rs.getString("nombre") + " " + rs.getString("apellido"));
                return data;
            });

            if (!empresas.isEmpty()) {
                return Optional.of(empresas);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            log.error("There was a problem listar veterinarios: " + e.getMessage());
            return Optional.empty();
        }
    }


    @Override
    public Optional<List<SelectorRaza>> getSelectorRaza() {

        log.debug("Obtener getSelectorRaza");

        try {
            // Construye la consulta SQL
            String sql = """
                    SELECT raza_id, descripcion
                    FROM raza
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                SelectorRaza data = new SelectorRaza();
                data.setIdRaza(rs.getInt("raza_id"));
                data.setTipoRaza(rs.getString("descripcion"));
                return data;
            });

            if (!empresas.isEmpty()) {
                return Optional.of(empresas);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            log.error("There was a problem listar razas: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<SelectorRols>> getSelectorRols() {

        log.debug("Obtener getSelectorRols");

        try {
            // Construye la consulta SQL
            String sql = """
                    SELECT rol_id, descripcion
                    FROM rol
                    """;

            var empresas = jdbcTemplate.query(sql, (rs, rowNum) -> {
                SelectorRols data = new SelectorRols();
                data.setIdRole(rs.getInt("rol_id"));
                data.setDescripcionRole(rs.getString("descripcion"));
                return data;
            });

            if (!empresas.isEmpty()) {
                return Optional.of(empresas);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {
            log.error("There was a problem listar roles de usuario: " + e.getMessage());
            return Optional.empty();
        }
    }
}
