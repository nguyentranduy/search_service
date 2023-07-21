package com.search.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse implements Serializable {

	private static final long serialVersionUID = 5033007343786423232L;
	
	private String code;
	private String name;
	private BigDecimal price;
}
