package ru.sportmaster.models.addToBasket.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataItem {
	private Total total;
	private List<ItemsItem> items;
}