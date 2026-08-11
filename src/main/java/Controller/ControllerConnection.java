package Controller;

import java.sql.*;

public class ControllerConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/academia";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection conn;
        public ControllerConnection() {
            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD); //fazendo a conexão com banco de dados
            } catch (SQLException exSql) {
                System.out.println("Ocorreu um erro com o banco de dados!" + exSql);

            }
        }

    public PreparedStatement preparedStatement(String sql){
                PreparedStatement comando = null;
                try {
                    comando =conn.prepareStatement(sql);
                }catch(SQLException exSql){
                    System.out.println("Erro de query sql" + exSql.toString());
                }
                return comando;
    }

    public void closeConnection(){
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException exSQL){
                System.out.println("Erro de sql : " + exSQL);
            }
    }

    public void closeConnection(PreparedStatement comando){
            closeConnection();
            try {
                if (comando != null) {
                    comando.close();
                }
            }catch(SQLException exSQL){
                System.out.println("Erro de sql: "+ exSQL);
            }

            }


    public void closeConnection(PreparedStatement comando, ResultSet resultSet){
        closeConnection(comando);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        }catch(SQLException exSQL){
            System.out.println("Erro de sql: " + exSQL);
        }
    }

}

