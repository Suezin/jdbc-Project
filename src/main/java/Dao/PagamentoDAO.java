package Dao;

import Controller.ControllerConnection;
import Model.Pagamentos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public List<Pagamentos> getPagamentos(){
        String sql = "SELECT * FROM pagamentostb";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ControllerConnection conn = new ControllerConnection();
        List<Pagamentos> pagamentosList = new ArrayList<>();
        try{
             Pagamentos pagamentos = new Pagamentos();
             statement = conn.preparedStatement(sql);
             resultSet = statement.executeQuery();
             while(resultSet.next()){
                 pagamentos.setIdAluno(resultSet.getInt("idaluno"));
                 pagamentos.setAmount(resultSet.getDouble("amount"));
                 pagamentos.setDataDePagamento(resultSet.getDate(String.valueOf("paymentdate")));
                 pagamentosList.add(pagamentos);

             }
        }catch(SQLException exSql){
            System.out.println("Erro de sql :" + exSql);
        }finally{
            conn.closeConnection(statement,resultSet);
        }
        return pagamentosList;
    }

    public void insertPagamento(int idAluno, double amount, LocalDate paymentDate){
        String sql = "INSERT INTO pagamentostb(idaluno,amount,paymentdate) VALUES(?,?,?)";
        PreparedStatement statement = null;
        ControllerConnection conn = new ControllerConnection();

        try {
            statement = conn.preparedStatement(sql);
            statement.setInt(1,idAluno);
            statement.setDouble(2,amount);
            statement.setDate(3, Date.valueOf(paymentDate));
            statement.executeUpdate();
        }catch(SQLException exSql){
            System.out.println("Erro de Sql :" + exSql);
        }finally{
            conn.closeConnection(statement  );
        }
    }

}
