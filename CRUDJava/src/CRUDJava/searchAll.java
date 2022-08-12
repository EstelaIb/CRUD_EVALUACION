package CRUDJava;

import javax.swing.*;

public class searchAll extends JFrame{
    private JComboBox optionsSearch;
    private JTextField stringToSearch;
    private JButton searchButton;
    private JTable productsTables;
    private JPanel readW;

    public searchAll(){
        setContentPane(readW);
        setSize(500,250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Inventory");
    }
}