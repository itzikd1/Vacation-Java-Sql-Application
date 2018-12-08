package Model;

import javafx.beans.property.SimpleStringProperty;

public class Vacation {
    private String VacationID; //not from GUI
    private String UserName; // not from GUI
    private String from;
    private String DepartureDate;
    private String DepartureTime;
    private String Destination;
    private String ArrivalDate;
    private String ArrivalTime;
    private String ReturnDate;
    private String ReturnTime;
    private String TicketType;
    private String FlightsCompany;
    private String ConnectionCountry;
    private String isBaggageIncluded;
    private String BaggageOptions;
    private String ClassType;
    private String Price;


    public Vacation(String vacationID, String userName, String from, String departureDate, String departureTime, String destination, String arrivalDate, String arrivalTime, String returnDate, String returnTime, String ticketType, String flightsCompany, String connectionCountry, String isBaggageIncluded, String baggageOptions, String classType, String price) {
        VacationID = vacationID;
        UserName = userName;
        this.from = from;
        DepartureDate = departureDate;
        DepartureTime = departureTime;
        Destination = destination;
        ArrivalDate = arrivalDate;
        ArrivalTime = arrivalTime;
        ReturnDate = returnDate;
        ReturnTime = returnTime;
        TicketType = ticketType;
        FlightsCompany = flightsCompany;
        ConnectionCountry = connectionCountry;
        this.isBaggageIncluded = isBaggageIncluded;
        BaggageOptions = baggageOptions;
        ClassType = classType;
        Price = price;
    }

    public Vacation(String [] data){
        VacationID = data[0];
        UserName = data[1];
        this.from = data[2];
        DepartureDate = data[3];
        DepartureTime = data[4];
        Destination = data[5];
        ArrivalDate = data[6];
        ArrivalTime = data[7];
        ReturnDate = data[8];
        ReturnTime = data[9];
        TicketType = data[10];
        FlightsCompany = data[11];
        ConnectionCountry = data[12];
        this.isBaggageIncluded = data[13];
        BaggageOptions = data[14];
        ClassType = data[15];
        Price = data[16];
    }

    public Vacation(Object [] data){
        VacationID = (String)data[0];
        UserName = (String)data[1];
        this.from = (String)data[2];
        DepartureDate = (String)data[3];
        DepartureTime = (String)data[4];
        Destination = (String)data[5];
        ArrivalDate = (String)data[6];
        ArrivalTime = (String)data[7];
        ReturnDate = (String)data[8];
        ReturnTime = (String)data[9];
        TicketType = (String)data[10];
        FlightsCompany = (String)data[11];
        ConnectionCountry = (String)data[12];
        this.isBaggageIncluded = (String)data[13];
        BaggageOptions = (String)data[14];
        ClassType = (String)data[15];
        Price = (String)data[16];
    }

    public String[] getDetails() {
            String[] details = {VacationID, UserName, from, DepartureDate, DepartureTime, Destination,ArrivalDate,ArrivalTime,
                    ReturnDate,ReturnTime,TicketType,FlightsCompany,ConnectionCountry,isBaggageIncluded,BaggageOptions,ClassType,Price};
            return details;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getVacationID() {
        return VacationID;
    }

    public void setVacationID(String vacationID) {
        VacationID = vacationID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        DepartureDate = departureDate;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(String returnTime) {
        ReturnTime = returnTime;
    }

    public String getTicketType() {
        return TicketType;
    }

    public void setTicketType(String ticketType) {
        TicketType = ticketType;
    }

    public String getFlightsCompany() {
        return FlightsCompany;
    }

    public void setFlightsCompany(String flightsCompany) {
        FlightsCompany = flightsCompany;
    }

    public String getConnectionCountry() {
        return ConnectionCountry;
    }

    public void setConnectionCountry(String connectionCountry) {
        ConnectionCountry = connectionCountry;
    }

    public String getIsBaggageIncluded() {
        return isBaggageIncluded;
    }

    public void setIsBaggageIncluded(String isBaggageIncluded) {
        this.isBaggageIncluded = isBaggageIncluded;
    }

    public String getBaggageOptions() {
        return BaggageOptions;
    }

    public void setBaggageOptions(String baggageOptions) {
        BaggageOptions = baggageOptions;
    }

    public String getClassType() {
        return ClassType;
    }

    public void setClassType(String classType) {
        ClassType = classType;
    }
}
