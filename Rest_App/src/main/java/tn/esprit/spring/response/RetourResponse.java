package tn.esprit.spring.response;

import java.util.ArrayList;
import java.util.List;

public class RetourResponse<T> {
	private String stringValue;
	private List<T> objectValue = new ArrayList<T>();

	public RetourResponse() {
		super();
	}

	public RetourResponse(String stringValue, List<T> objectValue) {
		super();
		this.stringValue = stringValue;
		this.objectValue = objectValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public List<T> getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(List<T> objectValue) {
		this.objectValue = objectValue;
	}

	@Override
	public String toString() {
		return "Retour [stringValue=" + stringValue + ", objectValue=" + objectValue + "]";
	}

}
