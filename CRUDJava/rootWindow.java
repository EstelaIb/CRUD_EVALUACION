package CRUDJava;

import javax.swing.*;

public class rootWindow extends JFrame{
    private JButton createButton;
    private JButton shareButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JPanel menuWindow;
    private JLabel title;
    private JLabel secondtitle;
    private JComboBox comboBox1;
    private JButton irButton;
    private JLabel warning;

    public rootWindow(){
        setContentPane(menuWindow);
        setVisible(true);
        setTitle("CRUD");
        setSize(800,450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton[] buttons = {createButton, updateButton, deleteButton, shareButton};
        for (JButton jB : buttons){
            jB.setEnabled(false);
        }

        irButton.addActionListener(e ->{
            for (JButton jB : buttons){
                jB.setEnabled(true);
            }
        });

        createButton.addActionListener(e -> {
            int option = comboBox1.getSelectedIndex();
            switch (option){
                case 0:
                    createProduct crP = new createProduct(0);
                    break;
                case 1:
                    createCategory crPro = new createCategory(0);
                    break;
                case 2:
                    createProvider crPr = new createProvider(0);
                    break;
                default:
                    System.out.println("This not happened");
                dispose();
            }
        });

        updateButton.addActionListener(e -> {
            int option = comboBox1.getSelectedIndex();
            switch (option){
                case 0:
                    createProduct crP = new createProduct(1);
                    break;
                case 1:
                    createCategory crPro = new createCategory(1);
                    break;
                case 2:
                    createProvider crPr = new createProvider(1);
                    break;
                default:
                    System.out.println("This not happened");
                    dispose();
            }
        });

        deleteButton.addActionListener(e -> {
            int option = comboBox1.getSelectedIndex();
            switch (option){
                case 0:
                    createProduct crP = new createProduct(2);
                    break;
                case 1:
                    createCategory crPro = new createCategory(2);
                    break;
                case 2:
                    createProvider crPr = new createProvider(2);
                    break;
                default:
                    System.out.println("This not happened");
                    dispose();
            }
        });
    }
}