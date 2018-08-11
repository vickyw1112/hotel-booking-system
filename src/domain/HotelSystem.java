package domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class HotelSystem {
	private LinkedList<Hotel> hotels;
	private HashMap<String, LinkedList<Booking>> cusBookings;
	
	public HotelSystem() {
		this.hotels = new LinkedList<Hotel>();
		this.cusBookings = new HashMap<String, LinkedList<Booking>>();
	}

	/**
	 * @return the hotels
	 */
	public LinkedList<Hotel> getHotels() {
		return hotels;
	}

	/**
	 * @return the cusBookings
	 */
	public HashMap<String, LinkedList<Booking>> getCusBookings() {
		return cusBookings;
	}
	
	// TODO make booking
	public  availableRoom(LocalDate date, int nights, int capicity) {
		
		
	}
	
	public void cancelBooking(String name) {
		
	}
	
	
}
