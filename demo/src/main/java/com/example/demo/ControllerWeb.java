/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import static com.example.demo.LogicaDeNegocio.calcularIVA;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author irlor
 */
@Controller
public class ControllerWeb {
    
    
    @GetMapping("/saludo")
    public String showPage(
            @RequestParam(name = "nombre", required = true, defaultValue = "john doe") String nombre,
            Model model
    ){
            model.addAttribute("nombreUsuario", nombre);
            return "saludo";
    }
    
    @GetMapping("/page")
    public String getPageNumber(
            @RequestParam(defaultValue = "1") int number,
            Model model
    ) {
        model.addAttribute("pageNumber", number);
        return "number";
    }
    
    @GetMapping("/formulario")
    public String mostrarFormulario(){
        return "formulario";
    }
    
    @PostMapping("/calcIVA")
    public String paginaIVA(
        @RequestParam(name = "number") float precio,
        Model model
    ){
        model.addAttribute("calculatedIVA", calcularIVA(precio));
        return "resultadoIVA";
    }
    
        @GetMapping("/conversor")
    public String mostrarFormularioCon() {
        return "conversor";
    }

    @PostMapping("/conversor")
    public String convertir(
            @RequestParam double cantidad,
            @RequestParam String de,
            @RequestParam String a,
            Model model
    ) {
        double resultado = cantidad;

        // Toda la lógica metida directamente aquí:
        if (de.equals("EUR") && a.equals("USD")) resultado = cantidad * 1.08;
        else if (de.equals("USD") && a.equals("EUR")) resultado = cantidad * 0.92;
        else if (de.equals("EUR") && a.equals("GBP")) resultado = cantidad * 0.85;
        else if (de.equals("GBP") && a.equals("EUR")) resultado = cantidad * 1.18;

        model.addAttribute("cantidad", cantidad);
        model.addAttribute("de", de);
        model.addAttribute("a", a);
        model.addAttribute("resultado", Math.round(resultado * 100.0) / 100.0);

        return "resultadoConversor";
    }
    
    
    
    
}
