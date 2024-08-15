package tailor.tailor_net.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Data
@Table(name = "agendamiento_cita")
public class Agendamiento {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_cita")
   private Long idCita;

   @Column(name = "fecha")
   private LocalDate fecha;

   @Column(name = "descripcion")
   private String descripcion;

   @Column(name = "nombre")
   private String nombre;

   @Column(name = "telefono")
   private Long telefono;

   @Column(name = "hora")
   private LocalTime hora;

   public Agendamiento() {
   }

   public Agendamiento(Long idCita, LocalDate fecha, String descripcion, String nombre, Long telefono, LocalTime hora) {
      this.idCita = idCita;
      this.fecha = fecha;
      this.descripcion = descripcion;
      this.nombre = nombre;
      this.telefono = telefono;
      this.hora = hora;
   }
}
