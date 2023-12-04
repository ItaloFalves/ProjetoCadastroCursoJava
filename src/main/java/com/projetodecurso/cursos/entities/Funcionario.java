package com.projetodecurso.cursos.entities;

import java.util.Objects;

public class Funcionario {

    private int codigo;
    private String nome;
    private String cpf;
    private String nascimento;
    private String cargo;
    private String admissao;
    private int status;

    public Funcionario(){

    }

    public Funcionario(int codigo, String nome, String cpf, String nascimento, String cargo, String admissao, short status) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.admissao = admissao;
        this.status = status;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAdmissao() {
        return admissao;
    }

    public void setAdmissao(String admissao) {
        this.admissao = admissao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return codigo == that.codigo && status == that.status && Objects.equals(nome, that.nome) && Objects.equals(cpf, that.cpf) && Objects.equals(nascimento, that.nascimento) && Objects.equals(cargo, that.cargo) && Objects.equals(admissao, that.admissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, cpf, nascimento, cargo, admissao, status);
    }
}
