package ir.maktab.DAO;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 * Created by nader on 11/19/2017.
 */
public class Manager {
    protected String TABLE_NAME;


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/university";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    protected static Connection conn = null;
    protected static Statement stmt = null;
    protected static ResultSet rs;

    protected void init() {
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

    protected void finalize(ResultSet rs, Statement stmt, Connection conn) {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void finalize(Statement stmt, Connection conn) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean delete(int id) throws SQLException {
        init();
        String sql;
        sql = "DELETE from " + TABLE_NAME + " where id=" + id;
        int status = stmt.executeUpdate(sql);
        finalize(stmt, conn);

        return status > 0;
    }

    public boolean delete(String name) throws SQLException {

        init();
        String sql;
        sql = "DELETE from " + TABLE_NAME + " where name='" + name + "'";
        int status = stmt.executeUpdate(sql);
        finalize(stmt, conn);

        return status > 0;
    }

    public int searchByName(String name) throws SQLException {
        init();
        String sql = "select * from " + TABLE_NAME + " where name ='" + name + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            int res = rs.getInt("id");
            finalize(rs, stmt, conn);
            return res;
        }

        finalize(rs, stmt, conn);
        return 0;
    }

    /*public static TableModel getAsModel(int i) {
        init();
        try {
            String sql;
            sql = "select * from " + TABLE_NAME;
            //System.out.print(sql);
            rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name", "Dept", "prof ID"}, 0);
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("dept");
                int sid = rs.getInt("supervisor_id");
                model.addRow(new Object[]{id, name, dept, sid});
            }
            return model;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalize(rs, stmt, conn);
        }
        return null;
    }*/

    public Object get(int i) {
        init();
        try {
            String sql = String.format("select * from %s");
            rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name"}, 0);
            if (rs.next())
                return new Object[]{rs.getInt("id"), rs.getString("name")};
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalize(rs, stmt, conn);
        }
        return null;
    }

    public boolean exist(int id) {
        init();
        try {
            String sql;
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE id =" + id;
            rs = stmt.executeQuery(sql);

            if (rs.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalize(rs, stmt, conn);
        }
        return false;
    }
}