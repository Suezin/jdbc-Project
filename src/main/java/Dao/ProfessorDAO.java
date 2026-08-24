package Dao;

import Controller.ControllerConnection;
import Model.Professor;

import javax.naming.ldap.Control;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {


    public List<Professor> getTrainner(){
        String sql = "SELECT * FROM trainertb;";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ControllerConnection conn = new ControllerConnection();
        List<Professor> professorList = new ArrayList<>();
        try{
            statement = conn.preparedStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Professor professor = new Professor();
                professor.setNome(resultSet.getString("name"));
                professor.setCpf(resultSet.getInt("cpf"));
                professor.setDataNasc(resultSet.getInt("dateofbirth"));
                professorList.add(professor);
            }

        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);
        }finally{
            conn.closeConnection(statement,resultSet);
        }
        return professorList;
    }


    public void addTrainner(String name, int cpf, int dataNasc){
        String sql = "INSERT INTO trainertb(name, cpf, dateofbirth) " +
                "VALUES(?,?,?);";
        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();

        try {
            statement = conn.preparedStatement(sql);
            statement.setString(1, name);
            statement.setInt(2,cpf);
            statement.setInt(3, dataNasc);
            statement.executeUpdate();
            System.out.println("Professor adicionado com sucesso !");
        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);

        }finally{
            conn.closeConnection(statement);
        }
    }

}
