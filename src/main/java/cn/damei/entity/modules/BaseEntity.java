
package cn.damei.entity.modules;



public class BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private String value;
	private String label;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BaseEntity() {
		super();
	}
	public BaseEntity(String value) {
		this.value = value;
	}
	public BaseEntity(String value, String label) {
		this.value = value;
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public String getValue() {
		return value;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}