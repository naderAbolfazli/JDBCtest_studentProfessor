package ir.maktab.DAO.Prof;

import ir.maktab.DAO.Manager;
import ir.maktab.models.Prof;
import ir.maktab.models.Student;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nader on 11/18/2017.
 */
public class ProfDAOImpl extends Manager implements ProfDAO {

    {
        TABLE_NAME = "prof";
    }

    public boolean add(Prof p) throws SQLException {
        if (exist(p))
            return false;

        init();
        String sql;
        if (p.getId() == null)
            sql = "INSERT INTO " + TABLE_NAME + " (name , address) VALUES ('" + p.getName() + "', '" + p.getAddress() + "' )";
        else
            sql = "INSERT INTO " + TABLE_NAME + " values ('" + p.getId() + "','" + p.getName() + "','" + p.getAddress() + "')";
        stmt.executeUpdate(sql);
        finalize(stmt, conn);

        return true;
    }

    public boolean edit(Prof p) throws SQLException {
        if (!exist(p.getId()))
            return false;

        delete(p.getId());
        return add(p);
    }

    public List<Prof> getAll() throws SQLException {

        init();
        String sql;
        sql = "select * from " + TABLE_NAME;
        ResultSet rs = stmt.executeQuery(sql);

        //STEP 5: Extract data from result set
        List<Prof> professors = new ArrayList<>();
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
    }

    @Override
    public DefaultTableModel getAllAsModel() throws SQLException {
        init();
        String sql;
        sql = "select * from " + TABLE_NAME;
        rs = stmt.executeQuery(sql);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name", "Address"}, 0);
        while (rs.next()) {
            //Retrieve by column name
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            model.addRow(new Object[]{id, name, address});
        }
        finalize(rs, stmt, conn);
        return model;
    }

    @Override
    public DefaultTableModel getAsModel(Prof p) throws SQLException {
        init();
        String sql = "select * from " + TABLE_NAME + " where id=" + p.getId();
        rs = stmt.executeQuery(sql);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Name", "Address"}, 0);
        if (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("address")});
        }
        finalize(rs, stmt, conn);
        return model;
    }

    @Override
    public List<Student> showStudents(int id) {
        return null;
    }

    public boolean exist(Prof p) throws SQLException {
        if (p.getId() == null)
            return exist(p.getName(), p.getAddress());
        else
            return exist(p.getId(), p.getName(), p.getAddress());
    }

    private boolean exist(int id, String name, String address) throws SQLException {
        init();
        try {
            String sql;
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE id ='" + id + "' OR (name='" + name + "' AND address='" + address + "')";
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

    private boolean exist(String name, String address) throws SQLException {
        init();
        try {
            String sql;
            sql = "SELECT * FROM " + TABLE_NAME + " WHERE name='" + name + "' AND address='" + address + "'";
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
