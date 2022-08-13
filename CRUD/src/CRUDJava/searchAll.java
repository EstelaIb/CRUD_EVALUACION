package CRUDJava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicReference;

public class searchAll extends JFrame{
    private JComboBox optionsSearch;
    private JTextField stringToSearch;
    private JButton searchButton;
    private JTable productsTables;
    private JPanel readW;
    private String tableName;
    private int len;
    public searchAll(int option){
        // Characteristics of Window
        setContentPane(readW);
        setSize(500,250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Inventory");
        connection co = new connection();

        if (option == 0){
            co.setTable("producto");
        } else if (option == 1){
            co.setTable("categoria");
        } else {
            co.setTable("proveedor");
        }

        AtomicReference<ResultSet> rS = new AtomicReference<>(co.qryAllData());

        showData(option, rS.get());
        searchButton.addActionListener(e -> {
            int optionSearch = optionsSearch.getSelectedIndex();
            String column;
            if (option == 0){
                column = (optionSearch == 0) ? "codPRO" : "descPRO";
            } else if (option == 1){
                column = (optionSearch == 0) ? "codCTG" : "descCTG";
            } else {
                column = (optionSearch == 0) ? "rucPRV" : "nomPRV";
            }

            String data = stringToSearch.getText();
            co.setData(data);
            co.setColumn(column);
            rS.set(co.qryLikeData());


            showData(option, rS.get());
        });
    }


    private void showData(int option, ResultSet rS){
        String[] columnsProduct = {"CODIGO", "DESCRIPCION", "STOCK", "PVP", "CATEGORIA", "PROVEEDOR"};
        String[] columnsProvider = {"RUC", "NOMBRE", "TELEFONO"};
        String[] columnsCategory = {"CODIGO", "DESCRIPCION"};

        DefaultTableModel t = new DefaultTableModel();
        if(option == 0){
            for(String n: columnsProduct){
                t.addColumn(n);
            }
            this.tableName = "producto";
            this.len = 6;
        } else if(option == 2){
            for(String n: columnsProvider){
                t.addColumn(n);
            }
            this.tableName = "proveedor";
            this.len = 3;
        } else {
            for(String n: columnsCategory){
                t.addColumn(n);
            }
            this.tableName = "categoria";
            this.len = 2;
        }

        this.productsTables.setModel(t);
        String[] data = new String[this.len];

        if (rS != null){
            try{
                while (rS.next()){
                    for (int i = 0; i < this.len; i++){
                        data[i] = rS.getString(i+1);
                    }
                    t.addRow(data);
                }
                this.productsTables.setModel(t);
            } catch (Exception er){
                System.out.println("Exception: " + er);
            }

        } else {
            System.out.println("Empty");
        }
    }
}