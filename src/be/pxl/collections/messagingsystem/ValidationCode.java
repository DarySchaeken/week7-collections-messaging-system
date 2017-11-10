package be.pxl.collections.messagingsystem;

public enum ValidationCode implements ErrorCode {
	MISSING_REQUIRED_FIELD("Missing required field(s)."),
	DUPLICATE_FOLLOWER("There's already a follower with...");
	
	private String message;
	
	private ValidationCode(String message) {
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}

}
