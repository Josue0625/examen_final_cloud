package com.examenfinalcloud.articulos.Services;
//Importando las librerias para el desarrollo del servicio del articulo
import com.examenfinalcloud.articulos.models.Articulo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticuloService {
    //Función intermedia entre la capa de la implementación del servicio y el controlador, mediante la cual se interactuan con estas funciones
    ResponseEntity<Articulo> createArticulo(Articulo articulo);
    ResponseEntity<List<Articulo>>listArticulo();
    ResponseEntity<Articulo> getArticulo(String codigo);
    ResponseEntity<Articulo> updateArticulo(String codigo, Articulo articulo);
    ResponseEntity<Articulo> deleteArticulo(String codigo);

}
