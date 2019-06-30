package com.tw.parking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SmartParkingBoyTest {

  @Test
  void
      should_return_a_ticket_and_parked_car_into_parking_lot_B_when_parking_given_first_parking_lot_A_and_second_parking_lot_B_and_B_available_space_more_than_A() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = new ParkingLot(2);
    SmartParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLotA, secondParkingLotB);

    Ticket ticket = parkingBoy.park(new Car());

    assertNotNull(ticket);
    assertNotNull(secondParkingLotB.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> firstParkingLotA.pick(ticket));
  }

  @Test
  void
  should_reject_parked_car_parking_lot_when_parking_given_first_parking_lot_A_and_second_parking_lot_B_both_space_unavailable() {
    ParkingLot firstParkingLotA = buildParkingLotWithoutAvailableSpace();
    ParkingLot secondParkingLotB = buildParkingLotWithoutAvailableSpace();
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLotA, secondParkingLotB);

    assertThrows(ParkingLotFullException.class, () -> smartParkingBoy.park(new Car()));
  }
  
  @Test
  void should_return_the_corresponding_given_one_available_ticket_when_pick_the_car() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLotA, secondParkingLotB);
    Car parkedCar = new Car();
    Ticket ticket = smartParkingBoy.park(parkedCar);

    Car pickedCar = smartParkingBoy.pick(ticket);

    assertSame(pickedCar, parkedCar);
  }

  @Test
  void should_reject_parked_car_given_one_invalid_ticket_when_pick_the_car() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLotA, secondParkingLotB);

    assertThrows(InvalidTicketException.class, () -> smartParkingBoy.pick(new Ticket()));
  }

  private ParkingLot buildParkingLotWithoutAvailableSpace() {
    ParkingLot firstParkingLot = new ParkingLot(1);
    firstParkingLot.park(new Car());
    return firstParkingLot;
  }

}
