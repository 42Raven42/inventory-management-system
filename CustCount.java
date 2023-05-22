package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
//import static smarket.ProductMenu.driver;

public class CustCount extends Frame implements ActionListener {
    static String USER;
    static String PASS;
    static String driver;
    static String DB_URL;

    Button jButton1;
    JLabel label;

    ScrollPane jScrollPane1;
    TextArea jTextArea1;
    TextField t1;

    public CustCount() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setBounds(150, 80, 680, 554);
        setVisible(true);
        setLayout(new FlowLayout());

        t1 = new TextField();
        t1.setBounds(210, 130, 80, 30);
        add(t1);



        Font myFont = new Font("Tahoma", 1, 20); // NOI18N
        setLayout(null);

        jButton1 = new Button("Enter");
        label = new JLabel("Enter the product id");
        label.setBounds(50,100,350,15);
        add(label);
        jButton1.setBounds(100, 200, 100,50);

        jButton1.setFont(myFont);

        jScrollPane1 = new ScrollPane();
        jTextArea1 = new TextArea();
        jTextArea1.setColumns(500);
        jTextArea1.setRows(500);
        jTextArea1.setFont(myFont);
        jScrollPane1.add(jTextArea1);

        add(jButton1);

        //add(jScrollPane1);
        jButton1.addActionListener(this);

        initconnectionData();
    }

    static void initconnectionData() {
        USER = "root";
        PASS = "";
        driver = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/SuperMarket";
    }

    public void actionPerformed(ActionEvent c) {
        String g = c.getActionCommand();


//        if (g.equals("Enter")) {
//            try {
//                Class.forName(driver);
//                System.out.println("Connecting to database...");
//                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                System.out.println("connection successfull");
//
////                System.out.println("inserting data into databse");
//
//
////                int a1 = Integer.parseInt(t1.getText());
//                String s = t1.getText();
//                Statement stmt = conn.createStatement();
//
//                ResultSet rs=stmt.executeQuery(s);
//
//                String sql = "SELECT * FROM CATEGORY "
//                        + "WHERE cat_id = " + t1.getText();
//
//                JTable jt = new JTable();
//                jt.setSize(300,300);
//                jt.setModel(DbUtils.resultSetToTableModel(rs));
//                JScrollPane pg = new JScrollPane(jt);
//
//                JFrame jf = new JFrame();
//                jf.add(pg);
//                jf.setFont(new Font("serif", Font.BOLD,20));
//                jf.setVisible(true);
//
//                stmt.executeUpdate(sql);
//
//                System.out.println("inserted into table");
//                conn.close();
//            } catch (ClassNotFoundException | SQLException se) {
//            } catch (NumberFormatException e) {
//            }
//        }

        if(g.equals("Enter")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager.getConnection(DB_URL, USER, PASS);
//            String s=t1.getText();
                String sql = "SELECT COUNT(c_id) from CUSTOMER";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                JTable jTable1 = new JTable();

                jTable1.setRowHeight(40);
                Font myFont = new Font("serif", 1, 20);

                jTable1.setFont(myFont);


                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
//                add(jTable1);

                JFrame j1 = new JFrame();
                JScrollPane pg = new JScrollPane(jTable1);

                pg.setFont(myFont);
                j1.add(pg);

                j1.setSize(500, 500);
                j1.setVisible(true);
                conn.close();
            } catch (ClassNotFoundException | SQLException y) {
            }
        }

    }

    public static void main(String args[]) {

        CustCount CC = new CustCount();
    }
}