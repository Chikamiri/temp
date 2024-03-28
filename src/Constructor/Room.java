package Constructor;

public class Room {
    //Room ID

    public enum RoomType {
        SINGLE, DOUBLE
    }
    public enum RoomRank{
        NORMAL, VIP
    }
    public enum RoomStatus{
        EMPTY, OCCUPIED, UNAVALIABLE
    }
    public enum Price{
        SINGLE(100), DOUBLE(200);
        private int price;
        Price(int price){
            this.price = price;
        }
        public int getPrice(){
            return price;
        }
    }
}
