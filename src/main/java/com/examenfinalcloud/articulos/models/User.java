package com.examenfinalcloud.articulos.models;

//importamos las librerias que vamos utilizar en el proyecto
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users") //Le damos el nombre de la tabla que va realizar en la base de datos MySQL la que va almacenar los usuarios
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                          // Creamos un dato long y le indicamos que va ser la llave primaria del usuario
    @Column(length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;
    @Column(length = 300, nullable = false)
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String lastname;
    @Column(length = 20, nullable = false, unique = true)
    @NotBlank(message = "El documento no puede estar en blanco")
    private String document;
    @Column(length = 100)
    private String direction;
    private Date date;
    @Column(length = 20)
    private String phone;
    @Column(nullable = false,unique = true, length = 100)
    @NotBlank(message = "El correo no puede estar en blanco")
    private String email;
    @Column(nullable = false, length = 64)
    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;
}
