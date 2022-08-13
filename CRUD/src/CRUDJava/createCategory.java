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
            co.setData(nameText.getText().trim());
            ResultSet rSC = co.qryData();
            if (rSC != null){
                try{
                    if (rSC.next()){
                        descriptionText.setText(rSC.getString("descCTG").trim());
                    }
                } catch (Exception ev){
                    JOptionPane.showMessageDialog(null, "THIS NOT HAPPENED", "EASTER EGG", JOptionPane.QUESTION_MESSAGE);
                }
                saveButton.setEnabled(true);
                cancelButton.setEnabled(true);
            } else{
                JOptionPane.showMessageDialog(null,"PROVIDER NOT FOUND!", "Failed",JOptionPane.ERROR_MESSAGE);
            }
        });

        // CRUD Category
        saveButton.addActionListener(e ->{
            String codCTG = nameText.getText().trim();
            String descCTG = descriptionText.getText().trim();
            Integer code;
            try{
                if (option == 0){
                    code = co.createCategorySql(codCTG, descCTG);
                    if (code.equals(0)){
                        JOptionPane.showMessageDialog(null,"CREATE SUCCESSFULLY", "CREATE",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"CODE ERROR: " + code, "ERROR",JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null,"CATEGORY NOT CREATED", "ERROR",JOptionPane.ERROR_MESSAGE);
                    }

                } else if (option == 1){
                    code = co.UpdateCategorySql(codCTG, descCTG);
                    if (code.equals(0)){
                        JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY", "UPDATE",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"CODE ERROR: " + code, "ERROR",JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null,"CATEGORY NOT UPDATED", "ERROR",JOptionPane.ERROR_MESSAGE);
                    }

                    this.disabledAll();
                } else{
                    co.deleteData();
                    JOptionPane.showMessageDialog(null,"DELETE SUCCESSFULLY", "DELETE",JOptionPane.INFORMATION_MESSAGE);
                    this.disabledAll();
                }
                this.cleanAll();
            } catch (Exception et) {
                JOptionPane.showMessageDialog(null, "THIS NOT HAPPENED", "EASTER EGG", JOptionPane.QUESTION_MESSAGE);
            }
        });

        cancelButton.addActionListener(e ->{
            this.cleanAll();
            this.disabledAll();
        });
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