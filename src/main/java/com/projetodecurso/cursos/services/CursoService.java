package com.projetodecurso.cursos.services;

import com.projetodecurso.cursos.entities.Curso;
import com.projetodecurso.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso createCourse(Curso curso){
        this.cursoRepository.createCourse(curso);

        return curso;
    }

    public Curso updateCourse(int codigo, Curso curso) throws SQLException {
        curso.setCodigo(codigo);
        this.cursoRepository.updateCourse(curso);

        return curso;
    }

    public void deleteCourse(int codigo) throws SQLException{
        this.cursoRepository.deleteCourse(codigo);
    }

    public ArrayList<Curso> getCourse () throws SQLException{
        return this.cursoRepository.getCursos();
    }


}
