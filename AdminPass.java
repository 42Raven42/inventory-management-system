package smarket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminPass extends Frame implements ActionListener {
    JLabel label, label2;
    JButton login;
    JPasswordField pass;

    AdminPass() {
        setSize(800,700);
        setLocation(300,50);
        setBackground(Color.CYAN);

        setResizable(false);

        label = new JLabel("Enter Your Password:");
        label2 = new JLabel("<HTML><U> !!Password Verification!! </U></HTML>");
        pass = new JPasswordField();
        login = new JButton("Login");

        label.setBounds(100,300,200,60);
        label2.setBounds(270,100,350,50);
        pass.setBounds(330,305,250,50);
        login.setBounds(380, 400, 100,50);

        // Styling
        label2.setFont(new Font("serif", Font.BOLD, 30));
        label.setFont(new Font("serif", Font.BOLD, 20));
        login.setFont(new Font("serif", Font.BOLD, 20));
        login.setForeground(Color.red);
        login.setBackground(Color.yellow);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        add(label);
        add(label2);
        add(pass);
        add(login);

        setLayout(null);
        setVisible(true);

        login.addActionListener(this);

        SuperMarket.initConnectionData();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        JFrame frame = new JFrame();

        if (btn.equals("Login")) {
            String pword = String.valueOf(pass.getPassword());

            if (pword.equals("admin")) {
                AdminLogin al = new AdminLogin();
                al.setSize(new Dimension(800, 700));
                al.setTitle("Admin Login Page");
                al.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(frame, "Incorrect Password!");
            }
        }

        if (btn.equals("Back")) {

        }
    }

}
