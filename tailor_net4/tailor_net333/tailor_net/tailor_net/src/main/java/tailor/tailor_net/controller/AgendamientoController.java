package tailor.tailor_net.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tailor.tailor_net.model.Agendamiento;
import tailor.tailor_net.service.AgendamientoService;

@Controller
public class AgendamientoController {
    @Autowired
    private AgendamientoService agendamientoService;

    public AgendamientoController() {
    }

    @GetMapping({"/agendamiento"})
    public String agendamiento(Model model) {
        model.addAttribute("agendamientoCita", new Agendamiento());
        return "agendamiento";
    }

    @GetMapping("/agendamientoCitas")
    public String agendamientoC(Model model) {
        List<Agendamiento> todasLasCitas = agendamientoService.listarTodasLasCitas();
        model.addAttribute("citas", todasLasCitas);
        return "agendamientoCitas";
    }

    @PostMapping("/agregarCita")
    public String agregarCita(@ModelAttribute Agendamiento cita, @RequestParam("hora") String horaValue) {
        // Convertir el valor de hora de String a LocalTime
        LocalTime hora = horaValue.equals("AM") ? LocalTime.of(8, 0) : LocalTime.of(13, 0); // 8 AM o 1 PM
        cita.setHora(hora);

        this.agendamientoService.guardarCita(cita);
        return "redirect:/index";
    }



    @GetMapping("/agendamiento_editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Agendamiento citaExistente = agendamientoService.buscarCitaPorId(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (citaExistente.getFecha() != null) {
            String fechaFormatted = citaExistente.getFecha().format(formatter);
            model.addAttribute("fechaFormatted", fechaFormatted);
        }

        model.addAttribute("cita", citaExistente);
        return "agendamiento_editar";
    }

    @PostMapping("/actualizarCita")
    public String actualizarCita(@ModelAttribute("cita") Agendamiento citaActualizada) {
        agendamientoService.actualizarCita(citaActualizada);
        return "redirect:/agendamientoCitas";
    }

    @GetMapping("/agendamiento_eliminar/{id}")
    public String mostrarConfirmacionEliminacion(@PathVariable Long id, Model model) {
        Agendamiento cita = agendamientoService.buscarCitaPorId(id);
        if (cita == null) {
            return "redirect:/agendamientoCitas";
        }
        model.addAttribute("cita", cita);
        return "agendamiento_eliminar";
    }

    @PostMapping("/agendamiento_eliminar")
    public String eliminarCita(@RequestParam("idCita") Long idCita, RedirectAttributes attributes) {
        agendamientoService.eliminarCitaPorId(idCita);
        attributes.addFlashAttribute("mensaje", "Cita eliminada correctamente.");
        return "redirect:/agendamientoCitas";
    }
}
