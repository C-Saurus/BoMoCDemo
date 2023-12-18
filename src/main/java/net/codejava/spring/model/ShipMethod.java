package net.codejava.spring.model;

public class ShipMethod {
	private int ship_method_id;
	private String ship_method_name;
	private float price;
	
	public ShipMethod() {}

	public ShipMethod(int ship_method_id, String ship_method_name, float price) {
		super();
		this.ship_method_id = ship_method_id;
		this.ship_method_name = ship_method_name;
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getShip_method_id() {
		return ship_method_id;
	}

	public void setShip_method_id(int ship_method_id) {
		this.ship_method_id = ship_method_id;
	}

	public String getShip_method_name() {
		return ship_method_name;
	}

	public void setShip_method_name(String ship_method_name) {
		this.ship_method_name = ship_method_name;
	}
	
	
}
