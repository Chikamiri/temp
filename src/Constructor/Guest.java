package Constructor;

@SuppressWarnings("unused")
public class Guest {
    //Guest ID

    private String FirstName;
    private String LastName;
    
    private enum Sex{
        MALE, FEMALE
    }

    //day-month-year
    //use split("-")
    private String DateOfBirth;
    private String PhoneNumber;

    public Guest(String FirstName, String LastName, String DateOfBirth, String PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateOfBirth = DateOfBirth;
        this.PhoneNumber = PhoneNumber;
    }

    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getDateOfBirth() {
        return DateOfBirth;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
