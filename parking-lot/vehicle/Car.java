package vehicle;

public class Car implements Vehicle {
	private final String licensePlate;

	public Car(String licensePlate){
		this.licensePlate=licensePlate;
	}

	public String getLicensePlate(){
		return this.licensePlate;
	}

	public VehicleSize getSize(){
		return VehicleSize.MEDIUM;
	}
}

