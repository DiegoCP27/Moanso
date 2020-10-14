package models;

public class Producto {
    private int id;
    private String nombres;
    private String categoria;
    private String stock;
    private String precio;       

    public Producto() {
    }

    public Producto(String nombres, String categoria, String stock, String precio) {
        this.nombres = nombres;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    public Producto(int id, String nombres, String categoria, String stock, String precio) {
        this.id = id;
        this.nombres = nombres;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    
    
}
