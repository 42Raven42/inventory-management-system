package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;
import static smarket.ProductMenu.driver;
import static smarket.SuperMarket.driver;

public class CustomerMenuAdmin extends Frame implements ActionListener {
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
    TextField t1, t2, t3, t4;

    public CustomerMenuAdmin() {
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
        jButton1.setBounds(140,270,125,38);

        jButton2 = new Button("Delete data");
        jButton2.setForeground(Color.red);
        jButton2.setBackground(Color.yellow);
        jButton2.setBounds(300,270,125,38);

        jButton3 = new Button("Update data");
        jButton3.setForeground(Color.red);
        jButton3.setBackground(Color.yellow);
        jButton3.setBounds(450,270,125,38);
        
        jButton4 = new Button("View Customer Details");
        jButton4.setForeground(Color.red);
        jButton4.setBackground(Color.yellow);
        jButton4.setBounds(140,320,275,38);
        
        jButton5 = new Button("Refresh Customer Records");
        jButton5.setForeground(Color.red);
        jButton5.setBackground(Color.yellow);
        jButton5.setBounds(450,320,275,38);

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
                String sql = "INSERT INTO customer " +
                        "VALUES('" + t1.getText() + "'," + a2 + "," + a3 + ")";

                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(InsertPop, "Inserted successfully!");
//                System.out.println("inserted into table");

                conn.close();
            } catch (ClassNotFoundException | SQLException se) {
            } catch (NumberFormatException e) {
            }
        }
        if (g.equals("Delete data")) {
            try {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("connection successful");

//                System.out.println("deleting data from database");
                Statement stmt = conn.createStatement();
                int a2 = Integer.parseInt(t2.getText());
                String sq4 = "DELETE FROM customer WHERE c_id='" + a2 + "'";

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
        }
        if(g.equals("View Customer Details"))
        {   try
        {   Class.forName(driver);
            System.out.println("Connecting to database...");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM cust_records";

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
            j1.setTitle("View Available products");
            j1.setVisible(true);
            conn.close();
        }
        catch(ClassNotFoundException | SQLException y){}
        }
        if(g.equals("Refresh Customer Records"))
        {
            try
            {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager .getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

                System.out.println("inserting data into databse");
                Statement stmt = conn.createStatement();
                //int a1=Integer.parseInt(t1.getText());
                //int a2=Integer.parseInt(t3.getText());
                //int a3=Integer.parseInt(t4.getText());
                String sql = "CREATE OR REPLACE VIEW cust_records as SELECT * FROM customer";

                stmt.executeUpdate(sql);
                //JOptionPane.showMessageDialog(insert_frame, "Inserted into database");
                System.out.println("inserted into table");
                conn.close();
            }
            catch(ClassNotFoundException | SQLException se){}
            catch(NumberFormatException e) {}
        }
    }
    public static void main(String args[])
    {
        CustomerMenuAdmin CM = new CustomerMenuAdmin();
         CM.setSize(new Dimension(800, 700));
         CM.setTitle("GraphicsDemo");
         CM.setVisible(true);
    }

}