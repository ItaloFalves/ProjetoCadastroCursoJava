package com.projetodecurso.cursos.controllers;

import com.projetodecurso.cursos.entities.Curso;
import com.projetodecurso.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> insert(@RequestBody Curso curso){
       curso = cursoService.createCourse(curso);
       return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @PutMapping(value = "/{codigo}")
    public ResponseEntity<Curso> update(@PathVariable int codigo, @RequestBody Curso curso) throws SQLException {
            curso = cursoService.updateCourse(codigo, curso);
            return ResponseEntity.ok().body(curso);
    }

    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable int codigo) throws SQLException{
        cursoService.deleteCourse(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Curso>> get() throws SQLException{
        ArrayList<Curso> cursoList = cursoService.getCourse();
        return ResponseEntity.ok().body(cursoList);
    }
}
