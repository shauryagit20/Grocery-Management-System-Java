package Grocerry_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Check_Availabilty implements ActionListener
{
    public static Check_Availibility_Output out;

    public static void Check_avail()  throws ClassNotFoundException, SQLException {
        JFrame fo = new JFrame();
        final String[] item_to_find_list = {""};
        fo.setSize(600, 450);
        fo.setLayout(null);
        fo.setVisible(true);
        System.out.println(fo.isVisible());
        JLabel l = new JLabel("Check Availability");
        l.setFont(new Font("Verdana", Font.PLAIN, 28));
        l.setBounds(190, 20, 350, 40);
        JLabel output_status = new JLabel();
        output_status.setBounds(10,330 , 500,30);
        fo.add(output_status);
        fo.add(l);
        JButton back = new JButton("Main menu");
        back.setBounds(400,370,200,50);
        back.setBackground(Color.red);
        fo.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fo.setVisible(false);
                try {
                    main.Option_List();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        JLabel question = new JLabel("Please enter the item name");
        question.setBounds(80, 90, 350, 40);
        JTextField tf = new JTextField();
        tf.setBounds(300, 100, 200, 20);
        JButton check_b =  new JButton("Check Availability");
        check_b.setBounds(180,220 , 190,30);
        fo.add(check_b);
        fo.add(tf);
        fo.add(question);
        fo.setVisible(true);
        check_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item_to_find_list[0] =  tf.getText().toString();
                tf.setText("");
                System.out.println(item_to_find_list[0]);
                String item_to_find = item_to_find_list[0];
                try {
                    Check_Availibility_Output x = search(item_to_find);
                    int quantity =  x.getCost();
                    if (quantity > 0)
                    {
                        int cost =  x.getCost();
                        output_status.setText("The item is available in stock name: " +  item_to_find +  " cost; "  + cost + " quantity: " + quantity );
                    }
                    else
                    {
                        output_status.setText("The item is not available in stock");
                    }
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        System.out.println("");
    }

    public static Check_Availibility_Output search(String item_to_find) throws ClassNotFoundException, SQLException {
        Connection con = Connect.CreateCon();
        String q = "Select * from Inventory";
        Statement st = con.createStatement();
        ResultSet set = st.executeQuery(q);
        boolean item_found = false;
        while(set.next())
        {
            String item_name =  set.getString(1);
            if (item_name.equalsIgnoreCase(item_to_find))
            {
                item_found =  true;
                int cost =  set.getInt(2);
                int quantity =  set.getInt(3);
                out = new Check_Availibility_Output(true, item_name , cost, quantity );
                System.out.println("This item is available, details as below");
                System.out.println("Item_name : " + item_name+  " Cost : " +  cost + " quantity : "  + quantity);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                return out;

            }

        }
        out  = new Check_Availibility_Output(false);
        return out;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}