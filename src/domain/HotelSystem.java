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
	
	/**
	 * find availabe room in hotels and return it
	 * @param date
	 * @param nights
	 * @param capicity
	 * @return
	 */
	public Room availableRoom(LocalDate date, int nights, int capicity) {
		Room r;
		for(Hotel hotel: this.hotels) {
			if((r = hotel.searchEmptyRoom(capicity)) != null) {
				return r;
			} else {
				for(Room room: hotel.getRooms()) {
					if(room.isRoomAvailable(capicity, date, nights))
						return room;
				}
			}
		}
		return null;	
	}
	
	/**
	 * make booking for customer and return true or false
	 * true means book success
	 * false means book unsuccess
	 * @param rooms
	 * @param name
	 * @param date
	 * @param nights
	 * @param numOfBooking
	 */
	public boolean makeBookings(LinkedList<Room> rooms, String name, 
				LocalDate date, int nights, int numOfBooking) {
		if(rooms.isEmpty() || rooms.toArray().length < numOfBooking) {
			return false;
		}
		LinkedList<Booking> bookings = new LinkedList<Booking>();
		for(Room room: rooms) {
			Booking booking = new Booking(name, date, nights);
			room.addBooking(booking);
			bookings.add(booking);
		}
		this.cusBookings.put(name, bookings);	
		return true;
	}
	
	/**
	 * cancel booking
	 * @param name
	 */
	public void cancelBooking(String name) {
		
	}
	
	
}
