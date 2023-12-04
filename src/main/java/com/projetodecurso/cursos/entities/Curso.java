package com.projetodecurso.cursos.entities;

import java.util.Objects;

public class Curso {

    private int codigo;
    private String nome;
    private String descricao;
    private int duracao;

    public Curso() {
    }

    public Curso(int codigo, String nome, String descricao, int duracao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return codigo == curso.codigo && duracao == curso.duracao && Objects.equals(nome, curso.nome) && Objects.equals(descricao, curso.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, descricao, duracao);
    }
}
