package com.tpFinalLabo4.labo4.controller;


import com.tpFinalLabo4.labo4.model.Clase;
import com.tpFinalLabo4.labo4.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    private IClaseService interClase;

    @GetMapping("/get-clase-list")
    public List<Clase> getClase(){
        return interClase.getClase();
    }


    @PostMapping("/crear")   //TODO Preguntar si ya recibiendo el objeto de tipo clase desde el front ya alcanza
    public String createClase(@RequestBody Clase clase){
        interClase.saveClase(clase);
        return "La clase fue guardado correctamente";
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteClase(@PathVariable Long id){

        interClase.deleteClase(id);
        return "La clase fue eliminada correctamente";
    }


    @PutMapping("/editar/{id}") //puede ser con el ID "/personas/editar/{id}"
    public Clase editClase (@PathVariable Long id,
                           @RequestParam ("title") String nuevoTitle,
                           @RequestParam("descripcion") String nuevaDescripcion,
                           @RequestParam("url") String nuevaUrl ,
                            @RequestParam("isVisto") boolean nuevoIsVisto){
        Clase clase= interClase.findById(id);


        clase.setTitle(nuevoTitle);
        clase.setDescripcion(nuevaDescripcion);
        clase.setUrl(nuevaUrl);
        clase.setVisto(nuevoIsVisto);
        interClase.saveClase(clase);

        return clase;


    }


    @GetMapping("/traer/{id}")
    public Clase findById(@PathVariable Long id){
        return interClase.findById(id);

    }


}
