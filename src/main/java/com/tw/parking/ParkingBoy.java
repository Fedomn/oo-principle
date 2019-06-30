package com.tw.parking;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy {

  private final List<ParkingLot> parkingLots;

  public ParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasAvailableSpace()) {
        return parkingLot.park(car);
      }
    }
    throw new ParkingLotFullException();
  }

  public Car pick(Ticket ticket) {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.isAvailableTicket(ticket)) {
        return parkingLot.pick(ticket);
      }
    }
    throw new InvalidTicketException();
  }
}
