package edu.migsw.inasistencia.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.migsw.inasistencia.entities.InasistenciaEntity;

@Repository
public interface InasistenciaRepository extends JpaRepository<InasistenciaEntity, Long> {

    @Query(value="select count(*) from inasistencias as i where i.rut = :rut and i.fecha = :fecha",
            nativeQuery = true)
    Integer countByRutAndDate(@Param("rut") String rut, @Param("fecha") String fecha);

    @Query(value="select count(*) from inasistencias as i where i.rut = :rut and i.justificada = 0",
            nativeQuery = true)
    Integer countInasistencias(@Param("rut") String rut);

    //findbyrut
    @Query(value="select * from inasistencias as i where i.rut = :rut",
            nativeQuery = true)
            ArrayList<InasistenciaEntity> findByRut(@Param("rut") String rut);
} 
