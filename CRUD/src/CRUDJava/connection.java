package CRUDJava;

import java.sql.*;

public class connection {
    Connection conn;
    private String table;
    private String column ;
    private String data;
    private PreparedStatement pS;
    private String userName;
    private String password;
    private int errorCode = 0;
    public connection(){
        this.getConnection();
    }


    // ---------------------------------------- Create connection to DB ----------------------------------------
    public void getConnection(){
        try{
            String stringConnection = "jdbc:mysql://localhost/[]";
            this.conn = DriverManager.getConnection(stringConnection, "[]", "[]");
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public ResultSet loginUser(){
        try{
            String sqlLogin = "SELECT * FROM usuarios WHERE" + " nomUSR = '" + this.userName + "' AND passwordUSR = '" + this.password + "'";
            this.pS = conn.prepareStatement(sqlLogin);
            return this.pS.executeQuery();
        } catch (Exception ty){
            System.out.println("Exception: " + ty);
            return null;
        }
    }
    public ResultSet qryLikeData(){
        try{
            String qryAll = "SELECT * FROM " + this.table + " WHERE " + this.column + " LIKE '%" + this.data + "%'";
            this.pS = conn.prepareStatement(qryAll);
            return pS.executeQuery();

        }  catch (Exception ey){
            System.out.println("Exception:" + ey);
            return null;
        }
    }

    public ResultSet qryAllData(){
        try{
            String qryAll = "SELECT * FROM " + this.table;
            this.pS = conn.prepareStatement(qryAll);
            return pS.executeQuery();

        }  catch (Exception ey){
            System.out.println("Exception:" + ey);
            return null;
        }
    }

    // ---------------------------------------- Query of all Tables ----------------------------------------
    public ResultSet qryData(){
        try{
            String sqlQry = "SELECT * FROM " + this.table + " WHERE " + this.column + " = '" + this.data + "'";
            this.pS = conn.prepareStatement(sqlQry);
            return this.pS.executeQuery();

        } catch (Exception e){
            System.out.println("Exception: " + e);
            return null;
        }
    }

    // ---------------------------------------- Delete of all Tables ----------------------------------------
    public void deleteData(){
        try{
            String sqlDelete = "DELETE FROM " + this.table + " WHERE " + this.column + " = '" + this.data + "'";
            this.pS = conn.prepareStatement(sqlDelete);
            this.pS.executeUpdate();
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
    }


    // ---------------------------------------- CREATE-UPDATE PRODUCT ----------------------------------------
    public int createProductSql(String codPro, String descPro, int stockPro, double pvpPro,
                              String FKcodCTG, String FKrucPRV){
        try{
            String createSql = "INSERT INTO producto(codPRO, descPRO, stockPRO, pvpPRO, codCTG_FK, rucPRV_FK) " +
                    "VALUES (?,?,?,?,?,?)";
            this.pS = conn.prepareStatement(createSql);
            this.pS.setString(1, codPro);
            this.pS.setString(2, descPro);
            this.pS.setInt(3,stockPro);
            this.pS.setDouble(4, pvpPro);
            this.pS.setString(5, FKcodCTG);
            this.pS.setString(6, FKrucPRV);
            this.pS.executeUpdate();
        } catch (SQLException e){
            this.errorCode = e.getErrorCode();
        }
        return this.errorCode;
    }
    public int updateProductSql(String codPro, String descPro, int stockPro, double pvpPro,
                              String FKcodCTG, String FKrucPRV){
        try{
            String sqlUpdate = "UPDATE producto SET codPRO = ?, descPRO = ?, stockPRO = ?, pvpPRO = ?, codCTG_FK = ?, " +
                    "rucPRV_FK = ? WHERE codPRO = ?";
            this.pS = conn.prepareStatement(sqlUpdate);
            this.pS.setString(1,  codPro );
            this.pS.setString(2,  descPro);
            this.pS.setInt(3, stockPro);
            this.pS.setDouble(4, pvpPro);
            this.pS.setString(5, FKcodCTG );
            this.pS.setString(6, FKrucPRV);
            this.pS.setString(7, this.data);
            this.pS.executeUpdate();

        } catch (SQLException e){
            this.errorCode = e.getErrorCode();
        }

        return this.errorCode;
    }


    // ---------------------------------------- CREATE-UPDATE PROVIDER ----------------------------------------
    public int createProviderSql(String rucPRV, String nomPRV, String telfPRV){
        try{
            String createSqlP = "INSERT INTO proveedor(rucPRV, nomPRV, telfPRV) VALUES (?,?,?)";
            this.pS = conn.prepareStatement(createSqlP);
            this.pS.setString(1, rucPRV);
            this.pS.setString(2, nomPRV);
            this.pS.setString(3, telfPRV);
            this.pS.executeUpdate();

        } catch (SQLException e){
            this.errorCode = e.getErrorCode();
        }
        return this.errorCode;
    }
    public int updateProviderSql(String rucPRV, String nomPRV,  String telfPRV){
        try{
            String sqlUpdate = "UPDATE  proveedor SET rucPRV = ?, nomPRV = ?, telfPRV = ? WHERE rucPRV = ?";
            this.pS = conn.prepareStatement(sqlUpdate);
            this.pS.setString(1, rucPRV);
            this.pS.setString(2, nomPRV);
            this.pS.setString(3, telfPRV);
            this.pS.setString(4, this.data);
            this.pS.executeUpdate();
        } catch (SQLException e){
            this.errorCode = e.getErrorCode();
        }
        return this.errorCode;
    }


    // ---------------------------------------- U Category ----------------------------------------
    public int createCategorySql(String codCTG, String descCTG){
        try{
            String createC = "INSERT INTO categoria(codCTG, descCTG) VALUES (?,?)";
            this.pS = conn.prepareStatement(createC);
            this.pS.setString(1, codCTG);
            this.pS.setString(2, descCTG);
            this.pS.executeUpdate();
        } catch (SQLException e){
            this.errorCode = e.getErrorCode();
        }

        return this.errorCode;
    }

    public int UpdateCategorySql(String codCTG, String descCTG){
        try{
            String sqlUpdate = "UPDATE categoria SET codCTG = ?, descCTG = ? WHERE codCTG = ?";
            this.pS = conn.prepareStatement(sqlUpdate);
            this.pS.setString(1, codCTG);
            this.pS.setString(2, descCTG);
            this.pS.setString(3, this.data);
            this.pS.executeUpdate();
        } catch (SQLException e){
            this.errorCode = e.getErrorCode();
        }
        return this.errorCode;
    }


    // ---------------------------------------- Getters and Setters ----------------------------------------
    public String getTable() {
        return table;
    }
    public void setTable(String table) {
        this.table = table;
    }
    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordName) {
        this.password = passwordName;
    }
}
