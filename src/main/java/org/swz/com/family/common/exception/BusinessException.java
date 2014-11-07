package org.swz.com.family.common.exception;


public class BusinessException extends SuperException{ 
    

	private static final long serialVersionUID = 1L;   
	
    public  BusinessException(String code, String message){
    	super(code, message);
    } 
		
}
