package Model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import Model.Excpetions.V4UException;

class Database {

    private String url;
    private Map<String, String[]> fieldsOfTables; // table name -> fields

    /**
     * create the DB class
     * fieldsOfTables [0] is always the PK !!!
     * @param url- to DB
     */
    protected Database(String url) {
        this.url = url;
        fieldsOfTables = new HashMap<String, String[]>();
    }

    protected String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    private boolean runQuery (String sql){
        boolean flag = true;
        Connection conn = connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }
        finally {
            disconnect(conn);
        }
        return flag;
    }

    private String[] runQueryReturnOutput (String sql, String tableName){
        ResultSet result = null;
        Connection conn = connect();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            disconnect(conn);
        }
        return convertResultSet(tableName, result);
    }

    public String[] convertResultSet(String tableName, ResultSet rs){
        String[] fields = fieldsOfTables.get(tableName);
        String[] result = new String[fields.length];

        try {
            int i=0;
            while (rs.next()) {
                result[i] = rs.getString(fields[i]);
                i++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * make a connection with the DB
     *
     * @return the connection
     */
    protected Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            DatabaseMetaData meta = conn.getMetaData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * disconnect from the DB
     *
     * @param conn - connection to disconnect
     */
    protected void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * we should know how many tables we have in our DB, so it is not so generic
     * it will be called just once !
     *
     * @return true if we created tables
     * false if we could not create all
     */
    protected boolean createTables() {
        boolean flag = false;
        String usersSQL = "CREATE TABLE IF NOT EXISTS Users(\n"
                + "	UserName varchar PRIMARY KEY,\n"
                + "	Password varchar NOT NULL,\n"
                + "    Birthdate varchar , \n"
                + "    FirstName varchar, \n"
                + "    LastName varchar, \n"
                + "    City varchar \n"
                + ");";
        String[] usersFields = {"UserName","Password","Birthdate","FirstName","LastName","City"};
        fieldsOfTables.put("Users" , usersFields);
        String purchasesSQL = "CREATE TABLE IF NOT EXISTS Purchases(\n"
                + "	PurchaseID varchar PRIMARY KEY,\n"
                + " VacationID varchar NOT NULL, \n"
                + " BuyerUserName varchar NOT NULL,\n"
                + " SellerUserName varchar NOT NULL, \n"
                + " Price varchar NOT NULL, \n"
                + " PaymentMethod varchar NOT NULL, \n"
                + " CreditCardNum varchar, \n"
                + " PayPalUserName varchar"
                + "  \n"
                + ");";
        String[] purchasesFields = {"PurchaseID","VacationID","BuyerUserName","SellerUserName",
                "Price","PaymentMethod","CreditCardNum", "PayPalUserName"};
        fieldsOfTables.put("Purchases" , purchasesFields);
        String vacationsSQL = "CREATE TABLE IF NOT EXISTS Vacations(\n"
                + " VacationID varchar PRIMARY KEY, \n"
                + "	UserName varchar,\n"
                + "	Departure varchar NOT NULL,\n"
                + " DepartureDate varchar  NOT NULL, \n"
                + " DepartureTime varchar NOT NULL, \n"
                + " Destination varchar NOT NULL, \n"
                + " DestinationDate varchar NOT NULL, \n"
                + " DestinationTime varchar NOT NULL, \n"
                + " ReturnDate varchar, \n" // return flight optional
                + " ReturnTime varchar, \n"// return flight optional
                + " ArrivalDateInDestination varchar, \n"// return flight optional
                + " ArrivalTimeInDestination varchar, \n"// return flight optional
                + " TicketType varchar NOT NULL, \n"
                + " FlightsCompany varchar NOT NULL, \n"
                + " ConnectionCountry varchar, \n"
                + " isBaggageIncluded bit , \n" // o or 1
                + " BaggageOptions varchar \n"
                + " ClassType varchar NOT NULL"
                + "  \n"
                + ");";
        String[] vacationFields = {"VacationID","UserName","Departure","DepartureDate","DepartureTime","Destination",
        "ReturnDate","ReturnTime","ArrivalDateInDestination","ArrivalTimeInDestination","TicketType","FlightsCompany",
                "ConnectionCountry","isBaggageIncluded","BaggageOptions","ClassType"};
        fieldsOfTables.put("Vacations" , vacationFields);
        boolean flag1 = runQuery(usersSQL);
        boolean flag2 = runQuery(purchasesSQL);
        boolean flag3 = runQuery(vacationsSQL);
        return flag1 && flag2 && flag3;
    }

    /**
     * drops table by name
     *
     * @param table_name - table name
     * @return - true if succeed, false if not
     */
    protected boolean dropTable(String table_name) {
        boolean flag = false;
        String sql = "DROP TABLE " + table_name + ";";
        Connection conn = connect();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * this function insertNewUser to a specific table a record
     *
     * @param tableName - table name to insertNewUser to
     * @param data       - the record in object array
     * @return - true - if succeed, false - if failed
     */
    protected boolean insert(String tableName, String[] data) throws  V4UException{
        String[] fields = fieldsOfTables.get(tableName);
        String fieldsForSQL = "(";
        String values = "(";
        for (int i = 0 ; i < fields.length ; i++){
            fieldsForSQL = fieldsForSQL + fields[i] + ",";
            values = values + data[i] + ",";
        }
        fieldsForSQL = fieldsForSQL.substring(0, fieldsForSQL.length()-1) + ")";
        values = values.substring(0, values.length()-1) + ")";

        String sql = "INSERT INTO " + tableName + " " +  fieldsForSQL + " VALUES " + values;

        return runQuery(sql);
    }

    public String[] read(String data, String tableName) {
        String field = fieldsOfTables.get(tableName)[0];
        String sql = "SELECT * FROM " + tableName + " WHERE " + field + " = ?";

        return runQueryReturnOutput(sql,tableName);
    }

    /**
     * deletation by primary key (fields[0]
     * @param data
     * @param tableName
     * @return
     */
    public boolean delete(String data, String tableName) {
        String[] fields = fieldsOfTables.get(tableName);
        String sql = "DELETE FROM " + tableName + "WHERE " + fields[0] + " = '" + data + "'";
        return runQuery(sql);

    }

    public boolean update (String[] data, String tableName) {
        String[] fields = fieldsOfTables.get(tableName);
        String sql = "UPDATE " + tableName + "SET ";
        for (int i=0; i<data.length; i++) {
            sql = sql + fields[i] + " = '" + data[i] + "',";
        }
        sql=sql.substring(0,sql.length()-1);
        sql = sql + " WHERE " + fields[0] + "LIKE '" + data[0] + "'";
        return runQuery(sql);
    }

//    protected void print_table(String table_name) {
//        if (table_name.equals("Users")) {
//            tables.get("Users").print(this);
//        }
//    }
}
