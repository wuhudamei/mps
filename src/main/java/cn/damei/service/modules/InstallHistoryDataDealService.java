package cn.damei.service.modules;


import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.KeyAuthenticateUtils2;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.MaterialsInfo;
import cn.damei.service.mobile.Inspector.MaterialsInfoService;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.service.mobile.Manager.OrderInstallPlanService;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.web.modules.MaterialInterfaceController;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.entity.modules.CheckNode;
import cn.damei.service.modules.CheckNodeService;
import cn.damei.dao.modules.InstallHistoryDataDealDao;
import cn.damei.entity.modules.InstallHistoryData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class InstallHistoryDataDealService{

	@Autowired
	private InstallHistoryDataDealDao dao;


	public List<InstallHistoryData> findList(InstallHistoryData installHistoryData) {
		
		return dao.findList(installHistoryData);
	}


	@Transactional(readOnly = false)
	public void deleteRepeatedData() {
		dao.deleteRepeatedData();
	}


	@Transactional(readOnly = false)
	public void updateApplyData() {
		dao.updateApplyData();
	}


	@Transactional(readOnly = false)
	public void updateSupplierData() {
		dao.updateSupplierData();
	}


	public List<InstallHistoryData> findApplyList() {
		return dao.findApplyList();
	}


	public List<InstallHistoryData> findSupplierList() {
		return dao.findSupplierList();
	}


	public List<InstallHistoryData> findAcceptList() {
		return dao.findAcceptList();
	}


	@Transactional(readOnly = false)
	public boolean batchInsertList(List<BizBusinessStatusLog> mixInsertList) {
		return (dao.batchInsertList(mixInsertList))?true:false;
	}
	
	
	
	@Autowired
	private CheckConfirmDao checkConfirmDao;
    @Autowired
    private OrderInstallPlanService orderInstallPlanService;
    @Autowired
    private CheckNodeService checkNodeService;
    @Autowired
    private MaterialsInfoService materialsInfoService;
    @Autowired
    private BizSynDataService bizSynDataService;
    
    private Logger logger = LoggerFactory.getLogger(MaterialInterfaceController.class);    
	

	@Transactional(readOnly = false)
	public void saveAnotherInfo(Integer id, HttpServletRequest request) {
	        
    	CheckConfirm confirm = checkConfirmDao.findSettlement(id);

        Integer orderId = confirm.getOrderId();
        String orderNumber = confirm.getOrderNumber();
        Integer nodeIndex = confirm.getQcCheckNodeIndex();


        CheckNode checkNode = checkNodeService.get(confirm.getQcCheckNodeId());
        if (checkNode != null && checkNode.getQcCheckNodeIndex() == 6) {


            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", confirm.getOrderId());
            map.put("type", "1");
            List<MaterialsInfo> materialsStandardList = materialsInfoService.queryMaterialsStandardInfoByParam(map);
            if (materialsStandardList != null && materialsStandardList.size() > 0) {
                JSONObject jsonObject = new JSONObject();

                String[] paramArr = new String[]{"orderNumber=" + confirm.getOrderNumber(), "isFloor=false"};
                String key = KeyAuthenticateUtils2.getKey(paramArr,
                        BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
                jsonObject.put("key", key);
                jsonObject.put("orderNumber", confirm.getOrderNumber());
                jsonObject.put("isFloor", false + "");
                jsonObject.put("assistInfo", JSONArray.fromObject(materialsStandardList).toString());

                BizSynData bizSynData = new BizSynData();
                bizSynData.setBusinessData(jsonObject.toString());
                bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
                bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
                bizSynData.setBusinessOnlyMarkInt(id);
                bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
                bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
                bizSynData.preInsert();

                bizSynDataService.save(bizSynData);
            }



            Inspector inspector = dao.findInspector(confirm.getOrderId());

            try {
                orderInstallPlanService.insertsyndata(orderNumber + "", "99999", inspector, ConstantUtils.BUSINESS_TYPE_401);
            } catch (Exception e) {
                logger.info("-------------------------墙地砖信息推送到订单流转系统：失败!--------------------------------");
            }

        }

        if (nodeIndex.intValue() == 9) {



            BizSynData bizSynData = new BizSynData();

            JSONObject jsonObject = new JSONObject();




            String[] paramArr = new String[]{"orderNumber=" + orderNumber, "isFloor=" + false};

            String key = KeyAuthenticateUtils2.getKey(paramArr,
                    BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
            jsonObject.put("key", key);

            jsonObject.put("orderNumber", orderNumber);
            jsonObject.put("isFloor", false + "");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", orderId);
            map.put("type", 2 + "");

            List<MaterialsInfo> list = materialsInfoService.queryMaterialsStandardInfoByParam(map);


            jsonObject.put("assistInfo", JSONArray.fromObject(list));

            String jsonData = jsonObject.toString();

            bizSynData.setBusinessData(jsonData);
            bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
            bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
            bizSynData.setBusinessOnlyMarkInt(id);
            bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
            bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
            bizSynData.preInsert();

            bizSynDataService.save(bizSynData);


            BizSynData bizSynData1 = new BizSynData();

            JSONObject jsonObject2 = new JSONObject();

            String[] paramArr1 = new String[]{"orderNumber=" + orderNumber, "isFloor=" + false};

            String key1 = KeyAuthenticateUtils2.getKey(paramArr1,
                    BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
            jsonObject2.put("key", key1);

            jsonObject2.put("orderNumber", orderNumber);
            jsonObject2.put("isFloor", false + "");



            List<MaterialsInfo> list2 = materialsInfoService.queryKgmbMaterialsInfo(orderId);

            jsonObject2.put("assistInfo", JSONArray.fromObject(list2));

            String jsonData1 = jsonObject2.toString();

            bizSynData1.setBusinessData(jsonData1);
            bizSynData1.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
            bizSynData1.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
            bizSynData1.setBusinessOnlyMarkInt(id);
            bizSynData1.setSynStatus(ConstantUtils.SYN_STATUS_4);
            bizSynData1.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
            bizSynData1.preInsert();

            bizSynDataService.save(bizSynData1);


            BizSynData bizSynData3 = new BizSynData();

            JSONObject jsonObject3 = new JSONObject();

            String[] paramArr3 = new String[]{"orderNumber=" + orderNumber, "isFloor=" + false};

            String key3 = KeyAuthenticateUtils2.getKey(paramArr3,
                    BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
            jsonObject3.put("key", key3);

            jsonObject3.put("orderNumber", orderNumber);
            jsonObject3.put("isFloor", false + "");

            List<MaterialsInfo> list3 = materialsInfoService.querySandMaterialsInfo(orderId);

            jsonObject3.put("assistInfo", JSONArray.fromObject(list3));

            String jsonData3 = jsonObject3.toString();

            bizSynData3.setBusinessData(jsonData3);
            bizSynData3.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
            bizSynData3.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
            bizSynData3.setBusinessOnlyMarkInt(id);
            bizSynData3.setSynStatus(ConstantUtils.SYN_STATUS_4);
            bizSynData3.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
            bizSynData3.preInsert();

            bizSynDataService.save(bizSynData3);


            BizSynData bizSynData4 = new BizSynData();

            JSONObject jsonObject4 = new JSONObject();

            String[] paramArr4 = new String[]{"orderNumber=" + orderNumber, "isFloor=" + false};

            String key4 = KeyAuthenticateUtils2.getKey(paramArr4,
                    BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
            jsonObject4.put("key", key4);

            jsonObject4.put("orderNumber", orderNumber);
            jsonObject4.put("isFloor", false + "");

            List<MaterialsInfo> list4 = materialsInfoService.queryFlMaterialsInfo(orderId);

            jsonObject4.put("assistInfo", JSONArray.fromObject(list4));

            String jsonData4 = jsonObject4.toString();

            bizSynData4.setBusinessData(jsonData4);
            bizSynData4.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
            bizSynData4.setBusinessType(ConstantUtils.BUSINESS_TYPE_505);
            bizSynData4.setBusinessOnlyMarkInt(id);
            bizSynData4.setSynStatus(ConstantUtils.SYN_STATUS_4);
            bizSynData4.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
            bizSynData4.preInsert();

            bizSynDataService.save(bizSynData4);

        }
    }


	public List<BizQcBill> findQcBillList() {
		return dao.findQcBillList();
	}


}