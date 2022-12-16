package com.examenfinalcloud.articulos.controllers;
//Importando las librerias para el desarrollo del controlador de articulos
import com.examenfinalcloud.articulos.Services.ArticuloService;
import com.examenfinalcloud.articulos.models.Articulo;
import com.examenfinalcloud.articulos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//Se declara como restController para que sea reconocido como un controlador
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private JWTUtil jwtUtil;//Con este atributo se protegen todos los endpoints, para que solo un usuario autenticado por JWT pueda acceder a los mismos
    @PostMapping(value = "/articulo")//Por medio de esta ruta o endpoint, se puede ingresar la información correspondiente al articulo
    public ResponseEntity createArticulo(@Valid @RequestBody Articulo articulo){//Función para crear los articulos cuando se consuma el endpoint
            return articuloService.createArticulo(articulo);//se retorna lo que venga de la función llamada en articuloServiceImpl
    }
    @GetMapping(value = "/articulos")
    public ResponseEntity listArticulo(@RequestHeader(value = "Authorization") String token){//Función para listar todos los articulos
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
            return articuloService.listArticulo();//se retorna lo que venga de la función llamada en articuloServiceImpl
    }
   @GetMapping(value = "articulo/{codigo}")
    public ResponseEntity getArticulo(@PathVariable String codigo, @RequestHeader(value = "Authorization") String token) {//Función para obtener un artículo por ID
       if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
       }
        return articuloService.getArticulo(codigo);//se retorna lo que venga de la función llamada en articuloServiceImpl
    }

    @PutMapping(value = "/updateArticulo/{codigo}")
    public ResponseEntity updateArticulo(@PathVariable String codigo,@Valid @RequestBody Articulo articulo, @RequestHeader(value = "Authorization") String token){//Función para actualizar un articulo por ID
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return articuloService.updateArticulo(codigo,articulo);//se retorna lo que venga de la función llamada en articuloServiceImpl
    }
    @DeleteMapping(value = "/delete/{codigo}")
    public ResponseEntity deleteArticulo(@PathVariable String codigo, @RequestHeader(value = "Authorization") String token) {//Función para eliminar un articulo por ID
        if(jwtUtil.getKey(token)==null){//se valida que el usuario esté autenticado por medio de token
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("¡Token no valido!");
        }
        return articuloService.deleteArticulo(codigo);//se retorna lo que venga de la función llamada en articuloServiceImpl
    }

}
