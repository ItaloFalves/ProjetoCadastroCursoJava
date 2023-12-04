package com.projetodecurso.cursos.controllers;

import com.projetodecurso.cursos.entities.TurmaParticipante;
import com.projetodecurso.cursos.services.TurmaParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/turmaparticipante")
public class TurmaParticipanteController {

    @Autowired
    private TurmaParticipanteService turmaParticipanteService;

    @PostMapping
       public ResponseEntity<TurmaParticipante> insert(@RequestBody TurmaParticipante turmaParticipante){
        turmaParticipante = turmaParticipanteService.createParticipatingClass(turmaParticipante);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaParticipante);
    }

    @PutMapping(value = "/{codigo}")
    public ResponseEntity<TurmaParticipante> update(@PathVariable int codigo, @RequestBody TurmaParticipante turmaParticipante) throws SQLException {
        turmaParticipante = turmaParticipanteService.updateParticipatingClass(codigo, turmaParticipante);
        return ResponseEntity.ok().body(turmaParticipante);
    }

    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable int codigo) throws SQLException{
        turmaParticipanteService.deleteParticipatingClass(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<TurmaParticipante>> get() throws SQLException{
        ArrayList<TurmaParticipante> turmaParticipanteList = turmaParticipanteService.getParticipatingClass();
        return ResponseEntity.ok().body(turmaParticipanteList);
    }

}
