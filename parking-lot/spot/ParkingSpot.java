package spot;

import vehicle.Vehicle;
import vehicle.VehicleSize;

public interface ParkingSpot {

	boolean isAvailable();

	void occupy(Vehicle vehicle);

	void vacate();

	int getSpotNumber();

	VehicleSize getSize();
}
