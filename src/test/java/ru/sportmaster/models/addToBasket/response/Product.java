package ru.sportmaster.models.addToBasket.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product{
	private String productId;
	private String wareId;
	private Price price;
}