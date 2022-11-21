package edu.migsw.inasistencia.controllers;

import edu.migsw.inasistencia.entities.InasistenciaEntity;
import edu.migsw.inasistencia.services.InasistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/inasistencias")
public class InasistenciaController {
    
    @Autowired
    InasistenciaService InasistenciaService;

    @GetMapping
    public ResponseEntity<List<InasistenciaEntity>> getAll(){
        List<InasistenciaEntity> Inasistencias = InasistenciaService.getAll();
        if(Inasistencias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Inasistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InasistenciaEntity> getInasistenciaById(@PathVariable("id") Long id){
        InasistenciaEntity Inasistencia = InasistenciaService.getInasistenciaById(id).get();
        if(Inasistencia == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Inasistencia);
    }

    @PostMapping()
    public ResponseEntity<InasistenciaEntity> save(@RequestBody InasistenciaEntity Inasistencia){
        InasistenciaEntity InasistenciaGuardada = InasistenciaService.save(Inasistencia);
        return ResponseEntity.ok(InasistenciaGuardada);
    }

    @GetMapping("/byrut/{rut}")
    public ResponseEntity<List<InasistenciaEntity>> getByRut(@PathVariable("rut") String rut){
        List<InasistenciaEntity> Inasistencias = InasistenciaService.getByRut(rut);
        if(Inasistencias.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Inasistencias);
    }

    @GetMapping("/byrutdate/{rut}/{fecha}")
    public ResponseEntity<Integer> countByRutAndDate(@PathVariable("rut") String rut, @PathVariable("fecha") String fecha){
        Integer Inasistencias = InasistenciaService.countByRutAndDate(rut, fecha);
        return ResponseEntity.ok(Inasistencias);
    }

    @GetMapping("/countbyrut/{rut}")
    public ResponseEntity<Integer> countByRut(@PathVariable("rut") String rut){
        Integer Inasistencias = InasistenciaService.countInasistencias(rut);
        return ResponseEntity.ok(Inasistencias);
    }

    @PutMapping("/justificar/{rut}")
    public ResponseEntity<String> justificarInasistencia(@PathVariable("rut") String rut){
        String justificacion = InasistenciaService.justificarInasistencia(rut);
        return ResponseEntity.ok(justificacion);
    }
}
