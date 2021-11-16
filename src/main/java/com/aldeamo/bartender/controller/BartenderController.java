package com.aldeamo.bartender.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aldeamo.bartender.dto.Mensaje;
import com.aldeamo.bartender.entity.Arrays;
import com.aldeamo.bartender.service.BartenderService;


@RestController
@RequestMapping("/bartender/1.0.0")
@CrossOrigin(origins = "http://localhost:4200")
public class BartenderController {
	
	@Autowired
	BartenderService bartenderService;
		
	@GetMapping("/listaPila")
    public ResponseEntity<List<Arrays>> listaPila(){
        List<Arrays> list = bartenderService.listaPila();
        return new ResponseEntity(list, HttpStatus.OK);
    }
	
	@GetMapping("/listaPila/{id}")
    public ResponseEntity<List<Arrays>> getPilaTrabajo(@PathVariable("id") int idPila){
		
		if(!bartenderService.existsById(idPila)) {
			return new ResponseEntity(new Mensaje("No existe el id de pila ingresado"), HttpStatus.NOT_FOUND);
		}		
		
        Arrays array = bartenderService.getPilaTrabajo(idPila).get();
        return new ResponseEntity(array, HttpStatus.OK);
    }
	
	@GetMapping("/procesarPila/{id}/{iteraciones}")
    public ResponseEntity<int[]> procesarPila(@PathVariable("id") int idPila, @PathVariable("iteraciones") int numeroIteraciones){
    	if(!bartenderService.existsById(idPila)) {
			return new ResponseEntity(new Mensaje("No existe el id de pila ingresado"), HttpStatus.NOT_FOUND);
		}
        int[] arrayRespuesta = bartenderService.procesarPila(numeroIteraciones, idPila);
        return new ResponseEntity(arrayRespuesta, HttpStatus.OK);
    }

}
