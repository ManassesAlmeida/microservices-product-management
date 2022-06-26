package com.manassesalmeida.auth.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
}
