package spot;

import vehicle.Vehicle;
import vehicle.VehicleSize;

public class RegularSpot implements ParkingSpot{
	private int spotNumber;
	private Vehicle vehicle;

	public RegularSpot(int spotNumber){
		this.spotNumber=spotNumber;
		this.vehicle=null;
	}

	@Override
	public int getSpotNumber(){
		return this.spotNumber;
	}

	@Override
	public boolean isAvailable(){
		return vehicle==null;
	}

	@Override
	public void occupy(Vehicle vehicle){
		if(isAvailable())
			this.vehicle=vehicle;
		else
			System.out.println("Spot is already occupied");
	}

	@Override
	public void vacate(){
		this.vehicle=null;
	}

	@Override
	public VehicleSize getSize(){
		return VehicleSize.MEDIUM;
	}
}

