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
    public ResponseEntity<Categoria> createCategoria(Categoria categoria){//Función para crear las categorias cuando se consuma el endpoint
        try{
            categoriaRepository.save(categoria);//Se guardan los datos registrados por medio del endpoint
            return new ResponseEntity(categoria, HttpStatus.CREATED);//Se crea la categoria
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return  ResponseEntity.badRequest().build();//Se retorna un badrequest si no vienen los datos correctamente.
        }
    }

    @Override
    public ResponseEntity<List<Categoria>> allCategorias(){//Función para listar todas las categorias
        List<Categoria> categorias = categoriaRepository.findAll();//Bucando todas las categorias registradas
        if(categorias.isEmpty()){//validación para saber cuando no se encuentra una categoria
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(categorias,HttpStatus.OK);//se retorna la categoria si la encuentra y el codigo de estado
        }
    }
    @Override
    public ResponseEntity<Categoria> getCategoriaById(Long id){//Función para obtener una categoria por ID
        Optional<Categoria> categoria = categoriaRepository.findById(id);//Se busca una categoria por ID
        if (categoria.isPresent()) {//Se valida si se encuentra la catgoria registrada
            return new ResponseEntity(categoria, HttpStatus.OK);//Si se encuentra se retorna la categoria y el codigo de estado
        } else {
            return ResponseEntity.notFound().build();//Se retorna un not found cuando no se encuentra la categoria
        }
    }
    @Override
    public ResponseEntity<Categoria> editCategoria(Long id, Categoria categoria){//Función para actualizar una categoria por ID
        Optional<Categoria> categoriaBD = categoriaRepository.findById(id);//Se busca la categoria en específico a actualizar
        if(categoriaBD.isPresent()){//se valida si se ha encontrado la categoria
            try {
                //Se obtienen todos los nuevos datos
                categoriaBD.get().setNombre(categoria.getNombre());
                categoriaBD.get().setDescripcion(categoria.getDescripcion());
                categoriaRepository.save(categoriaBD.get());//Se guardan los nuevos datos
                return new ResponseEntity(categoriaBD,HttpStatus.OK);//Se retornan los nuevos datos y el codigo actualizado
            }catch (Exception e){
                return ResponseEntity.badRequest().build();//Se retorna un badrequest si la solicitud o los datos no son correctos.
            }
        }else{
            return  ResponseEntity.notFound().build();//Se retorna un not found cuando no se encuentra la categoria
        }
    }
    @Override
    public ResponseEntity<Categoria> deleteCategoria(Long id){//Función para eliminar una categoria por ID
        Optional<Categoria> categoriaBD = categoriaRepository.findById(id);//Se busca la categoria que se desea eliminar
        if (categoriaBD.isPresent()) {//Se valida si se encuentra la categoria
            categoriaRepository.delete(categoriaBD.get());//se elimina la categoria
            return ResponseEntity.noContent().build();//Se retorna un noContent si se logra eliminar la categoria
        } else {
            return ResponseEntity.notFound().build();//Se retorna un not found cuando no se encuentra la categoria
        }
    }
}
