package com.examenfinalcloud.articulos.models;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "articulos")

public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true)
    private String codigo;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @Column(nullable = false)
    private Date fecha;
    @Column(length = 50, nullable = false)
    private int stock;
    @Column(length = 100, nullable = false)
    private double venta;
    @Column(length = 100, nullable = false)
    private int compra;
    @ManyToOne
    private  Categoria categoria;
    @ManyToOne
    private User user;

}