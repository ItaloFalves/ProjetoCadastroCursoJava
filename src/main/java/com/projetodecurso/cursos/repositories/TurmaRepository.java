package com.projetodecurso.cursos.repositories;

import com.projetodecurso.cursos.db.DB;
import com.projetodecurso.cursos.entities.Turma;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class TurmaRepository {

    Connection conn = null;
    PreparedStatement st = null;
    public void createClass(Turma turma) {

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO turma "
                            + "(inicio, fim, local, curso)"
                            + "VALUES "
                            + "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, turma.getInicio());
            st.setString(2, turma.getFim());
            st.setString(3, turma.getLocal());
            st.setInt(4, turma.getCurso());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    turma.setCodigo(id);
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

    public void updateClass(Turma turma) throws SQLException {

        PreparedStatement st = null;

        try {

            if(getClassById(turma.getCodigo()) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("UPDATE turma "
                    + "SET inicio = ?, fim = ?, local = ? "
                    + "WHERE "
                    + "codigo = ?"
            );

            st.setString(1, turma.getInicio());
            st.setString(2, turma.getFim());
            st.setString(3, turma.getLocal());
            st.setInt(4, turma.getCodigo());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public Turma getClassById(int codigo) {
        PreparedStatement st = null;
        Turma turma = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM "
                    + "turma "
                    + "WHERE "
                    + "codigo = ?"
            );

            st.setInt(1, codigo);
            ResultSet result = st.executeQuery();

            if(result.next()){
                turma = new Turma();
                int id = result.getInt(1);
                String inicio = result.getString(2);
                String fim = result.getString(3);
                String local = result.getString(4);
                int curso = result.getInt(5);

                turma.setCodigo(id);
                turma.setInicio(inicio);
                turma.setFim(fim);
                turma.setLocal(local);
                turma.setCurso(curso);

            }
            return turma;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClass(int codigo) throws SQLException{

        PreparedStatement st = null;

        try {

            if(getClassById(codigo) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("DELETE FROM turma "
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

    public ArrayList<Turma> getClassesbyCourse(int codigo) throws SQLException{

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM turma "
                    +"WHERE curso = ?"
                    );

            st.setInt(1, codigo);

            rs = st.executeQuery();
            ArrayList<Turma> getClass = new ArrayList<Turma>();

            while (rs.next()){

                Turma turma = new Turma();
                turma.setCodigo(rs.getInt("codigo"));
                turma.setInicio(rs.getString("inicio"));
                turma.setFim(rs.getString("fim"));
                turma.setLocal(rs.getString("local"));
                turma.setCurso(rs.getInt("curso"));

                getClass.add(turma);
            }

            return getClass;

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
