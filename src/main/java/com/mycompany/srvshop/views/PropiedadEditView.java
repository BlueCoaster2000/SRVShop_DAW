/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.srvshop.views;

import com.mycompany.srvshop.controller.CategoriaController;
import com.mycompany.srvshop.controller.OpcionController;
import com.mycompany.srvshop.controller.PromocionController;
import com.mycompany.srvshop.controller.PropiedadController;

import com.mycompany.srvshop.controller.TiendaController;
import com.mycompany.srvshop.model.Categoria;
import com.mycompany.srvshop.model.Imagen;

import com.mycompany.srvshop.model.Opcion;
import com.mycompany.srvshop.model.Promocion;
import com.mycompany.srvshop.model.Propiedad;

import com.mycompany.srvshop.model.Tienda;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.crypto.AEADBadTagException;

/**
 *
 * @author Administrador
 */
@ViewScoped
@Named
public class PropiedadEditView implements Serializable {

   
    @Inject
    private OpcionController opcionController;
    @Inject
    private TiendaController tiendaController;
    @Inject
    private CategoriaController categoriaController;
    @Inject
    private PromocionController promocionController;
    @Inject
    private PropiedadController propiedadController;
    private Propiedad propiedad;
   
    private Tienda tienda;
    private Promocion promocion;
    private Imagen imagenselected;

    private String destination = "";

    public PropiedadEditView() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        this.destination  = ctx.getExternalContext().getInitParameter("ruta_imagenes");
        //System.out.println("El valor es " + myConstantValue);
    }

    public void setSelected(Imagen img) {
        this.imagenselected = img;
    }

    public Imagen getSelected() {
        return this.imagenselected;
    }

    

    @PostConstruct
    public void init() {

        // this.tipo = new Tipo();
        if (this.propiedadController.getSelected() == null) {
            this.propiedad = new Propiedad();
        } else {
            //se clona por si se da a cancelar
            this.propiedad = (Propiedad) this.propiedadController.getSelected();//.clone(); //.getSelected();
        }
    }



   

    public List<Opcion> getOpciones() {
        return this.opcionController.getItems();
    }

    public void setOpcion(String item) {
        Optional<Opcion> consulta = this.opcionController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.propiedad.setOpcion(consulta.get());
        }
    }
    public List<Categoria> getCategorias() {
        return this.categoriaController.getItems();
    }

    public void setCategoria(String item) {
        Optional<Categoria> consulta = this.categoriaController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.propiedad.setCategoria(consulta.get());
        }
    }
    public List<Tienda> getTiendas() {
        return this.tiendaController.getItems();
    }

    public void setTienda(String item) {
        Optional<Tienda> consulta = this.tiendaController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.propiedad.setTienda(consulta.get());
        }
    }
    public List<Promocion> getPromociones() {
        return this.promocionController.getItems();
    }

    public void setPromociones(String item) {
        Optional<Promocion> consulta = this.promocionController.getItems().stream().filter(element -> {
            return element.getNombre().equals(item);
        }).findFirst();
        if (!consulta.isEmpty()) {
            this.propiedad.setPromocion(consulta.get());
        }
    }

 

    public String getOpcion() {
        if (this.propiedad.getOpcion() != null) {
            return this.propiedad.getOpcion().getNombre(); //this.getPropiedad().getOpcion();
        } else {
            return "";
        }
    }

   
    /**
     * @return the propiedad
     */
    public Propiedad getPropiedad() {
        return propiedad;
    }

    /**
     * @param propiedad the propiedad to set
     */
    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * @return the imagenselected
     */
    public Imagen getImagenselected() {
        return imagenselected;
    }

    /**
     * @param imagenselected the imagenselected to set
     */
    public void setImagenselected(Imagen imagenselected) {
        this.imagenselected = imagenselected;
    }

    public String add() {
        Propiedad p;
        if (this.propiedad != null) {
            if (this.propiedad.getId() != -1) {
                //se obtiene el original
                p = this.propiedadController.getPropiedadById(propiedad.getId());
                p.setDireccion(this.propiedad.getDireccion());
                p.setActivo(propiedad.isActivo());
              
                p.setOpcion(propiedad.getOpcion());
                p.setCategoria(propiedad.getCategoria());
               
                p.setPrecio(propiedad.getPrecio());
                this.propiedadController.setSelected(null);
                return "sucess";
            } else {
                //nuevo
                this.propiedadController.setSelected(this.propiedad);
                this.propiedadController.add();
                return "sucess";
            }
        } else {
            this.propiedadController.setSelected(null);
            return "failed";
        }

    }

    public String preEdit() {
        return "edit";
    }

    public String create() {
        this.propiedadController.setSelected(null);
        this.propiedad = new Propiedad();
        return "create";
    }

    public String cancel() {
        this.propiedad = null;
        return "sucess";
    }

    public String precreate() {
        return "imageadd";
    }

    public String removeImage() {
        File f = new File(this.destination + "/" + this.imagenselected.getPath());
        f.delete();
        this.propiedad.removeImagen(imagenselected);
        //  this.propiedadController.removeImage(imagenselected);

        return "reload";
    }
}
