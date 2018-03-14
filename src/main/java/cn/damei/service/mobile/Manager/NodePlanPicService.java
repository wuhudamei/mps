package cn.damei.service.mobile.Manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.mobile.Manager.NodePlanPicDao;
import cn.damei.entity.mobile.Manager.NodePlanPic;
import cn.damei.entity.mobile.Manager.AppOrder;

@Service
@Transactional(readOnly = true)
public class NodePlanPicService extends CrudService<NodePlanPicDao, NodePlanPic>{

	@Autowired
	private NodePlanPicDao nodePlanPicDao;

	public AppOrder getNodePlanPicById(String id) {
		return nodePlanPicDao.getNodePlanPicById(id);
	}

	@Transactional(readOnly = false)
	public String insertNodePlanPic(NodePlanPic nodePlanPic) {


		nodePlanPic.setCreateDate(new Date());
		nodePlanPic.setUpdateDate(new Date());
		nodePlanPic.setDelFlag("0");
		
		return (nodePlanPicDao.insertNodePlanPic(nodePlanPic)) ? "0" : "1";
	}

}
