package ProyectoEstructuras;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Tarjeta extends Lista<String> {

    private String data;
    private String title;
    private String tag;
    private float rating;
    public List<Float> ratings = new ArrayList<>();
    private LocalDateTime time;

    public Tarjeta(String data, String title, String tag, float rating) {
        this.data = data;
        this.title = title;
        this.tag = tag;
        this.rating = rating;
        this.time = LocalDateTime.now();
    }

    public Tarjeta(String data, String title, String tag) {
        this.data = data;
        this.title = title;
        this.tag = tag;
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void add(String data) {
        this.data = data;
    }

    public boolean hasRatings() {
        return !ratings.isEmpty(); // Devuelve true si la lista de calificaciones no está vacía
    }

    public float addRating(float rating) {
        ratings.add(rating);
        this.rating = calcularpromedio(); // Llamar al método para recalcular el promedio de las calificaciones
        return this.rating;
    }

    private float calcularpromedio() {
        if (!ratings.isEmpty()) {
            float sum = 0;
            for (Float r : ratings) {
                sum += r;
            }
            this.rating = sum / ratings.size(); // Calcular el promedio de las calificaciones
        }
        return rating;
    }

    public String toString() {
        return "Tarjeta{"
                + "Nombre='" + this.title + '\''
                + ", Descripcion='" + this.data + '\''
                + ", Etiqueta='" + this.tag + '\''
                + ", Calificacion=" + this.rating
                + '}';
    }

    public String resumen() {
        return this.title + ", " + this.tag;
    }

}
