package com.tw.parking;

public class Ticket {

  private final String carId;

  public Ticket(String carId) {
    this.carId = carId;
  }

  public String getCarId() {
    return carId;
  }
}
