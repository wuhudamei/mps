
package cn.damei.common.supcan.annotation.common.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupProperties {
	

	String id() default "";


	String key() default "";


	String isTree() default "";
	

	String isShowRuler() default "";


	String isFixTotalRow() default "";


	String totalBgColor() default "";


	String subTotalBgColor() default "";


	String editAble() default "";
	

	String addRowAble() default "";


	String separateBarStyle() default "";


	String sortAble() default "";


	String multiLayerAble() default "";


	String fadeInStep() default "";


	String headerBgColor() default "";


	String headerHeight() default "";


	String leftColor() default "";


	String rowHeight() default "";


	String curSelBgColor() default "";
	

	String displayMask() default "";
	

	String headerFontIndex() default "";
	

	SupBackground packground() default @SupBackground;


	SupExpress[] expresses() default {};
	

	String title() default "";

}
