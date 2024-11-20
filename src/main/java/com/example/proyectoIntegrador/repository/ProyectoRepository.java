package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.models.InfoUser;
import com.example.proyectoIntegrador.models.RegisterClient;
import com.example.proyectoIntegrador.models.RegisterDTO;

import java.util.Optional;

public interface ProyectoRepository {

    /**
     * Registra un nuevo usuario en la base de datos utilizando el objeto RegisterDTO proporcionado.
     *
     * @param registerDTO el objeto de transferencia de datos que contiene los detalles de registro del usuario
     * @return un Optional que contiene el número de filas afectadas si la operación es exitosa,
     * o un Optional vacío si ocurre una excepción
     */
    Optional<Integer> registerUser(RegisterDTO registerDTO);

    /**
     * Obtiene la información de un cliente basado en los datos proporcionados en el objeto RegisterDTO.
     *
     * @param registerDTO Objeto que contiene los datos del cliente a buscar.
     * @return Un Optional que contiene un objeto RegisterClient si se encuentra el cliente, o un Optional vacío si no se encuentra.
     */
    Optional<RegisterClient> getInfoUser(RegisterDTO registerDTO);

    /**
     * Inserta un nuevo registro de sesión en la tabla 'sesiones' con el código de sesión y el ID de usuario proporcionados.
     * La sesión se marca como activa y se utiliza la marca de tiempo actual como fecha de inicio.
     *
     * @param codeSession el token de sesión que se almacenará.
     * @param userId      el ID del usuario asociado con la sesión.
     * @return un Optional que contiene el número de filas afectadas por la operación de inserción,
     * o un Optional vacío si ocurre un error.
     */
    Optional<Integer> setSessiones(int codeSession, int userId);

    /**
     * Recupera el ID de un usuario basado en su nombre de usuario.
     *
     * @param userName el nombre de usuario del cual se desea obtener el ID
     * @return un Optional que contiene el ID del usuario si se encuentra, o un Optional vacío si no se encuentra
     */
    Optional<Integer> getIdUser(String userName);

    /**
     * Valida y recupera la información del usuario basado en el nombre de usuario proporcionado.
     *
     * @param username el nombre de usuario del cual se desea validar y obtener información
     * @return un Optional que contiene el objeto InfoUser con los detalles del usuario si se encuentra,
     * o un Optional vacío si el usuario no existe o si ocurre un error
     */
    Optional<InfoUser> validateUser(String username);

    /**
     * Valida la contraseña para un ID de cliente dado verificando si la contraseña existe en la base de datos.
     *
     * @param idClient el ID del cliente cuya contraseña se va a validar
     * @param password la contraseña a validar
     * @return un Optional que contiene Boolean.TRUE si la contraseña es válida, Boolean.FALSE si es inválida,
     * o un Optional vacío si ocurre un error durante la validación
     */
    Optional<Boolean> validatePassword(String idClient, String password);

    /**
     * Guarda la contraseña de un usuario en la base de datos.
     *
     * @param idClient el ID del cliente cuya contraseña se va a guardar
     * @param password la contraseña que se va a guardar
     * @return un Optional que contiene el número de filas afectadas si la operación es exitosa,
     * o un Optional vacío si ocurre un error
     */
    public Optional<Integer> savePasswordUser(int idClient, String password);

}
