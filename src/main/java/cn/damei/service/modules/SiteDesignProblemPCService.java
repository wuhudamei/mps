package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.SiteDesignProblemPCDao;
import cn.damei.entity.modules.BusinessPic;
import cn.damei.entity.modules.SiteDesignProblem;

@Service
public class SiteDesignProblemPCService extends CrudService2<SiteDesignProblemPCDao,SiteDesignProblem>{
@Transactional(readOnly=false)
	public void insertBusinessPic(BusinessPic bp) {
		 dao.insertBusinessPic(bp);
	}

public SiteDesignProblem findDetails(Integer id, String businessProblemSolveRole5, String businessProblemStatus70, String pictureType2082) {
	// TODO Auto-generated method stub
	return dao.findDetails(id,businessProblemSolveRole5,businessProblemStatus70,pictureType2082);
}

public List<String> findPictureByBusinessProblemId(Integer id, String picType) {
	// TODO Auto-generated method stub
	return dao.findPictureByBusinessProblemId(id, picType);
}

public Page<SiteDesignProblem> findVoPage(Page<SiteDesignProblem> page,
		SiteDesignProblem bizOrderInstallItemProblemVo) {
	bizOrderInstallItemProblemVo.setPage(page);
	page.setList(dao.querySiteDesignProblemVoList(bizOrderInstallItemProblemVo));
	return page;
}

public List<SiteDesignProblem> findQueryList(SiteDesignProblem bizOrderInstallItemProblemVo) {
	List<SiteDesignProblem> querySiteDesignProblemVoList = dao.querySiteDesignProblemVoList(bizOrderInstallItemProblemVo);
	return querySiteDesignProblemVoList;
}

}