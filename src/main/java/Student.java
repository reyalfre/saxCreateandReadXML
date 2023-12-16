/**
 * Clase Student: Clase Pojo para la clase CalificacionesParser
 */
class Student {
    private String name;
    private double calificacion1;
    private double calificacion2;
    private double proyecto;
    private double practica;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalificacion1() {
        return calificacion1;
    }

    public void setCalificacion1(double calificacion1) {
        this.calificacion1 = calificacion1;
    }

    public double getCalificacion2() {
        return calificacion2;
    }

    public void setCalificacion2(double calificacion2) {
        this.calificacion2 = calificacion2;
    }

    public double getProyecto() {
        return proyecto;
    }

    public void setProyecto(double proyecto) {
        this.proyecto = proyecto;
    }

    public double getPractica() {
        return practica;
    }

    public void setPractica(double practica) {
        this.practica = practica;
    }

    public double calculateAverage() {
        return (calificacion1 + calificacion2 + proyecto + practica) / 4;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", calificacion1=" + calificacion1 +
                ", calificacion2=" + calificacion2 +
                ", proyecto=" + proyecto +
                ", practica=" + practica +
                '}';
    }
}