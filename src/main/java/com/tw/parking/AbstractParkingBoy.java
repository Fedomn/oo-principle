package com.tw.parking;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParkingBoy implements IParkingLot {
  final List<ParkingLot> parkingLots;

  AbstractParkingBoy(ParkingLot... parkingLots) {
    this.parkingLots = Arrays.asList(parkingLots);
  }

  public final Car pick(Ticket ticket) {
    ParkingLot parkingLot =
        parkingLots.stream()
            .filter(p -> p.isAvailableTicket(ticket))
            .findFirst()
            .orElseThrow(InvalidTicketException::new);

    return parkingLot.pick(ticket);
  }

  public abstract Ticket park(Car car);

  @Override
  public boolean hasAvailableSpace() {
    return parkingLots.stream().anyMatch(ParkingLot::hasAvailableSpace);
  }

  @Override
  public boolean isAvailableTicket(Ticket ticket) {
    return parkingLots.stream().anyMatch(parkingLot -> parkingLot.isAvailableTicket(ticket));
  }
}
