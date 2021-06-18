package Grocerry_Management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

public class Checkout {
    int sum = 0;
    int quantity;
    DefaultTableModel model;

    public void setSum(int sum) {
        System.out.println("Inside: " +   sum);
        this.sum = sum;
    }
    public int getSum() {
        System.out.println("Get sum  : "  + sum);
        return this.sum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public static void Checkout() throws ClassNotFoundException, SQLException {
        Checkout obj =  new Checkout();
        int no_of_items = 100;
        DefaultTableModel model;
        int cost = 0;
        String item_name;

        int quantity = obj.quantity;
        int available = 0;
        JFrame f = new JFrame();
        f.setLayout(null);
        f.setSize(600, 450);
        f.setVisible(true);
        JLabel title = new JLabel("Checkout");
        title.setFont(new Font("Verdana", Font.PLAIN, 28));
        title.setBounds(220, 10, 220, 100);
        title.setBackground(Color.BLUE);
        f.add(title);
        JLabel l = new JLabel("Please enter the name of the items");
        JLabel l2  =  new JLabel("Please enter the number of items ");
        l.setBounds(30, 90, 300, 50);
        l2.setBounds(30, 135, 300, 50);
        f.add(l);
        f.add(l2);
        JTextField tf = new JTextField();
        JTextField tf2 = new JTextField();
        tf2.setBounds(300, 150, 200, 20);
        tf.setBounds(300, 110, 200, 20);
        f.add(tf2);
        JLabel l1 = new JLabel("Please enter the  of the item");
        l.setBounds(30, 90, 300, 50);
        f.add(l);
        JTextField tf1 = new JTextField();
        tf.setBounds(300, 110, 200, 20);
        f.add(tf);
        JTable tb = new JTable();
        JScrollPane sb = new JScrollPane(tb);
        JButton Paid = new JButton("PAID");
        Paid.setBackground(Color.orange);
        Paid.setBounds(400, 370, 200, 50);
        f.add(Paid);
        sb.setBounds(30, 180, 250, 200);
        model = new DefaultTableModel();
        model.addColumn("Item");
        model.addColumn("Cost");
        model.addColumn("quantity");
        JButton jb = new JButton("Add");
        jb.setBounds(300, 200, 200, 20);
        tb.setModel(model);
        f.add(sb);
        f.add(jb);
        JLabel sum_label = new JLabel("Total bill = 0 ");
        sum_label.setFont(new Font("Verdana", Font.BOLD, 14));
        sum_label.setBounds(350, 240, 190, 20);
        f.add(sum_label);
        Paid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
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
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] r =  new String[3];
                System.out.println("Button Clicked");
                String item_name =  tf.getText();
                int quantity  = Integer.parseInt(tf2.getText())  ;
                System.out.println("Quantity: " + quantity);
                int sum =  obj.getSum();
                try {
                    int x = Add_to_bill(item_name, quantity, sum);
                    int cost = x - sum;
                    if (x>0) {
                        tf.setText("");
                        tf2.setText("");
                        r[0] = item_name;
                        r[1] =  Integer.toString(cost);
                        r[2] =  Integer.toString(quantity);
                        model.addRow(r);
                        obj.setSum(x);
                        sum_label.setText("Sum: " + x);

                    }
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                    System.out.println("Last");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    System.out.println("Last");
                }
            }
        });
    }
    public static int Add_to_bill(String item_name, int quantity, int sum ) throws ClassNotFoundException, SQLException {
            Connection con = Connect.CreateCon();
            int available  =  0;
            int cost;
            String q1 = "Select * from Inventory";
            String[] r = new String[3];
            Statement st = con.createStatement();
            try (ResultSet set = st.executeQuery(q1)) {
                while (set.next()) {
                    String item_name_db = set.getString(1);
                    if (item_name_db.equalsIgnoreCase(item_name)) {
                        cost = set.getInt(2);
                        System.out.println("The total cost is " + cost);
                        available = set.getInt(3);
                        sum = sum + (cost * quantity);
                        available = available - quantity;
                        if (available != 0) {
                            String q2 = "UPDATE Inventory SET quantity = ? WHERE Item_name = ?";
                            PreparedStatement pstmt = con.prepareStatement(q2);
                            pstmt.setInt(1, available);
                            pstmt.setString(2, item_name);
                            pstmt.executeUpdate();
                            return sum;
                        } else {
                            String q3 = "DELETE FROM Inventory WHERE Item_name =  ? ";
                            PreparedStatement pstmt = con.prepareStatement(q3);
                            pstmt.setString(1, item_name);
                            pstmt.executeUpdate();
                            return sum;
                        }
                    }
                }
            }
    return 0;
    }
    public static void addActionListener(ActionListener actionListener) {
    }
}
