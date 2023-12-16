import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class CursoXMLGenerator {

    public static void main(String[] args) {
        try {
            // Generar el contenido del XML directamente en el código usando SAX
            StringBuilder xmlContent = new StringBuilder();
            xmlContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Curso>");

            // Información del primer alumno
            xmlContent.append("\n    <alumno id=\"jm\">");
            xmlContent.append("\n        <nombre>Juan Manuel Perez</nombre>");
            xmlContent.append("\n        <calificacion1>5</calificacion1>");
            xmlContent.append("\n        <calificacion2>7</calificacion2>");
            xmlContent.append("\n        <proyecto>8</proyecto>");
            xmlContent.append("\n        <practica>9</practica>");
            xmlContent.append("\n    </alumno>");

            // Información del segundo alumno
            xmlContent.append("\n    <alumno id=\"cm\">");
            xmlContent.append("\n        <nombre>Carlos Minguez</nombre>");
            xmlContent.append("\n        <calificacion1>7</calificacion1>");
            xmlContent.append("\n        <calificacion2>8</calificacion2>");
            xmlContent.append("\n        <proyecto>7</proyecto>");
            xmlContent.append("\n        <practica>4</practica>");
            xmlContent.append("\n    </alumno>");

            // Información del tercer alumno
            xmlContent.append("\n    <alumno id=\"jr\">");
            xmlContent.append("\n        <nombre>Julia Ramos</nombre>");
            xmlContent.append("\n        <calificacion1>6</calificacion1>");
            xmlContent.append("\n        <calificacion2>9</calificacion2>");
            xmlContent.append("\n        <proyecto>10</proyecto>");
            xmlContent.append("\n        <practica>10</practica>");
            xmlContent.append("\n    </alumno>");

            xmlContent.append("\n</Curso>");

            // Escribir el contenido al archivo
            writeToFile(xmlContent.toString(), "src/main/resources/Calificaciones.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String content, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            // Configurar la transformación para dar formato al archivo XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // Escribir el contenido al archivo
            transformer.transform(new SAXSource(new org.xml.sax.InputSource(new java.io.StringReader(content))),
                    new StreamResult(fos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
