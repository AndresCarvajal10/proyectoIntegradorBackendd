package com.example.proyectoIntegrador.repository.Impl;

import com.example.proyectoIntegrador.models.InfoMascota;
import com.example.proyectoIntegrador.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Slf4j
@RequiredArgsConstructor
public class PetsRepositoryImpl implements PetsRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * @param petName
     * @return
     */
    @Override
    public Optional<Boolean> validateExistPet(String nombre, int edad, String genero, Integer clienteId) {
        String sql = """
                    SELECT COUNT(*) FROM mascota
                    WHERE nombre = ? AND edad = ? AND genero = ? AND cliente_id = ?
                """;

        try {
            Integer count = jdbcTemplate.queryForObject(
                    sql,
                    Integer.class,
                    nombre.trim(), edad, genero.trim(), clienteId
            );

            return Optional.of(count > 0);
        } catch (Exception e) {
            log.error("Error al validar la mascota: {}", e.getMessage(), e);
            return Optional.empty();
        }

    }

    /**
     * @param clientId
     * @param petName
     * @param petAge
     * @param petGender
     * @param razaId
     * @return
     */
    @Override
    public Optional<Boolean> addPet(Integer clientId, String petCodigo, String petName, Integer petAge, String petGender, Integer razaId) {

        log.debug("Guardando pet");

        // SQL para insertar en agenda_cita
        String sqlAgendaCita = """
                INSERT INTO mascota
                (codigo, nombre, edad, cliente_id, raza_id, genero)
                VALUES( ?, ?, ?, ?, ?, ?);
                """;

        try {

            // Ahora insertamos en agenda_cita usando el cita_id generado
            int agendaRows = jdbcTemplate.update(sqlAgendaCita, petCodigo,
                    petName, petAge, clientId, razaId, petGender);

            return (agendaRows > 0) ? Optional.of(true) : Optional.empty();

        } catch (Exception e) {
            log.error("Error al guardar la mascota: {}", e.getMessage(), e);
            return Optional.empty();
        }

    }

    /**
     * @param idClient
     * @return
     */
    @Override
    public Optional<List<InfoMascota>> getListPetsByUser(int idClient) {
        log.debug("start getInfoMascota");

        try {
            // Construye la consulta SQL
            String sql = """
                    select m.mascota_id, m.codigo, m.nombre, m.edad, m.genero , r.descripcion as raza_mascota
                    from mascota m 
                    left join raza r on m.raza_id = r.raza_id 
                    where cliente_id = ? 
                    """;

            var infoMascota = jdbcTemplate.query(sql, (rs, rowNum) -> {
                InfoMascota data = new InfoMascota();
                data.setIdMascota(rs.getInt("mascota_id"));
                data.setCodigo(rs.getString("codigo"));
                data.setNombreMascota(rs.getString("nombre"));
                data.setEdad(rs.getInt("edad"));
                data.setTipoRaza(rs.getString("raza_mascota"));
                data.setGenero(rs.getString("genero"));
                return data;
            }, idClient);

            return infoMascota.isEmpty() ? Optional.empty() : Optional.of(infoMascota);

        } catch (Exception e) {
            log.error("There was a problem al obtener informacion de la mascota: " + e.getMessage());
            return Optional.empty();
        }
    }
}
