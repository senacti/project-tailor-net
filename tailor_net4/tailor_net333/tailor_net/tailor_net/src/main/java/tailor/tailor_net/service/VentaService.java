package tailor.tailor_net.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tailor.tailor_net.model.Venta;
import tailor.tailor_net.repository.VentaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public void guardarVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    public List<Venta> listarTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Venta buscarPorId(int idVenta) {
        Optional<Venta> optionalVenta = ventaRepository.findById((Integer) idVenta);
        return optionalVenta.orElseThrow(() -> new IllegalArgumentException("ID de venta no válido: " + idVenta));
    }

    public void actualizarVenta(Venta venta) {
        if (ventaRepository.existsById(venta.getIdVenta())) {
            ventaRepository.save(venta);
        } else {
            throw new IllegalArgumentException("ID de venta no válido: " + venta.getIdVenta());
        }
    }

    public void eliminarVenta(int idVenta) {
        if (ventaRepository.existsById((Integer) idVenta)) {
            ventaRepository.deleteById((Integer) idVenta);
        } else {
            throw new IllegalArgumentException("ID de venta no válido: " + idVenta);
        }
    }
}
