package Dao;

import Controller.ControllerConnection;
import Model.Aluno;
import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import javax.naming.ldap.Control;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public List<Aluno> getAluno(){ // Listagem de alunos do banco de dados
        String sql = "SELECT * FROM clientstb;";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aluno> alunoList = new ArrayList<>();
        try {
            statement = conn.preparedStatement(sql); // Testa a query
            resultSet = statement.executeQuery(); // Executa a query
            while(resultSet.next()){ // Pega cada linha de resultado da query
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("name"));
                aluno.setDataNasc(resultSet.getDate("dateofbirth"));
                aluno.setCpf(resultSet.getLong("cpf"));
                aluno.setTipoDePlano(resultSet.getString("plain"));
                aluno.setPagamentoPlano(resultSet.getBoolean("payment"));
                aluno.setIdTreino(resultSet.getInt("idtrainnig"));
                alunoList.add(aluno);
            }
        }catch(SQLException exSql) {
            System.out.println("Erro de Sql : " + exSql);
        }finally {
            conn.closeConnection(statement, resultSet);
        }
        return alunoList;

    }

    public void alterAluno(int id, String name){ // Alteração de Alunos
        String sql = "UPDATE clientstb " +
                "SET name = ?" +
                " WHERE id = ?";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        try{
            statement = conn.preparedStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }catch(SQLException exSql) {
            System.out.println("Erro de sql : " + exSql);
        }finally {
            conn.closeConnection(statement);
            }
        }

    public void insertAluno(String name, LocalDate dateofbirth, String cpf, String plain, Boolean payment, int idtrainnig){ // Inserção de aluno no banco de dados
        String sql = "INSERT INTO clientstb(name,dateofbirth,cpf,plain,payment,idtrainnig) " +
                "VALUES(?,?,?,?,?,?);";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.preparedStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, Date.valueOf(dateofbirth));
            statement.setString(3, cpf);
            statement.setString(4, plain);
            statement.setBoolean(5, payment);
            statement.setInt(6, idtrainnig);
            statement.executeUpdate();

        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);
        }finally{
            conn.closeConnection(statement, resultSet);
        }
    }

    public List<Aluno> getAlunoById(String name) {
        String sql = "SELECT * FROM clientstb WHERE name LIKE ?";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aluno> alunoList = new ArrayList<>();
        try {
            statement = conn.preparedStatement(sql);
            statement.setString(1, "%" + name + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setNome(resultSet.getString("name"));
                aluno.setDataNasc(resultSet.getDate("dateofbirth"));
                aluno.setCpf(resultSet.getLong("cpf"));
                aluno.setTipoDePlano(resultSet.getString("plain"));
                aluno.setPagamentoPlano(resultSet.getBoolean("payment"));
                aluno.setIdTreino(resultSet.getInt("idtrainnig"));
                alunoList.add(aluno);
            }

        } catch (SQLException exSql) {
            System.out.println("Erro de sql : " + exSql);
        }finally {
            conn.closeConnection(statement, resultSet);
        }
        return alunoList;
    }

    public void deleteById(int id) {
        String sql= "DELETE FROM clientstb WHERE id = ?";
        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();
        try {
            statement = conn.preparedStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException exSql){
        System.out.println("Erro de sql :" + exSql);
        }finally {
            conn.closeConnection(statement);
        }
    }

    public Boolean findId(int id){
        String sql = "SELECT * FROM clientstb WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ControllerConnection conn = new ControllerConnection();
        boolean result = false;
        try {
            statement = conn.preparedStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int idAluno = resultSet.getInt("id");
                if(idAluno == id){
                    result = true;
                }

            }
        }catch (SQLException exSql){
            System.out.println("Erro de slq :" + exSql);
        }finally{
            conn.closeConnection(statement,resultSet);

        }
        return result;
    }

}

