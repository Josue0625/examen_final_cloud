package com.examenfinalcloud.articulos;

import com.examenfinalcloud.articulos.models.User;
import com.examenfinalcloud.articulos.repository.UserRepository;
import com.examenfinalcloud.articulos.Services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class UserServiceMockTest {
    public static User mockUser() {
        User modelo = new User();
        modelo.setId(1L);
        modelo.setName("Josue");
        modelo.setLastname("Campo");
        modelo.setDocument("1002334333");
        modelo.setDirection("Ocaña");
        modelo.setDate(new Date(10,10,20));
        modelo.setPhone("3143454323");
        modelo.setEmail("josue@gmail.com");
        modelo.setPassword("ashwusudsu");
        return modelo;
    }
    public static User mockUserMod() {
        User modelo = new User();
        modelo.setName("yeison");
        modelo.setLastname("ascanio");
        modelo.setDocument("1002894333");
        modelo.setDirection("Ocaña");
        modelo.setDate(new Date(10,10,20));
        modelo.setPhone("3123454323");
        modelo.setEmail("yeison@gmail.com");
        modelo.setPassword("aureisfidsu");
        return modelo;
    }
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @DisplayName("Test para crear Usuario")
    @Test
    void createUserTest() {
        //Given
        User user = mockUser();
        given(userRepository.findAllByName(user.getName())).willReturn(List.of(user));
        given(userRepository.findAllByLastname(user.getLastname())).willReturn(List.of(user));
        given(userRepository.findAllByNameAndLastname(user.getName(), user.getLastname())).willReturn(List.of(user));
        given(userRepository.findByEmail(user.getEmail())).willReturn(user);
        given(userRepository.save(user)).willReturn(user);
        //When

        ResponseEntity<User> articuloGuardado = userService.createUser(user);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para listar a los Usuarios")
    @Test
    void getAllUsersTest() {

        //Given
        User user = mockUser();

        //When

        ResponseEntity<List<User>> lista = userService.allUsers();

        //Then
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para obtener usuarios por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        User user = mockUser();

        //When
        given(userRepository.findAllByName(user.getName())).willReturn(List.of(user));
        given(userRepository.findAllByLastname(user.getLastname())).willReturn(List.of(user));
        given(userRepository.findAllByNameAndLastname(user.getName(), user.getLastname())).willReturn(List.of(user));
        given(userRepository.findByEmail(user.getEmail())).willReturn(user);
        ResponseEntity<User> respuesta = userService.getUserById(user.getId());

        //Then
        Assertions.assertNotNull(respuesta);

    }




}
