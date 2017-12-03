package ir.maktab.DAO.Student;

import ir.maktab.models.Prof;
import ir.maktab.models.Student;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nader on 12/2/2017.
 */
public interface StudentDAO {

    boolean add(Student s) throws SQLException;

    boolean edit(Student s) throws SQLException;

    List<Student> getAll() throws SQLException;

    DefaultTableModel getAllAsModel() throws SQLException;

    DefaultTableModel getAsModel(Student s) throws SQLException;

    Prof showSupervisor(int id) throws SQLException;

    boolean exist(Student s) throws SQLException;

    int searchByName(String studentName) throws SQLException;

    boolean delete(int stId) throws SQLException;

    boolean delete(String stName) throws SQLException;
}
