package com.tw.parking;

public class GraduateParkingBoy extends AbstractParkingBoy {

  public GraduateParkingBoy(ParkingLot... parkingLots) {
    super(parkingLots);
  }

  @Override
  public Ticket park(Car car) {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.hasAvailableSpace()) {
        return parkingLot.park(car);
      }
    }
    throw new ParkingLotFullException();
  }
}
