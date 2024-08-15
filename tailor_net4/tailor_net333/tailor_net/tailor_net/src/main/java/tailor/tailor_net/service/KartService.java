package tailor.tailor_net.service;

import org.springframework.stereotype.Service;
import tailor.tailor_net.model.ProductoAseo;
import tailor.tailor_net.model.ProductoRopa;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class KartService {

    private final HttpSession session;

    public KartService(HttpSession session) {
        this.session = session;
    }

    public Map<Object, Integer> getItems() {
        Map<Object, Integer> kart = (Map<Object, Integer>) session.getAttribute("kart");
        if (kart == null) {
            kart = new HashMap<>();
            session.setAttribute("kart", kart);
        }
        return kart;
    }

    public void addItem(Object producto, int cantidad) {
        Map<Object, Integer> kart = getItems();
        kart.put(producto, kart.getOrDefault(producto, 0) + cantidad);
        session.setAttribute("kart", kart);
    }

    public void removeItem(Object producto) {
        Map<Object, Integer> kart = getItems();
        kart.entrySet().removeIf(entry -> {
            if (producto instanceof ProductoRopa) {
                return ((ProductoRopa) entry.getKey()).getIdProducto() == ((ProductoRopa) producto).getIdProducto();
            } else if (producto instanceof ProductoAseo) {
                return ((ProductoAseo) entry.getKey()).getIdProducto() == ((ProductoAseo) producto).getIdProducto();
            }
            return false;
        });
        session.setAttribute("kart", kart);
    }

    public double getTotal() {
        Map<Object, Integer> kart = getItems();
        return kart.entrySet().stream()
                .mapToDouble(entry -> {
                    if (entry.getKey() instanceof ProductoRopa) {
                        return ((ProductoRopa) entry.getKey()).getPrecioUnidad() * entry.getValue();
                    } else if (entry.getKey() instanceof ProductoAseo) {
                        return ((ProductoAseo) entry.getKey()).getPrecioUnidad() * entry.getValue();
                    }
                    return 0.0;
                })
                .sum();
    }
}
