package dao;

import controller.ControllerConnection;
import model.Pagamentos;

import javax.print.DocFlavor;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public List<Pagamentos> getPagamentos(){
        String sql = "SELECT * FROM pagamentostb";
        List<Pagamentos> pagamentosList = new ArrayList<>();
        try(Connection conn = ControllerConnection.getConnection()){

             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
             while(resultSet.next()){
                 Pagamentos pagamentos = new Pagamentos();
                 pagamentos.setIdAluno(resultSet.getInt("idaluno"));
                 pagamentos.setAmount(resultSet.getDouble("amount"));
                 pagamentos.setDataDePagamento(resultSet.getDate("paymentdate"));
                 pagamentosList.add(pagamentos);

             }
        }catch(SQLException exSql){
            System.out.println("Erro de sql :" + exSql);
        }
        return pagamentosList;
    }

    public void insertPagamento(int idAluno, double amount, Date paymentDate){
        String sql = "INSERT INTO pagamentostb(idaluno,amount,paymentdate) VALUES(?,?,?)";

        try (Connection conn = ControllerConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,idAluno);
            statement.setDouble(2,amount);
            statement.setDate(3, paymentDate);
            statement.executeUpdate();
        }catch(SQLException exSql){
            System.out.println("Erro de Sql :" + exSql);
        }
    }

}
