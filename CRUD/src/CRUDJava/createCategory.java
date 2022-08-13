package CRUDJava;

import jdk.nashorn.internal.runtime.ECMAException;

import javax.swing.*;
import java.sql.ResultSet;

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
        /*
        0 -> CREATE PRODUCT
        1 -> UPDATE PRODUCT
        2 -> DELETE PRODUCT
         */

        // Connection to core of SQ
        setContentPane(categoryWindow);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Connection to core of SQL
        connection co = new connection();
        co.setTable("categoria");
        co.setColumn("codCTG");

        // Query Button Not visible default
        queryButton.setVisible(false);

        // Title Default of Window
        String titleWindow = "CREATE CATEGORY";


        if (option == 1 | option == 2){
            queryButton.setVisible(true);
            this.disabledAll();
        }

        switch (option){
            case 1:
                titleWindow = "UPDATE PRODUCT";
                saveButton.setText("Actualizar");
                break;
            case 2:
                titleWindow = "DELETE PRODUCT";
                saveButton.setText("Eliminar");
        }
        setTitle(titleWindow);

        // Query Button
        queryButton.addActionListener(e -> {
            if(option == 1){
                nameText.setEnabled(true);
                descriptionText.setEnabled(true);
            }
            co.setData(nameText.getText());
            ResultSet rSC = co.qryData();
            if (rSC != null){
                try{
                    if (rSC.next()){
                        descriptionText.setText(rSC.getString("descCTG"));
                    }
                } catch (Exception ev){
                    System.out.println("Exception: " + ev);
                }
                saveButton.setEnabled(true);
                cancelButton.setEnabled(true);
            } else{
                JOptionPane.showMessageDialog(null,"PROVIDER NOT FOUND!", "Failed",JOptionPane.ERROR_MESSAGE);
            }
        });

        // CRUD Category
        saveButton.addActionListener(e ->{
            String codCTG = nameText.getText();
            String descCTG = descriptionText.getText();
            try{
                if (option == 0){
                    co.createCategorySql(codCTG, descCTG);
                    JOptionPane.showMessageDialog(null,"CREATE SUCCESSFULLY", "CREATE",JOptionPane.INFORMATION_MESSAGE);
                } else if (option == 1){
                    co.UpdateCategorySql(codCTG, descCTG);
                    JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY", "UPDATE",JOptionPane.INFORMATION_MESSAGE);
                    this.disabledAll();
                } else{
                    co.deleteData();
                    JOptionPane.showMessageDialog(null,"DELETE SUCCESSFULLY", "DELETE",JOptionPane.INFORMATION_MESSAGE);
                    this.disabledAll();
                }
                this.cleanAll();
            } catch (Exception et) {
                System.out.println("Exception: " + et);
            }
        });

        cancelButton.addActionListener(e -> this.cleanAll());
    }

    private void disabledAll(){
        cancelButton.setEnabled(false);
        saveButton.setEnabled(false);
        descriptionText.setEnabled(false);
    }

    private void cleanAll(){
        nameText.setText("");
        descriptionText.setText("");
    }
}