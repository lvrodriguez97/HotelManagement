package model;

public class Receipt {
    
    private final int serialNumber;
    private final int reservationId;
    private final String guestName;
    private final int roomNumber;
    private final int nights;
    private final long totalCost;

    Receipt(int serialNumber, int reservationId, String guestName, int roomNumber, int nights, long totalCost){
        this.serialNumber = serialNumber;
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.nights = nights;
        this.totalCost = totalCost;
    }

    public int getSerialNumber(){
        return serialNumber;
    }

    public int getReservationId(){
        return reservationId;
    }

    public String getGuestName(){
        return guestName;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public int getNights(){
        return nights;
    }

    public long getTotalCost(){
        return totalCost;
    }
}
