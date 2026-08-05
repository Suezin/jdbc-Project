package Controller;

import java.sql.*;

public class ControllerConnection {
    private static final String URL = "jdbc:mysql://127.0.0.0:3306/academia";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;
        public ControllerConnection() {
            try {
                Class.forName(DRIVER); // Nome do driver importado no pom.xml
                conn = DriverManager.getConnection(URL, USER, PASSWORD); //fazendo a conexão com banco de dados
/*      ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM clientes"); // executando um query no banco de dados para pegar todos os dados da tabela clintes
        while(resultSet.next()){ // resultSet pega a primeira linha antes da primeira linha de dados e o next() percorre para a frente para as linhas de dados caso não haja retorna False se houver retorna true e o while continua ate acabar
            System.out.println("Nome :" + resultSet.getString("id")); //.getString ou .getInt é usado para pegar um valor que esta dentro dos dados, como por exemplo o nome que é um valor contido na lista
        }*/
            } catch (ClassNotFoundException exception) {
                System.out.println("Driver do banco de dados não encontrado!");
            } catch (SQLException exSql) {
                System.out.println("Ocorreu um erro com o banco de dados!");

            }
        }

    public PreparedStatement preparedStatement(String sql){
                PreparedStatement comando = null;
                try {
                    comando = conn.prepareStatement(sql);
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

