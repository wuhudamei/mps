package cn.damei.service.mobile.Manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.BizPurchaseReceiveBillDao;
import cn.damei.dao.mobile.Manager.BizPurchaseReceiveBillProductDao;
import cn.damei.dao.mobile.Manager.ReceivedAuxiliaryDao;
import cn.damei.dao.mobile.Manager.ReceivedPanelDao;
import cn.damei.dao.mobile.Manager.ReceivedTileDao;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBill;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBillProduct;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBillVo;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.entity.mobile.Manager.ReceivedAuxiliary;
import cn.damei.entity.mobile.Manager.ReceivedPanel;
import cn.damei.entity.mobile.Manager.ReceivedTile;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.dao.modules.BizPurchaseDao;
import cn.damei.entity.modules.BizPurchase;
import cn.damei.service.modules.BizSeiralnumService;
@Service
@Transactional(readOnly = true)
public class BizPurchaseReceiveBillService extends CrudService2<BizPurchaseReceiveBillDao, BizPurchaseReceiveBill>{
	
	@Autowired
	private BizSeiralnumService bizSeiralnumService;
	@Autowired
	private BizPurchaseReceiveBillDao bizPurchaseReceiveBillDao;
	@Autowired
	private BizPurchaseReceiveBillProductDao bizPurchaseReceiveBillProductDao;
	@Autowired
	private ReceivedAuxiliaryDao receivedAuxiliaryDao;
	@Autowired
	private ReceivedPanelDao receivedPanelDao;
	@Autowired
	private ReceivedTileDao receivedTileDao;
	@Autowired
	private BizPurchaseDao bizPurchaseDao;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	@Transactional(readOnly = false)
	public void insert(HttpServletRequest request ,String[] photo, String[] ids, String[] receivingCounts, String txtBeginDate, String purchaseId,Manager manager,String purchaseType) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//保存到收货单
		BizPurchaseReceiveBill bill = new BizPurchaseReceiveBill();
		bill.setPurchaseId(Integer.parseInt(purchaseId));
		String code = bizSeiralnumService.getDateSequence("SH");
		bill.setPurchaseReceiveCode(code);
		bill.setReceiveDate(format.parse(txtBeginDate));
		bill.setReceiveEmployeeId(manager.getId());
		bill.setDelFlag("0");
		bill.setCreateDate(date);
		bill.setUpdateDate(date);
		bizPurchaseReceiveBillDao.insert(bill);
		BizPurchaseReceiveBill bill2 =  bizPurchaseReceiveBillDao.findByCode(code);
		//保存到收货单对应的商品表  //材料采购单中的数量
		for(int i =0; i<ids.length; i++){
			BizPurchaseReceiveBillProduct product = new BizPurchaseReceiveBillProduct();
			product.setPurchaseProductId(Integer.parseInt(ids[i]));
			product.setReceiveCount(Double.valueOf(receivingCounts[i]));
			product.setPurchaseReceiveBillId(bill2.getId());
			product.setCreateDate(date);
			product.setUpdateDate(date);
			product.setDelFlag("0");
			bizPurchaseReceiveBillProductDao.insert(product);
			if(PurchaseConstantUtil.PURCHASE_TYPE_1.equals(purchaseType)){//辅料
				ReceivedAuxiliary auxiliary = receivedAuxiliaryDao.queryAuxiliaryById(Integer.parseInt(ids[i]));
				receivedAuxiliaryDao.updateCount(Integer.parseInt(ids[i]),auxiliary.getReceivedCount()+Double.valueOf(receivingCounts[i]),auxiliary.getOwedCount()-Double.valueOf(receivingCounts[i]));
				
			}else if(PurchaseConstantUtil.PURCHASE_TYPE_5.equals(purchaseType)){//墙地砖
				ReceivedTile tile = receivedTileDao.queryTileById(Integer.parseInt(ids[i]));
				receivedTileDao.updateCount(Integer.parseInt(ids[i]),tile.getReceivedCount()+Double.valueOf(receivingCounts[i]),tile.getOwedCount()-Double.valueOf(receivingCounts[i]));
				
			}else if(PurchaseConstantUtil.PURCHASE_TYPE_2.equals(purchaseType)){//开关面板
				ReceivedPanel panel = receivedPanelDao.queryPanelById(Integer.parseInt(ids[i]));
				receivedPanelDao.updateCount(Integer.parseInt(ids[i]),panel.getReceivedCount()+Double.valueOf(receivingCounts[i]),panel.getOwedCount()-Double.valueOf(receivingCounts[i]));
			}else{//沙子水泥
				ReceivedAuxiliary auxiliary = receivedAuxiliaryDao.queryAuxiliaryById(Integer.parseInt(ids[i]));
				receivedAuxiliaryDao.updateCount(Integer.parseInt(ids[i]),auxiliary.getReceivedCount()+Double.valueOf(receivingCounts[i]),auxiliary.getOwedCount()-Double.valueOf(receivingCounts[i]));
			}
		}
	//	根据采购单id查询所有的商品
		List<ReceivedAuxiliary> list1 = receivedAuxiliaryDao.queryAuxiliaryByPurchase(Integer.parseInt(purchaseId));
		List<ReceivedTile> list2 = receivedTileDao.queryTileByPurchaseId(Integer.parseInt(purchaseId));
		List<ReceivedPanel> list3 = receivedPanelDao.queryPanelByPurchaseId(Integer.parseInt(purchaseId));
		
		if(list1.size() == 0 && list2.size() == 0 && list3.size() == 0){
			//修改采购单的状态为已完成
			BizPurchase bizPurchase = bizPurchaseDao.get(Integer.parseInt(purchaseId));
			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager1 = bizEmployeeService2.get(order.getItemManagerId());
			bizPurchaseDao.updateStatus1ById(Integer.parseInt(purchaseId),ConstantUtils.PURCHASE_STATUS_90,new Date());
			//同时发短信
//			if(purchaseType.equals(ConstantUtils.AUXILIARY_NUMBER)){//订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），申请辅料项目经理已全部收货。
//				BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId(),"5");
//				List<Integer> list = new ArrayList<Integer>();
//				List<BizEmployee2> employeelist = null;
//				if(null != bizMessagegroup ){
//					String[] str = bizMessagegroup.getEmployees().split(",");
//					for(String id1: str){
//						list.add(Integer.valueOf(id1));
//					}
//					employeelist = bizEmployeeService2.getById(list);
//					if(employeelist != null && employeelist.size()>0){
//						String content = "订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",项目经理（"+manager1.getRealname()+"-"+manager1.getPhone()+"），申请辅料项目经理已全部收货。";
//						for (int i=0;i<employeelist.size();i++) {
//							BizPhoneMsg message = new BizPhoneMsg();
//							message.setMsgContent(content);
//							message.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
//							message.setReceiveEmployeeId(employeelist.get(i).getId());
//							message.setReceivePhone(employeelist.get(i).getPhone());
//							message.setMsgGenerateDatetime(new Date());
//							message.setRelatedBusinessIdInt(bizPurchase.getId());
//							message.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_700301);
//							bizPhoneMsgService.insert(message);
//						}
//					}
//				}
//			}else if(ConstantUtils.WALL_FLOOR_BRICK_NUMBER.equals(purchaseType)){//订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），申请墙地砖项目经理已全部收货。
//				BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId(),"4");
//				List<Integer> list = new ArrayList<Integer>();
//				List<BizEmployee2> employeelist = null;
//				if(null != bizMessagegroup ){
//					String[] str = bizMessagegroup.getEmployees().split(",");
//					for(String id1: str){
//						list.add(Integer.valueOf(id1));
//					}
//					employeelist = bizEmployeeService2.getById(list);
//					if(employeelist != null && employeelist.size()>0){
//						String content = "订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",项目经理（"+manager1.getRealname()+"-"+manager1.getPhone()+"），申请墙地砖项目经理已全部收货。";
//						for (int i=0;i<employeelist.size();i++) {
//							BizPhoneMsg message = new BizPhoneMsg();
//							message.setMsgContent(content);
//							message.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
//							message.setReceiveEmployeeId(employeelist.get(i).getId());
//							message.setReceivePhone(employeelist.get(i).getPhone());
//							message.setMsgGenerateDatetime(new Date());
//							message.setRelatedBusinessIdInt(bizPurchase.getId());
//							message.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_800301);
//							bizPhoneMsgService.insert(message);
//						}
//					}
//				}
//			}else{//订单（小区名-楼号-单元号-门牌号-客户姓名-手机号），项目经理（姓名-手机号），申请开关面板项目经理已全部收货。
//				BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId(),"8");
//				List<Integer> list = new ArrayList<Integer>();
//				List<BizEmployee2> employeelist = null;
//				if(null != bizMessagegroup ){
//					String[] str = bizMessagegroup.getEmployees().split(",");
//					for(String id1: str){
//						list.add(Integer.valueOf(id1));
//					}
//					employeelist = bizEmployeeService2.getById(list);
//					if(employeelist != null && employeelist.size()>0){
//						String content = "订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()+"-"+order.getCustomerPhone()+",项目经理（"+manager1.getRealname()+"-"+manager1.getPhone()+"），申请开关面板项目经理已全部收货。";
//						for (int i=0;i<employeelist.size();i++) {
//							BizPhoneMsg message = new BizPhoneMsg();
//							message.setMsgContent(content);
//							message.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
//							message.setReceiveEmployeeId(employeelist.get(i).getId());
//							message.setReceivePhone(employeelist.get(i).getPhone());
//							message.setMsgGenerateDatetime(new Date());
//							message.setRelatedBusinessIdInt(bizPurchase.getId());
//							message.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_900301);
//							bizPhoneMsgService.insert(message);
//						}
//					}
//				}
//			}
		}else{
			//部分收货
			bizPurchaseDao.updateStatus2ById(Integer.parseInt(purchaseId),ConstantUtils.PURCHASE_STATUS_70);
		}
		
		//保存图片
		if(photo!=null){
			if(photo.length>0){
				for(String p:photo){
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String rootPath = request.getSession().getServletContext().getRealPath("");
					File filePath = new File(rootPath + ConstantUtils.UPLOAD_PURCHASE_RECEIVE + DateUtils.getDate1());
					//判断该文件是否存在
					if(!filePath.exists() && !filePath.isDirectory()){
						filePath.mkdirs();
					}
					String filepath = filePath + filePath.separator + uuid + ".jpeg";
					Base64Util.generateImage(p, filepath);
					
					String picpath = ConstantUtils.UPLOAD_PURCHASE_RECEIVE + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
					//保存图片到数据库
					BusinessPicture picture = new BusinessPicture();
					picture.setBusinessIdInt(bill2.getId());
					picture.setBusinessType(ConstantUtils.PICTURE_BUSINESS_TYPE_5);
					picture.setPicUrl(picpath);
					picture.setPicDateTime(new Date());
					businessPictureService.savePic(picture);
				}
			}
		}
	}

	public List<BizPurchaseReceiveBillVo> queryReceiveBill(Integer employeeId ,String type) {
		
		return dao.queryReceiveBill(employeeId,type);
	}

	public BizPurchaseReceiveBill queryById(Integer id) {
		
		return dao.queryById(id);
	}

	/**
	 * 根据采购单ID查询最新的一条收货记录
	 * @param id
	 * @param valueOf
	 * @return
	 */
	public BizPurchaseReceiveBillVo findNewReceiveBill(Integer purchaseId) {
		return dao.findNewReceiveBill(purchaseId);
	}
}
