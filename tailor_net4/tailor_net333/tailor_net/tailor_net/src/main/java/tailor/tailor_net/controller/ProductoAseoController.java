package tailor.tailor_net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tailor.tailor_net.model.EntityParent;
import tailor.tailor_net.model.ProductoAseo;
import tailor.tailor_net.service.ProductoAseoService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class ProductoAseoController {

    @Autowired
    private ProductoAseoService productoAseoService;

    @GetMapping("/inventario_aseo")
    public String mostrarInventarioAseo(Model model) {
        List<ProductoAseo> productos = productoAseoService.listarProductosAseo();
        EntityParent.byteImagenToBase64(productos);

        model.addAttribute("productosAseo", productos);
        return "inventario_aseo";
    }

    @GetMapping("/producto_aseo_agregar")
    public String mostrarFormularioProductoAseo(Model model) {
        model.addAttribute("productoAseo", new ProductoAseo());
        return "producto_aseo_agregar";
    }

    @PostMapping("/producto_aseo_agregar")
    public String insertarProductoAseo(@ModelAttribute("productoAseo") ProductoAseo productoAseo,
                                       BindingResult result,
                                       @RequestParam("imagen") MultipartFile archivo,
                                       Model model) throws IOException {
        if (!archivo.isEmpty()) {
            productoAseo.setImagen(archivo.getBytes());
        }
        productoAseo.setidTipo(3);
        productoAseoService.insertarProductoAseo(productoAseo);
        return "redirect:/inventario_aseo";
    }

    @GetMapping("/producto_aseo_editar/{id}")
    public String mostrarFormularioEdicionAseo(@PathVariable Integer id, Model model) {
        ProductoAseo productoAseo = productoAseoService.obtenerProductoAseoPorId(id);
        model.addAttribute("productoAseo", productoAseo);
        return "producto_aseo_editar";
    }

    @PostMapping("/producto_aseo_editar")
    public String actualizarProductoAseo(@ModelAttribute("productoAseo") ProductoAseo productoAseo,
                                         BindingResult result,
                                         @RequestParam("imagen") MultipartFile archivo,
                                         Model model) throws IOException {
        if (!archivo.isEmpty()) {
            productoAseo.setImagen(archivo.getBytes());
        }
        productoAseo.setidTipo(3);
        productoAseoService.insertarProductoAseo(productoAseo);
        return "redirect:/inventario_aseo";
    }

    @GetMapping("/producto_aseo_eliminar/{id}")
    public String mostrarConfirmacionEliminacionAseo(@PathVariable int id, Model model) {
        ProductoAseo productoAseo = productoAseoService.obtenerProductoAseoPorId(id);
        model.addAttribute("productoAseo", productoAseo);
        return "producto_aseo_eliminar";
    }

    @PostMapping("/producto_aseo_eliminar")
    public String eliminarProductoAseo(@RequestParam("idProducto") int idProducto, RedirectAttributes attributes) {
        productoAseoService.eliminarProductoAseoPorId(idProducto);
        attributes.addFlashAttribute("mensaje", "Producto eliminado correctamente.");
        return "redirect:/inventario_aseo";
    }
}

