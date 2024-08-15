package tailor.tailor_net.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tailor.tailor_net.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    // Puedes agregar métodos personalizados de consulta aquí si es necesario
}
