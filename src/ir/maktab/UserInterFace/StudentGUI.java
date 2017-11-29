/*
 * Created by JFormDesigner on Mon Nov 27 21:13:06 IRST 2017
 */

package ir.maktab.UserInterFace;

import ir.maktab.managers.StudentManager;
import ir.maktab.models.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 * @author nader abolfazli
 */
public class StudentGUI extends JFrame {

    public StudentGUI() {
        initComponents();
        setVisible(true);
    }

    private void ButtonActionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "getAll":
                table.setModel(StudentManager.getAllAsModel());
                break;
            case "load":

                break;
            case "delete":
                if (!id.getText().isEmpty()) {
                    if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
                        StudentManager.delete(Integer.parseInt(id.getText()));
                        table.setModel(StudentManager.getAllAsModel());
                    }
                } else
                    JOptionPane.showMessageDialog(this, "Id not determined or Exist!");
                break;
            case "edit":
                if (!id.getText().isEmpty()) {
                    //TODO//StudentManager.edit(Integer.parseInt(id.getText()));
                    table.setModel(StudentManager.getAllAsModel());

                } else
                    JOptionPane.showMessageDialog(this, "Id not determined or Exist!");
                break;
            case "add":
                Student s = new Student(Integer.parseInt(id.getText()), name.getText(), dept.getText(), Integer.parseInt(profId.getText()));
                StudentManager.add(s);
                table.setModel(StudentManager.getAllAsModel());
                break;
        }

    }

    private void removeModelRow(int id) {
        for (int i = model.getRowCount() - 1; i >= 0; i--)
            if (model.getValueAt(i, 0).equals(id)) {
                model.removeRow(i);
                break;
            }
    }

    public void selectionChanged(ListSelectionEvent e) {
        if (table.getSelectedRow() >= 0) {
            lastSelectedRow = table.getSelectedRow();
            id.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
            name.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
            dept.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
            profId.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - nader abolfazli
        scrollPane1 = new JScrollPane();
        table = new JTable();
        getAll = new JButton();
        load = new JButton();
        delete = new JButton();
        edit = new JButton();
        id = new HintTextField("id");
        name = new HintTextField("Name");
        dept = new HintTextField("Dept");
        profId = new HintTextField("Prof Id");
        addButton = new JButton();

        //======== this ========
        setTitle("Student Menu");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            model = StudentManager.getAllAsModel();
            scrollPane1.setViewportView(table);
            table.setModel(model);

            select = table.getSelectionModel();
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            select.addListSelectionListener(e -> selectionChanged(e));
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 20, 300, 195);

        //---- getAll ----
        getAll.setText("get All");
        getAll.setActionCommand("getAll");
        getAll.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(getAll);
        getAll.setBounds(20, 225, 75, getAll.getPreferredSize().height);

        //---- load ----
        load.setText("Load");
        load.setActionCommand("load");
        load.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(load);
        load.setBounds(95, 225, 75, load.getPreferredSize().height);

        //---- delete ----
        delete.setText("Delete");
        delete.setActionCommand("delete");
        delete.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(delete);
        delete.setBounds(170, 225, 75, delete.getPreferredSize().height);

        //---- edit ----
        edit.setText("Edit");
        edit.setActionCommand("edit");
        edit.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(edit);
        edit.setBounds(245, 225, 75, edit.getPreferredSize().height);

        contentPane.add(id);
        //id.setToolTipText("id");
        id.setBounds(330, 40, 55, id.getPreferredSize().height);
        contentPane.add(name);
        //name.setToolTipText("Name");
        name.setBounds(330, 80, 55, name.getPreferredSize().height);
        contentPane.add(dept);
        //dept.setToolTipText("Department");
        dept.setBounds(330, 120, 55, dept.getPreferredSize().height);
        contentPane.add(profId);
        //profId.setToolTipText("superVisor Id");
        profId.setBounds(330, 160, 55, profId.getPreferredSize().height);

        //---- addButton ----
        addButton.setText("Add");
        addButton.setActionCommand("add");
        addButton.addActionListener(e -> ButtonActionPerformed(e));
        contentPane.add(addButton);
        addButton.setBounds(330, 215, addButton.getPreferredSize().width, 42);

        contentPane.setPreferredSize(new Dimension(400, 270));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - nader abolfazli
    private JScrollPane scrollPane1;
    private JTable table;
    private JButton getAll;
    private JButton load;
    private JButton delete;
    private JButton edit;
    private JTextField id;
    private JTextField name;
    private JTextField dept;
    private JTextField profId;
    private JButton addButton;
    private ListSelectionModel select;
    private DefaultTableModel model;
    private int lastSelectedRow;


    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

