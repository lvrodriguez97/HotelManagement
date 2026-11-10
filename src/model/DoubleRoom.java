package model;

/**
 * One double bed, capacity 2. The bed rate depends on 1 or 2 guests.
 */
public class DoubleRoom extends Room {

    DoubleRoom(Hotel hotel, int number, int floor, boolean seaView, boolean premiumTv){
        super(hotel, number, floor, seaView, premiumTv);
    }

    @Override
    public int capacity(){
        return 2;
    }

    @Override
    protected long bedRate(int guests){
        if(guests == 2){
            return getHotel().doubleOccupancyDoubleBedRate();
        }
        return getHotel().singleOccupancyDoubleBedRate();
    }
}
