package tailor.tailor_net.domain;

import java.util.Arrays;

public enum Clasificacion {

    ROPA(2), ASEO(3);
    private long idTipo;

    Clasificacion(long id) {
        idTipo = id;
    }

    public static Clasificacion getById(long id) {
        return Arrays.stream(values())
                .filter(clasificacion -> clasificacion.idTipo == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("id no encontrado"));
    }
}
