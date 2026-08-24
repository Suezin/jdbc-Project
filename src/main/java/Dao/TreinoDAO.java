package Dao;

import Controller.ControllerConnection;
import Model.Treino;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {


    public List<Treino> getTrainning(){
        String sql = "SELECT * FROM trainigtb";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Treino> treinoList = new ArrayList<>();
        try{
            statement = conn.preparedStatement(sql);
            resultSet = statement.executeQuery(sql);
                while(resultSet.next()) {
                    Treino treino = new Treino();
                    treino.setId(resultSet.getInt("id"));
                    treino.setTipoDeTreino(resultSet.getString("trainigtype"));
                    treino.setDiasDeTreino(resultSet.getInt("daysoftrainnig"));
                    treino.setQuantidadeDeExercicios(resultSet.getInt("exercisesquantity"));
                    treino.setIdProfessor(resultSet.getInt("idtrainer"));
                    treinoList.add(treino);
                }
        }catch(SQLException exSql){
            System.out.println("Erro de slq : " + exSql);
        }finally {
            conn.closeConnection(statement, resultSet);
        }
        return treinoList;
    }

    public void addTrainning(String tipoDeTreino, int diasDeTreino, int quantidadeDeExercicios, int idProfessor){
        String sql = "INSERT INTO " +
                "trainigtb(trainigtype,exercisesquantity,daysoftrainnig,idtrainer) " +
                "VALUES(?,?,?,?);";
        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();

        try{
            statement = conn.preparedStatement(sql);
            statement.setString(1, tipoDeTreino);
            statement.setInt(2, diasDeTreino);
            statement.setInt(3, quantidadeDeExercicios);
            statement.setInt(4, idProfessor);
            statement.executeUpdate();
            System.out.println("Treino adicionado com sucesso !");
        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);
        }finally {
            conn.closeConnection(statement);
        }

    }
    public void deleteTrainning(int id){
        String sql = "DELETE FROM trainigtb WHERE id = ?";
        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();

        try{
            statement = conn.preparedStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Treino excluído com sucesso !");
        }catch(SQLException exSql){
            System.out.println("Erro de sql :" + exSql);

        }finally{
            conn.closeConnection(statement);
        }
    }
}
