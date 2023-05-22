package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;
import static smarket.CustomerMenu.driver;
import static smarket.ProductMenu.driver;

public class CategoryMenu extends Frame implements ActionListener {

    static String USER;
    static String PASS;
    static String driver;
    static String DB_URL;

    Button jButton1;
    Button jButton2;
    Button jButton3;
    Button jButton4;
    Button jButton5;

    ScrollPane jScrollPane1;
    TextArea jTextArea1;
    Label l1, l2, l3;
    TextField t1, t2, t3;

    public CategoryMenu() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setTitle("CategoryMenu");
        setSize(800,700);
        setLocation(300,50);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        setBackground(Color.cyan);

        Label l = new Label("Categories");
        l.setBounds(380, 20, 150,50);
        l.setFont(new Font("serif", Font.BOLD, 30));
        add(l);

        l1 = new Label("cat_id:");
        l1.setFont(new Font("serif", Font.BOLD, 20));
        l1.setBounds(100, 100, 90, 30);
        add(l1);

        t1 = new TextField();
        t1.setBounds(190, 100, 100, 25);
        add(t1);

        l2 = new Label("cat_name:");
        l2.setFont(new Font("serif", Font.BOLD, 20));
        l2.setBounds(100, 150, 90, 30);
        add(l2);

        t2 = new TextField();
        t2.setBounds(210, 150, 130, 25);
        add(t2);

        l3 = new Label("cat_storage:");
        l3.setFont(new Font("serif", Font.BOLD, 20));
        l3.setBounds(100, 200, 120, 30);
        add(l3);

        t3 = new TextField();
        t3.setBounds(220, 200, 130, 28);
        add(t3);

        Font myFont = new Font("Tahoma", 1, 20); // NOI18

        jButton1 = new Button("Insert data");
        jButton1.setBounds(100,300,120,50);
        jButton1.setForeground(Color.red);
        jButton1.setBackground(Color.yellow);

        jButton2 = new Button("Delete data");
        jButton2.setBounds(240,300,140,50);
        jButton2.setForeground(Color.red);
        jButton2.setBackground(Color.yellow);

        jButton3 = new Button("Update data");
        jButton3.setBounds(390,300,150,50);
        jButton3.setForeground(Color.red);
        jButton3.setBackground(Color.yellow);

        jButton4 = new Button("View existing categories");
        jButton4.setBounds(100,370,245,50);
        jButton4.setForeground(Color.red);
        jButton4.setBackground(Color.yellow);

        jButton5 = new Button("Search Specific Category");
        jButton5.setBounds(390,370,250,50);
        jButton5.setForeground(Color.red);
        jButton5.setBackground(Color.yellow);

        jButton1.setFont(myFont);
        jButton2.setFont(myFont);
        jButton3.setFont(myFont);
        jButton4.setFont(myFont);
        jButton5.setFont(myFont);

        jScrollPane1 = new ScrollPane();
        jTextArea1 = new TextArea();
        jTextArea1.setColumns(500);
        jTextArea1.setRows(500);
        jTextArea1.setFont(myFont);
        jScrollPane1.add(jTextArea1);

        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);
        add(jButton5);


        //add(jScrollPane1);
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jButton4.addActionListener(this);
        jButton5.addActionListener(this);

        initconnectionData();
    }

    static void initconnectionData() {
        USER = "root";
        PASS = "";
        driver = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/inventory";
    }

    public void actionPerformed(ActionEvent c) {
        JFrame frame = new JFrame();
        String g = c.getActionCommand();
        if (g.equals("Insert data")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn
                        = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

                System.out.println("inserting data into databse");
                Statement stmt = conn.createStatement();
                int a1 = Integer.parseInt(t1.getText());
                String sql = "INSERT INTO CATEGORY "
                        + "VALUES(" + a1 + ",'" + t2.getText() + "','" + t3.getText() + "')";

                stmt.executeUpdate(sql);
                System.out.println("inserted into table");
                JOptionPane.showMessageDialog(frame, "Inserted");

                conn.close();
            } catch (ClassNotFoundException | SQLException se) {
            } catch (NumberFormatException e) {
            }
        }

        if (g.equals("Search Specific Category")) 
        {
            try
            {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager .getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

//                System.out.println("updating data to database");
                //Statement stmt = conn.createStatement();
                String subq = "SELECT * from category natural join includes natural join product where cat_id in(SELECT cat_id from category where cat_name='drinks')";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(subq);

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
            }
            catch(ClassNotFoundException | SQLException se){}
            catch(NumberFormatException e) {}

        }

        if (g.equals("Delete data")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

                System.out.println("deleting data from databse");
                Statement stmt = conn.createStatement();
                int a1 = Integer.parseInt(t1.getText());
                String sq3 = "DELETE FROM CATEGORY WHERE cat_id='" + a1 + "'";

                stmt.executeUpdate(sq3);
                System.out.println("deleted from table");
                conn.close();
            } catch (ClassNotFoundException | SQLException se) {
            } catch (NumberFormatException e) {
            }
        }

        if(g.equals("View existing categories"))
        {   try
        {   Class.forName(driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM category";

            Statement stmt = conn.createStatement();

            ResultSet rs=stmt.executeQuery(sql);

            JTable jTable1=new JTable();
            Font myFont=new Font("Tahoma", 1, 15);
            jTable1.setFont(myFont);
            jTable1.setRowHeight(20);
            jTable1.setBackground(Color.CYAN);
            jTable1.setForeground(Color.red);
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

            JFrame j1=new JFrame();
            JScrollPane pg = new JScrollPane(jTable1);
            pg.setFont(myFont);
            j1.add(pg);
            j1.setResizable(false);
            j1.setSize(800, 700);
            j1.setLocation(300,50);
            j1.setTitle("Available categories");
            j1.setVisible(true);
            conn.close();
        }
        catch(ClassNotFoundException | SQLException y){}
        }
        if(g.equals("Update data"))
        {
            try
            {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager .getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

                System.out.println("updating data to database");
                Statement stmt = conn.createStatement();
                int a1=Integer.parseInt(t1.getText());
                String sq4 = "UPDATE CATEGORY SET cat_name='"+t2.getText()+"',cat_storage='"+t3.getText()+"' WHERE cat_id='"+a1+"'";
                stmt.executeUpdate(sq4);
                System.out.println("product updated successful");
                conn.close();
            }
            catch(ClassNotFoundException | SQLException se){}
            catch(NumberFormatException e) {}

        }
    }


public static void main(String args[])
    {
        CategoryMenu CM = new CategoryMenu();
         CM.setSize(new Dimension(800, 700));
         CM.setTitle("GraphicsDemo");
         CM.setVisible(true);
    }
}