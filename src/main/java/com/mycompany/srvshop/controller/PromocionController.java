/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.srvshop.controller;

import com.mycompany.srvshop.model.Promocion;
import com.mycompany.srvshop.model.Promocion;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Optional;

/**
 *
 * @author sebastian
 */
@Named
@ApplicationScoped
public class PromocionController extends AbstractController<Promocion> {

    @Inject
    PropiedadController propiedadcontroller;

    public PromocionController() {
        super(Promocion::new);

    }

    @Override
    @PostConstruct
    public void load() {
        this.create();
        this.getSelected().setActivo(true);

        this.getSelected().setNombre("2x1");
        this.getSelected().setDescripcion("Paga uno lleva dos");
        this.add();

        this.create();
        this.getSelected().setActivo(true);

        this.getSelected().setNombre("3X1");
        this.getSelected().setDescripcion("Paga uno lleva tres");
        this.add();

        this.create();
        this.getSelected().setActivo(false);

        this.getSelected().setNombre("20% MSI");
        this.getSelected().setDescripcion("20% en todos los productos de la marca MSI");
        this.add();
    }
    public Promocion findById(int id){
        Optional<Promocion> o=null;
       o=this.getItems().stream().filter(
               item -> {
                return item.getId() == id;
           }).findFirst();
       if(o.isEmpty())
           return null;
       else
           return o.get();
    }
      public Promocion findByName(String name){
        Optional<Promocion> o=null;
       o=this.getItems().stream().filter(
               item -> {
                return item.getNombre().equals(name);
           }).findFirst();
       if(o.isEmpty())
           return null;
       else
           return o.get();
    }
      
    public String remove() {
        if (this.getSelected() != null) {
            if (this.propiedadcontroller.getItems().stream().filter(item -> {
                return item.getPromocion() == this.getSelected();
            }).count() == 0) {
                this.repositorio.remove(this.getSelected());
                return "remove";
            } else {
                return "";
            }

        }
        //se tiene que poner el error
        return "";
    }

    @Override
    public String preEdit() {

        return "edit";
    }

    @Override
    public String add() {
        //si es nuevo
        if (this.getSelected().getId() == -1) {
            this.getSelected().setId(this.repositorio.getAll().size() + 1);
            this.repositorio.create(this.getSelected());
        } else {
            this.repositorio.update(this.getSelected());

        }
        return "sucess";
    }
}
