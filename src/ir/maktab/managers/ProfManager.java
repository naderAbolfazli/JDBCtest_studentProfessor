package ir.maktab.managers;

import ir.maktab.models.Prof;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nader on 11/18/2017.
 */
public class ProfManager extends Manager{

    static {
        TABLE_NAME = "prof";
    }

    public static void choose(){}

    public static void add(Prof p) {

        init();
        try {
            String sql;
            sql = "INSERT INTO " + TABLE_NAME + " (name , address) VALUES ('" + p.getName() + "', '" + p.getAddress() + "' )";
            //System.out.print(sql);
            stmt.executeUpdate(sql);

            //STEP 6: Clean-up environment
            finalize(stmt, conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Prof> getAll() {

        init();
        try {
            String sql;
            sql = "select * from " +TABLE_NAME;
            //System.out.print(sql);
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            List<Prof> professors = new ArrayList<Prof>();
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");

                professors.add(new Prof(id, name, address));
            }
            //STEP 6: Clean-up environment
            finalize(rs, stmt, conn);

            return professors;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
