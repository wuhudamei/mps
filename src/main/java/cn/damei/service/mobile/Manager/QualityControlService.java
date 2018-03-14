package cn.damei.service.mobile.Manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.DateUtils;
import cn.damei.entity.mobile.Inspector.ReCheckCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Manager.PurchaseTwoCode;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.dao.mobile.Manager.QualityControlDao;
import cn.damei.entity.mobile.Manager.BizQcBill;
import cn.damei.entity.mobile.Manager.BizQcCheckNode;
import cn.damei.entity.mobile.Manager.Inspector;
import cn.damei.entity.mobile.Manager.QualityControl;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;


@Service
@Transactional(readOnly = true)
public class QualityControlService extends CrudService2<QualityControlDao, QualityControl> {

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;

    public List<QualityControl> findOrderByItemManagerId(Integer itemManagerId) {
        return dao.findOrderByItemManagerId(itemManagerId);
    }


    public QualityControl findOrderById(Integer id) {
        return dao.findOrderById(id);
    }


    public Inspector findPhoneByOrderInspectorId(Integer orderInspectorId) {
        return dao.findPhoneByOrderInspectorId(orderInspectorId);
    }


    @Transactional(readOnly = false)
    public Integer insertQcBill(BizQcBill bizQcBill) {
        dao.insertQcBill(bizQcBill);


        return bizQcBill.getId();
    }



    @Transactional(readOnly = false)
    public String qcBillCode() {


        StringBuilder builder = new StringBuilder();
        Date date = new Date();

        ReCheckCode code1 = dao.getCode();

        if (null == code1) {
            dao.saveCode();
            code1 = dao.getCode();
        }


        if (!DateUtils.isSameDay(date, code1.getGenerateTime())) {

            code1.setGenerateTime(date);
            code1.setLastSeiralnum(1);

        } else {
            code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
            code1.setGenerateTime(date);
        }


        dao.updateCode(code1);
        String code = String.valueOf(code1.getLastSeiralnum());
        builder.append(code1.getBussinessType());
        builder.append(new SimpleDateFormat("yyyyMMdd").format(date));


        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {

            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() >= 4) {
            builder.append(code);
        }



        return builder.toString();

    }



    public List<BizQcCheckNode> findBizQcCheckNodeByStoreId(QualityControl quality) {
        return dao.findBizQcCheckNodeByStoreId(quality);
    }


    public String findBizQcBillByOrderId(BizQcBill bizQcBill) {
        return dao.findBizQcBillByOrderId(bizQcBill);
    }

    @Transactional(readOnly = false)
    public void insertPurchase(PurchaseTwoCode purchaseObjVo) {

        dao.insertPurchase(purchaseObjVo);
    }


    public BizQcBill findMax(BizQcBill bizQcBill) {
        return dao.findMax(bizQcBill);
    }


    public List<BizQcCheckNode> findTraditionalNode(int id) {
        return dao.findTraditionalNode(id);
    }


    public Integer findNumber(int id) {
        return dao.findNumber(id);
    }

    public BizQcBill findQcBillByOrderIdForCompleted(Integer orderId) {
        return dao.findQcBillByOrderIdForCompleted(orderId);
    }


    public Integer checkIdIsExists(BizQcBill bizQcBill) {
        return dao.checkIdIsExists(bizQcBill);
    }


    public Integer isOverTime(BizQcBill bizQcBill) {
        return dao.isOverTime(bizQcBill);
    }


    public List<BizQcBill> findBizQcBillRecordByOrderId(Integer id) {
        BizQcBill bizQcBill = new BizQcBill();
        bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
        bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
        bizQcBill.setOrderId(id);
        return dao.findBizQcBillRecordByOrderId(bizQcBill);
    }


    public Integer comparePqcDateIsAllowed(String date, Integer orderId) {


        return dao.comparePqcDateIsAllowed(date, orderId);
    }


    @Transactional(readOnly = false)
    public void saveCheckitemPicAll(List<BusinessPic> pList) {
        dao.saveCheckitemPicAll(pList);

    }


    public int fingdPicNum(int businessIdInt) {
        return dao.findPicNum(businessIdInt);
    }


    public List<BusinessPic> findPic(int businessIdInt) {
        return dao.findPic(businessIdInt);

    }



    public Integer findIsBasicByQcNodeId(int qcNodeId) {


        return dao.findIsBasicByQcNodeId(qcNodeId);
    }




    public Integer checkIsApplyPanelByOrderId(Integer orderId) {


        return dao.checkIsApplyPanelByOrderId(orderId);
    }




    public   Map<String,String> checkIsForBasicNodeByMap(Map<String,String> map){


        return dao.checkIsForBasicNodeByMap(map);
    }

    public  String findMaxNodeIdByOrderId(String orderId){

        return dao.findMaxNodeIdByOrderId(orderId);
    }
    @Transactional(readOnly = false)
	public void insertQcBillLog(BizQcBill bizQcBill,String input_date) {

    	Date expectCheckDatetime = bizQcBill.getExpectCheckDatetime();
    	
        BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
        statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3100);
        statusLog.setBusinessOnlyMarkInt(bizQcBill.getId());
        statusLog.setBusinessStatus(bizQcBill.getStatus());
        statusLog.setStatusDatetime(new Date());
        statusLog.setBusinessEmployeeId(bizQcBill.getApplyEmployeeId());
        statusLog.setBusinessRemarks("项目经理申请约检节点");
        statusLog.preInsert();
        String nodeName = dao.findCheckNode(bizQcBill.getQcCheckNodeId());
        statusLog.setRemarks(nodeName+":"+input_date);
        bizBusinessStatusLogDao.insert(statusLog);
	}
    
    
    

    public Integer findFirstNodeIdByOrderId(String orderId){
    	
    	return dao.findFirstNodeIdByOrderId(orderId);
    	
    }

}
