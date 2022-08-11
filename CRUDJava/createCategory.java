package CRUDJava;

import javax.swing.*;

public class createCategory extends  JFrame{
    private JTextField descriptionText;
    private JTextField nameText;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel categoryWindow;
    private JLabel title;
    private JLabel nameTitle;
    private JLabel descriptionTitle;
    private JButton queryButton;

    public createCategory(int option){
        setContentPane(categoryWindow);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        queryButton.setVisible(false);

        String titleWindow = "CREATE CATEGORY";

        if (option == 1 | option == 2){
            queryButton.setVisible(true);
            descriptionText.setEnabled(false);
            cancelButton.setEnabled(false);
            saveButton.setEnabled(false);
        }

        switch (option){
            case 0:
                break;
            case 1:
                titleWindow = "UPDATE PRODUCT";
                saveButton.setText("Actualizar");
                break;
            case 2:
                titleWindow = "DELETE PRODUCT";
                saveButton.setText("Eliminar");
        }
        setTitle(titleWindow);
    }
}