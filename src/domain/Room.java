package domain;

import java.time.LocalDate;
import java.util.LinkedList;

public class Room {
	private String number;
	private int capicity;
	private LinkedList<Booking> bookings;
	private String hotelName;
	
	/**
	 * constructor
	 * @param number
	 * @param capacity
	 */
	public Room(String number, int capacity, String hotelName) {
		this.number = number;
		this.capicity = capacity;
		this.hotelName = hotelName;
		this.bookings = new LinkedList<Booking>();
	}
	
	/**
	 * @return the bookings
	 */
	public LinkedList<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * @return hotel name
	 */
	public String hotelName() {
		return this.hotelName;
	}

	/**
	 * @return the capacity
	 */
	public int getCapicity() {
		return capicity;
	}
	
	/**
	 * append a new booking
	 * @param booking
	 */
	public void addBooking(Booking booking) {
		this.bookings.add(booking);
	}
	
	/**
	 * delete a booking(when customer wants to cancel or change booking)
	 * @param booking
	 */
	public void removeBooking(Booking booking) {
		this.bookings.remove(booking);
	}
	
	/**
	 * decide the room is available
	 * @param capicity
	 * @param date
	 * @param nights
	 * @return
	 */
	public boolean isRoomAvailable(int capicity, LocalDate date, int nights) {
		if(this.capicity == capicity && this.isTimeAvailable(date, nights))
			return true;
		return false;
	}
	
	/**
	 * decide time is whether available
	 * @param date
	 * @param len
	 * @return
	 */
	private boolean isTimeAvailable(LocalDate date, int len) {
		Booking prev = null;
		for(Booking booking: this.bookings) {
			// if booking list is empty return true
			if(prev == null)
				continue;
			// is prevBooking leave date <= date <= curBooking arrive date
			// return true
			if(booking.getArriveDate().isAfter(date.plusDays(len)) 
						&& prev.getLeaveDate().isBefore(date))
				return true;
			else if(booking.getArriveDate().isEqual(date.plusDays(len)) 
						&& prev.getLeaveDate().isEqual(date))
				return true;
			prev = booking;
		}
		return false;
	}
	
	
	
}
