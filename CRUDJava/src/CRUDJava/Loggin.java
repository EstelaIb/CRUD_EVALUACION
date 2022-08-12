package CRUDJava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Loggin extends JDialog{
    private JTextField userText;
    private JTextField passwordText;
    private JButton accederButton;
    private JPanel login;
    private JLabel title;
    private JLabel secondTitle;
    private JLabel user;
    private JLabel password;
    private String correctPassword;
    private String correctUser;
    private String usuario;
    private String contrasenia;
    public Loggin(JFrame parent) throws SQLException{
        super(parent);
        setContentPane(login);
        setSize(450,300);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        //SQL

        try {
            //Conecction to my Database
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/sistema?serverTimezone=UTC","root","2000");
            String sql = "SELECT  passwordUSR, nomUSR FROM usuarios";
            PreparedStatement pStatement = conn.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();

            //SAVE RESULT OF QUERY
            if(result.next()){
                correctPassword = result.getString(1);
                correctUser = result.getString(2);
            }
            accederButton.addActionListener(e -> {
                //CATCH DATA
                usuario = userText.getText();
                contrasenia = passwordText.getText();

                //COMPARE DATA
                if(usuario.equals(correctUser) && (contrasenia.equals(correctPassword))){
                    //OPEN MAIN MENU
                    rootWindow menu = new rootWindow();
                    dispose();
                }
                else{
                    //WARNING OF ERROR
                    JOptionPane.showMessageDialog(
                            Loggin.this,"email or password incorrect",
                            "try again",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });


        } catch (Exception e) {
            System.out.println("NO SE PUDO CONECTAR");;
        }

    }

    public static void main(String[] args) throws SQLException{
        Loggin loggin = new Loggin(null);
    }
}