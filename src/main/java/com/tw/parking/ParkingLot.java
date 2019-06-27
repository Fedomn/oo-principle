package com.tw.parking;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

  private final long maxCapacity;
  private ConcurrentHashMap<String, Car> carList = new ConcurrentHashMap<>();

  public ParkingLot(long maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public Ticket park(Car car) {
    checkParkingSpace();
    carList.put(car.getId(), car);
    return new Ticket(car.getId());
  }

  private void checkParkingSpace() {
    if (maxCapacity - carList.size() < 1) {
      throw new ParkingLotFullException("Parking lot space is full");
    }
  }

  public Car pick(Ticket ticket) {
    Car car = carList.get(ticket.getCarId());
    if (Objects.isNull(car)) {
      throw new InvalidTicketException("Invalid ticket with carId: " + ticket.getCarId());
    }
    return car;
  }
}
