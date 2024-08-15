package tailor.tailor_net.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tailor.tailor_net.model.EntityParent;
import tailor.tailor_net.model.ProductoRopa;
import tailor.tailor_net.repository.ProductoRopaRepository;

@Service
public class ProductoRopaService {

    @Autowired
    private ProductoRopaRepository productoRopaRepository;


    public ProductoRopa insertarProducto(ProductoRopa producto) {
        return ProductoRopaRepository.save(producto);
    }

    public ProductoRopa obtenerProductoPorId(Integer id) {
        return productoRopaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + id));
    }
    @Autowired
    private ProductoRopaRepository ProductoRopaRepository;

    public List<ProductoRopa> obtenerTodos() {
        List<ProductoRopa> productos = ProductoRopaRepository.findAll();
        EntityParent.byteImagenToBase64(productos);
        return productos;
    }


    public void eliminarProductoRopaPorId(Integer id) {
        productoRopaRepository.deleteById(id);
    }
}
