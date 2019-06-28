package com.tw.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

  @Test
  void
      should_return_the_car_a_parking_ticket_when_a_car_want_into_parking_lot_given_parking_lot_has_enough_space() {
    ParkingLot parkingLot = new ParkingLot(10);
    Car car = new Car();

    Ticket ticket = parkingLot.park(car);

    assertNotNull(ticket);
  }

  @Test
  void should_reject_the_car_when_a_car_want_into_parking_lot_given_parking_lot_space_is_full() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Car()));
  }

  @Test
  void should_return_the_corresponding_car_when_parking_lot_get_the_ticket_given_a_ticket() {
    ParkingLot parkingLot = new ParkingLot(10);
    Car parkedCar = new Car();
    Ticket ticket = parkingLot.park(parkedCar);

    Car pickedCar = parkingLot.pick(ticket);

    assertEquals(pickedCar, parkedCar);
  }

  @Test
  void should_reject_the_ticket_when_parking_lot_get_the_ticket_given_a_invalid_ticket() {
    ParkingLot parkingLot = new ParkingLot(1);
    Ticket invalidTicket = new Ticket();

    assertThrows(InvalidTicketException.class, () -> parkingLot.pick(invalidTicket));
  }

  @Test
  void should_reject_the_ticket_when_parking_lot_get_the_ticket_twice_given_a_valid_ticket() {
    ParkingLot parkingLot = new ParkingLot(10);
    Ticket ticket = parkingLot.park(new Car());

    parkingLot.pick(ticket);

    assertThrows(InvalidTicketException.class, () -> parkingLot.pick(ticket));
  }
}
