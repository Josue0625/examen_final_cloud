package com.examenfinalcloud.articulos.Services;

import com.examenfinalcloud.articulos.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {                              //Servicios para el usuario
    ResponseEntity<User> getUserById(Long codigo);             //TServisio para traer un usuario especifico por el id
    ResponseEntity<User> createUser(User user);                //Este es el serviciio para crear un usuario
    ResponseEntity<List<User>> allUsers();                     //Servicio para traer todos los usuarios
    ResponseEntity<List<User>> allUsersByName(String name);    //Servicio para traer todos los usuarios con el mismo nombre

    ResponseEntity<List<User>> allUsersByLastname(String lastname); //Servicio para traer todos los usuarios con el mismo apellido
    ResponseEntity<List<User>> allUsersByNameAndLastname(String name, String lastname); //Servicio para traer todos los usuarios con el mismo nombre y apellido
    ResponseEntity<User> editUser(Long id, User user);  //Servicio para editar un usuario
    ResponseEntity<User> deleteUser(Long id);           //Servicio para eliminar un usuario
    ResponseEntity login(String email, String password); //Servicio para iniciar seccion un usuario
}
