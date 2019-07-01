package com.tw.parking;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingManagerTest {

  @Test
  void should_parked_car_through_graduate_parking_boy_and_return_a_invalid_ticket_when_park_car_given_a_parking_manager_who_have_a_graduate_parking_boy_who_managed_parking_lot_A_with_available_space_and_a_smart_parking_boy_who_managed_parking_log_B_and_a_parking_lot_C() {

    ParkingLot parkingLotA = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotA);
    ParkingLot parkingLotB = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotB);
    ParkingLot parkingLotC = new ParkingLot(1);
    ParkingManager parkingManager = new ParkingManager(graduateParkingBoy, smartParkingBoy, parkingLotC);

    Ticket ticket = parkingManager.park(new Car());

    assertNotNull(ticket);
    assertNotNull(parkingLotA.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotB.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotC.pick(ticket));
  }

  @Test
  void should_parked_car_through_smart_parking_boy_and_return_a_invalid_ticket_when_park_car_given_a_parking_manager_who_have_a_graduate_parking_boy_who_managed_parking_lot_A_without_available_space_and_a_smart_parking_boy_who_managed_parking_log_B_with_available_space_and_a_parking_lot_C() {
    ParkingLot parkingLotA = buildParkingLotWithoutAvailableSpace();
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotA);
    ParkingLot parkingLotB = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotB);
    ParkingLot parkingLotC = new ParkingLot(1);
    ParkingManager parkingManager = new ParkingManager(graduateParkingBoy, smartParkingBoy, parkingLotC);

    Ticket ticket = parkingManager.park(new Car());

    assertNotNull(ticket);
    assertNotNull(parkingLotB.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotA.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotC.pick(ticket));
  }

  @Test
  void should_parked_car_self_and_return_a_invalid_ticket_when_park_car_given_a_parking_manager_who_have_a_graduate_parking_boy_who_managed_parking_lot_A_without_available_space_and_a_smart_parking_boy_who_managed_parking_log_B_without_available_space_and_a_parking_lot_C_with_available_space() {
    ParkingLot parkingLotA = buildParkingLotWithoutAvailableSpace();
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotA);
    ParkingLot parkingLotB = buildParkingLotWithoutAvailableSpace();
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotB);
    ParkingLot parkingLotC = new ParkingLot(1);
    ParkingManager parkingManager = new ParkingManager(graduateParkingBoy, smartParkingBoy, parkingLotC);

    Ticket ticket = parkingManager.park(new Car());

    assertNotNull(ticket);
    assertNotNull(parkingLotC.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotB.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotC.pick(ticket));
  }

  private ParkingLot buildParkingLotWithoutAvailableSpace() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }
}
