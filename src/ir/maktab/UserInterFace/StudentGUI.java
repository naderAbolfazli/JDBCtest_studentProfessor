/*
 * Created by JFormDesigner on Mon Nov 27 21:13:06 IRST 2017
 */

package ir.maktab.UserInterFace;

import ir.maktab.managers.StudentManager;
import ir.maktab.models.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author nader abolfazli
 */
public class StudentGUI extends JFrame {
    public StudentGUI() {
        initComponents();
        setVisible(true);
    }

    private void ButtonActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "getAll":
                table1.setModel(StudentManager.getAllAsModel());
                break;
            case "load":

                break;
            case "delete":
                StudentManager.delete(Integer.parseInt(id.getText()));
                break;
            case "edit":

                break;
            case "add":
                Student s = new Student(name.getText(),dept.getText(),Integer.parseInt(profId.getText()));
                StudentManager.add(s);

                break;
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - nader abolfazli
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        getAll = new JButton();
        load = new JButton();
        delete = new JButton();
        edit = new JButton();
        id = new JTextField();
        id.setToolTipText("Id");
        name = new JTextField();
        name.setToolTipText("Name");
        dept = new JTextField();
        profId = new JTextField();
        addButton = new JButton();

        //======== this ========
        setTitle("Student Menu");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
            table1.setModel(StudentManager.getAllAsModel());

            ListSelectionModel select= table1.getSelectionModel();
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            select.addListSelectionListener(e -> {
                id.setText(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 0)));
                name.setText(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 1)));
                dept.setText(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 2)));
                profId.setText(String.valueOf(table1.getValueAt(table1.getSelectedRow(), 3)));
            });
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 20, 300, 195);

        //---- getAll ----
        getAll.setText("get All");
        getAll.setActionCommand("getAll");
        getAll.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(getAll);
        getAll.setBounds(20, 225, 65, getAll.getPreferredSize().height);

        //---- load ----
        load.setText("Load");
        load.setActionCommand("load");
        load.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(load);
        load.setBounds(85, 225, 65, load.getPreferredSize().height);

        //---- delete ----
        delete.setText("Delete");
        delete.setActionCommand("delete");
        delete.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(delete);
        delete.setBounds(150, 225, 65, delete.getPreferredSize().height);

        //---- edit ----
        edit.setText("Edit");
        edit.setActionCommand("edit");
        edit.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(edit);
        edit.setBounds(215, 225, 65, edit.getPreferredSize().height);

        contentPane.add(id);
        id.setBounds(330, 40, 55, id.getPreferredSize().height);
        contentPane.add(name);
        name.setBounds(330, 80, 55, name.getPreferredSize().height);
        contentPane.add(dept);
        dept.setBounds(330, 120, 55, dept.getPreferredSize().height);
        contentPane.add(profId);
        profId.setBounds(330, 160, 55, profId.getPreferredSize().height);

        //---- addButton ----
        addButton.setText("Add");
        addButton.setActionCommand("add");
        addButton.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(addButton);
        addButton.setBounds(335, 215, addButton.getPreferredSize().width, 42);

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - nader abolfazli
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton getAll;
    private JButton load;
    private JButton delete;
    private JButton edit;
    private JTextField id;
    private JTextField name;
    private JTextField dept;
    private JTextField profId;
    private JButton addButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
