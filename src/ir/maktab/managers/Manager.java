package ir.maktab.managers;

import java.sql.*;

/**
 * Created by nader on 11/19/2017.
 */
public class Manager {
    public static String TABLE_NAME;


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/university";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs;

    static void init() {
        //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void finalize(ResultSet rs, Statement stmt, Connection conn) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void finalize( Statement stmt, Connection conn) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {

        init();
        try {
            String sql;
            sql = "DELETE from " + TABLE_NAME + " where id=" + id;
            stmt.executeUpdate(sql);

            finalize(stmt, conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String name) {

        init();
        try {
            String sql;
            sql = "DELETE from " + TABLE_NAME + " where name='" + name+"'";
            stmt.executeUpdate(sql);

            finalize(stmt, conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int searchByName(String name){
        init();
        try {
            String sql = "select * from "+ TABLE_NAME +" where name ='"+name+"'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                int res= rs.getInt("id");
                finalize(rs, stmt, conn);
                return res;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
