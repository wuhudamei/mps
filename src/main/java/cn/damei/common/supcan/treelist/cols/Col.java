
package cn.damei.common.supcan.treelist.cols;

import cn.damei.common.supcan.annotation.treelist.cols.SupCol;
import cn.damei.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;


@XStreamAlias("Col")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = {"text"})
public class Col {



	@XStreamAsAttribute
	private String name;


	@XStreamAsAttribute
	private String isUnique = "false";
	

	@XStreamAsAttribute
	private String nullAble = "true";


	@XStreamAsAttribute
	private String defaultValue;


	@XStreamAsAttribute
	private String dataType;
	

	@XStreamAsAttribute
	private String decimal;
	

	@XStreamAsAttribute
	private String isHyperlink;


	@XStreamAsAttribute
	private String isHide;
	

	@XStreamAsAttribute
	private String sortAble;
	

	@XStreamAsAttribute
	private String moveAble;
	

	@XStreamAsAttribute
	private String pasteAble;


	@XStreamAsAttribute
	private String textId;
	

	

	@XStreamAsAttribute
	private String isThousandSeparat;
	

	@XStreamAsAttribute
	private String width;
	

	@XStreamAsAttribute
	private String minWidth;
	

	@XStreamAsAttribute
	private String align;
	

	@XStreamAsAttribute
	private String vAlign;
	

	@XStreamAsAttribute
	private String alignHeader;
	

	@XStreamAsAttribute
	private String fontIndex;
	

	@XStreamAsAttribute
	private String headerFontIndex;
	

	@XStreamAsAttribute
	private String headerTextColor;
	

	@XStreamAsAttribute
	private String headerIcon;
	

	@XStreamAsAttribute
	private String headerIconTip;


	@XStreamAsAttribute
	private String displayMask;
	

	@XStreamAsAttribute
	private String atLayer;
	

	@XStreamAsAttribute
	private String extentRows;
	

	@XStreamAsAttribute
	private String dropDisplayType;
	

	@XStreamAsAttribute
	private String VColSep;
	

	@XStreamAsAttribute
	private String VColSepStyle;
	

	@XStreamAsAttribute
	private String totalExpress;


	@XStreamAsAttribute
	private String subTotalExpress;
	

	private String text;
	

	@XStreamOmitField
	private String groupId;
	

	@XStreamOmitField
	private int sort;
	
	public Col() {
		
	}

	public Col(String name) {
		this.name = name;
	}
	
	public Col(String name, String text) {
		this(name);
		this.text = text;
	}
	
	public Col(SupCol supCol){
		ObjectUtils.annotationToObject(supCol, this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}

	public String getNullAble() {
		return nullAble;
	}

	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDecimal() {
		return decimal;
	}

	public void setDecimal(String decimal) {
		this.decimal = decimal;
	}

	public String getIsHyperlink() {
		return isHyperlink;
	}

	public void setIsHyperlink(String isHyperlink) {
		this.isHyperlink = isHyperlink;
	}

	public String getIsHide() {
		return isHide;
	}

	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}

	public String getSortAble() {
		return sortAble;
	}

	public void setSortAble(String sortAble) {
		this.sortAble = sortAble;
	}

	public String getMoveAble() {
		return moveAble;
	}

	public void setMoveAble(String moveAble) {
		this.moveAble = moveAble;
	}

	public String getPasteAble() {
		return pasteAble;
	}

	public void setPasteAble(String pasteAble) {
		this.pasteAble = pasteAble;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public String getIsThousandSeparat() {
		return isThousandSeparat;
	}

	public void setIsThousandSeparat(String isThousandSeparat) {
		this.isThousandSeparat = isThousandSeparat;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getvAlign() {
		return vAlign;
	}

	public void setvAlign(String vAlign) {
		this.vAlign = vAlign;
	}

	public String getAlignHeader() {
		return alignHeader;
	}

	public void setAlignHeader(String alignHeader) {
		this.alignHeader = alignHeader;
	}

	public String getFontIndex() {
		return fontIndex;
	}

	public void setFontIndex(String fontIndex) {
		this.fontIndex = fontIndex;
	}

	public String getHeaderFontIndex() {
		return headerFontIndex;
	}

	public void setHeaderFontIndex(String headerFontIndex) {
		this.headerFontIndex = headerFontIndex;
	}

	public String getHeaderTextColor() {
		return headerTextColor;
	}

	public void setHeaderTextColor(String headerTextColor) {
		this.headerTextColor = headerTextColor;
	}

	public String getHeaderIcon() {
		return headerIcon;
	}

	public void setHeaderIcon(String headerIcon) {
		this.headerIcon = headerIcon;
	}

	public String getHeaderIconTip() {
		return headerIconTip;
	}

	public void setHeaderIconTip(String headerIconTip) {
		this.headerIconTip = headerIconTip;
	}

	public String getDisplayMask() {
		return displayMask;
	}

	public void setDisplayMask(String displayMask) {
		this.displayMask = displayMask;
	}

	public String getAtLayer() {
		return atLayer;
	}

	public void setAtLayer(String atLayer) {
		this.atLayer = atLayer;
	}

	public String getExtentRows() {
		return extentRows;
	}

	public void setExtentRows(String extentRows) {
		this.extentRows = extentRows;
	}

	public String getDropDisplayType() {
		return dropDisplayType;
	}

	public void setDropDisplayType(String dropDisplayType) {
		this.dropDisplayType = dropDisplayType;
	}

	public String getVColSep() {
		return VColSep;
	}

	public void setVColSep(String vColSep) {
		VColSep = vColSep;
	}

	public String getVColSepStyle() {
		return VColSepStyle;
	}

	public void setVColSepStyle(String vColSepStyle) {
		VColSepStyle = vColSepStyle;
	}

	public String getTotalExpress() {
		return totalExpress;
	}

	public void setTotalExpress(String totalExpress) {
		this.totalExpress = totalExpress;
	}

	public String getSubTotalExpress() {
		return subTotalExpress;
	}

	public void setSubTotalExpress(String subTotalExpress) {
		this.subTotalExpress = subTotalExpress;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
