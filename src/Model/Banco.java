
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * BancoDao.java
 */

package Model;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Amarildo dos Santos de lima
 *
 */

public class Banco {
    
    /** Creates a new instance of BancoDao */
    public Banco() {
        this("Boleto");                                                         // Nome da Pasta do Banco de Dados
    }
    
    public Banco(String BoletoName) {
        this.dbName = BoletoName;
        setDBSystemDir();
        dbProperties = loadDBProperties();
        String driverName = dbProperties.getProperty("derby.driver"); 
        loadDatabaseDriver(driverName);
        if(!dbExists()) {
            createDatabase();
        }
    }
    
    private boolean dbExists() {
        boolean bExists = false;
        String dbLocation = getDatabaseLocation();
        File dbFileDir = new File(dbLocation);
        if (dbFileDir.exists()) {
            bExists = true;
        }
        return bExists;
    }
    
    private void setDBSystemDir() {
    // decide on the db system directory
    // String userHomeDir = System.getProperty("user.home", ".");               // Neste Caso O Systema Decide Diretorio
       String userHomeDir = System.getProperty(".","C:/Amarildo/Programas/Java/java_DeskTop/");             // Caminho Do Banco
       String systemDir = userHomeDir + "Boleto/BancoDeDados";
       System.setProperty("derby.system.home", systemDir);
    // create the db system directory
        File fileSystemDir = new File(systemDir);
        fileSystemDir.mkdir();
    }
    
    private void loadDatabaseDriver(String driverName) {
        // load Derby driver
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    private Properties loadDBProperties() {
        InputStream dbPropInputStream = null;
        dbPropInputStream = Banco.class.getResourceAsStream("Configuration.properties");
        dbProperties = new Properties();
        try {
            dbProperties.load(dbPropInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dbProperties;
    }
    
    private boolean createDatabase() {
        boolean bCreated = false;
        Connection dbConnection = null;
        String dbUrl = getDatabaseUrl();
        dbProperties.put("create", "true");
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
            bCreated = createTables(dbConnection);
        } catch (SQLException ex) {
        // Inserir Mensagens de Erro
        }
        dbProperties.remove("create");
        return bCreated;
    }

   
    private boolean createTables(Connection dbConnection) {
        boolean bCreatedTables = false;
        Statement statement = null;
        try {
            statement = dbConnection.createStatement();
            statement.execute(strCreateTableTipo00);
            statement.execute(strCreateTableTipo01);
            statement.execute(strCreateTableTipo02);
            statement.execute(strCreateTableTipo03);
            statement.execute(strCreateTableFac);
            bCreatedTables = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bCreatedTables;
    }

    public void executeSQL_BdClear(String instrucao) {
       try {
        statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate(instrucao);
       }
       catch(SQLException sqlex){
       //System.out.println("InstruçãoSql = "+instrucao);
       JOptionPane.showMessageDialog(null,"nao foi possivel executar = "+sqlex
       +"o sql passado foi = "+ instrucao);
       }
    }
    
    public void executeSQL_BdAppend(String instrucao) {
       try {
        statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.executeUpdate(instrucao);
       }
       catch(SQLException sqlex){
       //System.out.println("InstruçãoSql = "+instrucao);
       JOptionPane.showMessageDialog(null,"nao foi possivel executar = "+sqlex
       +"o sql passado foi = "+ instrucao);
       }
    }

    public void executeSQL_BdNavigator(String instrucao) {
       try {
        statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(instrucao);
       }
       catch(SQLException sqlex){
       //System.out.println("InstruçãoSql = "+instrucao);
       JOptionPane.showMessageDialog(null,"nao foi possivel executar = "+sqlex
       +"o sql passado foi = "+ instrucao);
       }
    }
    
    public boolean connect() {
        String dbUrl = getDatabaseUrl();
        try {
            dbConnection = DriverManager.getConnection(dbUrl, dbProperties);
           // stmtSaveNewRecord = dbConnection.prepareStatement(strSaveAddress, Statement.RETURN_GENERATED_KEYS);
            isConnected = dbConnection != null;
        } catch (SQLException ex) {
            isConnected = false;
        }
        return isConnected;
    }
    
    private String getHomeDir() {
        return System.getProperty("user.home");
    }
    
    public void disconnect() {
        if(isConnected) {
            String dbUrl = getDatabaseUrl();
            dbProperties.put("shutdown", "true");
            try {
                DriverManager.getConnection(dbUrl, dbProperties);
            } catch (SQLException ex) {
            }
            isConnected = false;
        }
    }
    
    public String getDatabaseLocation() {
        String dbLocation = System.getProperty("derby.system.home") + "/" + dbName;
        return dbLocation;
    }
    
    public String getDatabaseUrl() {
        String dbUrl = dbProperties.getProperty("derby.url") + dbName;
        return dbUrl;
    }
    
// Começo Metodo Main Apenas Para teste de Conexão
 /*
    public static void main(String[] args) {
        BancoDao db = new BancoDao();
        System.out.println(db.getDatabaseLocation());
        System.out.println(db.getDatabaseUrl());
        db.connect();
        db.disconnect();
    }
  */
  //Final  Metodo Main Apenas Para teste de Conexão

    private Connection dbConnection;
    private Properties dbProperties;
    private boolean isConnected;
    private String dbName;
    private PreparedStatement stmtSaveNewRecord;
    public Statement statement;
    public ResultSet resultset;


    
    private static final String strCreateTableTipo00 =
      "Create Table App.Tipo00 (" +
      " TipoReg         VarChar(002), " +
      " Lixo1           VarChar(005), " +
      " Agencia         VarChar(005), " +
      " Conta           VarChar(008), " +
      " Carteira        VarChar(005), " +
      " CodCedente      VarChar(009), " +
      " Especie         VarChar(002), " +
      " Id              VarChar(025)  " +
      ")";

    private static final String strCreateTableTipo01 =
      "Create Table App.Tipo01 (" +
      " TipoReg         VarChar(002), " +
      " Cep             VarChar(008), " +
      " Processo        VarChar(008), " +
      " NossoNum        VarChar(014), " +
      " DigNosso        VarChar(001), " +
      " Nome            VarChar(040), " +
      " Cpf             VarChar(014), " +
      " Endereco        VarChar(055), " +
      " Cidade          VarChar(020), " +
      " Estado          VarChar(002), " +
      " Vencimento      VarChar(010), " +
      " Limite          VarChar(010), " +
      " Telefone        VarChar(012), " +
      " CodDevedor      VarChar(008), " +
      " CodCredor       VarChar(004), " +
      " Remessa         VarChar(006), " +
      " Brancos         Varchar(052), " +
      " Filial01        Varchar(070), " +
      " Filial02        VarChar(070), " +
      " Filial03        VarChar(070), " +
      " Filial04        VarChar(070), " +
      " Filial05        VarChar(070), " +
      " Filial06        VarChar(070), " +
      " Filial07        VarChar(070), " +
      " Titulo1         VarChar(050), " +
      " Titulo2         VarChar(050), " +
      " Contrato        VarChar(020), " +
      " Desconto        Varchar(003), " +
      " ValorTot        Varchar(010), " +
      " CodDivida       VarChar(010), " +
      " Lixo_1_To1      VarChar(010), " +
      " NossoNum2       VarChar(016), " +
      " Brancos01       VarChar(009), " +
      " Id              VarChar(025), " +
      " NumPostal       VarChar(020), " +
      " CodCep          VarChar(001), " +
      " CodTri          Varchar(001), " +
      " CodCif          Varchar(034), " +
      " Categoria       VarChar(005)  " +
      ")";
     
    private static final String strCreateTableTipo02 =
      "Create Table App.Tipo02 (" +
      " TipoReg         VarChar(002), " +
      " Vencimento      VarChar(010), " +
      " ValorAtual      VarChar(010), " +
      " Desconto        VarChar(010), " +
      " ValorDesc       VarChar(010), " +
      " Contrato        VarChar(020), " +
      " CodSegu2        VarChar(010), " +
      " PercDesc2       VarChar(010), " +
      " Id              VarChar(025)  " +
      ")";

    private static final String strCreateTableTipo03 =
      "Create Table App.Tipo03 (" +
      " TipoReg         VarChar(002), " +
      " Parcelas        VarChar(002), " +
      " ValorEntr       VarChar(010), " +
      " ValorParc       VarChar(010), " +
      " ValorAvist      VarChar(010), " +
      " CodSeg          VarChar(010), " +
      " PercDesc3       VarChar(010), " +
      " Cet_Mes         VarChar(010), " +
      " Cet_Ano         Varchar(010), " +
      " Id              VarChar(025)  " +
      ")";

    private static final String strCreateTableFac =
      "Create Table App.Hoepers (" +
      " Codigo_Dr       VarChar(002), " +
      " Cod_Admin       VarChar(008), " +
      " Num_Cartao      VarChar(012), " +
      " Num_Lote        VarChar(011), " +
      " Cod_Postag      VarChar(008), " +
      " Cep_Origem      VarChar(008), " +
      " N_Contrato      VarChar(010), " +
      " Categoria       VarChar(005), " +
      " NumPostag       Varchar(011), " +
      " DataPostag      Varchar(010), " +
      " NumPostPro      Varchar(011), " +
      " DataProg        Varchar(010)  " +
      ")";
    
    
    
/*
   private static final String strSaveAddress =
     "INSERT INTO APP.Tipo01 " +
     "(Nome, Endereco, Numero, Bairro, Cidade, Estado, Cep) "+
     "VALUES ('Nome', 'Endereco', 'Numero', 'Bairro', 'Cidade', 'Sp', 'Cep')";
*/


}
