package net.codejava.spring.model;

public class PayMethod {
	private int pay_method_id;
	private String pay_method_name;
	private float offer_percent;
	
	public PayMethod() {}

	public PayMethod(int pay_method_id, String pay_method_name, float offer_percent) {
		super();
		this.pay_method_id = pay_method_id;
		this.pay_method_name = pay_method_name;
		this.offer_percent = offer_percent;
	}

	public int getPay_method_id() {
		return pay_method_id;
	}

	public void setPay_method_id(int pay_method_id) {
		this.pay_method_id = pay_method_id;
	}

	public String getPay_method_name() {
		return pay_method_name;
	}

	public void setPay_method_name(String pay_method_name) {
		this.pay_method_name = pay_method_name;
	}

	public float getOffer_percent() {
		return offer_percent;
	}

	public void setOffer_percent(float offer_percent) {
		this.offer_percent = offer_percent;
	}
	
}
