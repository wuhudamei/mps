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

/**
 * 主材安装申请 历史数据处理Service
 */
@Service
@Transactional(readOnly = true)
public class InstallHistoryDataDealService{

	@Autowired
	private InstallHistoryDataDealDao dao;

	/**
	 * 查询历史数据页面
	 * @param installHistoryData 
	 * @return
	 */
	public List<InstallHistoryData> findList(InstallHistoryData installHistoryData) {
		
		return dao.findList(installHistoryData);
	}

	/**
	 * 批量删除重复数据 
	 */
	@Transactional(readOnly = false)
	public void deleteRepeatedData() {
		dao.deleteRepeatedData();
	}

	/**
	 * 批量更新状态为2：申请的日志信息
	 */
	@Transactional(readOnly = false)
	public void updateApplyData() {
		dao.updateApplyData();
	}

	/**
	 * 批量更新状态为3：下达供应商的日志信息 
	 */
	@Transactional(readOnly = false)
	public void updateSupplierData() {
		dao.updateSupplierData();
	}

	/**
	 * 查出所有需要批量插入的 2：申请日志  的主材信息
	 * @return
	 */
	public List<InstallHistoryData> findApplyList() {
		return dao.findApplyList();
	}

	/**
	 * 查出所有需要批量插入的 3：下达供应商 日志  的主材信息
	 * @return
	 */
	public List<InstallHistoryData> findSupplierList() {
		return dao.findSupplierList();
	}

	/**
	 * 查出所有需要批量插入的 4：验收 日志  的主材信息
	 * @return
	 */
	public List<InstallHistoryData> findAcceptList() {
		return dao.findAcceptList();
	}

	/**
	 * 批量插入
	 * @param mixInsertList
	 * @return
	 */
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
	
    /**
	 * 基装验收和竣工验收的接口推送历史数据
	 * @param id
	 * @param request
	 */
	@Transactional(readOnly = false)
	public void saveAnotherInfo(Integer id, HttpServletRequest request) {
	        
    	CheckConfirm confirm = checkConfirmDao.findSettlement(id);

        Integer orderId = confirm.getOrderId();// 订单id
        String orderNumber = confirm.getOrderNumber();// 订单编号
        Integer nodeIndex = confirm.getQcCheckNodeIndex();// 节点顺序

        // 判断是否是基装验收
        CheckNode checkNode = checkNodeService.get(confirm.getQcCheckNodeId());
        if (checkNode != null && checkNode.getQcCheckNodeIndex() == 6) {// 基装验收，推送数据

            // 获取标化辅料
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", confirm.getOrderId());
            map.put("type", "1");
            List<MaterialsInfo> materialsStandardList = materialsInfoService.queryMaterialsStandardInfoByParam(map);
            if (materialsStandardList != null && materialsStandardList.size() > 0) {
                JSONObject jsonObject = new JSONObject();
                //JSONArray jsonArray = new JSONArray();
                String[] paramArr = new String[]{"orderNumber=" + confirm.getOrderNumber(), "isFloor=false"};
                String key = KeyAuthenticateUtils2.getKey(paramArr,
                        BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
                jsonObject.put("key", key);
                jsonObject.put("orderNumber", confirm.getOrderNumber());
                jsonObject.put("isFloor", false + "");
                jsonObject.put("assistInfo", JSONArray.fromObject(materialsStandardList).toString());
                // 向同步表插入数据 以便发送 ------------------------
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


            //Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
            Inspector inspector = dao.findInspector(confirm.getOrderId());
            //插入订单同步数据表
            try {
                orderInstallPlanService.insertsyndata(orderNumber + "", "99999", inspector, ConstantUtils.BUSINESS_TYPE_401);
            } catch (Exception e) {
                logger.info("-------------------------墙地砖信息推送到订单流转系统：失败!--------------------------------");
            }

        }
        // 9 为竣工验收
        if (nodeIndex.intValue() == 9) {

            // 插入接口数据
            // 向同步表插入数据 以便发送 ------------------------
            BizSynData bizSynData = new BizSynData();

            JSONObject jsonObject = new JSONObject();
            // json 数组
            //JSONArray jsonArray = new JSONArray();

            // 是否包含墙地砖 1 为 是 2 为 否
            String[] paramArr = new String[]{"orderNumber=" + orderNumber, "isFloor=" + false};

            String key = KeyAuthenticateUtils2.getKey(paramArr,
                    BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
            jsonObject.put("key", key);

            jsonObject.put("orderNumber", orderNumber);
            jsonObject.put("isFloor", false + "");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", orderId);
            map.put("type", 2 + "");
            // 筒灯灯带
            List<MaterialsInfo> list = materialsInfoService.queryMaterialsStandardInfoByParam(map);
            // assistInfo 信息

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

            // 开关面板
            BizSynData bizSynData1 = new BizSynData();

            JSONObject jsonObject2 = new JSONObject();
            // 是否包含墙地砖 1 为 是 2 为 否
            String[] paramArr1 = new String[]{"orderNumber=" + orderNumber, "isFloor=" + false};

            String key1 = KeyAuthenticateUtils2.getKey(paramArr1,
                    BizOrderReportConstantUtil.REMOTE_INTERFACE_PARAM_KEY);
            jsonObject2.put("key", key1);

            jsonObject2.put("orderNumber", orderNumber);
            jsonObject2.put("isFloor", false + "");

            // json 数组
           // JSONArray jsonArray1 = new JSONArray();
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

            // 沙子水泥
            BizSynData bizSynData3 = new BizSynData();

            JSONObject jsonObject3 = new JSONObject();
            // 是否包含墙地砖 1 为 是 2 为 否
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

            // 辅料
            BizSynData bizSynData4 = new BizSynData();

            JSONObject jsonObject4 = new JSONObject();
            // 是否包含墙地砖 1 为 是 2 为 否
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

	/**
	 * 查询约检单(基装验收和竣工验收)
	 * @return
	 */
	public List<BizQcBill> findQcBillList() {
		return dao.findQcBillList();
	}


}