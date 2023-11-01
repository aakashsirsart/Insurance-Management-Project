package in.co.insurance.mgt.bean;

import java.io.InputStream;
import java.sql.Blob;

public class PolicyBean extends BaseBean {
	
	private String name;
	private String sumAssured;
	private String preminum;
	private String tenure;
	private long categoryId;
	private String categoryName;
	private long subCategoryId;
	private String subCategoryName;
	private Blob image;
	private InputStream photo;
	private String description;
	
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}

	public String getPreminum() {
		return preminum;
	}

	public void setPreminum(String preminum) {
		this.preminum = preminum;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
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
