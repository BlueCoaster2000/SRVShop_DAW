/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.srvshop.model;

import com.mycompany.srvshop.model.Opcion;
import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class Propiedad implements Cloneable {

    private String direccion;
    private int precio;
    private Opcion opcion;
    private Categoria categoria;
    private Tienda tienda;
    private Promocion promocion;
    private ArrayList<Imagen> imagenes;
    private Imagen principal;
    private boolean activo;
    private int id;

    public Propiedad() {
        this.imagenes = new ArrayList<>();
        this.id = -1;
        this.direccion = "";
        this.activo = true;
    
    }

    public String getDireccion() {
        return direccion;
    }
   
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public Opcion getOpcion() {
        return opcion;
    }
    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Promocion getPromocion() {
        return promocion;
    }
    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }
    public Tienda getTienda() {
        return tienda;
    }
    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

   
    public ArrayList<Imagen> getImagenes() {
        return imagenes;
    }
    public void setImagenes(ArrayList<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void removeImagen(Imagen img) {
            this.imagenes.removeIf(
                    item->{ return item.getId()==img.getId();}
            );
    }

    @Override
    public Object clone() {
        Propiedad p = new Propiedad();
        p.setId(id);
        p.setDireccion(direccion);
        p.setActivo(activo);
        p.setPrecio(precio);
        //copia superficial
      
        p.setOpcion(opcion);
        p.setCategoria(categoria);
        p.setPromocion(promocion);
        p.setTienda(tienda);
        p.setImagenes(this.imagenes);
        //faltan las imagenes
        return p;
    }
}
