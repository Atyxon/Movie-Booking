package sample;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection
{
    public  Connection databaseLink;
    public  Connection getConnection()
    {
        String databaseName = "movie_booking";
        String databaseUser = "root";
        String databasePassword = "Lubiejajecznice12";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
