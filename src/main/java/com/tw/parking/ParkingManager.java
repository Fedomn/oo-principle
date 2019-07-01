package com.tw.parking;

import java.util.Arrays;
import java.util.List;

public class ParkingManager {

  private final List<IParkingLot> parkingLots;

  public ParkingManager(IParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public Ticket park(Car car) {
    IParkingLot firstAvailableParkingLot = parkingLots.stream()
        .filter(IParkingLot::hasAvailableSpace)
        .findFirst()
        .orElseThrow(ParkingLotFullException::new);

    return firstAvailableParkingLot.park(car);
  }

  public Car pick(Ticket ticket) {
    IParkingLot parkingLot =
        parkingLots.stream()
            .filter(p -> p.isAvailableTicket(ticket))
            .findFirst()
            .orElseThrow(InvalidTicketException::new);

    return parkingLot.pick(ticket);
  }
}
