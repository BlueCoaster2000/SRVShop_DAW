/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.srvshop.controller;

import com.mycompany.srvshop.model.Tienda;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Optional;

/**
 *
 * @author Pedro
 */
@Named
@ApplicationScoped
public class TiendaController extends AbstractController<Tienda> {

    @Inject
    PropiedadController propiedadcontroller;

    public TiendaController() {
        super(Tienda::new);

    }

    @Override
    @PostConstruct
    public void load() {
        this.create();
        this.getSelected().setActivo(true);

        this.getSelected().setNombre("Carniceria Paquita");
        this.getSelected().setDescripcion("La mejor carniceria de la Vega Baja");
        this.getSelected().setCoordenadas("-39.7066, 62.5910");
        this.add();

        this.create();
        this.getSelected().setActivo(true);

        this.getSelected().setNombre("InterBeans Infórmatica");
        this.getSelected().setDescripcion("Servicios Web y Reparación de artículos informáticos");
        this.getSelected().setCoordenadas("-50.7022, 43.2003");
        this.add();

    
    }
    public Tienda findById(int id){
        Optional<Tienda> o=null;
       o=this.getItems().stream().filter(
               item -> {
                return item.getId() == id;
           }).findFirst();
       if(o.isEmpty())
           return null;
       else
           return o.get();
    }
      public Tienda findByName(String name){
        Optional<Tienda> o=null;
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
                return item.getTienda() == this.getSelected();
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
