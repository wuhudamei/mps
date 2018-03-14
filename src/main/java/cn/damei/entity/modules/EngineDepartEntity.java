package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joseph on 2017/8/17.
 */
public class EngineDepartEntity  implements Serializable{

    private static final long serialVersionUID = 1L;

    private String storeName;
    private String projectMode;
    private String engineDepartName;
private Integer storeId;

    private List<ManagerSyntheticStarEntity> list;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public String getEngineDepartName() {
        return engineDepartName;
    }

    public void setEngineDepartName(String engineDepartName) {
        this.engineDepartName = engineDepartName;
    }

    public List<ManagerSyntheticStarEntity> getList() {
        return list;
    }

    public void setList(List<ManagerSyntheticStarEntity> list) {
        this.list = list;
    }
}
