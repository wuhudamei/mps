package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderDisclosePic;


@MyBatisDao
public interface BizOrderDisclosePicDao extends CrudDao2<BizOrderDisclosePic>{

	List<BizOrderDisclosePic> getByOrderDiscloseId(Integer orderDiscloseId);

}
