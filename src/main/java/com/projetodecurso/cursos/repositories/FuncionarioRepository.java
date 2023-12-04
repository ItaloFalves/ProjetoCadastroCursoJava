package com.projetodecurso.cursos.repositories;

import com.projetodecurso.cursos.db.DB;
import com.projetodecurso.cursos.entities.Funcionario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class FuncionarioRepository {

    Connection conn = null;
    PreparedStatement st = null;

    public void createEmployee(Funcionario funcionario) {

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO funcionario "
                            + "(nome, cpf , nascimento, cargo, admissao, status)"
                            + "VALUES "
                            + "(?, ?, ?, ? , ? , ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, funcionario.getNome());
            st.setString(2, funcionario.getCpf());
            st.setString(3, funcionario.getNascimento());
            st.setString(4, funcionario.getCargo());
            st.setString(5, funcionario.getAdmissao());
            st.setInt(6, funcionario.getStatus());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    funcionario.setCodigo(id);
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

    public void deleteEmployee(int codigo) throws SQLException{

        PreparedStatement st = null;

        try {

            if(getEmployeeById(codigo) == null){
                throw new SQLException("Código não encontrado!");
            }

            conn = DB.getConnection();
            st = conn.prepareStatement("DELETE FROM funcionario "
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

    public Funcionario getEmployeeById(int codigo) {
        PreparedStatement st = null;
        Funcionario funcionario = null;

        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM "
                    + "funcionario "
                    + "WHERE "
                    + "codigo = ?"
            );

            st.setInt(1, codigo);
            ResultSet result = st.executeQuery();

            if(result.next()){
                funcionario = new Funcionario();
                int id = result.getInt(1);
                String nome = result.getString(2);
                String cpf = result.getString(3);
                String nascimento = result.getString(4);
                String cargo = result.getString(5);
                String admissao = result.getString(6);
                short status = result.getShort(7);

                funcionario.setCodigo(id);
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setNascimento(nascimento);
                funcionario.setCargo(cargo);
                funcionario.setAdmissao(admissao);
                funcionario.setStatus(status);

            }
            return funcionario;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Funcionario> getListEmployee() throws SQLException{

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT * FROM funcionario WHERE status = 1");

            rs = st.executeQuery();
            ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();

            while (rs.next()){

                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("codigo"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNascimento(rs.getString("nascimento"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setAdmissao(rs.getString("admissao"));
                funcionario.setStatus(rs.getShort("status"));

               listFuncionario.add(funcionario);
            }

            return listFuncionario;

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

}
