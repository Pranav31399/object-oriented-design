import fare.*;
import spot.*;
import vehicle.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotTest {

    // Minimal assertion helpers so this file compiles and runs without JUnit
    public static void assertTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    public static void assertFalse(boolean condition, String message) {
        if (condition) throw new AssertionError(message);
    }

    public static void assertNotNull(Object obj, String message) {
        if (obj == null) throw new AssertionError(message);
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null) {
            if (actual != null) throw new AssertionError(message + " (expected null, was: " + actual + ")");
        } else if (!expected.equals(actual)) {
            throw new AssertionError(message + " (expected: " + expected + ", was: " + actual + ")");
        }
    }

    public static void main(String[] args) {
        try {
            new ParkingLotTest().testVehicleJourney();
            System.out.println("All checks passed.");
        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
            System.exit(1);
        }
    }

    public void testVehicleJourney() {
        System.out.println("\n=== Testing Parking Lot System: Complete Vehicle Journey ===");

        System.out.println("\n--- Setting Up Parking Spots ---");
        Map<VehicleSize, List<ParkingSpot>> availableSpots = new HashMap<>();
        availableSpots.put(VehicleSize.MEDIUM, new ArrayList<>());
        availableSpots.get(VehicleSize.MEDIUM).add(new RegularSpot(1));
        availableSpots.get(VehicleSize.MEDIUM).add(new RegularSpot(2));
        System.out.println("✓ Created 2 regular parking spots for medium-sized vehicles");
        System.out.println("  - Spot 1: Regular parking spot");
        System.out.println("  - Spot 2: Regular parking spot");

        System.out.println("\n--- Initializing Parking Manager ---");
        Map<Vehicle, ParkingSpot> vehicleToSpotMap = new HashMap<>();
        ParkingManager parkingManager = new ParkingManager(availableSpots, vehicleToSpotMap);
        System.out.println("✓ Parking manager initialized with available spots");

        System.out.println("\n--- Setting Up Fare Calculation System ---");
        List<FareStrategy> strategies = new ArrayList<>(List.of(new BaseFareStrategy(), new PeakHoursStrategy()));
        FareCalculator fareCalculator = new FareCalculator(strategies);
        System.out.println("✓ Fare calculator initialized with multiple strategies:");
        System.out.println("  - Base fare strategy");
        System.out.println("  - Peak hours fare strategy");

        ParkingLot parkingLot = new ParkingLot(parkingManager, fareCalculator);

        System.out.println("\n--- Creating Test Vehicle ---");
        Vehicle car = new Car("ABC123");
        System.out.println("✓ Created car with license plate: ABC123");
        System.out.println("  - Vehicle type: Car (MEDIUM size)");

        System.out.println("\n--- Vehicle Entering Parking Lot ---");
        // Vehicle enters the parking lot
        Ticket ticket = parkingLot.enterVehicle(car);
        System.out.println("✓ Ticket generated for vehicle ABC123");
        assertNotNull(ticket, "Ticket should not be null");
        assertEquals(car, ticket.getVehicle(), "Vehicle should match the one that entered");
        assertNotNull(ticket.getParkingSpot(), "Parking spot should not be null");
        System.out.println("✓ Ticket validation passed:");
        System.out.println("  - Ticket is not null");
        System.out.println("  - Vehicle matches the one that entered");
        System.out.println("  - Parking spot assigned successfully");

        // Use the ticket's parking spot to verify occupation
        ParkingSpot parkedSpot = ticket.getParkingSpot();
        assertFalse(parkedSpot.isAvailable(), "Parking spot should be occupied after vehicle enters");

        System.out.println("\n--- Vehicle Leaving Parking Lot ---");
        // Vehicle leaves the parking lot
        parkingLot.leaveVehicle(ticket);
        assertNotNull(ticket.getExitTime(), "Exit time should be set");
        assertTrue(parkedSpot.isAvailable(), "Parking spot should be available after vehicle leaves");
        System.out.println("✓ Vehicle exit verification passed:");
        System.out.println("  - Exit time recorded on ticket");
        System.out.println("  - Parking spot is now available for other vehicles");
        System.out.println("=== Parking Lot Vehicle Journey Test Completed Successfully ===\n");
    }
}
