package model;

/**
 * Base class for all room types. Subclasses define capacity and bed rate.
 */
public abstract class Room {

    private final int number;
    private final int floor;
    private final boolean seaView;
    private final boolean premiumTv;
    private final Hotel hotel;
    private boolean enabled;

    // --- Construction (only Hotel creates rooms) ---

    Room(Hotel hotel, int number, int floor, boolean seaView, boolean premiumTv){
        if(number <= 0){
            throw new IllegalArgumentException("El número de habitación debe ser mayor que cero");
        }
        this.number = number;

        if(floor <= 0 || floor > hotel.getTotalFloors()){
            throw new IllegalArgumentException("Solo se admiten pisos entre 1 y " + hotel.getTotalFloors());
        }
        this.floor = floor;

        this.hotel = hotel;
        this.seaView = seaView;
        this.premiumTv = premiumTv;
        enabled = true;
    }

    // --- Attribute queries ---

    public int getNumber(){
        return number;
    }

    public int getFloor(){
        return floor;
    }

    public boolean hasSeaView(){
        return seaView;
    }

    public boolean hasPremiumTv(){
        return premiumTv;
    }

    protected Hotel getHotel(){
        return hotel;
    }

    // --- Service state (changed only by Hotel) ---

    public boolean isEnabled(){
        return enabled;
    }

    void enable(){
        enabled = true;
    }

    void putOutOfService(){
        enabled = false;
    }

    // --- Capacity ---

    public abstract int capacity();

    public boolean canHost(int numberOfGuests){
        return capacity() >= numberOfGuests;
    }

    // --- Night cost (Template Method) ---

    public long nightCost(int guests){
        return bedRate(guests) + adjustedBaseRate() + minibarRate();
    }

    public long adjustedBaseRate(){
        long adjustedBaseRate = hotel.getBaseRoomRate() + viewSurcharges() + premiumTvSurcharge();
        return adjustedBaseRate;
    }

    private long viewSurcharges(){
        long viewSurcharges = 0;
        if(hasSeaView()){
            viewSurcharges = hotel.seaViewSurcharge() + (hotel.seaViewSurcharge() * hotel.floorSurchargePercent(floor)) / 100;
        }
        return viewSurcharges;
    }

    private long premiumTvSurcharge(){
        long premiumTvSurcharge = 0;
        if(hasPremiumTv()){
            premiumTvSurcharge = hotel.getPremiumTvSurcharge();
        }
        return premiumTvSurcharge;
    }

    // Overridden by each subclass.
    protected abstract long bedRate(int guests);

    // Default 0; only Suite overrides it.
    protected long minibarRate(){
        return 0;
    }
}
