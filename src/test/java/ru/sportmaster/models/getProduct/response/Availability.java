package ru.sportmaster.models.getProduct.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Availability{
	private int delivery;
	private int deliveryExpress;
	private int pickupLater;
	private int pickpoint;
	private int pickup;
}