package Model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

class Database {

    private String url;
    private Map<String, Table> tables;


    protected Database(String url) {
        this.url = url;
        tables = new HashMap<String,Table>();
    }

    protected String getUrl() {
        return url;
    }

    protected void setUrl(String url) {
        this.url = url;
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
    protected boolean createTables() {//TODO PARAMETERS: NAME AND STRING[] COLUMNNAMES
        boolean flag = false;
        try {
            UsersTable usersTable = new UsersTable(url, this);
            tables.put("Users", usersTable);
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
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
     * this function insert to a specific table a record
     *
     * @param table_name - table name to insert to
     * @param data       - the record in object array
     * @return - true - if succeed, false - if failed
     */
    protected boolean insert(String table_name, Object[] data) {
        System.out.println(data[2]);
        System.out.println(data[2].getClass());
        if (table_name.equals("Users")) {
            UsersTable users = (UsersTable) tables.get("Users");
            return users.insert(this, data);
        } else return false;
    }

    /**
     * this function delete one record by id from a specific table
     *
     * @param table_name - the table name to delete from
     * @param id         - the record that we want to delete
     * @return - true- if succeed, false - if failed
     */
    protected boolean delete(String table_name, String id) {
        if (table_name.equals("Users")) {
            UsersTable users = (UsersTable) tables.get("Users");
            return users.delete(this, id);
        } else return false;
    }

    /**
     * this function return the user by PK
     *
     * @param table_name - the table name
     * @param id         - the record that we want to find
     * @return - true- if succeed, false - if failed
     */
    protected User read(String table_name, String id) {
        if (table_name.equals("Users")) {
            UsersTable users = (UsersTable) tables.get("Users");
            return (User) users.read(this, id);
        } else return null;
    }

    /**
     * this function updates a record with PK=id
     *
     * @param table_name - where
     * @param data       - new data
     * @param id         - PK of the record
     * @return - true- if succeed, false - if failed
     */
    protected boolean update(String table_name, Object[] data, String id) {
        System.out.println(data[2]);
        System.out.println(data[2].getClass());
        if (table_name.equals("Users")) {
            UsersTable users = (UsersTable) tables.get("Users");
            return users.update(this, id, data);
        } else return false;
    }

    protected void print_table(String table_name) {
        if (table_name.equals("Users")) {
            tables.get("Users").print(this);
        }
    }
}
