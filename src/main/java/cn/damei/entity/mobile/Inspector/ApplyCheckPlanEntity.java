package cn.damei.entity.mobile.Inspector;

import java.util.Objects;

/**
 * Created by joseph on 2017/6/13.
 */
public class ApplyCheckPlanEntity extends BasePqcEntity implements Comparable<ApplyCheckPlanEntity>  {

    private String isChecked;//是否验收, 作为高亮

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ApplyCheckPlanEntity that = (ApplyCheckPlanEntity) o;
        return Objects.equals(isChecked, that.isChecked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isChecked);
    }




    public int compareTo(ApplyCheckPlanEntity o){

        return this.getCheckNodeIndex()>o.getCheckNodeIndex()?1:0;
    }
}
