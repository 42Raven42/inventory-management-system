package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class CustomerMenu extends Frame implements ActionListener {
    static String USER;
    static String PASS;
    static String driver;
    static String DB_URL;
    Button jButton1;
    Button jButton2;
    Button jButton3;
    Button jButton4;
    ScrollPane jScrollPane1;
    TextArea jTextArea1;
    Label l1, l2, l3;
    TextField t1, t2, t3, t4;

    public CustomerMenu() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(800,700);
        setLocation(300,50);
        setBackground(Color.cyan);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        Label l = new Label ("MANAGE CUSTOMERS.");
        Font heading = new Font("serif", Font.BOLD, 28);
        l.setFont(heading);
        l.setBounds(250, 40, 340, 40);
        add(l);

        l1 = new Label("c_name");
        l1.setFont(new Font("serif", Font.BOLD, 20));
        l1.setBounds(110, 130, 90, 30);
        add(l1);

        t1 = new TextField();
        t1.setBounds(210, 133, 80, 25);
        add(t1);

        l2 = new Label("c_id");
        l2.setFont(new Font("serif", Font.BOLD, 20));
        l2.setBounds(120, 190, 90, 30);
        add(l2);

        t2 = new TextField();
        t2.setBounds(210, 190, 80, 25);
        add(t2);

        l3 = new Label("c_phone");
        l3.setFont(new Font("serif", Font.BOLD, 20));
        l3.setBounds(370, 130, 90, 30);
        add(l3);

        t3 = new TextField();
        t3.setBounds(470, 136, 80, 25);
        add(t3);

        Font myFont = new Font("Tahoma", 1, 20); // NOI18N

        jButton1 = new Button("Insert data");
        jButton1.setForeground(Color.red);
        jButton1.setBackground(Color.yellow);
        jButton1.setBounds(340,270,125,38);

        jButton2 = new Button("Delete data");
        jButton2.setForeground(Color.red);
        jButton2.setBackground(Color.yellow);
        jButton2.setBounds(300,270,125,38);

        jButton3 = new Button("Update data");
        jButton3.setForeground(Color.red);
        jButton3.setBackground(Color.yellow);
        jButton3.setBounds(450,270,125,38);

        jButton1.setFont(myFont);
        jButton2.setFont(myFont);
        jButton3.setFont(myFont);

        jScrollPane1 = new ScrollPane();
        jTextArea1 = new TextArea();
        jTextArea1.setColumns(500);
        jTextArea1.setRows(500);
        jTextArea1.setFont(myFont);
        jScrollPane1.add(jTextArea1);

        add(jButton1);
        //add(jButton2);
        //add(jButton3);

        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);

        initconnectionData();
    }

    static void initconnectionData() {
        USER = "root";
        PASS = "";
        driver = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/inventory";
    }

    public void actionPerformed(ActionEvent d) {
        String g = d.getActionCommand();
        JFrame InsertPop = new JFrame();
        JFrame DeletePop = new JFrame();
        JFrame UpdatePop = new JFrame();

        if (g.equals("Insert data")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

//                System.out.println("inserting data into database")
                Statement stmt = conn.createStatement();
                int a2 = Integer.parseInt(t2.getText());
                int a3 = Integer.parseInt(t3.getText());
                String sql = "INSERT INTO CUSTOMER " +
                        "VALUES('" + t1.getText() + "'," + a2 + "," + a3 + ")";

                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(InsertPop, "Inserted successfully!");
//                System.out.println("inserted into table");

                conn.close();
            } catch (ClassNotFoundException | SQLException se) {
            } catch (NumberFormatException e) {
            }
        }
        /*if (g.equals("Delete data")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("connection successful");

//                System.out.println("deleting data from database");
                Statement stmt = conn.createStatement();
                int a2 = Integer.parseInt(t2.getText());
                String sq4 = "DELETE FROM CUSTOMER WHERE c_id='" + a2 + "'";

                stmt.executeUpdate(sq4);
                if (t2.getText() != "") {
                    JOptionPane.showMessageDialog(DeletePop, "Deleted successfully.");
                }
//                System.out.println("deleted from table");
                conn.close();
            } catch (ClassNotFoundException | SQLException se) {
            } catch (NumberFormatException e) {
            }
        }
        if (g.equals("Update data")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

//                System.out.println("updating data to database");
                Statement stmt = conn.createStatement();
                String a1 = t1.getText();
                int a2 = Integer.parseInt(t2.getText());
                int a3 = Integer.parseInt(t3.getText());
                String sq3 = "UPDATE customer SET c_name='" + a1 + "',c_phone='" + a3 + "' WHERE c_id='" + a2 + "'";
                stmt.executeUpdate(sq3);
                JOptionPane.showMessageDialog(UpdatePop, "Updated successfully.");
//                System.out.println("update sucessfull");
                conn.close();
            } catch (ClassNotFoundException | SQLException se) {
            } catch (NumberFormatException e) {
            }

        }*/
    }
    public static void main(String args[])
    {
        CustomerMenu CM = new CustomerMenu();
         CM.setSize(new Dimension(800, 700));
         CM.setTitle("GraphicsDemo");
         CM.setVisible(true);
    }

}