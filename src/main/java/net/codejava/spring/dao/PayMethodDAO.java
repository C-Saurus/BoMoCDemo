package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.PayMethod;

public interface PayMethodDAO {
	public List<PayMethod> getPayMethod();
	public float getOfferById(int id);
}
