package tailor.tailor_net.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tailor.tailor_net.model.Agendamiento;
import tailor.tailor_net.repository.AgendamientoRepository;

@Service
public class AgendamientoService {
   @Autowired
   private AgendamientoRepository AgendamientoRepository;

   public AgendamientoService() {
   }

   public List<Agendamiento> listarTodasLasCitas() {
      return this.AgendamientoRepository.findAll();
   }

   public Agendamiento guardarCita(Agendamiento cita) {
      return this.AgendamientoRepository.save(cita);
   }
   public Agendamiento buscarCitaPorId(Long id) {
      return AgendamientoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con el id: " + id));
  }
  
  public Agendamiento actualizarCita(Agendamiento cita) {
      return AgendamientoRepository.save(cita);
  }
  public void eliminarCitaPorId(Long id) {
   AgendamientoRepository.deleteById(id);
}
}
