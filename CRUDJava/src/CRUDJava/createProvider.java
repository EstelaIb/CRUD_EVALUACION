package CRUDJava;

import javax.swing.*;

public class createProvider extends JFrame{
    private JPanel providerWindow;
    private JTextField rucText;
    private JTextField nomText;
    private JTextField lastNameText;
    private JTextField telfText;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel title;
    private JButton queryButton;

    public createProvider(int option){
        setContentPane(providerWindow);
        setVisible(true);
        setSize(350,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        String titleWindow = "CREATE PROVIDER";
        JTextField[] jTexts = {nomText, lastNameText, telfText};
        queryButton.setVisible(false);

        if (option == 1 | option == 2){
            for(JTextField jT : jTexts){
                jT.setEnabled(false);
            }
            queryButton.setVisible(true);
            saveButton.setEnabled(false);
            cancelButton.setEnabled(false);
        }

        switch (option){
            case 0:
                break;
            case 1:
                titleWindow = "UPDATE PROVIDER";
                saveButton.setText("Actualizar");
                break;
            case 2:
                titleWindow = "DELETE PROVIDER";
                saveButton.setText("Eliminar");
                break;
        }
        setTitle(titleWindow);
    }
}