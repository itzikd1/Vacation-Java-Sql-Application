package Model;

import Model.Excpetions.UserNameIsntValidException;
import Model.Excpetions.V4UException;

import java.sql.*;

class UsersTable implements ITable {

    protected String url_DB;

    protected UsersTable(String url_DB, Database db) {
        this.url_DB = url_DB;
        //create Users table
        String sql = "CREATE TABLE IF NOT EXISTS Users(\n"
                + "	UserName varchar PRIMARY KEY,\n"
                + "	Password varchar NOT NULL,\n"
                + "    Birthdate varchar , \n"
                + "    FirstName varchar, \n"
                + "    LastName varchar, \n"
                + "    City varchar \n"
                + ");";
        Connection conn = db.connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.disconnect(conn);
    }


    @Override
    public boolean insert(Database db, Object[] data) throws V4UException {
        boolean flag = false;
        Connection conn = db.connect();
        String details = "(";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                details += rsmd.getColumnName(i) + ",";
            }
            String fixedDetails = details.substring(0, details.length() - 1) + ")";
            String valuesPart = "VALUES(?";
            int numOfValues = rsmd.getColumnCount();
            while (numOfValues != 1) {
                valuesPart += ",?";
                numOfValues--;
            }
            valuesPart += ")";
            String sql = "INSERT INTO Users " + fixedDetails + " " + valuesPart;//REMOVE THE LAST ","
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < data.length; i++)
                pstmt.setString(i + 1, ((String) (data[i]).toString()));
            pstmt.executeUpdate();
            flag = true;

        }catch (SQLException se) {
            throw new UserNameIsntValidException();
        }
        catch
         (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            db.disconnect(conn);
        }
        return flag;
    }

    @Override
    public boolean update(Database db, String id, Object[] data) {
        boolean flag = false;
        String sql = "UPDATE Users SET Password = " + "'" + data[1] + "', "
                + "Birthdate = " + "'" + data[2] + "', "
                + "FirstName = " + "'" + data[3] + "', "
                + "LastName = " + "'" + data[4] + "', "
                + "City = " + "'" + data[5] + "'"
                + "WHERE UserName LIKE " + "'" + data[0] + "'";
        Connection conn = null;
        try {
            conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            db.disconnect(conn);
        }
        return flag;
    }

    @Override
    public boolean delete(Database db, String id) {
        boolean flag = false;
        String sql = "DELETE FROM Users WHERE UserName = ?";
        Connection conn = null;
        User user = (User) read(db, id);
        if (user != null) {
            try {
                conn = db.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                flag = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                db.disconnect(conn);
            }
        } else {
            System.out.println("Username :" + id + " - not exsists in the DB");
        }
        return flag;
    }

    @Override
    public Object read(Database db, String id) {
        String sql = "SELECT UserName, Password, Birthdate, FirstName, LastName, city "
                + "FROM Users Where UserName = ?";
        User user = null;
        Connection conn = null;
        try {
            conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setString(1, id);
            //
            ResultSet rs = pstmt.executeQuery();

            user = new User(rs.getString("UserName"),
                    rs.getString("Password"),
                    rs.getString("Birthdate"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("City"));
            user.toString();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            db.disconnect(conn);
        }
        return user;
    }

    public void print(Database db) {
        String sql = "SELECT * FROM Users";
        Connection conn = null;
        try {
            conn = db.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("Birthdate"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("City"));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            db.disconnect(conn);
        }
    }
}
