package Constructor;

@SuppressWarnings("unused")
public class Payment {
    private int GuestID;
    private int RoomID;
    private int TotalPrice;

    public Payment(int GuestID, int RoomID, int TotalPrice) {
        this.GuestID = GuestID;
        this.RoomID = RoomID;
        this.TotalPrice = TotalPrice;
    }
    public int getGuestID(){
        return GuestID;
    }
    public int getRoomID(){
        return RoomID;
    }
    public int getTotalPrice(){
        return TotalPrice;
    }
}
