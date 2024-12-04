package com.upiiz.securitydb.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facturas")

public class FacturasController {
        @GetMapping("/listar")
        public String listarFacturas(){
            return "Listado de facturas";
        }

        @GetMapping("/listar/{id}")
        public String listarFacturasID(@PathVariable int id){
            return "Listado de facturas"+id;
        }

        @PostMapping("/crear")
        public String createFacturas(){
            return "creacion de facturas";
        }
        @PutMapping("/actualizar/{id}")
        public String updateFacturas(@PathVariable int id){
            return "actualizacion de facturas"+id;
        }

        @DeleteMapping("/eliminar/{id}")
        public String deleteFacturas(@PathVariable int id){
            return "Eliminar de facturas"+id;
        }
    }

