package com.example.parcial_2909.modelo;

import androidx.annotation.Nullable;

import java.util.Locale;

public class Producto {
    private int cogido;
    private String descripcion;
    private double precio;

    public Producto(int cogido, String descripcion, double precio) {
        this.cogido = cogido;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getCogido() {
        return cogido;
    }

    public void setCogido(int cogido) {
        this.cogido = cogido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionLowerCase() {
        return descripcion.toLowerCase(Locale.ROOT);
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof Producto){
            if(cogido==((Producto) obj).cogido){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return cogido;
    }
}
