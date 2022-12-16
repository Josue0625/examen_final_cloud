package com.examenfinalcloud.articulos;

import com.examenfinalcloud.articulos.models.Categoria;
import com.examenfinalcloud.articulos.repository.CategoriaRepository;
import com.examenfinalcloud.articulos.Services.CategoriaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceMockTest {
    public static Categoria mockCategoria() {
        Categoria modelo = new Categoria();
        modelo.setId(1L);
        modelo.setNombre("Ropa");
        modelo.setDescripcion("Productos de tela");

        return modelo;
    }
    public static Categoria mockCategoriaMod() {
        Categoria modelo = new Categoria();
        modelo.setId(1L);
        modelo.setNombre("Ropa");
        modelo.setDescripcion("Productos de algodon");

        return modelo;
    }

    @InjectMocks
    private CategoriaServiceImpl categoriaService;
    @Mock
    private CategoriaRepository categoriaRepository;


    @DisplayName("Test para listar a las categorias")
    @Test
    void gatAllCategoriaTest() {
        //Given
        Categoria categoria = mockCategoria();
        //When
        ResponseEntity<List<Categoria>> lista = categoriaService.allCategorias();
        //Then
        Assertions.assertNotNull(lista);
    }
    @DisplayName("Test para crear Categoria")
    @Test
    void createArticleTest() {
        //Given
        Categoria categoria = mockCategoria();
        given(categoriaRepository.findById(categoria.getId())).willReturn(Optional.of(categoria));
        given(categoriaRepository.save(categoria)).willReturn(categoria);
        //When

        ResponseEntity<Categoria> categoriaGuardado = categoriaService.createCategoria(categoria);

        //Then
        Assertions.assertNotNull(categoriaGuardado);
    }
    @DisplayName("Test para editar una Categoria")
    @Test
    void editCategoriaTest() {
        // Given
        Categoria categoria = mockCategoria();
        Categoria categoriaMod = mockCategoriaMod();
        given(categoriaRepository.findById(categoria.getId())).willReturn(Optional.of(categoria));
        given(categoriaRepository.save(categoriaMod)).willReturn(categoriaMod);

        //when

        ResponseEntity<Categoria> categoriaGuardado = categoriaService.editCategoria(categoria.getId(), categoriaMod);

        //Then
        Assertions.assertNotNull(categoriaGuardado);
    }
}
