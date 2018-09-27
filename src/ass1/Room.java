package ass1;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
	private String number;
	private int capicity;
	private LinkedList<Booking> bookings;
	
	/**
	 * constructor
	 * @param number
	 * @param capacity
	 */
	public Room(String number, int capacity) {
		this.number = number;
		this.capicity = capacity;
		this.bookings = new LinkedList<Booking>();
	}
	
	/**
	 * @return active bookings
	 */
	public List<Booking> getBookings() {
		return bookings.stream().filter(Booking::isActive).sorted().collect(Collectors.toList());
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capicity;
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
	 * @return
	 */
	public boolean canBook(LocalDate date, int numDays) {
        for(Booking booking: this.getBookings()) {
            // if new booking date conflicts with the old booking date in list
            // return false
            if(booking.getArriveDate().isBefore(date.plusDays(numDays))
                    && date.isBefore(booking.getLeaveDate()))
                return false;
//            } else if(!(booking.getLeaveDate().isBefore(date)
//                    || booking.getLeaveDate().isEqual(date))) {
//                return false;
//            }
        }
        return true;
	}
	
	/**
	 * is empty room 
	 * @return
	 */
	public boolean noBookings() {
		return bookings.isEmpty();
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.number);
        for (Booking booking : this.getBookings()) {
            sb.append(' ');
            sb.append(booking);
        }
        return new String(sb);
	}
	
	
}
