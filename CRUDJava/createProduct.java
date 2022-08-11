package CRUDJava;

import javax.swing.*;

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


    public createProduct(int option){
        /*
        0 -> CREATE PRODUCT
        1 -> UPDATE PRODUCT
        2 -> DELETE PRODUCT
         */

        setContentPane(createPPanel);
        setVisible(true);
        setSize(500,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextField[] jTexts = {stockProduct, priceProduct, catProduct, provProduct};
        consultarButton.setVisible(false);

        String titleWindow = "CREATE PRODUCT";

        if (option == 1 | option == 2){
            for (JTextField jT : jTexts){
                jT.setEnabled(false);
                descriptionProduct.setEnabled(false);
            }
            consultarButton.setVisible(true);
            cancelarBProduct.setEnabled(false);
            savePButton.setEnabled(false);
        }

        switch (option){
            case 0:
                break;
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
}