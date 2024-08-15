package tailor.tailor_net.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tailor.tailor_net.model.Venta;
import tailor.tailor_net.service.VentaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/ventas")
    public String listaVentas(Model model) {
        List<Venta> ventas = ventaService.listarTodasLasVentas();
        model.addAttribute("ventas", ventas);
        return "ventas";
    }

    @GetMapping("/new")
    public String mostrarFormularioNuevaVenta(Model model) {
        model.addAttribute("venta", new Venta());
        return "nueva_venta";
    }

    @GetMapping("/edit/{id}")
    public String mostrarFormularioEditarVenta(@PathVariable("id") int id, Model model) {
        Venta venta = ventaService.buscarPorId(id);
        model.addAttribute("venta", venta);
        return "editar_venta"; // Devuelve el formulario para editar la venta con el ID especificado
    }

    @PostMapping("/update")
    public String procesarFormularioEditarVenta(
            @ModelAttribute Venta venta
    ) {
        //Venta venta = ventaService.buscarPorId(Integer.parseInt(id)); // Buscar la venta por el ID de la URL
        if (venta != null) { // Verificar si la venta existe
            ventaService.actualizarVenta(venta);

            return "redirect:/ventas";
        } else {

            return "redirect:/ventas";
        }
    }

    @GetMapping("/delete/{id}")
    public String eliminarVenta(@PathVariable("id") int id) {
        ventaService.eliminarVenta(id);
        return "redirect:/ventas"; // Redirige a la lista de ventas después de eliminar la venta
    }

    @PostMapping("/guardarVenta")
    public String submitForm(
            @RequestParam("id_usuario") int idUsuario, // Cambiado de id_cliente a id_usuario
            @RequestParam("total_venta") float totalVenta,
            @RequestParam("fecha_venta") String fechaVentaString,
            Model model) {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            LocalDate fechaVenta = LocalDate.parse(fechaVentaString, formatter);


            Venta venta = new Venta();
            venta.setIdUsuario(idUsuario);
            venta.setTotalVenta(totalVenta);
            venta.setFechaVenta(fechaVenta);

            ventaService.guardarVenta(venta);

            return "redirect:/ventas"; // Redirige a la página de ventas después de guardar la venta
        } catch (Exception e) {

            e.printStackTrace();
            model.addAttribute("error", "Ocurrió un error al guardar la venta."); // Agrega un mensaje de error al modelo
            return "nueva_venta"; // Vuelve al formulario de nueva venta con el mensaje de error
        }
    }

}
