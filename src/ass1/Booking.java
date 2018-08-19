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
public class Booking {
	private String customer;
	private LocalDate arriveDate;
	private int nights;
	private Status status;
	
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
		this.status = Status.Current;
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
	public Status getStatus() {
		return status;
	}

	/**
	 * change the status of a booking
	 * @param completed
	 */
	public void setStatus(Status completed) {
		this.status = completed;
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
		String text = this.arriveDate.format(formatter);
		return text;
	}
	

}
