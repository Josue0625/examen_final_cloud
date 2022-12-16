package com.examenfinalcloud.articulos.repository;
//Se importaron las librerias a usar dentro del repositorio del articulo
import com.examenfinalcloud.articulos.models.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
    Optional<Articulo> findByCodigo(String codigo);//Se crea un metodo para buscar el articulo por codigo en la BD
}
