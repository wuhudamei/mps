package cn.damei.entity.modules;



import java.io.Serializable;

/**
 * Created by joseph on 2017/8/17.
 */


public class ManagerSyntheticStarEntity  implements Serializable,Comparable<ManagerSyntheticStarEntity>{
    private static final long serialVersionUID = 1L;
    private  Integer star;
    private  Integer star_level;

    public Integer getStar_level() {
        return star_level;
    }

    public void setStar_level(Integer star_level) {
        this.star_level = star_level;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    @Override
    public int compareTo( ManagerSyntheticStarEntity o) {
        return this.getStar_level()>o.getStar_level()?1:0;
    }
}
