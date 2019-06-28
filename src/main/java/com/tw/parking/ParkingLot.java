package com.tw.parking;

import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {

  private final long maxCapacity;
  private ConcurrentHashMap<Ticket, Car> cars = new ConcurrentHashMap<>();

  public ParkingLot(long maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public Ticket park(Car car) {
    if (notHasAvailableSpace()) {
      throw new ParkingLotFullException();
    }

    Ticket ticket = new Ticket();
    cars.put(ticket, car);
    return ticket;
  }

  private boolean notHasAvailableSpace() {
    return maxCapacity - cars.size() <= 0;
  }

  public Car pick(Ticket ticket) {
    if (notContains(ticket)) {
      throw new InvalidTicketException();
    }

    return cars.get(ticket);
  }

  private boolean notContains(Ticket ticket) {
    return !cars.containsKey(ticket);
  }
}
