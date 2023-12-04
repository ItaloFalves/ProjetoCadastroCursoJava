package com.projetodecurso.cursos.services;

import com.projetodecurso.cursos.entities.Funcionario;
import com.projetodecurso.cursos.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario createEmployee(Funcionario funcionario){
        if(funcionario.getStatus() != 0 && funcionario.getStatus() != 1){
            throw new RuntimeException("O campo status aceita apenas o valor 0 ou 1!");
        }

        this.funcionarioRepository.createEmployee(funcionario);

        return funcionario;
    }

    public void deleteEmployee(int codigo) throws SQLException{
        this.funcionarioRepository.deleteEmployee(codigo);
    }

    public ArrayList<Funcionario> getEmployee () throws SQLException{
        return this.funcionarioRepository.getListEmployee();
    }
}
