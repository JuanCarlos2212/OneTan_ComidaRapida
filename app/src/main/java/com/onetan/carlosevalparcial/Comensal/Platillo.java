package com.onetan.carlosevalparcial.Comensal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Platillo {
    private Integer idplatillo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Bitmap imagen;
    private String ruta;

    public Platillo() {
    }

    public Platillo(Integer idplatillo, String nombre, String descripcion, Double precio, Bitmap imagen, String ruta) {
        this.idplatillo = idplatillo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.ruta = ruta;
    }

    public Integer getIdplatillo() {
        return idplatillo;
    }

    public void setIdplatillo(Integer idplatillo) {
        this.idplatillo = idplatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getRuta() {
        return ruta;
    }

    public void setDataImagen(String data) {
        try {
            byte[] bytecode = Base64.decode(data, Base64.DEFAULT);
            this.imagen = BitmapFactory.decodeByteArray(bytecode, 0, bytecode.length);


        } catch (Exception e) {
            e.printStackTrace();

        }


    }
//    public void gettDataImagen(Bitmap data) {
//        try {
//            byte[] bytecode = Base64.decode(data, Base64.DEFAULT);
//            this.imagen = BitmapFactory.decodeByteArray(bytecode, 0, bytecode.length);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//
//    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Platillo{" +
                "idplatillo=" + idplatillo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen=" + imagen +
                ", ruta='" + ruta + '\'' +
                '}';
    }
}
