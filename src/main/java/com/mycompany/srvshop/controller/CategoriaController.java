/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.srvshop.controller;

import com.mycompany.srvshop.model.Categoria;

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
public class CategoriaController extends AbstractController<Categoria> {

    @Inject
    PropiedadController propiedadcontroller;

    public CategoriaController() {
        super(Categoria::new);

    }

    @Override
    @PostConstruct
    public void load() {
        this.create();
        this.getSelected().setActivo(true);

        this.getSelected().setNombre("Informática");
        this.add();

        this.create();
        this.getSelected().setActivo(true);

        this.getSelected().setNombre("Panaderia");
        this.add();

        this.create();
        this.getSelected().setActivo(false);

        this.getSelected().setNombre("Pescaderia");
        this.add();
    }
    public Categoria findById(int id){
        Optional<Categoria> o=null;
       o=this.getItems().stream().filter(
               item -> {
                return item.getId() == id;
           }).findFirst();
       if(o.isEmpty())
           return null;
       else
           return o.get();
    }
      public Categoria findByName(String name){
        Optional<Categoria> o=null;
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
                return item.getCategoria() == this.getSelected();
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
