package com.onetan.carlosevalparcial.Repartidor;

public class Pedidos {
    private Integer idpedidos;
    private Integer idusuario;
    private String nombres;
    private String celular;
    private String nombre;
    private Double precio;
    private String latitud;
    private String longitud;

    public Pedidos() {
    }

    public Pedidos(Integer idpedidos, Integer idusuario, String nombres, String celular, String nombre, Double precio, String latitud, String longitud) {
        this.idpedidos = idpedidos;
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.celular = celular;
        this.nombre = nombre;
        this.precio = precio;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Integer getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(Integer idpedidos) {
        this.idpedidos = idpedidos;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "idpedidos=" + idpedidos +
                ", idusuario=" + idusuario +
                ", nombres='" + nombres + '\'' +
                ", celular='" + celular + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }
}
