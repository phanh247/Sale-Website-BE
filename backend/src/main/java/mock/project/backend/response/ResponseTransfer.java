package mock.project.backend.response;

public class ResponseTransfer {
	
	public ResponseTransfer(String text) {
		super();
		this.text = text;
	}

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
