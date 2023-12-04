package com.projetodecurso.cursos.controllers;

import com.projetodecurso.cursos.entities.Funcionario;
import com.projetodecurso.cursos.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> insert(@RequestBody Funcionario funcionario){
        funcionario = funcionarioService.createEmployee(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }


    @DeleteMapping(value = "/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable int codigo) throws SQLException{
        funcionarioService.deleteEmployee(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Funcionario>> get() throws SQLException{
        ArrayList<Funcionario> employeeList = funcionarioService.getEmployee();
        return ResponseEntity.ok().body(employeeList);
    }

}
