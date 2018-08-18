package domain;

import java.util.LinkedList;

public class BookingManage {
	private LinkedList<Room> rooms;
	private Hotel hotel;
	private String name;
	
	public BookingManage(Hotel hotel, LinkedList<Room> rooms) {
		this.rooms = rooms;
		this.hotel = hotel;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the bookings
	 */
	public LinkedList<Room> getRooms() {
		return rooms;
	}

	/**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}
	
	
}