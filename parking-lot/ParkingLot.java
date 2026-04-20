import fare.FareCalculator;
import fare.Ticket;
import spot.ParkingManager;
import spot.ParkingSpot;
import vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingLot{
	private final ParkingManager parkingManager;
	private final FareCalculator fareCalculator;

	public static void main(String[] args) {
	}


	public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator){
		this.parkingManager=parkingManager;
		this.fareCalculator=fareCalculator;
	}

	public Ticket enterVehicle(Vehicle vehicle){
		ParkingSpot spot=parkingManager.parkVehicle(vehicle);

		if(spot!=null){
			Ticket ticket=new Ticket(generateTicketId(),vehicle,spot, LocalDateTime.now());
			return  ticket;
		}
		else{
			return null;
		}
	}

	public void leaveVehicle(Ticket ticket){
		if(ticket!=null && ticket.getExitTime()==null){
			ticket.setExitTime(LocalDateTime.now());

			parkingManager.unparkVehicle(ticket.getVehicle());

			BigDecimal fare=fareCalculator.calculatorFare(ticket);
		}
		else {
			System.out.println("Invalid ticket or vehicle already exited.");
		}
	}

	private String generateTicketId() {
		return "TICKET-" + System.currentTimeMillis();
	}
}