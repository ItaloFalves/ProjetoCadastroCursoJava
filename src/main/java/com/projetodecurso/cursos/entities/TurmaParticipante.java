package com.projetodecurso.cursos.entities;

import java.util.Objects;

public class TurmaParticipante {

    private int codigo;
    private int turma;
    private int funcionario;

    public TurmaParticipante(){

    }

    public TurmaParticipante(int codigo, int turma, int funcionario) {
        this.codigo = codigo;
        this.turma = turma;
        this.funcionario = funcionario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurmaParticipante that = (TurmaParticipante) o;
        return codigo == that.codigo && turma == that.turma && funcionario == that.funcionario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, turma, funcionario);
    }
}
