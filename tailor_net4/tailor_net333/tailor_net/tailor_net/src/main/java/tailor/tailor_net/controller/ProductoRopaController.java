package tailor.tailor_net.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import tailor.tailor_net.model.EntityParent;
import tailor.tailor_net.model.ProductoRopa;
import tailor.tailor_net.service.ProductoRopaService;

@Controller
public class ProductoRopaController {

    @Autowired
    private ProductoRopaService productoRopaService;

    @GetMapping("/inventario")
    public String mostrarInventario(Model model) {
        List<ProductoRopa> productos = productoRopaService.obtenerTodos();
        EntityParent.byteImagenToBase64(productos);

        model.addAttribute("productos", productos);
        return "inventario";
    }

    @GetMapping("/producto_eliminar/{id}")
    public String mostrarConfirmacionEliminacion(@PathVariable int id, Model model) {
        ProductoRopa producto = productoRopaService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "producto_eliminar"; // Nombre de la vista Thymeleaf para confirmar la eliminación
    }

    @PostMapping("/producto_eliminar")
    public String eliminarProducto(@RequestParam("idProducto") int idProducto, RedirectAttributes attributes) {
        productoRopaService.eliminarProductoRopaPorId(idProducto);
        attributes.addFlashAttribute("mensaje", "Producto eliminado correctamente.");
        return "redirect:/inventario"; // Asegúrate de que esta ruta sea correcta y lleve al inventario actualizado
    }

    @GetMapping("/producto_editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        ProductoRopa producto = productoRopaService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "producto_editar"; // Nombre del HTML de Thymeleaf
    }

    @PostMapping("/producto_editar")
    public String actualizarProducto(@ModelAttribute("producto") ProductoRopa producto,
                                     BindingResult result,
                                     @RequestParam("imagen") MultipartFile archivo,
                                     Model model) throws IOException {

        if (!archivo.isEmpty()) {
            producto.setImagen(archivo.getBytes());
        }
        producto.setIdTipo(2);
        productoRopaService.insertarProducto(producto);
        return "redirect:/inventario";
    }

    @GetMapping("/producto_agregar")
    public String mostrarFormularioProducto(Model model) {
        ProductoRopa producto = new ProductoRopa();
        model.addAttribute("producto", producto);
        return "producto_agregar";
    }

    @PostMapping("/producto_agregar")
    public String insertarProducto(@ModelAttribute("producto") ProductoRopa producto,
                                   BindingResult result,
                                   @RequestParam("imagen") MultipartFile archivo,
                                   Model model) throws IOException {

        if (!archivo.isEmpty()) {
            producto.setImagen(archivo.getBytes());
        }
        producto.setIdTipo(2);
        productoRopaService.insertarProducto(producto);
        return "redirect:/inventario";
    }
}
