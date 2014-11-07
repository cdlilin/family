package org.swz.com.family.common.exception;

public class SuperException extends Exception {

	 
		private static final long serialVersionUID = -1857853182814811550L;
		private String code = "-1";
		private String message;
		
		protected SuperException(String code, String message) {  
	        super(message);  
	        this.code = code;
	        this.message = message;
	    } 
		
		public SuperException(String message) {  
	        super(message);  
	    }  
	  
	    public SuperException(Throwable cause) {  
	        super(cause);  
	    }  
	  
	    public SuperException(String message, Throwable cause) {  
	        super(message, cause);  
	        // TODO Auto-generated constructor stub  
	    }  
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		public String toString(){
			return "{\"code\": " + "\"" + this.code + "\","  + "\"message\": \"" + this.message + "\"}";
		}
		
}
