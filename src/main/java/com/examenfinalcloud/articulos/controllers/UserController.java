package com.examenfinalcloud.articulos.controllers;

//importamos las clases y librerias que vamos a utilizar
import com.examenfinalcloud.articulos.Services.UserService;
import com.examenfinalcloud.articulos.models.User;
import com.examenfinalcloud.articulos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/user/{id}")                                                 //endPoind para traer un usuario por id
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
       if(jwtUtil.getKey(token)==null){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
       }
        return userService.getUserById(id);
    }

    @PostMapping("/user")                                                             //endpoint para agregar un usuario
    public ResponseEntity CreateUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/listusers")                                                        //endPoint para Listar todos los usuarios
    public ResponseEntity listUsers(@RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return userService.allUsers();
    }

    @GetMapping("/user/name/{name}")                                              //endPoint para listar los usuarios con el mismo nombre
    public ResponseEntity listByName(@PathVariable String name, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return userService.allUsersByName(name);
    }

    @GetMapping("/user/lastname/{lastname}")                                     //endPoint para listar todos los usuarios con el mismo apellido
    public ResponseEntity listByLastname(@PathVariable String lastname, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return userService.allUsersByLastname(lastname);
    }

    @GetMapping("/user/{name}/{lastname}")                                     //endPoint para listar usuarios con el mismo nombre y apellido
    public ResponseEntity listByNameAndLastname(@PathVariable String name, @PathVariable String lastname, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return userService.allUsersByNameAndLastname(name, lastname);
    }

    @PutMapping("/user/{id}")                                               //endPoint para modificar un usuario
    public ResponseEntity editUser(@PathVariable Long id,@Valid @RequestBody  User user, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return userService.editUser(id, user);
    }
    @DeleteMapping("/user/{id}")                                          //endPoint para eliminar un usuario
    public ResponseEntity deleteUser(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if(jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
