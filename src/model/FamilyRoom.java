package model;

/**
 * Two double beds plus 2 to 4 single beds, capacity 6 to 8.
 */
public class FamilyRoom extends Room {

    private static final int DOUBLE_BEDS = 2;
    private static final int MIN_SINGLE_BEDS = 2;
    private static final int MAX_SINGLE_BEDS = 4;

    private final int singleBeds;

    FamilyRoom(Hotel hotel, int number, int floor, boolean seaView, boolean premiumTv, int singleBeds){
        super(hotel, number, floor, seaView, premiumTv);
        if(singleBeds < MIN_SINGLE_BEDS || singleBeds > MAX_SINGLE_BEDS){
            throw new IllegalArgumentException("Una habitación familiar debe tener entre " + MIN_SINGLE_BEDS + " y " + MAX_SINGLE_BEDS + " camas sencillas");
        }
        this.singleBeds = singleBeds;
    }

    public int getSingleBeds(){
        return singleBeds;
    }

    private long baseBedRate(){
        return getHotel().doubleOccupancyDoubleBedRate()*DOUBLE_BEDS + getHotel().getSingleBedRate()*MIN_SINGLE_BEDS;
    }

    @Override 
    public int capacity(){
        return getSingleBeds()+DOUBLE_BEDS*2;
    }

    @Override 
    protected long bedRate(int guests){
        long extraGuests = guests - 6;
        if(extraGuests > 0){
            return (extraGuests*getHotel().additionalPersonRate()) + baseBedRate();
        }
        return baseBedRate();
    }
}
