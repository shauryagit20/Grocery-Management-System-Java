package Grocerry_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

public class Update {
    public static void Menu() throws SQLException, ClassNotFoundException {
        JFrame f =  new JFrame();
        JLabel l1 = new JLabel("Welcome to the grocery");
        l1.setFont(new Font("Verdana", Font.PLAIN, 28));
        l1.setBounds(120, 20, 350, 40);
        f.setSize(600,450);
        f.setLayout(null);
        f.setVisible(true);
        System.out.println("");
        JButton add = new JButton();
        JLabel l = new JLabel("Please select your choice");
        l.setFont(new Font("Verdana", Font.PLAIN, 18));
        l.setBounds(150, 20, 350, 40);
        f.add(l);
        JButton AddB =  new JButton("Add");
        AddB.setBounds(220,100 , 100,30);
        JButton DeleteB =  new JButton("Delete");
        DeleteB.setBounds(220,140 , 100,30);
        f.add(DeleteB);
        f.add(AddB);
        AddB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                Update x =  new Update();
                x.add_ele();
            }
        });
        JButton back = new JButton("Main menu");
        back.setBounds(400,370,200,50);
        back.setBackground(Color.red);
        f.add(back);
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
        DeleteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                Update x =  new Update();
                try {
                    x.del_ele();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


    }
    public void del_ele() throws ClassNotFoundException, SQLException {
        JFrame f =  new JFrame();
        f.setSize(600,450);
        f.setLayout(null);
        f.setVisible(true);
        JLabel l1 = new JLabel("Add item");
        l1.setFont(new Font("Verdana", Font.PLAIN, 28));
        l1.setBounds(240, 20, 350, 40);
        f.add(l1);
        JButton jb =  new JButton("Delete Items");
        JLabel l = new JLabel("Please enter the name of the items");
        l.setBounds(30, 90, 300, 50);
        JTextField tf = new JTextField();
        tf.setBounds(300, 110, 200, 20);
        jb.setBounds(200,250,140,40);
        JLabel l4  =  new JLabel("Status:  ");
        f.add(jb);
        f.add(tf);
        f.add(l);
        l4.setBounds(30,300,300,50);
        f.add(l4);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item_delete =  tf.getText();
                tf.setText("");
                try {
                    Connection con = Connect.CreateCon();
                    String q1 = "DELETE FROM Inventory WHERE Item_name =  ? ";
                    PreparedStatement pstmt = con.prepareStatement(q1);
                    pstmt.setString(1, item_delete);
                    pstmt.executeUpdate();
                    System.out.println("Deleted " + item_delete + " form the inventory");
                    l4.setText("Status: Item deleted");

                }
                catch (ClassNotFoundException | SQLException k)
                {
                    l4.setText("Not able to connect to the database");
                }
            }
        });
        JButton back = new JButton("Main menu");
        back.setBounds(400,370,200,50);
        back.setBackground(Color.red);
        f.add(back);
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


    }
    public void add_ele()
    {
        String item_name;
        JFrame f =  new JFrame();
        f.setSize(600,450);
        f.setLayout(null);
        f.setVisible(true);
        JLabel l1 = new JLabel("Add item");
        l1.setFont(new Font("Verdana", Font.PLAIN, 28));
        l1.setBounds(240, 20, 350, 40);
        f.add(l1);
        JButton jb =  new JButton("Add");
        JLabel l = new JLabel("Please enter the name of the items");
        JLabel l2  =  new JLabel("Please enter the number of items ");
        JLabel l3  =  new JLabel("Please enter the cost per item ");
        JLabel l4  =  new JLabel("Status:  ");
        l.setBounds(30, 90, 300, 50);
        l2.setBounds(30, 135, 300, 50);
        l3.setBounds(30, 175, 300, 50);
        l4.setBounds(30,300,300,50);
        f.add(l);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        JTextField tf = new JTextField();
        JTextField tf2 = new JTextField();
        JTextField tf3 = new JTextField();
        tf2.setBounds(300, 150, 200, 20);
        tf.setBounds(300, 110, 200, 20);
        tf3.setBounds(300, 190, 200, 20);
        f.add(tf2);
        f.add(tf3);
        f.add(tf);
        JButton Add_b=  new JButton("Add");
        Add_b.setBounds(200,250,100,40);
        f.add(Add_b);
        JButton back = new JButton("Main menu");
        back.setBounds(400,370,200,50);
        back.setBackground(Color.red);
        f.add(back);
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
        Add_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item_name =  tf.getText();
                int quantity =  Integer.parseInt(tf2.getText().toString());
                int cost =  Integer.parseInt(tf3.getText().toString());
                tf.setText("");
                tf2.setText("");
                tf3.setText("");
                try
                {
                    Connection con = Connect.CreateCon();
                    String query =  "insert into Inventory(Item_name,Cost,quantity) values(?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, item_name);
                    pstmt.setInt(2, cost);
                    pstmt.setInt(3, quantity);
                    l4.setText("Status:  Item added");
                    pstmt.executeUpdate();
                } catch (ClassNotFoundException | SQLException k) {
                    k.printStackTrace();
                    System.out.println("Could not add the data, plese contact the adminstratory");
                    l4.setText("Status:  Item Could not added, please contact the administrator");
                }
            }
        });

    }

}

