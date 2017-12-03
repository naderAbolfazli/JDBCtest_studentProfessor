/*
 * Created by JFormDesigner on Tue Nov 28 11:47:03 IRST 2017
 */

package ir.maktab.UserInterFace;

import ir.maktab.DAO.Prof.ProfDAO;
import ir.maktab.DAO.Prof.ProfDAOImpl;
import ir.maktab.models.Prof;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 * @author nader abolfazli
 */
public class ProfGUI extends JFrame {
    private ProfDAO profDAO = new ProfDAOImpl();

    public ProfGUI() {
        try {
            initComponents();
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    new MainGUI();
                    super.windowClosed(e);
                }
            });
            setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ButtonActionPerformed(ActionEvent e) {

        try {
            switch (e.getActionCommand()) {
                case "getAll":
                    table.setModel(profDAO.getAllAsModel());
                    break;
                case "load":
                    if(!id.getText().isEmpty())
                        if(profDAO.exist(new Prof(Integer.parseInt(id.getText())))) {
                            table.setModel(profDAO.getAsModel(new Prof(Integer.parseInt(id.getText()))));
                        }else
                            JOptionPane.showMessageDialog(this, "Not Found!");
                    else
                        JOptionPane.showMessageDialog(this, "Empty Field!");

                    break;
                case "delete":
                    if (!id.getText().isEmpty()) {
                        if (JOptionPane.showConfirmDialog(this, "Are you sure?") == JOptionPane.OK_OPTION) {
                            if (!profDAO.delete(Integer.parseInt(id.getText())))
                                JOptionPane.showMessageDialog(this, "Not Found!");
                            table.setModel(profDAO.getAllAsModel());
                        }
                    } else
                        JOptionPane.showMessageDialog(this, "Empty Field!");
                    break;
                case "edit":
                    if (checkField()>=0) {
                        Prof p = new Prof(Integer.parseInt(id.getText()), name.getText(), address.getText());
                        if(!profDAO.edit(p))
                            JOptionPane.showMessageDialog(this, "Not Found!");
                        table.setModel(profDAO.getAllAsModel());

                    } else
                        JOptionPane.showMessageDialog(this, "Empty Fields!");
                    break;
                case "add":
                    Prof p = null;
                    if (checkField()==1)
                        p = new Prof(Integer.parseInt(id.getText()), name.getText()
                                , address.getText());
                    else if(checkField()==0)
                        p= new Prof(name.getText(), address.getText());

                    if(p==null)
                        JOptionPane.showMessageDialog(this, "Empty Fields!");
                    else
                    if(!profDAO.add(p))
                        JOptionPane.showMessageDialog(this, "Already Exist");
                    table.setModel(profDAO.getAllAsModel());
                    break;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private int checkField() {
        if (!id.getText().isEmpty() && !name.getText().isEmpty() )
            return 1;
        else if (!name.getText().isEmpty() )
            return 0;
        else
            return -1;
    }

    public void selectionChanged(ListSelectionEvent e) {
        if (table.getSelectedRow() >= 0) {
            id.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
            name.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
            address.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
        }
    }

    private void initComponents() throws SQLException{
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
        address = new HintTextField("Address");
        addButton = new JButton();

        //======== this ========
        setTitle("Professors Menu");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            model = profDAO.getAllAsModel();
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
        id.setBounds(330, 40, 55, id.getPreferredSize().height);
        contentPane.add(name);
        name.setBounds(330, 80, 55, name.getPreferredSize().height);
        contentPane.add(address);
        address.setBounds(330, 120, 55, address.getPreferredSize().height);


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
    private JTextField address;
    private JButton addButton;
    private ListSelectionModel select;
    private DefaultTableModel model;


    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
