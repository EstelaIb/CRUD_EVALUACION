package CRUDJava;

import javax.swing.*;
import java.sql.ResultSet;

public class createProvider extends JFrame{
    private JPanel providerWindow;
    private JTextField rucText;
    private JTextField nomText;
    private JTextField telfText;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel title;
    private JButton queryButton;

    JTextField[] jTexts = {nomText, telfText};

    public createProvider(int option){
        /*
        0 -> CREATE PRODUCT
        1 -> UPDATE PRODUCT
        2 -> DELETE PRODUCT
         */

        // Connection to core of SQL
        connection co = new connection();
        co.setTable("proveedor");
        co.setColumn("rucPRV");

        // Characteristics of Window
        setContentPane(providerWindow);
        setVisible(true);
        setSize(350,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Default Title
        String titleWindow = "CREATE PROVIDER";

        // No visible 'queryButton' default
        queryButton.setVisible(false);

        // Disabled some objects
        if (option == 1 | option == 2){
            queryButton.setVisible(true);
            this.enabledAll();
        }

        switch (option){
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


        // Query Button
        queryButton.addActionListener(e ->{
            if (option == 1){
                for(JTextField jT : jTexts){
                    jT.setEnabled(true);
                }
            }
            co.setData(rucText.getText());
            ResultSet rS = co.qryData();
            if (rS != null) {
                try {
                    if (rS.next()) {
                        rucText.setText(rS.getString("rucPRV"));
                        nomText.setText(rS.getString("nomPRV"));
                        telfText.setText(rS.getString("telfPRV"));
                    }
                } catch (Exception eq) {
                    System.out.println("Exception" + eq);
                }
                saveButton.setEnabled(true);
                cancelButton.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null,"PROVIDER NOT FOUND!", "Failed",JOptionPane.ERROR_MESSAGE);
            }
        });

        // CRUD Provider
        saveButton.addActionListener(e -> {
            String rucPRV = rucText.getText();
            String namePRV = nomText.getText();

            String phonePRV = telfText.getText();

            try{
                if (option == 0){
                    co.createProviderSql(rucPRV, namePRV, phonePRV);
                    JOptionPane.showMessageDialog(null,"CREATE SUCCESSFULLY", "CREATE",JOptionPane.INFORMATION_MESSAGE);
                } else if (option == 1){
                    co.updateProviderSql(rucPRV, namePRV, phonePRV);
                    JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY", "UPDATE",JOptionPane.INFORMATION_MESSAGE);
                    this.enabledAll();
                } else {
                    co.deleteData();
                    JOptionPane.showMessageDialog(null,"DELETE SUCCESSFULLY", "DELETE",JOptionPane.INFORMATION_MESSAGE);
                }
                this.cleanAll();
            } catch (Exception ep){
                System.out.println("Exception :" + ep);
            }
        });

        // Cancel Button
        cancelButton.addActionListener(e -> this.cleanAll());
    }


    private void cleanAll(){
        for(JTextField jT : jTexts){
            jT.setText("");
        }
        rucText.setText("");
    }

    private void enabledAll(){
        for(JTextField jT : jTexts){
            jT.setEnabled(false);
        }
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }
}