package CRUDJava;

import javax.swing.*;

public class Loggin extends JFrame{
    private JTextField userText;
    private JTextField passwordText;
    private JButton accederButton;
    private JPanel login;
    private JLabel title;
    private JLabel secondTitle;
    private JLabel user;
    private JLabel password;

    public Loggin(){
        setContentPane(login);
        setSize(450,300);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        accederButton.addActionListener(e -> {
            rootWindow menu = new rootWindow();
            dispose();
        });
    }

    public static void main(String[] args) {
        Loggin loggin = new Loggin();
    }
}