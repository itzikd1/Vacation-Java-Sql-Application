package Model;

public class Vacation {
    private String VacationID;
    private String UserName;
    private String Departure;
    private String DepartureDate;
    private String DepartureTime;
    private String Destination;
    private String DestinationDate;
    private String DestinationTime;
    private String ReturnDate;
    private String ReturnTime;
    private String ArrivalDateInDestination;
    private String ArrivalTimeInDestination;
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



    public Vacation(String vacationID, String userName, String departure, String departureDate, String departureTime, String destination, String destinationDate, String destinationTime, String returnDate, String returnTime, String arrivalDateInDestination, String arrivalTimeInDestination, String ticketType, String flightsCompany, String connectionCountry, String isBaggageIncluded, String baggageOptions, String classType, String price) {
        VacationID = vacationID;
        UserName = userName;
        Departure = departure;
        DepartureDate = departureDate;
        DepartureTime = departureTime;
        Destination = destination;
        DestinationDate = destinationDate;
        DestinationTime = destinationTime;
        ReturnDate = returnDate;
        ReturnTime = returnTime;
        ArrivalDateInDestination = arrivalDateInDestination;
        ArrivalTimeInDestination = arrivalTimeInDestination;
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

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
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

    public String getDestinationDate() {
        return DestinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        DestinationDate = destinationDate;
    }

    public String getDestinationTime() {
        return DestinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        DestinationTime = destinationTime;
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

    public String getArrivalDateInDestination() {
        return ArrivalDateInDestination;
    }

    public void setArrivalDateInDestination(String arrivalDateInDestination) {
        ArrivalDateInDestination = arrivalDateInDestination;
    }

    public String getArrivalTimeInDestination() {
        return ArrivalTimeInDestination;
    }

    public void setArrivalTimeInDestination(String arrivalTimeInDestination) {
        ArrivalTimeInDestination = arrivalTimeInDestination;
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
