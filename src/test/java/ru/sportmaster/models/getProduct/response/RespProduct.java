package ru.sportmaster.models.getProduct.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespProduct{
	private String productId;
	private String productBrand;
	private List<String> productSport;
	private List<String> marks;
	private Availability availability;
	private String productAge;
	private String productName;
	private String productCategory;
	private String productSubcategory;
	private String productGender;
	private double productSalePrice;
	private String productSku;
	private String productPriceType;
	private String productGroup;
	private String productSubgroup;
	private String productColor;
	private double productPrice;
	private String productType;
}