package Controller;

import Model.Aluno;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControllerAluno {

    public List<Aluno> getAluno(){
        String sql = "SELECT * FROM clientstb;";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Aluno> alunoList = new ArrayList<>();
        try {
            statement = conn.preparedStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Aluno aluno = new Aluno();

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

    public void alterAluno(int id, String name){
        String sql = "UPDATE clientstb " +
                "SET name = ?" +
                " WHERE id = ?";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
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




    public void insertAluno(String name, LocalDate dateofbirth, String cpf, String plain, Boolean payment, int idtrainnig){
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
}

