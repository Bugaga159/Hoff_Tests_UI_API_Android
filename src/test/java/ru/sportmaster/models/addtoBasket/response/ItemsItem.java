package ru.sportmaster.models.addtoBasket.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemsItem{
	private Product product;
	private int quantity;
	private Price price;
}