
package cn.damei.common.supcan.annotation.common.fonts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupFont {
	

	String faceName() default "";


	String charSet() default "";


	String height() default "";
	

	String weight() default "";


	String width() default "";
	

	String italic() default "";
	

	String underline() default "";

}
