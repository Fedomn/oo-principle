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
}
