package tailor.tailor_net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tailor.tailor_net.domain.Clasificacion;
import tailor.tailor_net.model.EntityParent;
import tailor.tailor_net.model.ProductoAseo;
import tailor.tailor_net.model.ProductoRopa;
import tailor.tailor_net.service.KartService;
import tailor.tailor_net.service.ProductoAseoService;
import tailor.tailor_net.service.ProductoRopaService;
import java.io.IOException;
import tailor.tailor_net.service.PdfService;

@Controller
@RequestMapping("/kart")
public class KartController {

    @Autowired
    private KartService kartService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private ProductoRopaService productoRopaService;

    @Autowired
    private ProductoAseoService productoAseoService;

    @GetMapping
    public String verKart(Model model) {
        model.addAttribute("items", kartService.getItems());
        model.addAttribute("total", kartService.getTotal());
        return "kart";
    }

@PostMapping("/agregar")
    public String agregarAlKart(
            @RequestParam Integer idProducto,
            @RequestParam Integer idTipo,
            @RequestParam Integer cantidad,
            HttpServletRequest request) {
        System.out.println("agregando productos " + idProducto + "   " + idTipo + "   " + cantidad);

        EntityParent producto = switch (Clasificacion.getById(idTipo)) {
            case Clasificacion.ROPA -> productoRopaService.obtenerProductoPorId(idProducto);
            default -> productoAseoService.obtenerProductoAseoPorId(idProducto);
        };

        if (producto != null) {
            kartService.addItem(producto, cantidad);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/eliminar")
    public String eliminarDelKart(@RequestParam Integer idProducto, @RequestParam String tipoProducto) {
        if ("ropa".equals(tipoProducto)) {
            ProductoRopa producto = productoRopaService.obtenerProductoPorId(idProducto);
            if (producto != null) {
                kartService.removeItem(producto);
            }
        } else if ("aseo".equals(tipoProducto)) {
            ProductoAseo producto = productoAseoService.obtenerProductoAseoPorId(idProducto);
            if (producto != null) {
                kartService.removeItem(producto);
            }
        }
        return "redirect:/kart";
    }
    @GetMapping("/checkout")
    public void checkout(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=carrito.pdf");
        pdfService.export(response, kartService.getItems(), kartService.getTotal());
    }
}
