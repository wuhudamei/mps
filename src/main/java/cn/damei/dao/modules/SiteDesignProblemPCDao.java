package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BusinessPic;
import cn.damei.entity.modules.SiteDesignProblem;

@MyBatisDao
public interface SiteDesignProblemPCDao extends CrudDao2<SiteDesignProblem>{
	void insertBusinessPic(BusinessPic bp);
	SiteDesignProblem findDetails(Integer id, String businessProblemSolveRole5, String businessProblemStatus70, String pictureType2082);
	
	public List<String> findPictureByBusinessProblemId(Integer id,String picType );
	List<SiteDesignProblem> querySiteDesignProblemVoList(SiteDesignProblem bizOrderInstallItemProblemVo);
}
