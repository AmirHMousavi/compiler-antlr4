package pa3.analysis;

import java.io.Serializable;

public class Record implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String type;

	public Record(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public String getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

}
