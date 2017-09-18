package messages;

public class ErrorMessage {
	private String error;

	public ErrorMessage() {
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorMessage [error=" + error + "]";
	}

	public ErrorMessage(String error) {
		super();
		this.error = error;
	}
}