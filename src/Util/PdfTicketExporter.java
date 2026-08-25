package Util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

/**
 * Escribe una imagen renderizada como un PDF de una sola página.
 * No depende de ninguna librería externa: comprime los píxeles sin pérdida
 * con java.util.zip.Deflater (formato zlib, el mismo que exige /FlateDecode)
 * y los incrusta en un PDF construido a mano.
 */
public final class PdfTicketExporter {

    private PdfTicketExporter() {
    }

    public static void guardarComoPdf(BufferedImage imagen, double anchoPuntos, double altoPuntos, File destino) throws IOException {
        byte[] datosComprimidos = comprimirRgbSinPerdida(imagen);
        int width = imagen.getWidth();
        int height = imagen.getHeight();

        List<Integer> offsets = new ArrayList<>();
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();

        escribir(pdf, "%PDF-1.4\n%âãÏÓ\n");

        offsets.add(pdf.size());
        escribir(pdf, "1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");

        offsets.add(pdf.size());
        escribir(pdf, "2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n");

        offsets.add(pdf.size());
        escribir(pdf, "3 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 " + formatear(anchoPuntos) + " " + formatear(altoPuntos)
                + "] /Resources << /XObject << /Im0 5 0 R >> >> /Contents 4 0 R >>\nendobj\n");

        String contenido = formatear(anchoPuntos) + " 0 0 " + formatear(altoPuntos) + " 0 0 cm\n/Im0 Do\n";
        offsets.add(pdf.size());
        escribir(pdf, "4 0 obj\n<< /Length " + contenido.length() + " >>\nstream\n" + contenido + "endstream\nendobj\n");

        offsets.add(pdf.size());
        escribir(pdf, "5 0 obj\n<< /Type /XObject /Subtype /Image /Width " + width + " /Height " + height
                + " /ColorSpace /DeviceRGB /BitsPerComponent 8 /Filter /FlateDecode /Length " + datosComprimidos.length
                + " >>\nstream\n");
        pdf.write(datosComprimidos);
        escribir(pdf, "\nendstream\nendobj\n");

        int xrefOffset = pdf.size();
        escribir(pdf, "xref\n0 " + (offsets.size() + 1) + "\n0000000000 65535 f \n");
        for (int offset : offsets) {
            escribir(pdf, String.format("%010d 00000 n \n", offset));
        }
        escribir(pdf, "trailer\n<< /Size " + (offsets.size() + 1) + " /Root 1 0 R >>\nstartxref\n" + xrefOffset + "\n%%EOF");

        try (OutputStream out = new FileOutputStream(destino)) {
            pdf.writeTo(out);
        }
    }

    private static byte[] comprimirRgbSinPerdida(BufferedImage imagen) throws IOException {
        int width = imagen.getWidth();
        int height = imagen.getHeight();
        int[] pixeles = imagen.getRGB(0, 0, width, height, null, 0, width);

        byte[] rgb = new byte[width * height * 3];
        int i = 0;
        for (int pixel : pixeles) {
            rgb[i++] = (byte) ((pixel >> 16) & 0xFF);
            rgb[i++] = (byte) ((pixel >> 8) & 0xFF);
            rgb[i++] = (byte) (pixel & 0xFF);
        }

        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        deflater.setInput(rgb);
        deflater.finish();

        ByteArrayOutputStream comprimido = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        while (!deflater.finished()) {
            int n = deflater.deflate(buffer);
            comprimido.write(buffer, 0, n);
        }
        deflater.end();

        return comprimido.toByteArray();
    }

    private static String formatear(double valor) {
        if (valor == Math.floor(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }

    private static void escribir(ByteArrayOutputStream out, String texto) {
        byte[] bytes = texto.getBytes(StandardCharsets.ISO_8859_1);
        out.write(bytes, 0, bytes.length);
    }
}
