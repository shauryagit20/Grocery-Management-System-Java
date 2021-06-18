package Grocerry_Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Display {
    DefaultTableModel model;
    public void Display_Rows() throws ClassNotFoundException, SQLException {
        JFrame f =  new JFrame();
        f.setVisible(true);
        f.setLayout(null);
        f.setSize(600,450);
        JLabel l1 = new JLabel("Display");
        l1.setFont(new Font("Verdana", Font.PLAIN, 28));
        l1.setBounds(240, 20, 350, 40);
        f.add(l1);
        JTable tb = new JTable();
        JScrollPane sb = new JScrollPane(tb);
        sb.setBounds(30, 100, 400, 200);
        model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Cost");
        model.addColumn("quantity");
        tb.setModel(model);
        Connection con =  Connect.CreateCon();
        f.add(sb);
        String q = "Select * from Inventory";
        Statement st =  con.createStatement();
        ResultSet set  =  st.executeQuery(q);
        JButton back = new JButton("Main Menu");
        back.setBounds(400,370,200,50);
        back.setBackground(Color.red);
        f.add(back);
        while (set.next())
        {
            String[] r  =  new String[3];
            String item_name =  set.getString(1);
            int cost  = set.getInt(2);
            int quantinty =  set.getInt(3);
            r[0] =  item_name;
            r[1] =  Integer.toString(cost);
            r[2] =  Integer.toString(quantinty);
            model.addRow(r);
        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                try {
                    main.Option_List();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
//        while (set.next())
//        {
//            String item_name =  set.getString(1);
//            int cost  = set.getInt(2);
//            int quantinty =  set.getInt(3);
//            System.out.println("Item name -  " +  item_name);
//            System.out.println("Item Cost - " +  cost);
//            System.out.println("Quantitiy - " +  quantinty);
//            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//
//        }
//
//        main.Option_List();
//
  }
}
