package com.examenfinalcloud.articulos.Services;

import com.examenfinalcloud.articulos.models.User;
import com.examenfinalcloud.articulos.repository.UserRepository;
import com.examenfinalcloud.articulos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {   //implementamos los servicios
    @Autowired
    private UserRepository userRepository;   //importamos el reositorio de usuario
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;  //importamos el passwordEncoder para encriptar la cotraseña

    @Override
    public ResponseEntity<User> getUserById(Long codigo) {              //sobrescribimos el metodo de traer un usuario po codigo
        Optional<User> user= userRepository.findById(codigo);
        if(user.isPresent()){
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> createUser(User user) {    //obrescribimos el metodo de crear un usuario
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity(user, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<User>> allUsers() {   //Sobrescribimos el metodo de traer todos los usuarios
        List<User> users =  userRepository.findAll();
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByName(String name) {  //Sobrescribimos el metodo de traer los usuarios con el mismo nombre
        List<User> users =  userRepository.findAllByName(name);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByLastname(String lastname) { //Sobrescribimos el metodo de traer los usuarios con el mismo apellido
        List<User> users =  userRepository.findAllByLastname(lastname);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> allUsersByNameAndLastname(String name, String lastname) { //Sobrescribimos el metodo de traer los usuarios con el mismo nombre y apellido
        List<User> users =  userRepository.findAllByNameAndLastname(name,lastname);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> editUser(Long id, User user) { //Sobrescribimos el metodo de editar un usuario
        Optional <User> userBD= userRepository.findById(id);
        if(userBD.isPresent()){
            try{
                userBD.get().setName(user.getName());
                userBD.get().setLastname(user.getLastname());
                userBD.get().setDirection(user.getDirection());
                userBD.get().setDocument(user.getDocument());
                userBD.get().setDate(user.getDate());
                userBD.get().setPhone(user.getPhone());
                userBD.get().setEmail(user.getEmail());
                userBD.get().setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(userBD.get());
                return new ResponseEntity(userBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }

        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> deleteUser(Long id) {         //Sobrescribimos el metodo de eliminar un usuario
        Optional<User> userBD= userRepository.findById(id);
        if(userBD.isPresent()){
            userRepository.delete(userBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity login (String email, String password){                            //Metodo para iniciar siccion
        try{
            User usuario = userRepository.findByEmail(email);
            if(passwordEncoder.matches(password, usuario.getPassword())){
                String  token = jwtUtil.create(String.valueOf(usuario.getId()),usuario.getEmail());
                return  ResponseEntity.ok(token);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

}
