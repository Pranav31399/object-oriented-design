package vehicle;

public class Truck implements Vehicle{

	private final String licensePlate;

	public Truck(String licensePlate){
		this.licensePlate=licensePlate;
	}

	public String getLicensePlate(){
		return this.licensePlate;
	}

	public VehicleSize getSize(){
		return VehicleSize.LARGE;
	}
}



