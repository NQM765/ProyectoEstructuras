package ProyectoEstructuras;
public class Tarjeta extends Lista<String>{
    private String data;
    private String title;
    private String tag;
    private Float rating;

    public Tarjeta(String data, String title, String tag, Float rating) {
        this.data = data;
        this.title = title;
        this.tag = tag;
        this.rating = rating;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }


    public void add(String data) {
        this.data = data;
    }



}
