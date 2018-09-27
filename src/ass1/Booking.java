/**
 * 
 */
package ass1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author vicky
 *
 */
public class Booking implements Comparable<Booking> {
	private String customer;
	private LocalDate arriveDate;
	private int nights;
	private boolean isActive;
	
	/**
	 * constructor
	 * @param customer
	 * @param arriveDate
	 * @param nights
	 */
	public Booking(String customer, LocalDate arriveDate, int nights) {
		this.customer = customer;
		this.arriveDate = arriveDate;
		this.nights = nights;
		this.isActive = true;
	}

	@Override
	public int compareTo(Booking o) {
		return this.arriveDate.compareTo(o.getArriveDate());
	}

	/**
	 * @return the arriveDate
	 */
	public LocalDate getArriveDate() {
		return arriveDate;
	}

	/**
	 * @return the status
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * change the status of a booking
	 */
	public void toggleActiveness() {
		this.isActive = !this.isActive;
	}
	
	/**
	 * calculate leave date and return
	 * @return
	 */
	public LocalDate getLeaveDate() {
		return this.arriveDate.plusDays(this.nights);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
		String date = this.arriveDate.format(formatter);
		return date + " " + nights;
	}
	

}
