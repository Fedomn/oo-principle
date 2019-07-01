package com.tw.parking;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ParkingManagerTest {

  @Test
  void should_parked_car_through_graduate_parking_boy_and_return_a_invalid_ticket_when_park_car_given_a_parking_manager_who_have_a_graduate_parking_boy_who_managed_parking_lot_A_with_available_space_and_a_smart_parking_boy_who_managed_parking_log_B_and_a_parking_lot_C() {

    ParkingLot parkingLotA = new ParkingLot(1);
    GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotA);
    ParkingLot parkingLotB = new ParkingLot(1);
    SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotB);
    ParkingLot parkingLotC = new ParkingLot(1);
    ParkingManager parkingManager = new ParkingManager(graduateParkingBoy, smartParkingBoy,
        parkingLotC);

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
    ParkingManager parkingManager = new ParkingManager(graduateParkingBoy, smartParkingBoy,
        parkingLotC);

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
    ParkingManager parkingManager = new ParkingManager(graduateParkingBoy, smartParkingBoy,
        parkingLotC);

    Ticket ticket = parkingManager.park(new Car());

    assertNotNull(ticket);
    assertNotNull(parkingLotC.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotB.pick(ticket));
    assertThrows(InvalidTicketException.class, () -> parkingLotC.pick(ticket));
  }

  @ParameterizedTest()
  @MethodSource("AllParkingManagerScenario")
  void should_picked_corresponding_car_when_pick_car_by_parking_manager_given_valid_ticket_whoever_go_to_parked(List<IParkingLot> iParkingLots) {
    ParkingManager parkingManager = new ParkingManager(iParkingLots.toArray(new IParkingLot[0]));
    Car parkedCar = new Car();
    Ticket ticket = parkingManager.park(parkedCar);

    Car pickedCar = parkingManager.pick(ticket);

    assertSame(pickedCar, parkedCar);
  }

  @ParameterizedTest()
  @MethodSource("AllParkingManagerScenario")
  void should_reject_the_ticket_when_pick_car_by_parking_manager_given_invalid_ticket_whatever_parking_manager_managed(List<IParkingLot> iParkingLots) {
    ParkingManager parkingManager = new ParkingManager(iParkingLots.toArray(new IParkingLot[0]));

    assertThrows(InvalidTicketException.class, () -> parkingManager.pick(new Ticket()));
  }

  @ParameterizedTest()
  @MethodSource("AllParkingManagerScenario")
  void should_picked_corresponding_car_first_time_and_reject_second_time_when_pick_car_twice_use_same_ticket_by_parking_manager_given_valid_ticket_whoever_go_to_parked(List<IParkingLot> iParkingLots) {
    ParkingManager parkingManager = new ParkingManager(iParkingLots.toArray(new IParkingLot[0]));
    Car parkedCar = new Car();
    Ticket ticket = parkingManager.park(parkedCar);

    Car pickedCar = parkingManager.pick(ticket);

    assertSame(pickedCar, parkedCar);
    assertThrows(InvalidTicketException.class, () -> parkingManager.pick(ticket));
  }

  private static Stream<List<IParkingLot>> AllParkingManagerScenario() {
    IParkingLot[] iParkingLots = {new GraduateParkingBoy(new ParkingLot(1)),
        new SmartParkingBoy(new ParkingLot(1)),
        new ParkingLot(1)};

    return fullCombination(iParkingLots).stream();
  }

  private static List<List<IParkingLot>> fullCombination(IParkingLot[] m) {
    List<List<IParkingLot>> result = new ArrayList<>();
    for (int i = 1; i < Math.pow(2, m.length) - 1; i++) {
      List<IParkingLot> eligibleCollections = new ArrayList<>();
      for (int j = 0; j < m.length; j++) {
        if ((i & (int) Math.pow(2, j)) == Math.pow(2, j)) {
          eligibleCollections.add(m[j]);
        }
      }
      result.add(eligibleCollections);
    }
    return result;
  }

  private ParkingLot buildParkingLotWithoutAvailableSpace() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    return parkingLot;
  }
}
