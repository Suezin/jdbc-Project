package Dao;

import Controller.ControllerConnection;
import Model.Professor;

import javax.naming.ldap.Control;
import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Result;
import java.awt.image.ShortLookupTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
                professor.setCpf(resultSet.getLong("cpf"));
                professor.setDataNasc(resultSet.getDate("dateofbirth"));
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

    public void alterTrainner(int id, String name, int cpf, int dataNasc){
        String sql = "UPDATE trainertb" +
                "SET name = ?," +
                "cpf = ?," +
                "dateofbirth = ?" +
                "WHERE id = ?";
        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();

        try {
            statement = conn.preparedStatement(sql);
            statement.setString(1, name);
            statement.setInt(2,cpf);
            statement.setInt(3,dataNasc);
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println("professor alterado com sucesso");

        }catch(SQLException exSql ){
            System.out.println("Erro de sql : " + exSql);
        }finally{
            conn.closeConnection(statement);
        }
    }

    public void deleteTrainner(int id){
        String sql = "DELETE FROM trainertb WHERE id = ?";
        ControllerConnection conn = new ControllerConnection();
        PreparedStatement statement = null;
        List<Professor> professorList = getTrainner();
        for(Professor prof : professorList){
            System.out.println("Id : " + prof.getId());
            System.out.println("Nome : " + prof.getNome());
            System.out.println("Cpf : " + prof.getCpf());
            System.out.println("Data de nascimento : " + prof.getDataNasc());
            System.out.println("\n");
        }
        try {
            statement = conn.preparedStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException exSql){
            System.out.println("Erro de sql : " + exSql);
        }finally{
            conn.closeConnection(statement);
        }
    }

}
