package Model;

import Model.Excpetions.V4UException;

public class Model {

    private static Model singleton = null;
    public Database database = null;
    public User connected_user = null;
    public int vacationIDCounter;
    private int requestIDCounter;
    private int purchaseIDCounter;
    private BuyingRequest current_buying_request = null; //only while user clicked on BUY button to pay this info will be updated
    private Vacation current_buying_vacation = null; //only while user try to buy or show details of vacation this will update
    private String current_trade_id = null;
    private String current_trade_seller_user_name = null;

    public String getCurrent_trade_seller_user_name() {
        return current_trade_seller_user_name;
    }

    public void setCurrent_trade_seller_user_name(String current_trade_seller_user_name) {
        this.current_trade_seller_user_name = current_trade_seller_user_name;
    }

    public String getCurrent_trade_id() {
        return current_trade_id;
    }

    public void setCurrent_trade_id(String currrent_trade_id) {
        this.current_trade_id = currrent_trade_id;
    }


    public Vacation getCurrent_buying_vacation() {
        return current_buying_vacation;
    }

    public void setCurrent_buying_vacation(Vacation current_buying_vacation) {
        this.current_buying_vacation = current_buying_vacation;
    }

    public BuyingRequest getCurrent_buying_request() {
        return current_buying_request;
    }

    public void setCurrent_buying_request(BuyingRequest current_buying_request) {
        this.current_buying_request = current_buying_request;
    }


    private Model() {

        createDataBase();
        //before_hagasha();
        createTables();
        vacationIDCounter = searchForNext() + 1;
        requestIDCounter = searchForNextRequestID() + 1;
        purchaseIDCounter = searchForNextPurchaseID()  + 1;



    }

    private int searchForNextPurchaseID() {
        return database.getMaxPurchaseID();
    }


    private int searchForNext() {
        return database.getMaxVacationID();
    }

    private int searchForNextRequestID() {
        return database.getMaxRequestID();
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

    public int getNextRequestID (){
        requestIDCounter += 1;
        return requestIDCounter - 1;
    }

    public int getNextPurchaseID (){
        purchaseIDCounter += 1;
        return purchaseIDCounter - 1;
    }

    private void createDataBase() {

        database = new Database("jdbc:sqlite:Vaction4U.sqlite");
    }

    private void createTables() {
        database.createTables();
    }


    public boolean insert(String table_name, String[] data) throws V4UException {
        return database.insert(table_name, data);
    }

    public boolean delete_user() {
        boolean flag =  database.delete(connected_user.getUsername(), "Users");
        if (flag)
            delete_all_for_user(connected_user.getUsername());
            log_out();
        return flag;
    }

    private void delete_all_for_user(String username) {
        //boolean flag
    }

    public boolean delete_buying_request() {
        boolean flag =  database.delete(getCurrent_buying_request().getRequestID(), "BuyingRequests");
        return flag;
    }

    public boolean delete_all_buying_requets_for_vacationID() {
        boolean flag = database.deleteAllVacations(current_buying_vacation.getVacationID(),"BuyingRequests" );
        return flag;
    }


    public boolean delete_all_trade_requets_for_vacationID() {
        boolean flag = database.deleteAllVacations(current_buying_vacation.getVacationID(),"TradeRequests" );
        return flag;
    }

    public int get_num_of_fields(String table_name) {
        return database.get_num_of_fields(table_name);
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

            case "Vacations":
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
                String Status = result[17];
                ans = new Vacation (VacationID, UserName, _From, DepartureDate, DepartureTime, Destination,
                        ArrivalDate,ArrivalTime,ReturnDate,ReturnTime,TicketType,FlightsCompany,ConnectionCity
                        ,isBaggageIncluded,BaggageOptions,ClassType,Price, Status);
                break;
            case "Purhcases":
                String PurchaseID = result[0];
                String VacationID2 = result[1];
                String BuyerUserName = result[2];
                String SellerUserName = result[3];
                String Price2 = result[4];
                String DateOfPurchase = result[5];
                ans = new Purchase(PurchaseID,VacationID2,BuyerUserName,SellerUserName,Price2,DateOfPurchase);
                break;
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
//        database.dropTable("Vacations");
  database.dropTable("Purchases");
      // database.dropTable("BuyingRequests");
       //database.dropTable("TradeRequests");
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

    public Object[] readAll(String tableName) {
        Object[] items = database.getAllData(tableName);
        switch (tableName) {
            case "Vacations":
                Vacation[] vacations = new Vacation[items.length];
                for (int i = 0; i < items.length; i++) {
                    Vacation v = new Vacation((String[]) items[i]);
                    vacations[i] = v;
                }
                return vacations;
            case "BuyingRequests":
                BuyingRequest[] requests = new BuyingRequest[items.length];
                for (int i=0; i< items.length; i++) {
                    BuyingRequest br = new BuyingRequest((String[]) items[i]);
                    requests[i] = br;
                }
                return requests;
        }
        return null;
    }

    public Object[] readAllForOneUser(String tableName, String field) {
        String user_name_id = get_connected_user_id();
        Object[] items;
        switch (tableName) {
            case "Vacations":
                items  = database.getAllDataForOneUser(tableName,"UserName", user_name_id);
                Vacation[] vacations = new Vacation[items.length];
                for (int i = 0; i < items.length; i++) {
                    Vacation v = new Vacation((String[]) items[i]);
                    vacations[i] = v;
                }
                return vacations;
            case "BuyingRequests":
                return buying_req_switch_case(tableName,field,user_name_id);
            case "TradeRequests":
                return trade_req_switch_case(tableName,field,user_name_id);
            case "Purchases":
                return purchase_switch_case(tableName,field,user_name_id);


        }
        return null;
    }

    private TradeRequest[] trade_req_switch_case(String tableName, String field, String user_name_id) {
        Object[] items = null;
        items = getObjects(tableName, field, user_name_id, items);
        TradeRequest[] requests = new TradeRequest[items.length];
        for (int i=0; i< items.length; i++) {
            TradeRequest br = new TradeRequest((String[]) items[i]);
            requests[i] = br;
        }
        return requests;
    }

    private Object[] getObjects(String tableName, String field, String user_name_id, Object[] items) {
        switch (field) {
            case "SellerUserName":
                items = database.getAllDataForOneUser(tableName, "SellerUserName", user_name_id);
                break;
            case "BuyerUserName":
                items = database.getAllDataForOneUser(tableName, "BuyerUserName", user_name_id);
                break;
        }
        return items;
    }

    private  BuyingRequest[] buying_req_switch_case(String tableName, String field, String user_name_id) {
        Object[] items = null;
        items = getObjects(tableName, field, user_name_id, items);
        BuyingRequest[] requests = new BuyingRequest[items.length];
        for (int i=0; i< items.length; i++) {
            BuyingRequest br = new BuyingRequest((String[]) items[i]);
            requests[i] = br;
        }
        return requests;
    }

    private  Purchase[] purchase_switch_case(String tableName, String field, String user_name_id) {
        Object[] items = null;
        items = getObjects(tableName, field, user_name_id, items);
        Purchase[] purchases = new Purchase[items.length];
        for (int i=0; i< items.length; i++) {
            Purchase pu = new Purchase((String[]) items[i]);
            purchases[i] = pu;
        }
        return purchases;
    }


    public String get_connected_user_id() {
        if (connected_user!=null)
            return connected_user.getUsername();
        return null;
    }

    public boolean insertBuyingRequest(String[] data) throws V4UException {
        String trade_id = "";
        String[] new_data;
        String table_name = "BuyingRequests";
        if (this.current_trade_id != null) {
            table_name = "TradeRequests";
            trade_id = current_trade_id;
            new_data = new String[database.get_num_of_fields(table_name)];
            for (int i = 0; i < data.length; i++) {
                new_data[i] = data[i];
            }
            new_data[2] = getCurrent_trade_seller_user_name(); //for changing it to current seller. will be setted from GUI
            String tmp = new_data[1];
            new_data[1] = trade_id;
            new_data[new_data.length - 1] = tmp;

            setCurrent_trade_id(null);
            setCurrent_trade_seller_user_name(null);
            if (!database.tradeRequestExists(new_data[1], new_data[3]) && !database.buyingRequestExists(new_data[1], new_data[3]))
                return database.insert(table_name, new_data);
        } else {
            new_data = data;
            if (!database.buyingRequestExists(new_data[1], new_data[3]) && !database.tradeRequestExists(new_data[1], new_data[3]))
                return database.insert(table_name, new_data);

        }
        return false;

    }


    public boolean buying_req_exists(String vac_id, String buyer_user_name) {
        return database.buyingRequestExists(vac_id,buyer_user_name);
    }

    public boolean trade_req_exists(String vac_id, String buyer_user_name) {
        return database.tradeRequestExists(vac_id,buyer_user_name);
    }

    public boolean updateRequest(String requestID, String status) {
        return database.update_one_field(status,"BuyingRequests","isApproved",requestID);
    }

    public boolean updateTradeRequest(String requestID, String status) {
        return database.update_one_field(status,"TradeRequests","isApproved",requestID);

    }

    public String getPriceForCurrentVacation() {
        return database.getPriceForCurrentVacation(current_buying_request.getVacationID());
    }

    public boolean delete_vacation() {

        return database.update_one_field("Sold","Vacations","Status",current_buying_vacation.getVacationID());
    }

    public void delete_myVacation(String vactionID) {
        database.deleteAllVacations(vactionID,"Vacations");
    }

    public void updateMyVacation(String vacationID) {
    }

    public boolean delete_trade_request() {
        boolean flag =  database.delete(getCurrent_buying_request().getRequestID(), "TradeRequests");
        return flag;
    }
}

