package com.examenfinalcloud.articulos.models;

//Se importaron las librerias a usar dentro del modelo de categoria
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categorias") //Se crea el nombre de la tabla para almacenar las categorias en MySql
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Se crea un dato tipo Long para soportar muchos id, y se le asigna como llave primaria de la tabla categoria
    @Column(length = 200,nullable = false)//Se le dan propiedades al dato nombre con esta notación, para que la longitud máxima sea de 200 caracteres, y que el dato no puede ser null
    private String nombre; //Atributo para almacenar el nombrede la categoria
    @Column(length = 300)//Se le da la propiedad de que la longitud de la descripción sea de máximo 300 caracteres
    private String descripcion;//Se crea el atributo para almacenar la descripción de una categoría.
}