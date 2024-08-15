package tailor.tailor_net.service;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;
import tailor.tailor_net.model.ProductoAseo;
import tailor.tailor_net.model.ProductoRopa;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Service
public class PdfService {

    public void export(HttpServletResponse response, Map<Object, Integer> items, double total) throws IOException {
        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        document.add(new Paragraph("Detalle del Carrito").setFontSize(18).setBold());

        float[] columnWidths = {1, 5, 2, 2, 2};
        Table table = new Table(columnWidths);

        table.addHeaderCell(new Cell().add(new Paragraph("Imagen").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Producto").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Precio").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Subtotal").setBold()));

        for (Map.Entry<Object, Integer> entry : items.entrySet()) {
            Object producto = entry.getKey();
            Integer cantidad = entry.getValue();
            String nombre = "";
            double precio = 0;
            String imagen = "";

            if (producto instanceof ProductoRopa) {
                ProductoRopa ropa = (ProductoRopa) producto;
                nombre = ropa.getNombre();
                precio = ropa.getPrecioUnidad();
                imagen = ropa.getImagenBase64();
            } else if (producto instanceof ProductoAseo) {
                ProductoAseo aseo = (ProductoAseo) producto;
                nombre = aseo.getNombre();
                precio = aseo.getPrecioUnidad();
                imagen = aseo.getImagenBase64();
            }

            table.addCell(new Cell().add(new Paragraph("Imagen"))); // Aquí puedes agregar la lógica para mostrar la imagen
            table.addCell(new Cell().add(new Paragraph(nombre)));
            table.addCell(new Cell().add(new Paragraph("$" + precio)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(cantidad))));
            table.addCell(new Cell().add(new Paragraph("$" + (precio * cantidad))));
        }

        document.add(table);

        document.add(new Paragraph("Total: $" + total).setBold().setFontSize(14));

        document.close();
    }
}
