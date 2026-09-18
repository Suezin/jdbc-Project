package dao;

import controller.ControllerConnection;
import model.Planos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public List<Planos> getPlanos() {
        String sql = "SELECT * FROM planostb";

        List<Planos> planosList = new ArrayList<>();
        try (Connection conn = ControllerConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
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
        }
        return planosList;
    }


    public void insertPlano(String name, Double price, int durationDays){
        String sql = "INSERT INTO planostb(name,price,durationdays) VALUES(?,?,?);";

        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2,price);
            statement.setInt(3,durationDays);
            statement.executeUpdate();

        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);

        }
    }

    public boolean searchById(int id) {
        String sql = "SELECT * FROM planostb WHERE id = ?";

        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }catch(SQLException exSql){
            System.out.println("Erro de Sql :" + exSql);

            return false;
        }
    }
    public void deleteById(int id ){
        if(!searchById(id)){
            System.out.println("O número de id passado não existe! Insira um id existente");
        }
        String sql = "DELETE FROM planostb WHERE id = ?";
        try (Connection conn = ControllerConnection.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Plano deletado com sucesso!");
        }catch(SQLException exSql){
            System.out.println("Erro de sql :" + exSql);
        }

    }

}
