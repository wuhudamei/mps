
package cn.damei.entity.modules;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.TreeEntity;


public class Office extends TreeEntity<Office> {

	private static final long serialVersionUID = 1L;


	private Area area;
	private String code;


	private String type;
	private String grade;
	private String address;
	private String zipCode;
	private String master;
	private String phone;
	private String fax;
	private String email;
	private String useable;
	private User primaryPerson;
	private User deputyPerson;
	private List<String> childDeptList;
	
	private String label;
	private String value;

	public Office(){
		super();

		this.type = "2";
	}

	public Office(String id){
		super(id);
	}

	@Length(min=0, max=255)
	public String getAddress() {
		return address;
	}

	@NotNull
	public Area getArea() {
		return area;
	}
	public List<String> getChildDeptList() {
		return childDeptList;
	}

	@Length(min=0, max=100)
	public String getCode() {
		return code;
	}
	
	public User getDeputyPerson() {
		return deputyPerson;
	}

	@Length(min=0, max=200)
	public String getEmail() {
		return email;
	}

	@Length(min=0, max=200)
	public String getFax() {
		return fax;
	}

	@Length(min=1, max=1)
	public String getGrade() {
		return grade;
	}

	public String getLabel() {
		return label;
	}

	@Length(min=0, max=100)
	public String getMaster() {
		return master;
	}



	public Office getParent() {
		return parent;
	}

	@Length(min=0, max=200)
	public String getPhone() {
		return phone;
	}

public User getPrimaryPerson() {
		return primaryPerson;
	}

	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public String getUseable() {
		return useable;
	}

	public String getValue() {
		return value;
	}
	
	@Length(min=0, max=100)
	public String getZipCode() {
		return zipCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setArea(Area area) {
		this.area = area;
	}


















	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDeputyPerson(User deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}










	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPrimaryPerson(User primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}




	
	@Override
	public String toString() {
		return name;
	}
}