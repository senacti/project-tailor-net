package tailor.tailor_net.model;

import tailor.tailor_net.model.ProductoRopa;

import java.util.HashMap;
import java.util.Map;

public class Kart {
    private Map<ProductoRopa, Integer> items = new HashMap<>();

    public Map<ProductoRopa, Integer> getItems() {
        return items;
    }

    public void addItem(ProductoRopa producto, int cantidad) {
        items.put(producto, items.getOrDefault(producto, 0) + cantidad);
    }

    public void removeItem(ProductoRopa producto) {
        items.remove(producto);
    }

    public double getTotal() {
        return items.entrySet().stream().mapToDouble(e -> e.getKey().getPrecioUnidad() * e.getValue()).sum();
    }
}
