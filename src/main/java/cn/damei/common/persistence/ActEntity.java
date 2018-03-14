
package cn.damei.common.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.damei.entity.modules.Act;


public abstract class ActEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Act act;

	public ActEntity() {
		super();
	}
	
	public ActEntity(String id) {
		super(id);
	}
	
	@JsonIgnore
	public Act getAct() {
		if (act == null){
			act = new Act();
		}
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}


	public String getProcInsId() {
		return this.getAct().getProcInsId();
	}


	public void setProcInsId(String procInsId) {
		this.getAct().setProcInsId(procInsId);
	}
}
