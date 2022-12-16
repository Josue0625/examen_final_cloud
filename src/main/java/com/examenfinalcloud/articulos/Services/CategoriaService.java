package com.examenfinalcloud.articulos.Services;
//Importando las librerias para el desarrollo del servicio de categoria
import com.examenfinalcloud.articulos.models.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface CategoriaService {
    //Función intermedia entre la capa de la implementación del servicio y el controlador, mediante la cual se interactuan con estas funciones
    ResponseEntity<Categoria> createCategoria(Categoria categoria);
    ResponseEntity<List<Categoria>> allCategorias();
    ResponseEntity<Categoria> getCategoriaById(Long id);
    ResponseEntity<Categoria> editCategoria(Long id, Categoria categoria);
    ResponseEntity<Categoria> deleteCategoria(Long id);
}
