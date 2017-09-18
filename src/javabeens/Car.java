package javabeens;


public class Car {
	private long id;
	private String licensePlate;
	private Make make;
	private Colors color;
	private int yearMunuf;
	private int capacity;
	private int ownersNum;
	
	public Car() {
		super();
	}

	public Car(long id, String licensePlate, Make make, Colors color, int year, int capacity, int ownersNum) {
		super();
		this.id = id;
		this.licensePlate = licensePlate;
		this.make = make;
		this.color = color;
		this.yearMunuf = year;
		this.capacity = capacity;
		this.ownersNum = ownersNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Make getMake() {
		return make;
	}

	public void setMake(Make make) {
		this.make = make;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public int getYear() {
		return yearMunuf;
	}

	public void setYear(int year) {
		this.yearMunuf = year;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getOwnersNum() {
		return ownersNum;
	}

	public void setOwnersNum(int ownersNum) {
		this.ownersNum = ownersNum;
	}

	@Override
	public String toString() {
		return "Cars [id=" + id + ", licensePlate=" + licensePlate + ", make=" + make + ", color=" + color + ", year="
				+ yearMunuf + ", capacity=" + capacity + ", ownersNum=" + ownersNum + "]";
	}
	
}
