package tailor.tailor_net.controller;

import tailor.tailor_net.model.EntityParent;
import tailor.tailor_net.model.ProductoRopa;

import tailor.tailor_net.model.ProductoAseo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tailor.tailor_net.service.ProductoAseoService;
import tailor.tailor_net.service.ProductoRopaService;

import java.util.List;

@Controller
public class TiendaController {

    @Autowired
    private ProductoRopaService productoRopaService;

    @Autowired
    private ProductoAseoService productoAseoService;

    @GetMapping("/tienda")
    public String verTienda(Model model) {
        List<ProductoRopa> productosRopa = productoRopaService.obtenerTodos();
        EntityParent.byteImagenToBase64(productosRopa);

        List<ProductoAseo> productosAseo = productoAseoService.obtenerTodos();
        EntityParent.byteImagenToBase64(productosAseo);

        model.addAttribute("productosRopa", productosRopa);
        model.addAttribute("productosAseo", productosAseo);
        return "tienda";
    }
    @GetMapping("/index")
    public String verIndex(Model model) {
        List<ProductoRopa> productosRopa = productoRopaService.obtenerTodos();
        EntityParent.byteImagenToBase64(productosRopa);

        List<ProductoAseo> productosAseo = productoAseoService.obtenerTodos();
        EntityParent.byteImagenToBase64(productosAseo);

        model.addAttribute("productosRopa", productosRopa);
        model.addAttribute("productosAseo", productosAseo);
        return "index";
    }
}
