import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalificacionesParser extends DefaultHandler {
    private List<Student> students;
    private Student currentStudent;
    private String currentElement;
    private boolean showAverage;
    private SAXParserFactory saxParser;

    public CalificacionesParser() {
        students = new ArrayList<>();
        showAverage = false;
    }

    /**
     * Método invocado durante el análisis cuando se encuentra un nuevo elemento XML.
     * Establece currentElement como el nombre del elemento actual (qName).
     * Si el elemento es "alumno", crea una nueva instancia de la clase Student (currentStudent).
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @throws SAXException
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if ("alumno".equals(qName)) {
            currentStudent = new Student();
        }
    }

    /**
     * Método invocado durante el análisis cuando se alcanza el final de un elemento XML.
     * Si el elemento es "alumno", agrega el estudiante actual (currentStudent) a la lista de estudiantes (students).
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @throws SAXException
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("alumno".equals(qName)) {
            students.add(currentStudent);
        }
    }

    /**
     * Método invocado durante el análisis cuando se encuentran datos de caracteres dentro de un elemento XML.
     * Convierte los datos de caracteres en una cadena y elimina espacios en blanco.
     * Si la cadena no está vacía, asigna el valor al atributo correspondiente del estudiante actual (currentStudent) según el elemento actual (currentElement).
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @throws SAXException
     */

    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (!value.isEmpty()) {
            switch (currentElement) {
                case "nombre":
                    currentStudent.setName(value);
                    break;
                case "calificacion1":
                    currentStudent.setCalificacion1(Double.parseDouble(value));
                    break;
                case "calificacion2":
                    currentStudent.setCalificacion2(Double.parseDouble(value));
                    break;
                case "proyecto":
                    currentStudent.setProyecto(Double.parseDouble(value));
                    break;
                case "practica":
                    currentStudent.setPractica(Double.parseDouble(value));
                    break;
            }
        }
    }

    /**
     * Devuelve la lista de estudiantes analizados.
     * @return
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Devuelve el valor de la bandera showAverage, que indica si se deben mostrar los promedios.
     * @return un boolean
     */
    public boolean isShowAverage() {
        return showAverage;
    }

    /**
     * Establece el valor de la bandera showAverage según el argumento proporcionado.
     * @param showAverage
     */
    public void setShowAverage(boolean showAverage) {
        this.showAverage = showAverage;
    }

    /*public static void main(String[] args) {
        try {
            File xmlFile = new File("src/main/resources/Calificaciones.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            CalificacionesParser handler = new CalificacionesParser();
            saxParser.parse(xmlFile, handler);

            System.out.println("Contenido del archivo XML:");
            for (Student student : handler.getStudents()) {
                System.out.println(student);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("¿Quieres ver la media de cada alumno y la media total de la clase, s/n? ");
            char answer = scanner.nextLine().toLowerCase().charAt(0);
            //Calcula y muestra los promedios si el usuario introduce s.
            if (answer == 's') {
                handler.setShowAverage(true);
                List<Student> students = handler.getStudents();
                double classAverage = students.stream().mapToDouble(Student::calculateAverage).average().orElse(0);

                System.out.println("\nMedias:");
                for (Student student : students) {
                    System.out.printf("%s: %.2f%n", student.getName(), student.calculateAverage());
                }
                System.out.printf("Media total de la clase: %.2f%n", classAverage);

                // Crea el archivo XML llamado Medias.xml con la media del curso y de cada estudiante.
                try (FileWriter writer = new FileWriter("src/main/resources/Medias.xml")) {
                    writer.write(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n<Calificaciones MediaCurso=\"%.2f\">%n", classAverage));
                    for (Student student : students) {
                        writer.write(String.format("\t<alumno>%n\t\t<nombre>%s</nombre>%n\t\t<calificacion>%.2f</calificacion>%n\t</alumno>%n",
                                student.getName(), student.calculateAverage()));
                    }
                    writer.write("</Calificaciones>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (answer == 'n') {
                System.out.println("Programa finalizado.");
            } else {
                System.out.println("Respuesta no válida. Programa finalizado.");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/main/resources/Calificaciones.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            CalificacionesParser handler = new CalificacionesParser();
            saxParser.parse(xmlFile, handler);

            System.out.println("Contenido del archivo XML:");
            for (Student student : handler.getStudents()) {
                System.out.println(student);
            }

            Scanner scanner = new Scanner(System.in);
            char answer;

            do {
                System.out.print("¿Quieres ver la media de cada alumno y la media total de la clase? Introduzca en minúscula 's' si quieres o 'n' sino quieres ");
                String userInput = scanner.nextLine().toLowerCase();

                if (userInput.length() == 1) {
                    answer = userInput.charAt(0);
                    if (answer == 's' || answer == 'n') {
                        break;  // Salir del bucle si la entrada es válida
                    } else {
                        System.out.println("Respuesta no válida. Por favor, ingresa 's' o 'n'.");
                    }
                } else {
                    System.out.println("Respuesta no válida. Por favor, ingresa 's' o 'n'.");
                }
            } while (true);

            if (answer == 's') {
                handler.setShowAverage(true);
                List<Student> students = handler.getStudents();
                double classAverage = students.stream().mapToDouble(Student::calculateAverage).average().orElse(0);

                System.out.println("\nMedias:");
                for (Student student : students) {
                    System.out.printf("%s: %.2f%n", student.getName(), student.calculateAverage());
                }
                System.out.printf("Media total de la clase: %.2f%n", classAverage);

                // Crear el archivo XML con las medias
                try (FileWriter writer = new FileWriter("src/main/resources/Medias.xml")) {
                    writer.write(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>%n<Calificaciones MediaCurso=\"%.2f\">%n", classAverage));
                    for (Student student : students) {
                        writer.write(String.format("\t<alumno>%n\t\t<nombre>%s</nombre>%n\t\t<calificacion>%.2f</calificacion>%n\t</alumno>%n",
                                student.getName(), student.calculateAverage()));
                    }
                    writer.write("</Calificaciones>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Programa finalizado.");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}