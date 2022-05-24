package ru.sportmaster.models.addToComparison.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespProductIds{
	private List<String> productIds;
}