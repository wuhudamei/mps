/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessPic;

/**
 * 公共图片上传
 * @author llp
 * @version 2016-09-08
 */
@MyBatisDao
public interface BizBusinessPicDao extends CrudDao2<BizBusinessPic>{

	List<BizBusinessPic> getByBusinessId(Integer valueOf);

	List<BizBusinessPic> getByRecheckID(Integer recheckID, String type);

}