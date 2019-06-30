package com.tw.parking;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GraduateParkingBoyTest {

  @Test
  void
      should_park_the_car_to_a_parking_lot_when_parking_given_first_parking_lot_A_and_second_parking_lot_B_both_space_are_available() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);

    Ticket ticket = graduateParkingBoy.park(new Car());

    assertNotNull(ticket);
    assertNotNull(firstParkingLotA.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> secondParkingLotB.pick(ticket));
  }

  @Test
  void
      should_park_the_car_to_a_parking_lot_when_parking_given_first_parking_lot_A_without_available_space_and_second_parking_lot_B_with_available_space() {
    ParkingLot firstParkingLotA = buildParkingLotWithoutAvailableSpace();
    ParkingLot secondParkingLotB = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);

    Ticket ticket = graduateParkingBoy.park(new Car());

    assertNotNull(ticket);
    assertNotNull(secondParkingLotB.pick(ticket));
  }

  @Test
  void
      should_park_the_car_to_a_parking_lot_when_parking_given_first_parking_lot_A_with_available_space_and_second_parking_lot_B_without_available_space() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = buildParkingLotWithoutAvailableSpace();
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);

    Ticket ticket = graduateParkingBoy.park(new Car());

    assertNotNull(ticket);
    assertNotNull(firstParkingLotA.pick(ticket));
  }

  @Test
  void
      should_reject_parked_car_parking_lot_when_parking_given_first_parking_lot_A_and_second_parking_lot_B_both_space_unavailable() {
    ParkingLot firstParkingLotA = buildParkingLotWithoutAvailableSpace();
    ParkingLot secondParkingLotB = buildParkingLotWithoutAvailableSpace();
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);

    assertThrows(ParkingLotFullException.class, () -> graduateParkingBoy.park(new Car()));
  }

  private ParkingLot buildParkingLotWithoutAvailableSpace() {
    ParkingLot firstParkingLot = new ParkingLot(1);
    firstParkingLot.park(new Car());
    return firstParkingLot;
  }

  @Test
  void should_return_the_corresponding_given_one_available_ticket_when_pick_the_car() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);
    Car parkedCar = new Car();
    Ticket ticket = graduateParkingBoy.park(parkedCar);

    Car pickedCar = graduateParkingBoy.pick(ticket);

    assertSame(pickedCar, parkedCar);
  }

  @Test
  void should_reject_parked_car_given_one_invalid_ticket_when_pick_the_car() {
    ParkingLot firstParkingLotA = new ParkingLot(1);
    ParkingLot secondParkingLotB = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);

    assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pick(new Ticket()));
  }

  @Test
  void
      should_pick_the_car_when_picking_car_given_given_first_parking_lot_A_and_second_parking_lot_B_with_parked_car() {
    ParkingLot firstParkingLotA = buildParkingLotWithoutAvailableSpace();
    ParkingLot secondParkingLotB = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(firstParkingLotA, secondParkingLotB);
    Car parkedCar = new Car();
    Ticket ticket = graduateParkingBoy.park(parkedCar);

    Car pickedCar = graduateParkingBoy.pick(ticket);

    assertSame(parkedCar, pickedCar);
  }
}
