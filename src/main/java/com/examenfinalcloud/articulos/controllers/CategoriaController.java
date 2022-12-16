package com.examenfinalcloud.articulos.controllers;

//Importando las librerias para el desarrollo del controlador de categoria
import com.examenfinalcloud.articulos.Services.CategoriaService;
import com.examenfinalcloud.articulos.models.Categoria;
import com.examenfinalcloud.articulos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//Se declara como restController para que sea reconocido como un controlador
@RestController
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private JWTUtil jwtUtil; //Con este atributo se protegen todos los endpoints, para que solo un usuario autenticado por JWT pueda acceder a los mismos
    @PostMapping(value = "/categoria")//Por medio de esta ruta o endpoint, se puede ingresar la información correspondiente a la categoria
    public ResponseEntity createCategoria(@Valid @RequestBody Categoria categoria){ //Función para crear las categorias cuando se consuma el endpoint
        return categoriaService.createCategoria(categoria);//Se retornan los datos asociados a la categoria
    }
    @GetMapping(value = "categorias")
    public ResponseEntity listCategorias(@RequestHeader(value = "Authorization") String token){//Función para listar todas las categorias
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoriaService.allCategorias();//se retorna lo que venga de la función llamada en categoriaServiceImpl
        }
    }
    @GetMapping(value = "categoria/{id}")
    public ResponseEntity getCategoria(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {//Función para obtener una categoria por ID
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoriaService.getCategoriaById(id);//se retorna lo que venga de la función llamada en categoriaServiceImpl
        }
    }
    @PutMapping(value = "/updateCategoria/{id}")
    public ResponseEntity updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria, @RequestHeader(value = "Authorization") String token){//Función para actualizar una categoria por ID
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoriaService.editCategoria(id, categoria);//se retorna lo que venga de la función llamada en categoriaServiceImpl
        }
    }
    //La categoria se puede eliminar siempre y cuando esa categoria no este relacionado con ningun articulo
    @DeleteMapping(value = "/deleteCategoria/{id}")
    public ResponseEntity deleteCategoria(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {//Función para eliminar una categoria por ID
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }else{
            return categoriaService.deleteCategoria(id);//se retorna lo que venga de la función llamada en categoriaServiceImpl
        }
    }
}