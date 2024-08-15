package tailor.tailor_net.model;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import tailor.tailor_net.util.ImagenUtil;

import java.util.Base64;
import java.util.List;

@MappedSuperclass
public class EntityParent {
    @Lob
    @Column(name = "IMAGEN")
    private byte[] imagen;
    @Transient
    public String imagenBase64;

    public String getImagenBase64() {
        if (this.imagen != null && this.imagenBase64 == null) {
            this.imagenBase64 = Base64.getEncoder().encodeToString(this.imagen);
        }

        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
        this.imagenBase64 = Base64.getEncoder().encodeToString(imagen); // Convertir automáticamente a base64
    }

    public static void byteImagenToBase64(List<? extends EntityParent> entities) {
        entities.stream()
                .filter(producto -> producto.getImagen() != null)
                .forEach(producto ->
                        producto.setImagenBase64(ImagenUtil.byteAString64(producto.getImagen()))
                );
    }
}
