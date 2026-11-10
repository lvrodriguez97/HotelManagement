package model;

/**
 * One single bed, capacity 1.
 */
public class SingleRoom extends Room {

    SingleRoom(Hotel hotel, int number, int floor, boolean seaView, boolean premiumTv){
        super(hotel, number, floor, seaView, premiumTv);
    }

    @Override
    public int capacity(){
        return 1;
    }

    @Override 
    protected long bedRate(int guests){
        return getHotel().getSingleBedRate();
    }
}
