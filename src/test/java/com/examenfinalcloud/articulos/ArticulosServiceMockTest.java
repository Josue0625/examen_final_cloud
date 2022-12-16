package com.examenfinalcloud.articulos;

import com.examenfinalcloud.articulos.Services.ArticuloServiceImpl;
import com.examenfinalcloud.articulos.models.Articulo;
import com.examenfinalcloud.articulos.repository.ArticuloRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.examenfinalcloud.articulos.CategoriaServiceMockTest.mockCategoria;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ArticulosServiceMockTest {
    public static Articulo mockArticulo() {
        Articulo modelo = new Articulo();
        modelo.setId(1L);
        modelo.setCodigo("123");
        modelo.setNombre("Limpido");
        modelo.setStock(2);
        modelo.setCategoria(mockCategoria());
        modelo.setDescripcion("HCL");
        modelo.setVenta(2000);
        modelo.setCompra(5000);
        modelo.setFecha(new Date(10,10,20));

        return modelo;
    }
    public static Articulo mockArticuloMod() {
        Articulo modelo = new Articulo();
        modelo.setId(1L);
        modelo.setCodigo("1");
        modelo.setNombre("Lejia");
        modelo.setStock(2);
        modelo.setCategoria(mockCategoria());
        modelo.setDescripcion("HCL");
        modelo.setVenta(2000);
        modelo.setCompra(5000);
        modelo.setFecha(new Date(10,10,20));

        return modelo;
    }
    @InjectMocks
    private ArticuloServiceImpl articuloService;

    @Mock
    private ArticuloRepository articuloRepository;

    @DisplayName("Test para obtener articulos por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        Articulo articulo = mockArticulo();

        //When
        when(articuloRepository.findByCodigo(anyString())).thenReturn(Optional.of(articulo));
        ResponseEntity<Articulo> respuesta = articuloService.getArticulo(articulo.getCodigo());

        //Then
        Assertions.assertNotNull(respuesta);

    }

    @DisplayName("Test para listar a los Articulos")
    @Test
    void getAllArticlesTest() {

        //Given
        Articulo articulo = mockArticulo();

        //When

        ResponseEntity<List<Articulo>> lista = articuloService.listArticulo();

        //Then
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para crear Articulo")
    @Test
    void createArticleTest() {
        //Given
        Articulo articulo = mockArticulo();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articulo)).willReturn(articulo);
        //When

        ResponseEntity<Articulo> articuloGuardado = articuloService.createArticulo(articulo);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para editar un Articulo")
    @Test
    void editArticleTest() {
        // Given
        Articulo articulo = mockArticulo();
        Articulo articuloMod = mockArticuloMod();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articuloMod)).willReturn(articuloMod);

        //when

        ResponseEntity<Articulo> articuloGuardado = articuloService.updateArticulo(articulo.getCodigo(), articuloMod);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para eliminar un Articulo")
    @Test
    void deleteArticleTest() {
        //Given
        Articulo articulo = mockArticulo();given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        articuloRepository.deleteById(articulo.getId());



        //when

        Optional<Articulo> elmArticulo = articuloRepository.findById(articulo.getId());

        //Then

        assertThat(elmArticulo).isEmpty();


    }

    @Test
    @DisplayName("Test para una lista vacia")
    void listaArticulosVacia() {
        when(articuloRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity mockArticleService = articuloService.listArticulo();

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }
}