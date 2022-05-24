package ru.sportmaster.models.addToComparison.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqProductId {
	private String productId;
}