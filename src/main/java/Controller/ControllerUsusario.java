package Controller;

import Model.Aluno;

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
}
