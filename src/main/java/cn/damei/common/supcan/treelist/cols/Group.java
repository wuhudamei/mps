
package cn.damei.common.supcan.treelist.cols;

import java.util.List;

import com.google.common.collect.Lists;
import cn.damei.common.supcan.annotation.treelist.cols.SupGroup;
import cn.damei.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("Group")
public class Group {


	@XStreamAsAttribute
	private String id;
	

	@XStreamAsAttribute
	private String name;


	@XStreamAsAttribute
	private String headerFontIndex;


	@XStreamAsAttribute
	private String textColor;
	

	@XStreamAsAttribute
	private String align;
	

	@XStreamAlias("Cols")
	@XStreamImplicit
	private List<Object> cols;
	

	@XStreamOmitField
	private String parentId;


	@XStreamOmitField
	private int sort;
	
	public Group() {
		
	}
	
	public Group(String name) {
		this();
		this.name = name;
	}
	
	public Group(String name, List<Object> cols) {
		this(name);
		this.cols = cols;
	}
	
	public Group(SupGroup supGroup){
		ObjectUtils.annotationToObject(supGroup, this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Object> getCols() {
		if (cols == null){
			cols = Lists.newArrayList();
		}
		return cols;
	}

	public void setCols(List<Object> cols) {
		this.cols = cols;
	}

	public String getHeaderFontIndex() {
		return headerFontIndex;
	}

	public void setHeaderFontIndex(String headerFontIndex) {
		this.headerFontIndex = headerFontIndex;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
