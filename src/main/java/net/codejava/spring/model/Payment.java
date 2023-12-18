package net.codejava.spring.model;

public class Payment {
	private int pay_id;
	private String customer_id;
	private int order_id;
	private int ship_id;
	private String pay_status;
	private String pay_date;
	private int pay_method_id;
	
	public Payment() {}

	public Payment(int pay_id, String customer_id, int order_id, int ship_id, String pay_status, String pay_date,
			int pay_method_id) {
		super();
		this.pay_id = pay_id;
		this.customer_id = customer_id;
		this.order_id = order_id;
		this.ship_id = ship_id;
		this.pay_status = pay_status;
		this.pay_date = pay_date;
		this.pay_method_id = pay_method_id;
	}

	public int getPay_id() {
		return pay_id;
	}

	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
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

	public int getShip_id() {
		return ship_id;
	}

	public void setShip_id(int ship_id) {
		this.ship_id = ship_id;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public int getPay_method_id() {
		return pay_method_id;
	}

	public void setPay_method_id(int pay_method_id) {
		this.pay_method_id = pay_method_id;
	}
}
