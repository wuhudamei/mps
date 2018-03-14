
package cn.damei.common.supcan.annotation.treelist.cols;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupGroup {


	String id();
	

	String name() default "";


	String headerFontIndex() default "";


	String textColor() default "";
	

	String align() default "";


	String parentId() default "";
	

	int sort() default 0;
}
