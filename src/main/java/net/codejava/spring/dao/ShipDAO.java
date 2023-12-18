package net.codejava.spring.dao;

import net.codejava.spring.model.Shipment;

public interface ShipDAO {
	public int addShip(Shipment ship);
	public String updateStatus(String status, int ship_id);
	public String addOrderId(int order_id, int ship_id);
	public Shipment getShipmentInfo(int shipment_id);
	public int getShipMethodId(int ship_id);
}
