package vehicle;

public class MotorCycle implements Vehicle{
	private final String licensePlate;

	public MotorCycle(String licensePlate){
		this.licensePlate=licensePlate;
	}

	public String getLicensePlate(){
		return this.licensePlate;
	}

	public VehicleSize getSize(){
		return VehicleSize.SMALL;
	}
}


