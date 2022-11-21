package edu.migsw.inasistencia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import edu.migsw.inasistencia.entities.InasistenciaEntity;
import edu.migsw.inasistencia.repositories.InasistenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class InasistenciaService {

    @Autowired
    InasistenciaRepository InasistenciaRepository;

    public List<InasistenciaEntity> getAll() {
        return InasistenciaRepository.findAll();
    }

     public List<InasistenciaEntity> getByRut(String rut) {
        return InasistenciaRepository.findByRut(rut);
    } 
    
    public Integer countByRutAndDate(String rut, String fecha) {
        return InasistenciaRepository.countByRutAndDate(rut, fecha);
    }

    public Optional<InasistenciaEntity> getInasistenciaById(Long id) {
        return InasistenciaRepository.findById(id);
    }
    public InasistenciaEntity save(InasistenciaEntity Inasistencia) {
        InasistenciaEntity InasistenciaNueva=InasistenciaRepository.save(Inasistencia);
        return InasistenciaNueva;
    } 

    public Integer countInasistencias(String rut) {
        return InasistenciaRepository.countInasistencias(rut);
    }

    public String justificarInasistencia(String rut){
        ArrayList<InasistenciaEntity> inasistencia = InasistenciaRepository.findByRut(rut);
        if(inasistencia!=null){
            for(InasistenciaEntity inas:inasistencia){
                if(inas.getJustificada()==0){
                    inas.setJustificada(1);
                    InasistenciaRepository.save(inas);
                }
                else{
                    inas.setJustificada(0);
                    InasistenciaRepository.save(inas);
                }
            }
            return("Inasistencias justificadas");
        }
        return("Inasistencias no encontradas");
    }


    /* public InasistenciaEntity crearInasistencia( String rut, String fecha){
            return new InasistenciaEntity(null,rut,fecha,0);
    } */
}
