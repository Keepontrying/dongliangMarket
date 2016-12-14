package org.gradle.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7115551391485860565L;
	protected Integer id;
	protected Date createDate;
	protected Date modifiedDate;
	
}
