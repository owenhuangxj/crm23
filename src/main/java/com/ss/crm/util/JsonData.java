package com.ss.crm.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {

	private String key;
	private Object value;
	private String msg;
	private Boolean state;

}
