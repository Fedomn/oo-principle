package com.tw.parking;

import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot implements IParkingLot {

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

    return cars.remove(ticket);
  }

  private boolean notContains(Ticket ticket) {
    return !cars.containsKey(ticket);
  }

  public boolean hasAvailableSpace() {
    return !notHasAvailableSpace();
  }

  public boolean isAvailableTicket(Ticket ticket) {
    return cars.containsKey(ticket);
  }

  long remainingAvailableSpaceNumber() {
    return maxCapacity - cars.size();
  }
}
