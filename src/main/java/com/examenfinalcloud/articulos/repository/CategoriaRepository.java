package com.examenfinalcloud.articulos.repository;
//Se importaron las librerias a usar dentro del modelo de categoria
import com.examenfinalcloud.articulos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findById(Long id);//Se crea un metodo para buscar la categoria por ID
}
