
package cn.damei.common.supcan.annotation.treelist.cols;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupCol {


	

	String name() default "";


	String isUnique() default "";
	

	String nullAble() default "";
	

	String defaultValue() default "";
	

	String dataType() default "";
	

	String decimal() default "";
	

	String isHyperlink() default "";
	

	String isHide() default "";
	

	String sortAble() default "";
	

	String moveAble() default "";
	

	String pasteAble() default "";
	

	String textId() default "";
	

	

	String isThousandSeparat() default "";
	

	String width() default "";
	

	String minWidth() default "";
	

	String align() default "";
	

	String vAlign() default "";
	

	String alignHeader() default "";
	

	String fontIndex() default "";
	

	String headerFontIndex() default "";
	

	String headerTextColor() default "";
	

	String headerIcon() default "";
	

	String headerIconTip() default "";
	

	String displayMask() default "";
	

	String atLayer() default "";
	

	String extentRows() default "";
	

	String dropDisplayType() default "";
	

	String VColSep() default "";
	

	String VColSepStyle() default "";
	

	String totalExpress() default "";
	

	String subTotalExpress() default "";
	

	String text() default "";
	

	String groupId() default "";


	int sort() default 0;
}
