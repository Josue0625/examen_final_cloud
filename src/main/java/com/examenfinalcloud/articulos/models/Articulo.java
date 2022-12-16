package com.examenfinalcloud.articulos.models;
//Se importaron las librerias a usar dentro del modelo de Articulo
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articulos")//Se crea el nombre de la tabla para almacenar las articulos en MySql

public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Se crea un dato tipo Long para soportar muchos id, y se le asigna como llave primaria de la tabla categoria
    @Column(length = 50, unique = true)
    private String codigo; // Creamos un dato String para el codigo del articulo
    @Column(length = 100, nullable = false)
    private String nombre; // Creamos un dato String para el nombre del articulo
    @Column(length = 500)
    private String descripcion;// Creamos un dato String para la descripcion del articulo
    @Column(nullable = false)
    private Date fecha;// Creamos un dato date para la fecha de ingreso del articulo
    @Column(length = 50, nullable = false)
    private int stock; // Creamos un dato int para el stock del articulo
    @Column(length = 100, nullable = false)
    private double venta; // Creamos un dato double para el precio de venta del articulo
    @Column(length = 100, nullable = false)
    private int compra;// Creamos un dato double para el precio de compra del articulo
    @ManyToOne
    private  Categoria categoria;// creamos un atributo de tipo categoria, en donde vamos a almacenar el id de la categoria a la que pertenece el articulo
    @ManyToOne
    private User user;// creamos un atributo de tipo user, en donde vamos a almacenar el id del usuario que ingreso el articulo

}