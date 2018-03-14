
package cn.damei.common.supcan.common.properties;

import java.util.List;

import com.google.common.collect.Lists;
import cn.damei.common.supcan.annotation.common.properties.SupExpress;
import cn.damei.common.supcan.annotation.common.properties.SupProperties;
import cn.damei.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


@XStreamAlias("Properties")
public class Properties {
	

	@XStreamAsAttribute
	private String id;


	@XStreamAsAttribute
	private String key;


	@XStreamAsAttribute
	private String isTree = "false";
	

	@XStreamAsAttribute
	private String isShowRuler = "false";


	@XStreamAsAttribute
	private String isFixTotalRow = "false";


	@XStreamAsAttribute
	private String totalBgColor = "#FFFFCC";


	@XStreamAsAttribute
	private String subTotalBgColor = "#FFFFCC";


	@XStreamAsAttribute
	private String editAble = "false";
	

	@XStreamAsAttribute
	private String addRowAble = "true";
	

	@XStreamAsAttribute
	private String separateBarStyle = "false";


	@XStreamAsAttribute
	private String sortAble = "true";


	@XStreamAsAttribute
	private String multiLayerAble = "false";


	@XStreamAsAttribute
	private String fadeInStep = "0";


	@XStreamAsAttribute
	private String headerBgColor = "#FDFDFD,#F0F1EF";


	@XStreamAsAttribute
	private String headerHeight = "28";


	@XStreamAsAttribute
	private String leftColor = "#F0F1EF,#FDFDFD";


	@XStreamAsAttribute
	private String rowHeight = "28";


	@XStreamAsAttribute
	private String curSelBgColor = "#F5F5F5,#EDEDED";
	

	@XStreamAsAttribute
	private String displayMask;
	

	@XStreamAsAttribute
	private String headerFontIndex;
	

	@XStreamAlias("Background")
	private Background packground = new Background();


	@XStreamAlias("Expresses")
	private List<Express> expresses;
	

	@XStreamAsAttribute
	private String title;
	
	public Properties() {
		
	}

	public Properties(SupProperties supProperties) {
		this();
		ObjectUtils.annotationToObject(supProperties, this);
		if (supProperties.packground() != null){
			this.packground = new Background(supProperties.packground());
		}
		if (supProperties.expresses() != null){
			for (SupExpress supExpress : supProperties.expresses()){
				if (this.expresses == null){
					this.expresses = Lists.newArrayList();
				}
				this.expresses.add(new Express(supExpress));
			}
		}
	}
	
	public Properties(String id) {
		this();
		this.id = id;
	}

	public Properties(String id, String key) {
		this(id);
		this.key = key;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIsTree() {
		return isTree;
	}

	public void setIsTree(String isTree) {
		this.isTree = isTree;
	}

	public String getIsShowRuler() {
		return isShowRuler;
	}

	public void setIsShowRuler(String isShowRuler) {
		this.isShowRuler = isShowRuler;
	}

	public String getIsFixTotalRow() {
		return isFixTotalRow;
	}

	public void setIsFixTotalRow(String isFixTotalRow) {
		this.isFixTotalRow = isFixTotalRow;
	}

	public String getTotalBgColor() {
		return totalBgColor;
	}

	public void setTotalBgColor(String totalBgColor) {
		this.totalBgColor = totalBgColor;
	}

	public String getSubTotalBgColor() {
		return subTotalBgColor;
	}

	public void setSubTotalBgColor(String subTotalBgColor) {
		this.subTotalBgColor = subTotalBgColor;
	}

	public String getEditAble() {
		return editAble;
	}

	public void setEditAble(String editAble) {
		this.editAble = editAble;
	}

	public String getSeparateBarStyle() {
		return separateBarStyle;
	}

	public void setSeparateBarStyle(String separateBarStyle) {
		this.separateBarStyle = separateBarStyle;
	}

	public String getSortAble() {
		return sortAble;
	}

	public void setSortAble(String sortAble) {
		this.sortAble = sortAble;
	}

	public String getMultiLayerAble() {
		return multiLayerAble;
	}

	public void setMultiLayerAble(String multiLayerAble) {
		this.multiLayerAble = multiLayerAble;
	}

	public String getFadeInStep() {
		return fadeInStep;
	}

	public void setFadeInStep(String fadeInStep) {
		this.fadeInStep = fadeInStep;
	}

	public String getHeaderBgColor() {
		return headerBgColor;
	}

	public void setHeaderBgColor(String headerBgColor) {
		this.headerBgColor = headerBgColor;
	}

	public String getHeaderHeight() {
		return headerHeight;
	}

	public void setHeaderHeight(String headerHeight) {
		this.headerHeight = headerHeight;
	}

	public String getLeftColor() {
		return leftColor;
	}

	public void setLeftColor(String leftColor) {
		this.leftColor = leftColor;
	}

	public String getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(String rowHeight) {
		this.rowHeight = rowHeight;
	}

	public String getCurSelBgColor() {
		return curSelBgColor;
	}

	public void setCurSelBgColor(String curSelBgColor) {
		this.curSelBgColor = curSelBgColor;
	}

	public String getHeaderFontIndex() {
		return headerFontIndex;
	}

	public void setHeaderFontIndex(String headerFontIndex) {
		this.headerFontIndex = headerFontIndex;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Background getPackground() {
		return packground;
	}

	public void setPackground(Background packground) {
		this.packground = packground;
	}

	public List<Express> getExpresses() {
		return expresses;
	}

	public void setExpresses(List<Express> expresses) {
		this.expresses = expresses;
	}

	public String getDisplayMask() {
		return displayMask;
	}

	public void setDisplayMask(String displayMask) {
		this.displayMask = displayMask;
	}

	public String getAddRowAble() {
		return addRowAble;
	}

	public void setAddRowAble(String addRowAble) {
		this.addRowAble = addRowAble;
	}
	
}
