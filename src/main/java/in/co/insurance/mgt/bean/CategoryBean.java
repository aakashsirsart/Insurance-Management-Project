package in.co.insurance.mgt.bean;

import java.io.InputStream;
import java.sql.Blob;

public class CategoryBean extends BaseBean{

	private String name;
	private String description;
	private Blob image;
	private InputStream photo;
	
	
	
	
	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return name;
	}

}
