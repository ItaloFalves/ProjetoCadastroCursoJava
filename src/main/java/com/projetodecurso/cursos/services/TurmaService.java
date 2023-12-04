package com.projetodecurso.cursos.services;

import com.projetodecurso.cursos.entities.Curso;
import com.projetodecurso.cursos.entities.Turma;
import com.projetodecurso.cursos.repositories.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma createClass(Turma turma){
        this.turmaRepository.createClass(turma);

        return turma;
    }

    public Turma updateCourse(int codigo, Turma turma) throws SQLException {
        if(turma.getCurso() > 0){
            throw new RuntimeException("Não é possivél alterar o curso de uma turma existente!");
        }

        turma.setCodigo(codigo);
        this.turmaRepository.updateClass(turma);

        return turma;
    }

    public void deleteCourse(int codigo) throws SQLException{
        this.turmaRepository.deleteClass(codigo);
    }

    public ArrayList<Turma> getCourse (int codigo) throws SQLException{
        return this.turmaRepository.getClassesbyCourse(codigo);
    }
}
