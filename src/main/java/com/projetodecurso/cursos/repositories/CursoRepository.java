package com.projetodecurso.cursos.repositories;

import com.projetodecurso.cursos.db.DB;
import com.projetodecurso.cursos.db.DbException;
import com.projetodecurso.cursos.entities.Curso;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class CursoRepository {

    Connection conn = null;
    PreparedStatement st = null;

    public void createCourse(Curso curso) {

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO curso "
                            + "(nome, descricao , duracao)"
                            + "VALUES "
                            + "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, curso.getNome());
            st.setString(2, curso.getDescricao());
            st.setInt(3, curso.getDuracao());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    curso.setCodigo(id);
                    System.out.println("Done! Código = " + id);
                }
                DB.closeResultSet(rs);
            } else {
                System.out.println("No rown affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public void updateCourse(Curso curso) throws SQLException {

        PreparedStatement st = null;

        try {

            if(getCourseById(curso.getCodigo()) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("UPDATE curso "
                    + "SET nome = ?, descricao = ?, duracao = ? "
                    + "WHERE "
                    + "codigo = ?"
            );

            st.setString(1, curso.getNome());
            st.setString(2, curso.getDescricao());
            st.setInt(3, curso.getDuracao());
            st.setInt(4, curso.getCodigo());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public Curso getCourseById(int codigo) {
        PreparedStatement st = null;
        Curso curso = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM "
                    + "curso "
                    + "WHERE "
                    + "codigo = ?"
            );

           st.setInt(1, codigo);
           ResultSet result = st.executeQuery();

           if(result.next()){
                curso = new Curso();
               int id = result.getInt(1);
               String nome = result.getString(2);
               String descricao = result.getString(3);
               int duracao = result.getInt(4);

               curso.setCodigo(id);
               curso.setNome(nome);
               curso.setDescricao(descricao);
               curso.setDuracao(duracao);
           }
           return curso;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCourse(int codigo) throws SQLException{

        PreparedStatement st = null;

        try {

            if(getCourseById(codigo) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("DELETE FROM curso "
                    + "WHERE "
                    + "codigo = ?");

            st.setInt(1, codigo);

            st.executeUpdate();

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public ArrayList<Curso> getCursos() throws SQLException{

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM curso");

            rs = st.executeQuery();
            ArrayList<Curso> cursoList = new ArrayList<Curso>();

            while (rs.next()){

                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setNome(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDuracao(rs.getInt("duracao"));

                cursoList.add(curso);
            }

            return cursoList;

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}

