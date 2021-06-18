package Grocerry_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

public class main implements ActionListener
{
    static JFrame f1;

    void Welcome()
    {
        System.out.println("Welcome to the groccery martker");
        JFrame f =  new JFrame();
        f.setSize(600,450);
        f.setLayout(null);
        f.setVisible(true);
        JLabel l = new JLabel("Welcome to the grocery");
        JLabel l2 = new JLabel("Market");
        l.setFont(new Font("Verdana", Font.PLAIN, 28));
        l.setBounds(120, 20, 350, 40);
        l2.setFont(new Font("Verdana", Font.PLAIN, 28));
        l2.setBounds(220, 50, 350, 40);

        JButton b =  new JButton("Admin");
        b.setBackground(new Color(242,97,77));
        b.setBounds(260,360,80,50);
        f.add(l);
        f.add(b);
        f.add(l2);
        b.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            f.setVisible(false);
                            main.Option_List();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                    }
                }
        );

    }
    public static void Option_List() throws SQLException, ClassNotFoundException {
        Scanner sc  =  new Scanner(System.in);
        JFrame f =  new JFrame();
        f.setSize(600,450);
        System.out.println("Printing");
        f.setLayout(null);
        f.setVisible(true);
        JLabel l = new JLabel("Please select your choice");
        l.setFont(new Font("Verdana", Font.PLAIN, 18));
        l.setBounds(150, 20, 350, 40);
        f.add(l);
        JButton CheckoutB =  new JButton("Checkout");
        CheckoutB.setBounds(220,100 , 100,30);
        JButton UpdateB =  new JButton("Update");
        UpdateB.setBounds(220,140 , 100,30);
        JButton DisplayB =  new JButton("Display");
        DisplayB.setBounds(220,180 , 100,30);
        JButton Check_AvailabiltyB  =  new JButton("Check Availability");
        Check_AvailabiltyB.setBounds(180,220 , 190,30);
        f.add(CheckoutB);
        f.add(Check_AvailabiltyB);
        f.add(DisplayB);
        f.add(UpdateB);
        CheckoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    f.setVisible(false);

                    Checkout obj = new Checkout();
                    obj.Checkout();

                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        Check_AvailabiltyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    f.setVisible(false);
                    Check_Availabilty.Check_avail();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        DisplayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    f.setVisible(false);
                    Display obj =  new Display();
                    obj.Display_Rows();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        UpdateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    f.setVisible(false);
                    Update.Menu();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        main obj1 =  new main();
        obj1.Welcome();
//        obj1.Option_List();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
