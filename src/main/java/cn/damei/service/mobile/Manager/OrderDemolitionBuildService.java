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

/** 
* @ClassName: OrderDemolitionBuildService 
* @Description: 拆改交底 
* @author zkj  
* @date 2017年10月19日 下午1:59:41 
* @version V1.0 
*/
@Service
@Transactional(readOnly=false)
public class OrderDemolitionBuildService extends CrudService2<OrderDemolitionBuildDao, OrderDemolitionBuild>{

	@Autowired
	private SignDao signDao;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	
	/** 
	* @Description: 查询项目经理下的所有订单 
	* @param @param request
	* @param @return
	* @author zkj 
	* @date 2017年10月19日 下午2:31:45 
	*/
	public List<Order> findOrderDemolitionBuildList(HttpServletRequest request) {
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		List<Order> list = dao.findOrderDemolitionBuildList(manager.getId());
		return list;
	}

	/** 
	* @Description: 现场签到 
	* @param @param signDetail
	* @param @param request
	* @author zkj 
	* @date 2017年10月19日 下午5:04:08 
	*/
	public String sceneSign(SignDetail signDetail, HttpServletRequest request) {
		String flag = "success";
		//判断是否签到过，如果签到过，不可以再次签到
		boolean isSign = signDao.isSignSuccess(signDetail);
		if(isSign){
			flag = "repeat";
			return flag;
		}
		
		try {
			//Manager manager = SessionUtils.getManagerSession(request);
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
			//获取客户详细信息
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

	/** 
	* @Description: 判断是否可以交底
	* @param @param signDetail
	* @param @param request
	* @param @return
	* @author zkj 
	* @date 2017年10月19日 下午6:56:36 
	*/
	public String disclose(SignDetail signDetail, HttpServletRequest request) {
		String flag = "fail";
		//判断是否签到过，如果签到过，如果签到过可以交底
		boolean isSign = signDao.isSignSuccess(signDetail);
		if(isSign){
			flag = "success";
		}
		return flag;
	}

	/** 
	* @Description: 查询改订单的签到时间
	* @param @param signDetail
	* @param @return
	* @author zkj 
	 * @param request 
	* @date 2017年10月20日 上午10:59:23 
	*/
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

	/** 
	* @Description: 保存交底数据 
	* @param @param orderDemolitionBuild
	* @param @param request
	* @author zkj 
	* @date 2017年10月20日 下午4:55:28 
	*/
	@Transactional(readOnly=false)
	public String saveDisclose(OrderDemolitionBuild orderDemolitionBuild, HttpServletRequest request) {
		String flag = "NO";
		//判断该订单是否交底过
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
