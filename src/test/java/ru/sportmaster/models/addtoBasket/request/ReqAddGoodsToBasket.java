package ru.sportmaster.models.addtoBasket.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqAddGoodsToBasket{
	private String cartFormat;
	private List<ItemsGoods> items;
}