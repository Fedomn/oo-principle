package com.tw.parking;


public interface IParkingLot {

  Ticket park(Car car);

  Car pick(Ticket ticket);

  boolean hasAvailableSpace();
}
