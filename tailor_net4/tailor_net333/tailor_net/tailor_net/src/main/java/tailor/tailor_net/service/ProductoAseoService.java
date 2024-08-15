package tailor.tailor_net.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tailor.tailor_net.model.EntityParent;
import tailor.tailor_net.model.ProductoAseo;
import tailor.tailor_net.model.ProductoRopa;
import tailor.tailor_net.repository.ProductoAseoRepository;

import java.util.List;

@Service
public class ProductoAseoService {

    @Autowired
    private ProductoAseoRepository productoAseoRepository;

    public ProductoAseo insertarProductoAseo(ProductoAseo productoAseo) {
        return productoAseoRepository.save(productoAseo);
    }

    public List<ProductoAseo> listarProductosAseo() {
        return productoAseoRepository.findAll();
    }

    public ProductoAseo obtenerProductoAseoPorId(Integer id) {
        return productoAseoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public void eliminarProductoAseoPorId(Integer id) {
        productoAseoRepository.deleteById(id);
    }


        @Autowired
        private ProductoAseoRepository ProductoAseoRepository;

        public List<ProductoAseo> obtenerTodos() {

                    List<ProductoAseo> productos = ProductoAseoRepository.findAll();
        EntityParent.byteImagenToBase64(productos);
        return productos;
        }


}
