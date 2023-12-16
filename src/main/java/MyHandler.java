import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileWriter;
import java.io.IOException;

public class MyHandler extends DefaultHandler {
    private StringBuilder data;
    private FileWriter writer;

    public MyHandler() {
        data = new StringBuilder();
        try {
            // Create a FileWriter to write XML data to a file
            writer = new FileWriter("output.xml");

            // Start writing the XML prolog and the root element
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<Curso>\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Start of an element, reset data buffer
        data.setLength(0);

        if ("alumno".equals(qName)) {
            // Write the opening tag for each alumno element
            try {
                writer.write("    <alumno id=\"" + attributes.getValue("id") + "\">\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Accumulate characters into the data buffer
        data.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // End of an element, process the data and write to the file
        try {
            if ("nombre".equals(qName) || "calificacion1".equals(qName) ||
                    "calificacion2".equals(qName) || "proyecto".equals(qName) || "practica".equals(qName)) {
                writer.write("        <" + qName + ">" + data.toString().trim() + "</" + qName + ">\n");
            } else if ("alumno".equals(qName)) {
                // Write the closing tag for each alumno element
                writer.write("    </alumno>\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        // End of the document, close the XML file
        try {
            // Write the closing tag for the root element
            writer.write("</Curso>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
