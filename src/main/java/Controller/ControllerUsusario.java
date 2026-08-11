package Controller;

import Model.Aluno;
import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ControllerUsusario {

    public List<Aluno> getUser(){
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

    public void alterUser(int id, String name){
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
            conn.closeConnection(statement, resultSet);
            List<Aluno> listAluno = new ArrayList<>();
            for(Aluno aluno : listAluno){
                System.out.println(aluno.getNome());
                System.out.println(aluno.getCpf());
                System.out.println(aluno.getDataNasc());
                System.out.println(aluno.getTipoDePlano());
                System.out.println(aluno.getPagamentoPlano());
                listAluno.add(aluno);
            }
        }


    }
}
