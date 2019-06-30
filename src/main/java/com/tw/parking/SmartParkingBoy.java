package com.tw.parking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {

  private final List<ParkingLot> parkingLots;

  public SmartParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    ParkingLot parkingLot =
        parkingLots.stream()
            .filter(ParkingLot::hasAvailableSpace)
            .max(Comparator.comparingLong(ParkingLot::remainingAvailableSpaceNumber))
            .orElseThrow(ParkingLotFullException::new);

    return parkingLot.park(car);
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
