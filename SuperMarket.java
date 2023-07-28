package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
// import net.proteanit.sql.DbUtils;
// import static smarket.ProductMenu.driver;
// import net.proteanit.sql.DbUtils;

public class SuperMarket extends Frame implements ActionListener {

    static String USER, PASS, driver, DB_URL;

    JButton admin, customer;
    JLabel head;

//    ScrollPane jScrollPane1;
//    TextArea jTextArea1;

    SuperMarket() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setSize(800, 700);
        setLocation(300,50);
        setBackground(Color.CYAN);

        setResizable(false);

        Font heading = new Font("serif", Font.BOLD, 28);
        Font button = new Font("serif", Font.BOLD, 20);

        admin = new JButton("Admin Login");
        customer = new JButton("Customer Login");
        head = new JLabel("<HTML><U> Super Market Management </U></HTML>");

        head.setFont(heading);

        head.setBounds(257, 100, 350, 40);
        admin.setBounds(200,300,150,60);
        customer.setBounds(400,300,180,60);

        admin.setFont(button);
        customer.setFont(button);

        admin.setBackground(Color.yellow);
        admin.setForeground(Color.red);

        customer.setBackground(Color.yellow);
        customer.setForeground(Color.red);

//        jScrollPane1 = new ScrollPane();
//        jTextArea1 = new TextArea();
//        jTextArea1.setColumns(500);
//        jTextArea1.setRows(500);
//        jTextArea1.setFont(myFont);
//        jScrollPane1.add(jTextArea1);

        add(admin);
        add(customer);
        add(head);

        admin.addActionListener(this);
        customer.addActionListener(this);

        setLayout(null);
        setVisible(true);

        initConnectionData();
    }

    static void initConnectionData() {
        USER = "root";
        PASS = "";
        driver = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/inventory";
    }

    public void actionPerformed(ActionEvent a) {
        String g = a.getActionCommand();
        if (g.equals("Admin Login")) {

            AdminPass adminPass = new AdminPass();
            adminPass.setVisible(true);
            adminPass.setTitle("Password Verification");
        }
        /*if (g.equals("Category")) {

            CategoryMenu e = new CategoryMenu();
            e.setSize(new Dimension(370, 700));
            e.setTitle("GraphicsDemo");
            e.setVisible(true);
        }*/
        if (g.equals("Customer Login")) {
            CustomerMenu e = new CustomerMenu();
            e.setSize(new Dimension(800, 700));
            e.setTitle("Customer Signup");
            e.setVisible(true);
        }
        /*if (g.equals("select")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                String s = jTextArea1.getText();
                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(s);
                JTable jTable1 = new JTable();
                Font myFont = new Font("Tahoma", 1, 26);
                jTable1.setFont(myFont);
//                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                JFrame j1 = new JFrame();
                JScrollPane pg = new JScrollPane(jTable1);
                pg.setFont(myFont);
                j1.add(pg);
                j1.setSize(500, 300);
                j1.setVisible(true);
                conn.close();
            } catch (ClassNotFoundException | SQLException y) {}
        }*/
    }

    public static void main(String args[]) {
        SuperMarket e = new SuperMarket();
//        e.setSize(new Dimension(800, 700));
        e.setTitle("Super Market Management.");
//        e.setVisible(true);
    }
}