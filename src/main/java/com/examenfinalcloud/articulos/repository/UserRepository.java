package com.examenfinalcloud.articulos.repository;


import com.examenfinalcloud.articulos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);    //Trae los usuarios con el mismo nombre
    List<User> findAllByLastname(String lastname); //Trae los usuarios con el mismo apellido
    List<User> findAllByNameAndLastname(String name, String lastname);  //Trae los usuarios con el mismo apellido y nombre
    User findByEmail(String email);     //Trae un usuario por el correo

}
