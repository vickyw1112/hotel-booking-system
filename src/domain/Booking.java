/**
 * 
 */
package domain;

import java.time.LocalDate;

/**
 * @author vicky
 *
 */
public class Booking {
	private String customer;
	private LocalDate arriveDate;
	private int nights;
	private Status status;
	private int bid;
	public static int count = 0;
	
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
		this.bid = count;
		count++;
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
	 * @return the bid
	 */
	public int getBid() {
		return bid;
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
	

}
