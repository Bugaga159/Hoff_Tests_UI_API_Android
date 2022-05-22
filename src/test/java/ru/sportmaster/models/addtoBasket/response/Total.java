package ru.sportmaster.models.addtoBasket.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Total{
	private int productsQuantity;
	private int productsCost;
	private int totalCost;
}