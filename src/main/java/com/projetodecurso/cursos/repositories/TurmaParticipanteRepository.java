package com.projetodecurso.cursos.repositories;

import com.projetodecurso.cursos.db.DB;
import com.projetodecurso.cursos.entities.Curso;
import com.projetodecurso.cursos.entities.TurmaParticipante;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class TurmaParticipanteRepository {

    Connection conn = null;
    PreparedStatement st = null;

    public void createParticipatingClass(TurmaParticipante turmaParticipante) {

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO turmaparticipante "
                            + "(turma, funcionario)"
                            + "VALUES "
                            + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, turmaParticipante.getTurma());
            st.setInt(2, turmaParticipante.getFuncionario());


            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    turmaParticipante.setCodigo(id);
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

    public void updateParticipatingClass(TurmaParticipante turmaParticipante) throws SQLException {

        PreparedStatement st = null;

        try {

            if(getParticipatingClassById(turmaParticipante.getCodigo()) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("UPDATE turmaparticipante "
                    + "SET turma = ?, funcionario = ? "
                    + "WHERE "
                    + "codigo = ?"
            );

            st.setInt(1, turmaParticipante.getTurma());
            st.setInt(2, turmaParticipante.getFuncionario());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    public TurmaParticipante getParticipatingClassById(int codigo) {
        PreparedStatement st = null;
        TurmaParticipante turmaParticipante = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM "
                    + "turmaparticipante "
                    + "WHERE "
                    + "codigo = ?"
            );

            st.setInt(1, codigo);
            ResultSet result = st.executeQuery();

            if(result.next()){
                turmaParticipante = new TurmaParticipante();

                int id = result.getInt(1);
                int turma = result.getInt(2);
                int funcionario = result.getInt(3);


                turmaParticipante.setCodigo(id);
                turmaParticipante.setTurma(turma);
                turmaParticipante.setFuncionario(funcionario);

            }
            return turmaParticipante;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteParticipatingClass(int codigo) throws SQLException{

        PreparedStatement st = null;

        try {

            if(getParticipatingClassById(codigo) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("DELETE FROM turmaparticipante "
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

    public ArrayList<TurmaParticipante> getParticipatingClass() throws SQLException{

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM turmaparticipante");

            rs = st.executeQuery();
            ArrayList<TurmaParticipante> turmaParticipantesList = new ArrayList<TurmaParticipante>();

            while (rs.next()){

                TurmaParticipante turmaParticipante = new TurmaParticipante();
                turmaParticipante.setCodigo(rs.getInt("codigo"));
                turmaParticipante.setTurma(rs.getInt("turma"));
                turmaParticipante.setFuncionario(rs.getInt("funcionario"));

                turmaParticipantesList.add(turmaParticipante);
            }

            return turmaParticipantesList;

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
