
package cn.damei.common.supcan.annotation.treelist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.damei.common.supcan.annotation.common.fonts.SupFont;
import cn.damei.common.supcan.annotation.common.properties.SupProperties;
import cn.damei.common.supcan.annotation.treelist.cols.SupGroup;


@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupTreeList {
	

	SupProperties properties() default @SupProperties;


	SupFont[] fonts() default {};
	

	SupGroup[] groups() default {};
	
}
