package com.tw.parking;

import java.util.Comparator;

public class SmartParkingBoy extends AbstractParkingBoy {

  public SmartParkingBoy(ParkingLot... parkingLots) {
    super(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    ParkingLot parkingLot =
        parkingLots.stream()
            .filter(ParkingLot::hasAvailableSpace)
            .max(Comparator.comparingLong(ParkingLot::remainingAvailableSpaceNumber))
            .orElseThrow(ParkingLotFullException::new);

    return parkingLot.park(car);
  }
}
