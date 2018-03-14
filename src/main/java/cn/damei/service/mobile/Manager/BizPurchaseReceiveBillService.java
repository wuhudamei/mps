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

		for(int i =0; i<ids.length; i++){
			BizPurchaseReceiveBillProduct product = new BizPurchaseReceiveBillProduct();
			product.setPurchaseProductId(Integer.parseInt(ids[i]));
			product.setReceiveCount(Double.valueOf(receivingCounts[i]));
			product.setPurchaseReceiveBillId(bill2.getId());
			product.setCreateDate(date);
			product.setUpdateDate(date);
			product.setDelFlag("0");
			bizPurchaseReceiveBillProductDao.insert(product);
			if(PurchaseConstantUtil.PURCHASE_TYPE_1.equals(purchaseType)){
				ReceivedAuxiliary auxiliary = receivedAuxiliaryDao.queryAuxiliaryById(Integer.parseInt(ids[i]));
				receivedAuxiliaryDao.updateCount(Integer.parseInt(ids[i]),auxiliary.getReceivedCount()+Double.valueOf(receivingCounts[i]),auxiliary.getOwedCount()-Double.valueOf(receivingCounts[i]));
				
			}else if(PurchaseConstantUtil.PURCHASE_TYPE_5.equals(purchaseType)){
				ReceivedTile tile = receivedTileDao.queryTileById(Integer.parseInt(ids[i]));
				receivedTileDao.updateCount(Integer.parseInt(ids[i]),tile.getReceivedCount()+Double.valueOf(receivingCounts[i]),tile.getOwedCount()-Double.valueOf(receivingCounts[i]));
				
			}else if(PurchaseConstantUtil.PURCHASE_TYPE_2.equals(purchaseType)){
				ReceivedPanel panel = receivedPanelDao.queryPanelById(Integer.parseInt(ids[i]));
				receivedPanelDao.updateCount(Integer.parseInt(ids[i]),panel.getReceivedCount()+Double.valueOf(receivingCounts[i]),panel.getOwedCount()-Double.valueOf(receivingCounts[i]));
			}else{
				ReceivedAuxiliary auxiliary = receivedAuxiliaryDao.queryAuxiliaryById(Integer.parseInt(ids[i]));
				receivedAuxiliaryDao.updateCount(Integer.parseInt(ids[i]),auxiliary.getReceivedCount()+Double.valueOf(receivingCounts[i]),auxiliary.getOwedCount()-Double.valueOf(receivingCounts[i]));
			}
		}

		List<ReceivedAuxiliary> list1 = receivedAuxiliaryDao.queryAuxiliaryByPurchase(Integer.parseInt(purchaseId));
		List<ReceivedTile> list2 = receivedTileDao.queryTileByPurchaseId(Integer.parseInt(purchaseId));
		List<ReceivedPanel> list3 = receivedPanelDao.queryPanelByPurchaseId(Integer.parseInt(purchaseId));
		
		if(list1.size() == 0 && list2.size() == 0 && list3.size() == 0){

			BizPurchase bizPurchase = bizPurchaseDao.get(Integer.parseInt(purchaseId));
			Order2 order = orderService2.findOrderById(bizPurchase.getOrderId());
			BizEmployee2 manager1 = bizEmployeeService2.get(order.getItemManagerId());
			bizPurchaseDao.updateStatus1ById(Integer.parseInt(purchaseId),ConstantUtils.PURCHASE_STATUS_90,new Date());













































































		}else{

			bizPurchaseDao.updateStatus2ById(Integer.parseInt(purchaseId),ConstantUtils.PURCHASE_STATUS_70);
		}
		

		if(photo!=null){
			if(photo.length>0){
				for(String p:photo){
					String uuid = UUID.randomUUID().toString().replaceAll("-", "");
					String rootPath = request.getSession().getServletContext().getRealPath("");
					File filePath = new File(rootPath + ConstantUtils.UPLOAD_PURCHASE_RECEIVE + DateUtils.getDate1());

					if(!filePath.exists() && !filePath.isDirectory()){
						filePath.mkdirs();
					}
					String filepath = filePath + filePath.separator + uuid + ".jpeg";
					Base64Util.generateImage(p, filepath);
					
					String picpath = ConstantUtils.UPLOAD_PURCHASE_RECEIVE + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";

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


	public BizPurchaseReceiveBillVo findNewReceiveBill(Integer purchaseId) {
		return dao.findNewReceiveBill(purchaseId);
	}
}
