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


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

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
