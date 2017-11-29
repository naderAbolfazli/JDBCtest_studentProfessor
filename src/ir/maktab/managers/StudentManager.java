package ir.maktab.managers;

import ir.maktab.models.Prof;
import ir.maktab.models.Student;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nader on 11/18/2017.
 */
public class StudentManager extends Manager{

    static {
        TABLE_NAME = "students";
    }

    public static void choose(){}

    public static void add(Student s) {
        init();
        try {
            String sql;
            sql = "INSERT INTO "+TABLE_NAME+" (name , dept, supervisor_id) VALUES ('"
                    + s.getName() + "', '" + s.getDept() + "', '"+s.getSupervisor_id()+"' )";
            stmt.executeUpdate(sql);

            //STEP 6: Clean-up environment
            finalize(stmt, conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAll() {

        init();
        try {
            String sql;
            sql = "select * from "+TABLE_NAME;
            //System.out.print(sql);
            rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("dept");
                int sid = rs.getInt("supervisor_id");

                students.add(new Student(id, name, dept, sid));
            }

            return students;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            finalize(rs, stmt, conn);
        }
        return null;
    }

    public static DefaultTableModel getAllAsModel() {

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
        }finally {
            finalize(rs, stmt, conn);
        }
        return null;
    }

    public static Prof showSupervisor(int id){
        try {
            String sql = "select prof.name, address from "
                    + "students inner join prof on prof.id=students.supervisor_id "
                    + "where students.id = "+id;
            rs = stmt.executeQuery(sql);

            if (rs.next()){
                String name = rs.getString("name");
                String address= rs.getString("address");

                return new Prof(id, name, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            finalize(rs, stmt, conn);
        }
        return null;
    }

}
