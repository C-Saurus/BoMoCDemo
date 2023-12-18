package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.ShipMethod;

public interface ShipMethodDAO {
	public List<ShipMethod> getShipMethod();
	public float getPriceById(int id);
}
