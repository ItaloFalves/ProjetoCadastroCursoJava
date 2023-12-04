package com.projetodecurso.cursos.controllers;

import com.projetodecurso.cursos.entities.Turma;
import com.projetodecurso.cursos.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Turma> insert(@RequestBody Turma turma){
        turma = turmaService.createClass(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(turma);
    }

    @PutMapping(value = "/{codigo}")
    public ResponseEntity<Turma> update(@PathVariable int codigo, @RequestBody Turma turma) throws SQLException {
        turma = turmaService.updateCourse(codigo, turma);
        return ResponseEntity.ok().body(turma);
    }

    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable int codigo) throws SQLException{
        turmaService.deleteCourse(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{codigo}")
    public ResponseEntity<ArrayList<Turma>> get(@PathVariable int codigo) throws SQLException{
        ArrayList<Turma> classList = turmaService.getCourse(codigo);
        return ResponseEntity.ok().body(classList);
    }
}


