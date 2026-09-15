package Dao;

import Controller.ControllerConnection;
import Model.Planos;
import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public List<Planos> getPlanos() {
        String sql = "SELECT * FROM planostb";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ControllerConnection conn = new ControllerConnection();
        List<Planos> planosList = new ArrayList<>();
        try {
            statement = conn.preparedStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Planos plano = new Planos();
                plano.setId(resultSet.getInt("id"));
                plano.setName(resultSet.getString("name"));
                plano.setPreco(resultSet.getDouble("price"));
                plano.setDiasDoPlano(resultSet.getInt("durationdays"));
                planosList.add(plano);
            }
        }catch(SQLException exSql){
            System.out.println("Erro de sql :" + exSql);

        }finally{
            conn.closeConnection(statement, resultSet);
        }
        return planosList;
    }


    public void insertPlano(String name, Double price, int durationDays){

        String sql = "INSERT INTO planostb(name,price,durationdays) VALUES(?,?,?);";

        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();

        try {
            statement = conn.preparedStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2,price);
            statement.setInt(3,durationDays);
            statement.executeUpdate();

        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);

        }finally{
            conn.closeConnection(statement);
        }
    }

    public boolean searchById(int id) {
        String slq = "SELECT * FROM planostb WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ControllerConnection conn = new ControllerConnection();
        try{
            return resultSet.next();
        }catch(SQLException exSql){
            System.out.println("Erro de Sql :" + exSql);

            return false;

        }finally{
            conn.closeConnection(statement,resultSet);
        }

    }
    public void deleteById(int id ){
        if(!searchById(id)){
            System.out.println("O número de id passado não existe! Insira um id existente");
        }
        String sql = "DELETE FROM planostb WHERE id = ?";

        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();
        try {
            statement = conn.preparedStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Plano deletado com sucesso!");
        }catch(SQLException exSql){
            System.out.println("Erro de sql :" + exSql);
        }

    }

}
