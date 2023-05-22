package smarket;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class ProductMenu extends Frame implements ActionListener
{  static String USER ;
    static  String PASS;
    static  String driver;
    static  String DB_URL;
    Button jButton1;
    Button jButton2;
    Button jButton3;
    Button jButton4;
    Button jButton5;
    ScrollPane jScrollPane1;
    TextArea jTextArea1;
    Label l1, l2, l3,l4,l5;
    TextField t1, t2,t3,t4;
    public ProductMenu()
    {   addWindowListener(new WindowAdapter()
    {   public void windowClosing(WindowEvent we)
    {   System.exit(0); }
    });

        setSize(800,700);
        setTitle("Product Menu");
        setLocation(300,50);
        setBackground(Color.CYAN);

        setVisible(true);
        setLayout(null);
        setResizable(false);

        Font heading = new Font("serif", Font.BOLD, 28);
        Label l1 = new Label ("MANAGE PRODUCTS.");
        l1.setFont(heading);
        l1.setBounds(250, 40, 300, 40);
        add(l1);

        l2 = new Label ("Prod_Id:");
        l2.setFont(new Font("serif", Font.BOLD, 20));
        l2.setBounds(110, 130, 90, 30);
        add(l2);

        t1=new TextField();
        t1.setBounds(210, 133, 80, 25);
        add (t1);

        l3 = new Label ("Name:");
        l3.setFont(new Font("serif", Font.BOLD, 20));
        l3.setBounds(120, 190, 90, 30);
        add(l3);

        t2=new TextField();
        t2.setBounds(210, 190, 80, 25);
        add (t2);

        l4 = new Label ("Price:");
        l4.setFont(new Font("serif", Font.BOLD, 20));
        l4.setBounds(370, 130, 90, 30);
        add(l4);

        t3=new TextField();

        t3.setBounds(470, 136, 80, 25);
        add (t3);
        //Integer.parseInt(t3.getText());

        l5 = new Label ("Quantity:");
        l5.setFont(new Font("serif", Font.BOLD, 20));
        l5.setBounds(380, 190, 90, 30);
        add(l5);

        t4=new TextField();
        t4.setBounds(470, 190, 90, 25);
        add (t4);
        //Integer.parseInt(t4.getText());


        Font myFont=new Font("Tahoma", 1, 20);

        jButton1 = new Button("Insert data");
        jButton1.setForeground(Color.red);
        jButton1.setBackground(Color.yellow);
        jButton1.setBounds(140,270,125,38);
        add(jButton1);
        jButton1.addActionListener(this);

        jButton2 = new Button("Delete data");
        jButton2.setForeground(Color.red);
        jButton2.setBackground(Color.yellow);
        jButton2.setBounds(300,270,125,38);
        add(jButton2);
        jButton2.addActionListener(this);

        jButton3 = new Button("Update data");
        jButton3.setForeground(Color.red);
        jButton3.setBackground(Color.yellow);
        jButton3.setBounds(450,270,125,38);
        add(jButton3);
        jButton3.addActionListener(this);

        jButton4 = new Button("View existing products");
        jButton4.setForeground(Color.red);
        jButton4.setBackground(Color.yellow);
        jButton4.setBounds(140,320,225,38);
        add(jButton4);
        jButton4.addActionListener(this);

        jButton5 = new Button("Products Category");
        jButton5.setForeground(Color.red);
        jButton5.setBackground(Color.yellow);
        jButton5.setBounds(370,320,225,38);
        add(jButton5);
        jButton5.addActionListener(this);
        
        jButton1.setFont(myFont);
        jButton2.setFont(myFont);
        jButton3.setFont(myFont);
        jButton4.setFont(myFont);
        jButton5.setFont(myFont);
        initconnectionData();
    }
    static void initconnectionData()
    {  USER ="root";
        PASS="";
        driver="com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/inventory";
    }
    public void actionPerformed(ActionEvent b)
    {   String g=b.getActionCommand();
        JFrame insert_frame = new JFrame();
        JFrame delete_frame = new JFrame();
        JFrame update_frame = new JFrame();
        if(g.equals("Insert data"))
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
                int a1=Integer.parseInt(t1.getText());
                int a2=Integer.parseInt(t3.getText());
                int a3=Integer.parseInt(t4.getText());
                String sql = "INSERT INTO PRODUCT "+
                        "VALUES("+a1+",'"+t2.getText()+"',"+a2+","+a3+")";

                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(insert_frame, "Inserted into database");
                System.out.println("inserted into table");
                conn.close();
            }
            catch(ClassNotFoundException | SQLException se){}
            catch(NumberFormatException e) {}
        }
        if(g.equals("Delete data"))
        {
            try
            {
                Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn =
                        DriverManager .getConnection(DB_URL, USER, PASS);
                System.out.println("connection successfull");

                System.out.println("deleting data from databse");
                Statement stmt = conn.createStatement();
                int a1=Integer.parseInt(t1.getText());
                String sq2 = "DELETE FROM PRODUCT WHERE pr_id='"+a1+"'";

                stmt.executeUpdate(sq2);
                JOptionPane.showMessageDialog(delete_frame, "Deleted from databse");
                conn.close();
            }
            catch(ClassNotFoundException | SQLException se){}
            catch(NumberFormatException e) {}
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

                System.out.println("updating data to databse");
                Statement stmt = conn.createStatement();
                int a1=Integer.parseInt(t1.getText());
                int a2=Integer.parseInt(t3.getText());
                int a3=Integer.parseInt(t4.getText());
                String sq3 = "UPDATE PRODUCT SET pr_type='"+t2.getText()+"',price='"+a2+"',quantity='"+a3+"' WHERE pr_id='"+a1+"'";

                stmt.executeUpdate(sq3);
                JOptionPane.showMessageDialog(update_frame, "update sucessfull");
                conn.close();
            }
            catch(ClassNotFoundException | SQLException se){}
            catch(NumberFormatException e) {}

        }

        if(g.equals("View existing products"))
        {   
            try
            {   Class.forName(driver);
                System.out.println("Connecting to database...");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                String sql = "SELECT * FROM product";

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
        if(g.equals("Products Category"))
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
                String prodcat = "select product.pr_type, product.price, category.cat_name, category.cat_storage from product natural join includes natural join category;";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(prodcat);

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
    }
    public static void main(String args[])
    {
        ProductMenu PM = new ProductMenu();
         PM.setSize(new Dimension(800, 700));
         PM.setTitle("GraphicsDemo");
         PM.setVisible(true);
    }

}