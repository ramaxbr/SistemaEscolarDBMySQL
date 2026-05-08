package database;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class sqlConn {
    private static final String url = "jdbc:mysql://localhost:3306/sistema_escolarbd";
    private static final String user = "root";
    private static final String pws = "Senai@134";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, pws);
    }

    public static void testConnection(){
        try(Connection conn = getConnection()){
            System.out.println("A conexão foi estabelecida! " + conn + "\n");
        }catch (SQLException errorSQL){
            System.out.println("Falha na conexão" + errorSQL.getMessage());
            System.out.println("Verifique: ");
            System.out.println("1. MySql não está rodando");
            System.out.println("2. O banco " + url + " nao existe.");
            System.out.println("3. O usuario ou senha não está correto.)");
        }
    }
}
