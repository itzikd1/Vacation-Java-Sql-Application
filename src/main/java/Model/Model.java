package Model;

import Model.Excpetions.V4UException;
import Model.Excpetions.WrongDetailsException;

public class Model {

    private static Model singleton = null;
    public Database database;
    public User user;

    private Model() {

        createDataBase();
        //before_hagasha();
        createTables();

    }

    public static Model getInstance() {
        if (singleton == null)
            singleton = new Model();
        return singleton;

    }

    private void createDataBase() {

        database = new Database("jdbc:sqlite:Vaction4U.sqlite");
    }

    private void createTables() {
        database.createTables();
    }


    public void insert(String table_name, String[] data) throws V4UException {
        database.insert(table_name, data);
    }

    public boolean delete(String table_name, String id) {
        return database.delete(table_name, id);
    }

    public Object read(String table_name, String pk) {
        String[] result = database.read(table_name, pk);
        Object ans= null;
        switch (table_name){
            case "Users":
                String Username = result [0];
                String Password = result [1];
                String BDay = result [2];
                String FName = result [3];
                String LName = result [4];
                String City = result [5];
                ans = new User (Username, Password, BDay, FName, LName, City);
                break;
                // todo: add other cases
        }
        return ans;
    }

    public boolean update(String table_name, String[] data) {
        return database.update(data, table_name);
    }


    public boolean confirm(String table_name, String user, String password) {
        String[] userDetails = database.read(user, table_name);
        if (user == null)
            return false;
        else return this.user.getPassword().equals(password);
    }

    public String[] readConnectedUser() {
        User user = (User) read("Users", this.user.getUsername());
        return user.getDetails();
    }


    public void before_hagasha() {
        database.dropTable("Users");
    }


}

