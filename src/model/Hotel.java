package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hotel {

    private static final int MIN_FLOORS = 8;

    // Floor bands for the sea view surcharge: low 1-3, medium 4-6, high 7 and up.
    private static final int MEDIUM_FLOOR_START = 4;
    private static final int HIGH_FLOOR_START = 7;
    private static final int LOW_FLOOR_PERCENT = 0;
    private static final int MEDIUM_FLOOR_PERCENT = 10;
    private static final int HIGH_FLOOR_PERCENT = 20;

    // Bed rates as a percentage of the single bed rate.
    private static final int SINGLE_OCCUPANCY_DOUBLE_BED_FACTOR = 145;
    private static final int DOUBLE_OCCUPANCY_DOUBLE_BED_FACTOR = 190;
    private static final int ADDITIONAL_PERSON_FACTOR = 70;

    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Guest> guests = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private int nextGuestId = 0;
    private int nextReservationId = 0;
    private int nextReceiptNumber = 0; 


    private final int id;
    private final String name;
    private final String address;
    private final int totalFloors;
    private final long baseRoomRate;
    private final long singleBedRate;
    private final long minibarRate;
    private final long premiumTvSurcharge;
    private final int seaViewSurchargePercent;
    
    public Hotel(int id, String name, String address, int totalFloors, long baseRoomRate, long singleBedRate, long minibarRate, long premiumTvSurcharge, int seaViewSurchargePercent){
        
        validateArgument(id, 1, "El ID del hotel debe ser mayor que cero");
        this.id = id;
        validateNotBlank(name, "Nombre");
        this.name = name;
        validateNotBlank(address, "Dirección");
        this.address = address;
        validateArgument(totalFloors, MIN_FLOORS, "El hotel debe tener como mínimo " + MIN_FLOORS + " pisos");
        this.totalFloors = totalFloors;
        validateArgument(baseRoomRate, 1, "El precio base por habitación debe ser mayor que cero");
        this.baseRoomRate = baseRoomRate;
        validateArgument(singleBedRate, 1, "El precio base por cama debe ser mayor que cero");
        this.singleBedRate = singleBedRate;
        validateArgument(minibarRate, 1, "El costo por minibar debe ser mayor que cero");
        this.minibarRate = minibarRate;
        validateArgument(premiumTvSurcharge, 1, "El recargo por TV premium debe ser mayor que cero");
        this.premiumTvSurcharge = premiumTvSurcharge;
        validateArgument(seaViewSurchargePercent, 1, "El porcentaje de recargo por vista al mar debe ser mayor que cero");
        this.seaViewSurchargePercent = seaViewSurchargePercent;
    }

    // --- Attribute queries ---

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public int getTotalFloors(){
        return totalFloors;
    }

    public long getBaseRoomRate(){
        return baseRoomRate;
    }

    public long getSingleBedRate(){
        return singleBedRate;
    }

    public long getMinibarRate(){
        return minibarRate;
    }

    public long getPremiumTvSurcharge(){
        return premiumTvSurcharge;
    }

    public int getSeaViewSurchargePercent(){
        return seaViewSurchargePercent;
    }

    // Read-only view: Main can list the rooms but cannot add or remove them.
    public List<Room> getRooms(){
        return Collections.unmodifiableList(rooms);
    }

    // --- Validation helpers ---

    private void validateArgument(long value, long min, String message){
        if(value < min){
            throw new IllegalArgumentException(message);
        }
    }

    private void validateNotBlank(String value, String fieldName){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("El campo " + fieldName + " no puede estar vacío");
        }
    }

    // --- Rate calculations (used by Room and its subclasses) ---

    public long seaViewSurcharge(){
        return (baseRoomRate*seaViewSurchargePercent)/100;
    }

    public int floorSurchargePercent(int floorNumber){
        if(floorNumber >= HIGH_FLOOR_START){
           return HIGH_FLOOR_PERCENT;
        }
        if(floorNumber >= MEDIUM_FLOOR_START){
            return MEDIUM_FLOOR_PERCENT;
        }else{
            return LOW_FLOOR_PERCENT;
        }
    }

    public long singleOccupancyDoubleBedRate(){
        return (singleBedRate*SINGLE_OCCUPANCY_DOUBLE_BED_FACTOR)/100;
    }

    public long doubleOccupancyDoubleBedRate(){
        return (singleBedRate*DOUBLE_OCCUPANCY_DOUBLE_BED_FACTOR)/100;
    }

    public long additionalPersonRate(){
        return (singleBedRate*ADDITIONAL_PERSON_FACTOR)/100;
    }
}
