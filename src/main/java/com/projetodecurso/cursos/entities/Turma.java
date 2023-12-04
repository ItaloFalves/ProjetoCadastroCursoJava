package com.projetodecurso.cursos.entities;

import java.util.Date;

public class Turma {

    private int codigo;
    private String inicio;
    private String fim;
    private String local;
    private int curso;

    public Turma(){

    }

    public Turma(int codigo, String inicio, String fim, String local, int curso) {
        this.codigo = codigo;
        this.inicio = inicio;
        this.fim = fim;
        this.local = local;
        this.curso = curso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
}
