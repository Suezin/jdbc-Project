package dao;

import com.mysql.cj.protocol.a.SqlDateValueEncoder;
import controller.ControllerConnection;
import model.Aluno;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> getAluno(){ // Listagem de alunos do banco de dados
        String sql = "SELECT * FROM alunostb;";
        List<Aluno> alunoList = new ArrayList<>();
        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){ // Pega cada linha de resultado da query
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("name"));
                aluno.setDataNasc(resultSet.getDate("dateofbirth"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setIdPlano(resultSet.getInt("idplain"));
                aluno.setProximoPagamento(resultSet.getDate("nextpayment"));
                alunoList.add(aluno);
            }
        }catch(SQLException exSql) {
            System.out.println("Erro de Sql : " + exSql);
        }
        return alunoList;

    }

    public void alterAluno(int id, String name){ // Alteração de Alunos
        String sql = "UPDATE alunostb " +
                "SET name = ?" +
                " WHERE id = ?";

        try(Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }catch(SQLException exSql) {
            System.out.println("Erro de sql : " + exSql);
        }
    }

    public void insertAluno(String name, LocalDate dateofbirth, String cpf, int idplain
    ) throws SQLException { // Inserção de aluno no banco de dados
        PlanoDAO plano = new PlanoDAO();
        int quantidadeDeDias = plano.getDaysById(idplain);
        String sql = "INSERT INTO alunostb(name,dateofbirth,cpf,idplain,nextpayment) " +
                "VALUES(?,?,?,?,?);";
        LocalDate nextpayment = LocalDate.now().plusDays(quantidadeDeDias);

        try (Connection conn = ControllerConnection.getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                statement.setDate(2, Date.valueOf(dateofbirth));
                statement.setString(3, cpf);
                statement.setInt(4, idplain);
                statement.setDate(5, Date.valueOf(nextpayment));
                statement.executeUpdate();

                int idGerado;
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        PagamentoDAO pagamentoDAO = new PagamentoDAO();
                        pagamentoDAO.insertPagamento(conn, rs.getInt(1), idplain);

                    } else {
                        throw new SQLException("Id do aluno inserido não obtido");
                    }
                }
                conn.commit();

            }catch (SQLException exSql){
                conn.rollback();
                throw exSql;
            }
        }
    }

    public List<Aluno> getAlunoByName(String name) {
        String sql = "SELECT * FROM alunostb WHERE name LIKE ?";
        List<Aluno> alunoList = new ArrayList<>();
        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setNome(resultSet.getString("name"));
                aluno.setDataNasc(resultSet.getDate("dateofbirth"));
                aluno.setCpf(resultSet.getString("cpf"));
                aluno.setIdPlano(resultSet.getInt("idplain"));
                aluno.setProximoPagamento(resultSet.getDate(String.valueOf("nextpayment")));
                alunoList.add(aluno);
            }

        } catch (SQLException exSql) {
            System.out.println("Erro de sql : " + exSql);
        }
        return alunoList;
    }

    public void deleteById(int id) {
        String sql= "DELETE FROM alunostb WHERE id = ?";

        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException exSql){
        System.out.println("Erro de sql :" + exSql);
        }
    }

    public Boolean findId(int id){
        String sql = "SELECT * FROM alunostb WHERE id = ?";
        boolean result = false;
        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int idAluno = resultSet.getInt("id");
                if(idAluno == id){
                    result = true;
                }

            }
        }catch (SQLException exSql){
            System.out.println("Erro de slq :" + exSql);
        }
        return result;
    }

}

