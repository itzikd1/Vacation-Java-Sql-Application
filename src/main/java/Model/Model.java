package Model;

import Model.Excpetions.V4UException;

public class Model {

    private static Model singleton = null;
    public Database database = null;
    public User connected_user = null;
    public int vacationIDCounter;

    private Model() {

        createDataBase();
        //before_hagasha();
        createTables();
        vacationIDCounter = searchForNext() + 1;


    }

    private int searchForNext() {
        return database.getMaxVacationID();
    }

    public static Model getInstance() {
        if (singleton == null)
            singleton = new Model();
        return singleton;

    }

    public int getNextVacationID (){
        vacationIDCounter += 1;
        return vacationIDCounter - 1;
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

    public boolean delete_user() {
        boolean flag =  database.delete(connected_user.getUsername(), "Users");
        if (flag)
            log_out();
        return flag;
    }

    public Object read(String table_name, String pk) {
        String[] result = database.read(pk,table_name);
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

            case "Vacation":
                String VacationID = result [0];
                String UserName = result [1];
                String _From = result [2];
                String DepartureDate = result [3];
                String DepartureTime = result [4];
                String Destination = result [5];
                String ArrivalDate = result [6];
                String ArrivalTime = result [7];
                String ReturnDate = result [8];
                String ReturnTime = result [9];
                String TicketType = result [10];
                String FlightsCompany = result [11];
                String ConnectionCity = result [12];
                String isBaggageIncluded = result [13];
                String BaggageOptions = result [14];
                String ClassType = result [15];
                String Price = result [16];
                ans = new Vacation (VacationID, UserName, _From, DepartureDate, DepartureTime, Destination,
                        ArrivalDate,ArrivalTime,ReturnDate,ReturnTime,TicketType,FlightsCompany,ConnectionCity
                        ,isBaggageIncluded,BaggageOptions,ClassType,Price);
                break;
            // todo: add other cases
        }
        return ans;
    }

    public boolean update(String table_name, String[] data) {
        boolean flag = database.update(data, table_name);
        if (flag)
            update_connected_user(data[0]);
        return flag;
    }


    public boolean confirm(String table_name, String user_name, String password) {
        String[] userDetails = database.read(user_name, table_name);
        this.connected_user = new User(userDetails[0],userDetails[1],userDetails[2],userDetails[3],userDetails[4],userDetails[5]);
        if (this.connected_user == null)
            return false;
        else return this.connected_user.getPassword().equals(password);
    }

    public String[] readConnectedUser() {
//        User user = (User) read("Users", this.connected_user.getUsername());
        if (connected_user!=null)
            return connected_user.getDetails();
        else return null;
    }


    public void before_hagasha() {
        database.dropTable("Users");
    }

    private void clear_connected_user() {
        this.connected_user = null;

    }

    public void update_connected_user(String user_name) {
        String[] userDetails = database.read(user_name, "Users");
        this.connected_user = new User(userDetails[0],userDetails[1],userDetails[2],userDetails[3],userDetails[4],userDetails[5]);
    }


    public void log_out() {
        clear_connected_user();
    }

    public Object[] readAll(String tableName){
        Object[] items = database.getAllData(tableName);
        if (tableName.equals("Vacations")){
            Vacation[] vacations = new Vacation[items.length];
            for( int i=0; i<items.length; i++){
                Vacation v = new Vacation((String[])items[i]);
                vacations[i] = v;
            }
            return vacations;
        }
        return null;
    }
}

