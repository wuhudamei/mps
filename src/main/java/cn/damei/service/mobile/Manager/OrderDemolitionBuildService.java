package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.dao.mobile.Manager.OrderDemolitionBuildDao;
import cn.damei.entity.mobile.Manager.OrderDemolitionBuild;
import cn.damei.dao.mobile.Manager.SignDao;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.Order;


@Service
@Transactional(readOnly=false)
public class OrderDemolitionBuildService extends CrudService2<OrderDemolitionBuildDao, OrderDemolitionBuild>{

	@Autowired
	private SignDao signDao;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	

	public List<Order> findOrderDemolitionBuildList(HttpServletRequest request) {
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		List<Order> list = dao.findOrderDemolitionBuildList(manager.getId());
		return list;
	}


	public String sceneSign(SignDetail signDetail, HttpServletRequest request) {
		String flag = "success";

		boolean isSign = signDao.isSignSuccess(signDetail);
		if(isSign){
			flag = "repeat";
			return flag;
		}
		
		try {

			Manager manager = (Manager)request.getSession().getAttribute("manager");
			Date date = new Date();
			double signDistance = signDetail.getSignDistance();
			SignDetail sign = new SignDetail();
			sign.setOrderId(signDetail.getOrderId());
			sign.setSignDate(date);
			sign.setSignAddress(signDetail.getSignAddress());
			sign.setSignDistance(signDistance);
			sign.setSignXy(signDetail.getLon() + "," + signDetail.getLat());
			sign.setSignType(ConstantUtils.SIGN_TYPE_SCENE_SIGN_4);
			sign.setManagerId(manager.getId());
			sign.setSignName(manager.getRealname());
			sign.setManagerName(manager.getRealname());

			AppOrder order = signDao.getCustomerInfoByOrderId(signDetail
					.getOrderId());
			sign.setCustomerInfo(order.getCommunityName() + "-"
					+ order.getBuildNumber() + "-" + order.getBuildUnit() + "-"
					+ order.getBuildRoom() + "-" + order.getCustomerName());
			signDao.signSuccess(sign);
		} catch (Exception e) {
			flag = "fail";
			e.printStackTrace();
		}
		return flag;
		
	}


	public String disclose(SignDetail signDetail, HttpServletRequest request) {
		String flag = "fail";

		boolean isSign = signDao.isSignSuccess(signDetail);
		if(isSign){
			flag = "success";
		}
		return flag;
	}


	public SignDetail findOrderSignDatetime(SignDetail signDetail, HttpServletRequest request) {
		String flag = "success";
		signDetail = dao.findOrderSignDatetime(signDetail);
		if(signDetail == null){
			flag = "fail";
			signDetail = new SignDetail();
			signDetail.setDelFlag(flag);
			return signDetail;
		}
		Manager manager = SessionUtils.getManagerSession(request);
		signDetail.setManagerId(manager.getId());
		return signDetail;
	}


	@Transactional(readOnly=false)
	public String saveDisclose(OrderDemolitionBuild orderDemolitionBuild, HttpServletRequest request) {
		String flag = "NO";

		boolean isDisclose = dao.isDisclose(orderDemolitionBuild.getOrderId());
		if(isDisclose){
			flag = "YES";
			return flag;
		}
		save(orderDemolitionBuild);
		String[] photo = orderDemolitionBuild.getPhoto();
		if(null!=photo && photo.length>0){
			wallAndFloorProblemService.saveProblemPic(Integer.parseInt(String.valueOf(orderDemolitionBuild.getId())),PictureTypeContantUtil.PICTURE_TYPE_10020,photo,PicturePathContantUtil.UPLOAD_DELAYSHEET_PATH,request);
		}
		return "NO";
	}
}
