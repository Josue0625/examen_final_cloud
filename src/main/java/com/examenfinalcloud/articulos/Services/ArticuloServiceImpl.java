package com.examenfinalcloud.articulos.Services;
//Importando las librerias para el desarrollo de la implementación del servicio del articulo
import com.examenfinalcloud.articulos.models.Articulo;
import com.examenfinalcloud.articulos.repository.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticuloServiceImpl implements ArticuloService{

    private final ArticuloRepository articuloRepository;//Declaramos el atrubito mediante el cual podemos utilizar las funciones del repository y por ende del controlador

    @Override
    public ResponseEntity<Articulo> createArticulo(Articulo articulo){//Función para crear los articulos cuando se consuma el endpoint
        try{
            articuloRepository.save(articulo);//Se guardan los datos registrados por medio del endpoint
            return new ResponseEntity(articulo, HttpStatus.CREATED);//Se crea el articulo
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();//Se retorna un badrequest si no vienen los datos correctamente.
        }
    }

    @Override
    public ResponseEntity<List<Articulo>> listArticulo(){//Función para listar los articulos cuando se consuma el endpoint
        List<Articulo> articulos = articuloRepository.findAll();//Bucando todas los articulos registrados
        if(articulos.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(articulos, HttpStatus.OK);//se retorna el articulo si lo encuentra y el codigo de estado
        }
    }

    @Override
    public ResponseEntity<Articulo> getArticulo(String codigo) {//Función para obtener un articulo por ID
        Optional<Articulo> articulo = articuloRepository.findByCodigo(codigo);
        if (articulo.isPresent()) {
            return new ResponseEntity(articulo, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();//Se retorna un not found cuando no se encuentra el articulo
        }
    }

    @Override
    public ResponseEntity<Articulo> updateArticulo(String codigo, Articulo articulo){//Función para actualizar un articulo por ID
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if(articuloBD.isPresent()){
            try {
                //Se obtienen todos los nuevos datos
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setFecha(articulo.getFecha());
                articuloBD.get().setCompra(articulo.getCompra());
                articuloBD.get().setVenta(articulo.getVenta());
                articuloBD.get().setCategoria(articulo.getCategoria());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloBD.get().setStock(articulo.getStock());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articuloBD,HttpStatus.OK);//Se retornan los nuevos datos y el codigo de estado
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }else{
            return  ResponseEntity.notFound().build();//Se retorna un not found cuando no se encuentra el articulo
        }
    }

    @Override
    public ResponseEntity<Articulo> deleteArticulo(String codigo) {//Función para eliminar un articulo por ID
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);//Se busca el articulo que se desea eliminar
        if (articuloBD.isPresent()) {//Se valida si se encuentra el articulo
            articuloRepository.delete(articuloBD.get());//se elimina el articulo
            return ResponseEntity.noContent().build();//Se retorna un noContent si se logra eliminar el articulo
        } else {
            return ResponseEntity.notFound().build();//Se retorna un not found cuando no se encuentra el articulo
        }
    }
}
