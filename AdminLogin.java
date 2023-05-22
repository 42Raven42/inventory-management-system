package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

 import net.proteanit.sql.DbUtils;

public class AdminLogin extends Frame implements ActionListener {

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

    public AdminLogin() {
        setSize(800,700);
        setLocation(300,50);
        setBackground(Color.CYAN);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        Font myFont = new Font("Tahoma", 1, 20); // NOI18N
        setLayout(null);
        setResizable(false);
        setVisible(true);

        jButton1 = new Button("Product");
        jButton1.setBackground(Color.yellow);
        jButton1.setForeground(Color.red);
        jButton1.setBounds(100, 300, 100, 50);

        jButton2 = new Button("Category");
        jButton2.setBackground(Color.yellow);
        jButton2.setForeground(Color.red);
        jButton2.setBounds(280, 300, 100, 50);

        jButton3 = new Button("Customer");
        jButton3.setBackground(Color.yellow);
        jButton3.setForeground(Color.red);
        jButton3.setBounds(440, 300, 120, 50);

        // jButton4 = new Button("Select");
        jButton1.setFont(myFont);
        jButton2.setFont(myFont);
        jButton3.setFont(myFont);
        // jButton4.setFont(myFont);
        jScrollPane1 = new ScrollPane();
        jTextArea1 = new TextArea();
        jTextArea1.setColumns(500);
        jTextArea1.setRows(500);
        jTextArea1.setFont(myFont);
        jScrollPane1.add(jTextArea1);
        add(jButton1);
        add(jButton2);
        add(jButton3);
        // add(jButton4);
        //add(jScrollPane1);
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        // jButton4.addActionListener(this);
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
        if (g.equals("Product")) {
            ProductMenu e = new ProductMenu();
            e.setSize(new Dimension(800, 700));
            e.setTitle("Product Page");
            e.setVisible(true);
        }
        if (g.equals("Category")) {
            CategoryMenu e = new CategoryMenu();
            e.setSize(new Dimension(800, 700));
            e.setTitle("Category Page");
            e.setVisible(true);
        }
        if (g.equals("Customer")) {
            CustomerMenuAdmin e = new CustomerMenuAdmin();
            e.setSize(new Dimension(800, 700));
            e.setTitle("Customer Details");
            e.setVisible(true);
        }
        if (g.equals("select")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                String s = jTextArea1.getText();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(s);
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
        }
    }
     public static void main(String args[])
    {
        AdminLogin AL = new AdminLogin();
         AL.setSize(new Dimension(800, 700));
         AL.setTitle("GraphicsDemo");
         AL.setVisible(true);
    }

}