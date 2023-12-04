package com.projetodecurso.cursos.services;

import com.projetodecurso.cursos.entities.Curso;
import com.projetodecurso.cursos.entities.TurmaParticipante;
import com.projetodecurso.cursos.repositories.TurmaParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class TurmaParticipanteService {

    @Autowired
    private TurmaParticipanteRepository turmaParticipanteRepository;

    public TurmaParticipante createParticipatingClass(TurmaParticipante turmaParticipante){
        this.turmaParticipanteRepository.createParticipatingClass(turmaParticipante);

        return turmaParticipante;
    }

    public TurmaParticipante updateParticipatingClass(int codigo, TurmaParticipante turmaParticipante) throws SQLException {
        turmaParticipante.setCodigo(codigo);
        this.turmaParticipanteRepository.updateParticipatingClass(turmaParticipante);

        return turmaParticipante;
    }

    public void deleteParticipatingClass(int codigo) throws SQLException{
        this.turmaParticipanteRepository.deleteParticipatingClass(codigo);
    }

    public ArrayList<TurmaParticipante> getParticipatingClass () throws SQLException{
        return this.turmaParticipanteRepository.getParticipatingClass();
    }
}
