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
    private String name;                                      // Creamos un dato String para el nombre del usuario
    @Column(length = 300, nullable = false)
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String lastname;                                  // Creamos un dato String para el apellido del usuario
    @Column(length = 20, nullable = false, unique = true)
    @NotBlank(message = "El documento no puede estar en blanco")
    private String document;                                  // Creamos un dato String para el documento del usuario
    @Column(length = 100)
    private String direction;                                 // Creamos un dato String para el direccion del usuario
    private Date date;                                        // Creamos un dato tipo date para la fecha de nacimiento
    @Column(length = 20)
    private String phone;                                    // Creamos un dato String para el numero telefonico del usuario
    @Column(nullable = false,unique = true, length = 100)
    @NotBlank(message = "El correo no puede estar en blanco")
    private String email;                                    // Creamos un dato String para el correo del usuario
    @Column(nullable = false, length = 64)
    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;                                 // Creamos un dato String para la contraseña del usuario, va tener una longitud de 64 bits ya que va estar encriptada
}
