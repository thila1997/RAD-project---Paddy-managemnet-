package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHandler extends Config {

    public Connection databaseLink;
    public Connection getConnection(){


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
        return databaseLink;

    }

}