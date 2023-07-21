package com.search.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse implements Serializable {
	
	private static final long serialVersionUID = 2176973820874541498L;
	
	private String username;
	private String email;
}
