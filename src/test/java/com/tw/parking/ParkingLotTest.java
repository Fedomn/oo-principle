package com.tw.parking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {

  @Test
  void
      should_return_the_car_a_parking_ticket_when_a_car_want_into_parking_lot_given_parking_lot_has_enough_space() {
    ParkingLot parkingLot = new ParkingLot(10);
    Car car = new Car("car_id");

    Ticket ticket = parkingLot.park(car);

    assertEquals(ticket.getCarId(), car.getId());
  }

  @Test
  void should_reject_the_car_when_a_car_want_into_parking_lot_given_parking_lot_space_is_full() {
    ParkingLot parkingLot = new ParkingLot(1);
    Car firstCar = new Car("first_car_id");
    Car secondCar = new Car("second_car_id");

    parkingLot.park(firstCar);

    assertThrows(ParkingLotFullException.class, () -> parkingLot.park(secondCar));
  }

  @Test
  void should_return_the_corresponding_car_when_parking_lot_get_the_ticket_given_a_ticket() {
    ParkingLot parkingLot = new ParkingLot(10);
    Car car = new Car("car_id");
    Ticket ticket = parkingLot.park(car);

    Car pickedCar = parkingLot.pick(ticket);

    assertEquals(pickedCar.getId(), car.getId());
  }

  @Test
  void should_reject_the_ticket_when_parking_lot_get_the_ticket_given_a_invalid_ticket() {
    ParkingLot parkingLot = new ParkingLot(1);
    Ticket invalidTicket = new Ticket("invalid-car-id");

    assertThrows(InvalidTicketException.class, () -> parkingLot.pick(invalidTicket));
  }
}
