package net.codejava.spring.dao;

import net.codejava.spring.model.Payment;

public interface PayDAO {
	public int addPayment(Payment payment);
	public Payment getPaymentInfo(int payment_id);
	public String updatePaymentStatus(int pay_id, String status);
}
