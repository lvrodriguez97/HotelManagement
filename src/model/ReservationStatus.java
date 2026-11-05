package model;

// Only valid values live here; valid transitions are controlled by Reservation.
public enum ReservationStatus {
    PENDING,
    CONFIRMED,
    CANCELLED
}
