package CRUDJava;

import javax.swing.*;
import java.sql.*;

public class Loggin extends JFrame{
    private JTextField userText;
    private JButton accederButton;
    private JPanel login;
    private JLabel title;
    private JLabel secondTitle;
    private JLabel user;
    private JLabel password;
    private JPasswordField passwordText;
    public Loggin(){
        setContentPane(login);
        setSize(450,300);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        accederButton.addActionListener(e -> {
            try {
                connection co = new connection();
                co.setUserName(userText.getText());
                co.setPassword(String.valueOf(passwordText.getPassword()));
                ResultSet rS = co.loginUser();
                if (rS != null) {
                    rootWindow menu = new rootWindow();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            null,"Username or Password incorrect", "Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ev) {
                System.out.println("Exception: " + ev);
            }
        });


    }

    public static void main(String[] args){
        Loggin loggin = new Loggin();
    }
}