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

/**
 * 质量管理
 *
 * @author Administrator
 */
@Service
@Transactional(readOnly = true)
public class QualityControlService extends CrudService2<QualityControlDao, QualityControl> {

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
    //通过项目经理id查询项目经理下所有的订单
    public List<QualityControl> findOrderByItemManagerId(Integer itemManagerId) {
        return dao.findOrderByItemManagerId(itemManagerId);
    }

    //通过订单id查询订单详情
    public QualityControl findOrderById(Integer id) {
        return dao.findOrderById(id);
    }

    //通过质检员id查询质检员信息
    public Inspector findPhoneByOrderInspectorId(Integer orderInspectorId) {
        return dao.findPhoneByOrderInspectorId(orderInspectorId);
    }

    //保存约检单
    @Transactional(readOnly = false)
    public Integer insertQcBill(BizQcBill bizQcBill) {
        dao.insertQcBill(bizQcBill);


        return bizQcBill.getId();
    }


    /**
     * 质检单编号
     *
     * @return
     */
    @Transactional(readOnly = false)
    public String qcBillCode() {
        // 以ZJ开头

        StringBuilder builder = new StringBuilder();
        Date date = new Date();
        // num
        ReCheckCode code1 = dao.getCode();

        if (null == code1) {
            dao.saveCode();
            code1 = dao.getCode();
        }

        //如果不是同一天
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

        // 判断长度
        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {
            // 拼接采购单编号
            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() >= 4) {
            builder.append(code);
        }


        // 返回采购单编号
        return builder.toString();

    }


    //根据门店查询所有约检节点
    public List<BizQcCheckNode> findBizQcCheckNodeByStoreId(QualityControl quality) {
        return dao.findBizQcCheckNodeByStoreId(quality);
    }

    //根据订单id查询所有约检单
    public String findBizQcBillByOrderId(BizQcBill bizQcBill) {
        return dao.findBizQcBillByOrderId(bizQcBill);
    }

    @Transactional(readOnly = false)
    public void insertPurchase(PurchaseTwoCode purchaseObjVo) {

        dao.insertPurchase(purchaseObjVo);
    }

    /**
     * 根据订单id，查询约检单biz_qc_bill中（约检节点最大）的一条记录
     *
     * @param bizQcBill
     * @return
     */
    public BizQcBill findMax(BizQcBill bizQcBill) {
        return dao.findMax(bizQcBill);
    }

    /**
     * 根据订单ID 查询所有传统未约检的节点
     *
     * @param id
     * @return
     */
    public List<BizQcCheckNode> findTraditionalNode(int id) {
        return dao.findTraditionalNode(id);
    }

    /**
     * 根据订单查询是否有未验收的节点
     *
     * @param id
     * @return
     */
    public Integer findNumber(int id) {
        return dao.findNumber(id);
    }

    public BizQcBill findQcBillByOrderIdForCompleted(Integer orderId) {
        return dao.findQcBillByOrderIdForCompleted(orderId);
    }

    /**
     * 判断该节点是否申请过
     *
     * @param bizQcBill
     * @return
     */
    public Integer checkIdIsExists(BizQcBill bizQcBill) {
        return dao.checkIdIsExists(bizQcBill);
    }

    /**
     * 距离上次的申请时间是否超过5分钟
     *
     * @param bizQcBill
     * @return
     */
    public Integer isOverTime(BizQcBill bizQcBill) {
        return dao.isOverTime(bizQcBill);
    }

    /**
     * 根据订单查询所有的约检记录
     *
     * @param id
     * @return
     */
    public List<BizQcBill> findBizQcBillRecordByOrderId(Integer id) {
        BizQcBill bizQcBill = new BizQcBill();
        bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
        bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
        bizQcBill.setOrderId(id);
        return dao.findBizQcBillRecordByOrderId(bizQcBill);
    }

    /**
     * 2017-04-21  门店下有质检申请数量限制
     */
    public Integer comparePqcDateIsAllowed(String date, Integer orderId) {


        return dao.comparePqcDateIsAllowed(date, orderId);
    }

    /**
     * 批量插入申请约检图片
     *
     * @param pList
     */
    @Transactional(readOnly = false)
    public void saveCheckitemPicAll(List<BusinessPic> pList) {
        dao.saveCheckitemPicAll(pList);

    }

    /*
     * 查询约检图片数量
     */
    public int fingdPicNum(int businessIdInt) {
        return dao.findPicNum(businessIdInt);
    }

    /*
     * 查询申请约检的图片
     */
    public List<BusinessPic> findPic(int businessIdInt) {
        return dao.findPic(businessIdInt);

    }


    /**
     * 根据约检id 查询是否为基装节点  是为1  否为0
     *
     * @param qcNodeId
     * @return 返回基装节点字段
     */
    public Integer findIsBasicByQcNodeId(int qcNodeId) {


        return dao.findIsBasicByQcNodeId(qcNodeId);
    }


    /**
     * 根据(准产业)订单id 查询是否申请开关面板
     *
     * @param orderId
     * @return
     */

    public Integer checkIsApplyPanelByOrderId(Integer orderId) {


        return dao.checkIsApplyPanelByOrderId(orderId);
    }



    /**
     * 查询订单下一节点是否为基装节点
     * @return
     */
    public   Map<String,String> checkIsForBasicNodeByMap(Map<String,String> map){


        return dao.checkIsForBasicNodeByMap(map);
    }

    public  String findMaxNodeIdByOrderId(String orderId){

        return dao.findMaxNodeIdByOrderId(orderId);
    }
    @Transactional(readOnly = false)
	public void insertQcBillLog(BizQcBill bizQcBill,String input_date) {
		// 添加状态日志信息
    	Date expectCheckDatetime = bizQcBill.getExpectCheckDatetime();
    	
        BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
        statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3100);
        statusLog.setBusinessOnlyMarkInt(bizQcBill.getId());
        statusLog.setBusinessStatus(bizQcBill.getStatus());//项目经理申请约检节点
        statusLog.setStatusDatetime(new Date());
        statusLog.setBusinessEmployeeId(bizQcBill.getApplyEmployeeId());
        statusLog.setBusinessRemarks("项目经理申请约检节点");
        statusLog.preInsert();
        String nodeName = dao.findCheckNode(bizQcBill.getQcCheckNodeId());
        statusLog.setRemarks(nodeName+":"+input_date);
        bizBusinessStatusLogDao.insert(statusLog);
	}
    
    
    
    /**
     * 如果订单第一次申请，根据订单id的门店和模式
     * 查询第一个约检节点
     * @param orderId
     * @return
     */
    public Integer findFirstNodeIdByOrderId(String orderId){
    	
    	return dao.findFirstNodeIdByOrderId(orderId);
    	
    }

}
