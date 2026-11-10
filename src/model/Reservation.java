package model;

/**
 * A guest's booking of one room. Controls its own state transitions.
 */
public class Reservation {

    private final int id;
    private final Guest guest;
    private final Room room;
    private final int guestCount;
    private final int nights;
    private ReservationStatus status;
    private Receipt receipt;

    // --- Construction (only Hotel creates reservations) ---

    Reservation(int id, Guest guest, Room room, int guestCount, int nights){

        if(!room.canHost(guestCount)){
            throw new IllegalArgumentException("La habitación no tiene capacidad para "+ guestCount + " personas");
        }
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.guestCount = guestCount;
        this.nights = nights;
        status = ReservationStatus.PENDING;
        receipt = null;
    }

    // --- Attribute queries ---

    public int getId(){
        return id;
    }

    public Guest getGuest(){
        return guest;
    }

    public Room getRoom(){
        return room;
    }

    public int getGuestCount(){
        return guestCount;
    }

    public int getNights(){
        return nights;
    }

    public ReservationStatus getStatus(){
        return status;
    }

    // null until the reservation is confirmed.
    public Receipt getReceipt(){
        return receipt;
    }

    // --- Cost ---

    public long totalCost(){
        return getRoom().nightCost(getGuestCount())*getNights();
    }

    // --- State transitions (called only by Hotel) ---

    void confirm(int receiptNumber){
        if(getStatus() == ReservationStatus.CANCELLED){
            throw new IllegalStateException("No se pudo confirmar la reserva porque fue cancelada");
        }
        if(getStatus() == ReservationStatus.CONFIRMED){
            throw new IllegalStateException("La reserva ya fue confirmada");
        }
        status = ReservationStatus.CONFIRMED;
        receipt = new Receipt(receiptNumber, getId(), getGuest().getFullName(), getRoom().getNumber(), getNights(), totalCost());
    }

    void cancel(){
        if(getStatus()==ReservationStatus.CANCELLED){
            throw new IllegalStateException("La reserva ya fue cancelada");
        }
        status = ReservationStatus.CANCELLED;
    }

    boolean isActive(){
        return (status == ReservationStatus.PENDING || status == ReservationStatus.CONFIRMED);
    }
}
