package net.codejava.spring.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Shipment {
	private int ship_id;
	private String customer_id;
	private int order_id;
	@Positive(message = "Ship method is required")
	private int ship_method_id;
	private int ship_service_id;
	private String ship_status;
	@NotBlank(message = "Address is required")
	private String address;
	
	public Shipment() {}

	public Shipment(int ship_id, String customer_id, int order_id, int ship_method_id, int ship_service_id,
			String ship_status, String address) {
		super();
		this.ship_id = ship_id;
		this.customer_id = customer_id;
		this.order_id = order_id;
		this.ship_method_id = ship_method_id;
		this.ship_service_id = ship_service_id;
		this.ship_status = ship_status;
		this.address = address;
	}

	public int getShip_id() {
		return ship_id;
	}

	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getShip_method_id() {
		return ship_method_id;
	}

	public void setShip_method_id(int ship_method_id) {
		this.ship_method_id = ship_method_id;
	}

	public int getShip_service_id() {
		return ship_service_id;
	}

	public void setShip_service_id(int ship_service_id) {
		this.ship_service_id = ship_service_id;
	}

	public String getShip_status() {
		return ship_status;
	}

	public void setShip_status(String ship_status) {
		this.ship_status = ship_status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Shipment [ship_id=" + ship_id + ", customer_id=" + customer_id + ", order_id=" + order_id
				+ ", ship_method_id=" + ship_method_id + ", ship_service_id=" + ship_service_id + ", ship_status="
				+ ship_status + ", address=" + address + "]";
	}
	
	
}
