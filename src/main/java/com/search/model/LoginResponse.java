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
public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 5885576811818159097L;

	private UserResponse user;
	private String token;
}
