package be.pxl.collections.messagingsystem;

import java.util.HashMap;
import java.util.Map;

public class MessageSystemException extends Exception {
	private static final long serialVersionUID = -3578744716576589038L;
	private ErrorCode errorCode;
	private Map<String, String> fields;

	public MessageSystemException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
		fields = new HashMap<String, String>();
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void addField(String key, String value) {
		fields.put(key, value);
	}

	public String getField(String key) {
		return fields.get(key);
	}

	public String getMessage() {
		String returnString = errorCode.getMessage() + "\n";
		returnString = returnString + fields.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue() + "\n")
				.reduce("", (accumulator, element) -> accumulator + element);
		return returnString;
	}

}
