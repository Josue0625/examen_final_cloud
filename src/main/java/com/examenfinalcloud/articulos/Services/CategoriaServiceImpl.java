package com.examenfinalcloud.articulos.Services;
//Importando las librerias para el desarrollo de la implementación del servicio de categoria
import com.examenfinalcloud.articulos.models.Categoria;
import com.examenfinalcloud.articulos.repository.CategoriaRepository;
import com.examenfinalcloud.articulos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository; //Declaramos el atrubito mediante el cual podemos utilizar las funciones del repository y por ende del controlador
    @Override
    public ResponseEntity<Categoria> createCategoria(Categoria categoria){//
        try{
            categoriaRepository.save(categoria);
            return new ResponseEntity(categoria, HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return  ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Categoria>> allCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        if(categorias.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(categorias,HttpStatus.OK);
        }
    }
    @Override
    public ResponseEntity<Categoria> getCategoriaById(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return new ResponseEntity(categoria, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Categoria> editCategoria(Long id, Categoria categoria){
        Optional<Categoria> categoriaBD = categoriaRepository.findById(id);
        if(categoriaBD.isPresent()){
            try {
                categoriaBD.get().setNombre(categoria.getNombre());
                categoriaBD.get().setDescripcion(categoria.getDescripcion());
                categoriaRepository.save(categoriaBD.get());
                return new ResponseEntity(categoriaBD,HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @Override
    public ResponseEntity<Categoria> deleteCategoria(Long id){
        Optional<Categoria> categoriaBD = categoriaRepository.findById(id);
        if (categoriaBD.isPresent()) {
            categoriaRepository.delete(categoriaBD.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
