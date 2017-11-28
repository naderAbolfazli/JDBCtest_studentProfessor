/*
 * Created by JFormDesigner on Mon Nov 27 21:07:27 IRST 2017
 */

package ir.maktab.UserInterFace;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author nader abolfazli
 */
public class MainGUI extends JFrame {

    public MainGUI() {
        initComponents();
        setVisible(true);
    }

    public static void computePrefSize(Container contentPane){ // compute preferred size
        Dimension preferredSize = new Dimension();
        for(int i = 0; i < contentPane.getComponentCount(); i++) {
            Rectangle bounds = contentPane.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = contentPane.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        contentPane.setMinimumSize(preferredSize);
        contentPane.setPreferredSize(preferredSize);
    }

    private void ButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (e.getActionCommand().equals("student"))
            new StudentGUI();
        else if (e.getActionCommand().equals("prof"))
            new ProfGUI();

    }

    private void studentButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - nader abolfazli
        studentButton = new JButton();
        profButton = new JButton();

        //======== this ========
        setTitle("Main Menu");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- studentButton ----
        studentButton.setText("Students");
        studentButton.addActionListener(e -> studentButtonActionPerformed(e));
        contentPane.add(studentButton);
        studentButton.setBounds(35, 20, 85, studentButton.getPreferredSize().height);

        //---- profButton ----
        profButton.setText("Professors");
        contentPane.add(profButton);
        profButton.setBounds(new Rectangle(new Point(135, 20), profButton.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(255, 100));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - nader abolfazli
    private JButton studentButton;
    private JButton profButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
