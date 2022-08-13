package CRUDJava;

import javax.swing.*;
import java.sql.ResultSet;

public class createProduct extends JFrame{
    private JTextField codeProduct;
    private JTextField stockProduct;
    private JTextField priceProduct;
    private JTextField catProduct;
    private JTextField provProduct;
    private JButton savePButton;
    private JButton cancelarBProduct;
    private JPanel createPPanel;
    private JTextArea descriptionProduct;
    private JButton consultarButton;

    JTextField[] jTexts = {stockProduct, priceProduct, catProduct, provProduct};


    public createProduct(int option){
        /*
        0 -> CREATE PRODUCT
        1 -> UPDATE PRODUCT
        2 -> DELETE PRODUCT
         */

        // Connection to core of SQL
        connection co = new connection();
        co.setTable("producto");
        co.setColumn("codPRO");

        // Characteristics of Window
        setContentPane(createPPanel);
        setVisible(true);
        setSize(500,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Default Title
        String titleWindow = "CREATE PRODUCT";

        // Occult buttons with option choose
        consultarButton.setVisible(false);

        if (option == 1 | option == 2){
            consultarButton.setVisible(true);
            this.enabledAll();
        }

        // Complement query of CRUD
        consultarButton.addActionListener(e ->{
            if (option == 1){
                for (JTextField jT : jTexts){
                    jT.setEnabled(true);
                    descriptionProduct.setEnabled(true);
                }
            }
            co.setData(codeProduct.getText());
            ResultSet rs = co.qryData();
            if (rs != null){
                try{
                    if(rs.next()){
                        descriptionProduct.setText(rs.getString("descPRO"));
                        stockProduct.setText(String.valueOf(rs.getInt("stockPRO")));
                        priceProduct.setText(String.valueOf(rs.getDouble("pvpPRO")));
                        catProduct.setText(rs.getString("codCTG_FK"));
                        provProduct.setText(rs.getString("rucPRV_FK"));
                    }

                } catch (Exception eqry){
                    System.out.println("[-] ERROR, load data into JTextFields Failed !");
                }

                savePButton.setEnabled(true);
                cancelarBProduct.setEnabled(true);
            } else{
                JOptionPane.showMessageDialog(null,"PRODUCT NOT FOUND!", "Failed",JOptionPane.ERROR_MESSAGE);
            }
        });

        // CRUD of Product
        savePButton.addActionListener(e ->{
            // Get data of all JTextField to prepare any Sql Statement
            String codeP = codeProduct.getText();
            String descriptionP = descriptionProduct.getText();
            int stockP = Integer.parseInt(stockProduct.getText());
            double priceP = Double.parseDouble(priceProduct.getText());
            String categoryP = catProduct.getText();
            String providerP = provProduct.getText();

            try{
                if(option == 0){
                    co.createProductSql(codeP, descriptionP, stockP, priceP, categoryP, providerP);
                    JOptionPane.showMessageDialog(null,"CREATE SUCCESSFULLY", "CREATE",JOptionPane.INFORMATION_MESSAGE);
                } else if (option == 1){
                    co.updateProductSql(codeP, descriptionP, stockP, priceP, categoryP, providerP);
                    JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFULLY", "Update",JOptionPane.INFORMATION_MESSAGE);
                    this.enabledAll();
                } else {
                    co.deleteData();
                    JOptionPane.showMessageDialog(null,"DELETE SUCCESSFULLY", "Delete",JOptionPane.INFORMATION_MESSAGE);
                }
                this.cleanAll();
            } catch (Exception exc){
                System.out.println("Exception: " + exc);
            }
        });

        // Cancel Button
        cancelarBProduct.addActionListener( e -> this.cleanAll());

        // Show more characteristics of Window
        switch (option){
            case 1:
                titleWindow = "UPDATE PRODUCT";
                savePButton.setText("Actualizar");
                break;
            case 2:
                titleWindow = "DELETE PRODUCT";
                savePButton.setText("Eliminar");
                break;
        }

        setTitle(titleWindow);
    }

    public void cleanAll(){
        for (JTextField jT : jTexts){
            jT.setText("");
        }
        descriptionProduct.setText("");
        codeProduct.setText("");
    }

    public void enabledAll(){
        cancelarBProduct.setEnabled(false);
        savePButton.setEnabled(false);
        for (JTextField jT : jTexts){
            jT.setEnabled(false);
        }
        descriptionProduct.setEnabled(false);
    }
}