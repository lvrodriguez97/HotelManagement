package model;

/**
 * One double bed, capacity 2, premium TV and minibar.
 * Only allowed on one of the two top floors.
 */
public class Suite extends Room {

    // No premiumTv parameter: every suite has it.
    Suite(Hotel hotel, int number, int floor, boolean seaView){
        super(hotel, number, floor, seaView, true);
        if(floor < hotel.getTotalFloors() - 1){
            throw new IllegalArgumentException("Una suite solo puede estar en los pisos " + (hotel.getTotalFloors() - 1) + " o " + hotel.getTotalFloors());
        }
    }

    @Override 
    public int capacity(){
        return 2;
    }

    @Override 
    protected long bedRate(int guests){
        return getHotel().doubleOccupancyDoubleBedRate();
    }

    @Override 
    protected long minibarRate(){
        return getHotel().getMinibarRate();
    }
}
