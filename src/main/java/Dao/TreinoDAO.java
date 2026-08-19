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


    public List<Treino> getTrain(){
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
}
