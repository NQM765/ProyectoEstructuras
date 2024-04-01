package ProyectoEstructuras;

import java.util.ArrayList;
import java.util.List;

public class Tarjeta extends Lista<String> {

    private String data;
    private String title;
    private String tag;
    private float rating;
    private List<Float> ratings = new ArrayList<>();

    public Tarjeta(String data, String title, String tag, float rating) {
        this.data = data;
        this.title = title;
        this.tag = tag;
        this.rating = rating;
    }

    public Tarjeta(String data, String title, String tag) {
        this.data = data;
        this.title = title;
        this.tag = tag;
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

    public void addRating(float rating) {
        ratings.add(rating);
        calcularpromedio(); // Llamar al método para recalcular el promedio de las calificaciones
    }

    private void calcularpromedio() {
        if (!ratings.isEmpty()) {
            float sum = 0;
            for (Float r : ratings) {
                sum += r;
            }
            this.rating = sum / ratings.size(); // Calcular el promedio de las calificaciones
        }
    }

}
