package javabeens;

public class MessageException extends Exception {
	private static final long serialVersionUID = -2079740207471716823L;
	
	public MessageException() {
	}

	public MessageException(String message) {
		super(message);
	}

	public MessageException(Throwable cause) {
		super(cause);
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);
	}


}
