/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

import java.util.Date;


/**
 * 经理约检问题统计Entity
 * @author mh
 * @version 2017-05-27
 */
public class BizBusinessSynProblemQuery extends DataEntity<BizBusinessSynProblemQuery> {
	
private Integer  managerId;
private Integer storeId;
private Integer projectMode;
private  String realName;
private String managerPhone;
private String engineDepartName;
private Integer managerCount;
private Integer engineDepartId;
private Date start;
private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getEngineDepartId() {
        return engineDepartId;
    }

    public void setEngineDepartId(Integer engineDepartId) {
        this.engineDepartId = engineDepartId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(Integer projectMode) {
        this.projectMode = projectMode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getEngineDepartName() {
        return engineDepartName;
    }

    public void setEngineDepartName(String engineDepartName) {
        this.engineDepartName = engineDepartName;
    }

    public Integer getManagerCount() {
        return managerCount;
    }

    public void setManagerCount(Integer managerCount) {
        this.managerCount = managerCount;
    }
}