package cn.damei.web.mobile.Manager;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.constantUtils.toDoConstant.ApplyCheckToDoConstatntUtil;
import cn.damei.service.modules.ToDoItemService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.BizQcBill;
import cn.damei.entity.mobile.Manager.BizQcCheckNode;
import cn.damei.entity.mobile.Manager.QualityControl;
import cn.damei.service.mobile.Manager.QualityControlService;

/**
 * 质量管理,申请约检
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class QualityControlController {

    @Autowired
    private QualityControlService qualityControlService;
    @Autowired
    private PhoneMessageDao messageDao;
    private static Logger logger = LoggerFactory.getLogger(PackTimeChangeController.class);//日志

    //质量管理页面
    @RequestMapping(value = {"qualityControlList", ""})
    public String qualityControlList(HttpServletRequest request, Model model) {
        request.getSession().removeAttribute("reportManagerText");
        return "mobile/modules/Manager/quality_manage";
    }


    //申请约检页面
    @RequestMapping(value = {"qualityApply"})
    public String qualityApply(QualityControl qualityControl, HttpServletRequest request, Model model) {
        //获得项目经理
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        qualityControl.setItemManagerId(manager.getId());

        //通过项目经理id查询项目经理下所有的订单
        List<QualityControl> order = qualityControlService.findOrderByItemManagerId(qualityControl.getItemManagerId());


        model.addAttribute("order", order);
        return "mobile/modules/Manager/quality_apply";
    }

    //申请约检页
    @RequestMapping(value = {"qualityCheck"})
    public String qualityCheck(Integer id, HttpServletRequest request, Model model) {
        //获得项目经理的  姓名和电话--作为备注
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        String managerInfo = manager.getRealname() + ":" + manager.getPhone();
        String orderInfo = "";
        String check = "";
        int checkId = 0;
        String qcBillStatus = "";
        List<BizQcCheckNode> traditionalNodeList = null;
        Date planCheckDate = null;
        //查询订单相关信息---工程模式
        QualityControl order = qualityControlService.findOrderById(id);


        //一、 订单   产业 准产业(2017-08-03 加入) 直接不等于传统
        if (null != order && null != order.getProjectMode()) {
        	if(!order.getProjectMode().equals("2")){
        		
        		//1.根据订单id，查询约检单biz_qc_bill中（约检节点最大）的一条记录
        		BizQcBill bizQcBill = new BizQcBill();
        		bizQcBill.setOrderId(id);
        		bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
        		bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
        		BizQcBill max = qualityControlService.findMax(bizQcBill);
        		//顾客信息
        		orderInfo = order.getCustomerName()+"-"+order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName();
        		
        		
        		//2.根据门店查询所有约检节点
        		QualityControl quality = new QualityControl();
        		quality.setStoreId(order.getStoreId());
        		quality.setOrderId(id);
        		List<BizQcCheckNode> bizQcCheckNodeList = qualityControlService.findBizQcCheckNodeByStoreId(quality);
        		
        		//3.获取 按顺序 下一个节点
        		if (null != bizQcCheckNodeList && bizQcCheckNodeList.size() > 0) {
        			for (BizQcCheckNode list : bizQcCheckNodeList) {
        				if (null != max && null != max.getQcCheckNodeId()) {
        					qcBillStatus = max.getStatus();
        					if (list.getQcCheckNodeIndex() > max.getQcCheckNodeIndex()) {
        						check = list.getQcCheckNodeName();
        						checkId = list.getId();
        						planCheckDate = list.getPlanCheckDate();
        						break;
        					}
        				} else {
        					qcBillStatus = "100";
        					check = list.getQcCheckNodeName();
        					checkId = list.getId();
        					planCheckDate = list.getPlanCheckDate();
        					break;
        				}
        				
        			}
        		}
        	}else{
        		
        		//二、订单  传统
        		
        		//1.根据订单ID 查询所有传统未约检的节点
        		traditionalNodeList = qualityControlService.findTraditionalNode(id);
        		//2.根据订单查询是否有未验收的节点
        		Integer count = qualityControlService.findNumber(id);
        		if (null != count && count > 0) {
        			//3.不允许申请
        			qcBillStatus = "5";
        		} else {
        			//3.允许申请
        			qcBillStatus = "100";
        		}
        	}

        } 

        model.addAttribute("order", order);
        model.addAttribute("traditionalNodeList", traditionalNodeList);
        model.addAttribute("check", check);
        model.addAttribute("checkId", checkId);
        model.addAttribute("qcBillStatus", qcBillStatus);
        model.addAttribute("planCheckDate", planCheckDate);


        //备注   订单id  订单详细地址
        model.addAttribute("managerInfo", managerInfo);
        model.addAttribute("orderId", id);
        model.addAttribute("info", orderInfo);
        return "mobile/modules/Manager/quality_check";
    }


    /**
     * 查看待办service
     */
    @Autowired
    private ToDoItemService toDoItemService;


    //确认申请---约检
    @RequestMapping(value = "qualityCheckSubmit", method = RequestMethod.POST)
    public @ResponseBody
    String qualityCheckSubmit(String planCheckDate, String delayReasonPm, String checkId, String selectCheck, String input_date, String remarks, String orderId, String[] photo, HttpServletRequest request, Model model) {

        Date date = new Date();
        String result = "0";
        //通过订单id查询订单详情
        QualityControl qualityControl = qualityControlService.findOrderById(Integer.valueOf(orderId));

        BizQcBill bizQcBill = new BizQcBill();
        bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
        bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
        bizQcBill.setOrderId(Integer.valueOf(orderId));
        if (null != qualityControl && null != qualityControl.getProjectMode()) {
            if("2".equals(qualityControl.getProjectMode())){
            	 if (StringUtils.isNotBlank(selectCheck)) {
                     bizQcBill.setQcCheckNodeId(Integer.valueOf(selectCheck));
                 }
            }else{
            	if (StringUtils.isNotBlank(checkId)) {
                    bizQcBill.setQcCheckNodeId(Integer.valueOf(checkId));
                }
            }
        	
        }

        //距离上次申请的时间是否已超过5分钟
//		Integer countTime = qualityControlService.isOverTime(bizQcBill);
//		if(null!=countTime && countTime>0){
//			//该订单距离上次申请时间没有超过5分钟
//			return "1";
//		}
        //判断这个节点是否已经申请过
        Integer countExists = qualityControlService.checkIdIsExists(bizQcBill);
        if (null != countExists && countExists > 0) {
            //该订单本节点已申请
            return "2";
        }
        //根据订单查询是否有未验收的节点
        Integer count = qualityControlService.findNumber(Integer.valueOf(orderId));
        if (null != count && count > 0) {
            //有未验收的节点
            return "3";
        }

        //获取项目经理
        Manager manager = (Manager) request.getSession().getAttribute("manager");

        bizQcBill.setQcBillCode(qualityControlService.qcBillCode());
        bizQcBill.setApplyRemarks(remarks);

        bizQcBill.setStatus("2");
        bizQcBill.setApplyEmployeeId(manager.getId());

        bizQcBill.setExpectCheckDatetime(DateUtils.parseDate(input_date));
        bizQcBill.setCreateDate(date);
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(planCheckDate == null ? "" : planCheckDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bizQcBill.setPlanCheckDate(date1);
        bizQcBill.setUpdateDate(date);
        bizQcBill.setDelayReasonPm(delayReasonPm);
        Integer qcBillId = qualityControlService.insertQcBill(bizQcBill);
        //插入日志记录
        qualityControlService.insertQcBillLog(bizQcBill,input_date);

        //orderId  qcNodeId  type 查看是否是待办

        String id = toDoItemService.selectId(ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE, String.valueOf(bizQcBill.getQcCheckNodeId()), orderId);
        if (null != id) {

            //是待办 就更新待办为已查看  (0:已查看 1:已处理)

            toDoItemService.updateViewdOrSolvedByObj(id, "1");

        }


        if (null != qcBillId && qcBillId > 0) {
            //约检单保存成功
            //===================短信发送=====================================
            //亲，订单（东晨小区-10-4-202-王维-13333333333），项目经理（王毅-13212341234），项目经理已申请约检，请及时登录APP查看详情。
            PhoneMessageEntity entity = new PhoneMessageEntity();

            entity.setReceiveEmployeeId(qualityControl.getOrderInspectorId());
            entity.setReceivePhone(qualityControl.getOrderInspectorPhone());
            entity.setMessageContent("订单（" + qualityControl.getCommunityName() + "-" + qualityControl.getBuildNumber() + "-" + qualityControl.getBuildUnit() + "-" + qualityControl.getBuildRoom() + "-" + qualityControl.getCustomerName() + "-" + qualityControl.getCustomerPhone() + ",项目经理 (" + SessionUtils.getManagerSession(request).getRealname() + "-" + SessionUtils.getManagerSession(request).getPhone() + ")，项目经理已申请约检，请及时登录APP查看详情。");
            entity.setMessageGenerateTime(new Date());
            entity.setStatus("0");
            entity.setRelatedBusinessType("600101");
            entity.setRelatedBusinessId(Integer.parseInt(orderId));
            messageDao.saveMessageContent(entity);

        } else {
            //约检单保存失败
            result = "4";
        }
        if (null != photo && photo.length > 0) {
            List<BusinessPic> pList = new ArrayList<BusinessPic>();
            for (String p : photo) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//				String rootPath = RootName.SystemEnvironment(request);
                String rootPath = request.getSession().getServletContext().getRealPath("");
                File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1());
                //判断该文件是否存在
                if (!filePath.exists() && !filePath.isDirectory()) {
                    filePath.mkdirs();
                }
                String filepath = filePath + filePath.separator + uuid + ".jpeg";
                String picpath = ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";
                Base64Util.generateImage(p, filepath);
                BusinessPic businessPic = new BusinessPic();
                businessPic.setId(null);
                businessPic.setBusinessIdInt(bizQcBill.getId());
                businessPic.setPicUrl(picpath);
                businessPic.setBusinessType(PictureTypeContantUtil.PICTURE_TYPE_2);
                businessPic.setPicDatetime(date);
                //	businessPic.set
                pList.add(businessPic);
            }
            qualityControlService.saveCheckitemPicAll(pList);
        }
        return result;
    }


    /**
     * 申请约检记录
     *
     * @param id
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = {"qualityCheckRecord", ""})
    public String qualityCheckRecord(Integer id, Model model) {
        //根据订单查询所有的约检记录
        List<BizQcBill> list = qualityControlService.findBizQcBillRecordByOrderId(id);
        for (BizQcBill b : list) {
            int picCount = qualityControlService.fingdPicNum(b.getId());
            b.setPicCount(picCount);
        }
        //int count =
        model.addAttribute("list", list);


        return "mobile/modules/Manager/quality_check_record";
    }


    /**
     * 该日期该门店是否约满(订单id查询门店)
     *
     * @param date
     * @return
     */
    @RequestMapping(value = {"comparePqcDateIsAllowed", ""})
    public @ResponseBody
    String comparePqcDateIsAllowed(String date, Integer orderId) {
        Integer count = qualityControlService.comparePqcDateIsAllowed(date, orderId);
        if (null != count && count < 1) {

            return "numberLimited";

        } else {

            return "stillHaveNum";
        }
    }

    /**
     * 查看约检图片
     *
     * @param
     * @return
     */
    @RequestMapping(value = "viewPic")
    private String viewPic(int qcBillId, HttpServletRequest request, Model model) throws Exception {

        //通过质检单id查询图片
        List<BusinessPic> picList = qualityControlService.findPic(qcBillId);

        String baseUrl = PicRootName.picPrefixName();
        //String baseUrl = "http://localhost:8080/mdn";
        model.addAttribute("picList", picList);
        model.addAttribute("baseUrl", baseUrl);
        return "mobile/modules/pqc/qualityCheck/managercheckdetail/photo";
    }


    /**
     * //准产业检查当前节点是否为基装节点, 如果是 查询是否申请了开关面板
     *
     * @return
     */
    @RequestMapping(value = "checkOrderApply")
    @ResponseBody
    public String checkOrderApply(@RequestParam String orderId) {

        Map<String, String> map = new HashMap<>(12);
        //1:根据订单查询 该申请的约检节点并查看 该节点是否为基装节点
        String maxNodeId = qualityControlService.findMaxNodeIdByOrderId(orderId);

       
        
       
            map.put("nodeId", maxNodeId==null?qualityControlService.findFirstNodeIdByOrderId(orderId).toString():maxNodeId);
            map.put("orderId", orderId);

           Map<String,String>returnMap = qualityControlService.checkIsForBasicNodeByMap(map);

           String isBasic = returnMap.get("isForBasicwork");
           String qcName = returnMap.get("qcName");


            if (null != isBasic && "1".equals( isBasic)) {

                //2: 如果成立 , 查询该订单是否申请了开关面板

                Integer isApply = qualityControlService.checkIsApplyPanelByOrderId(Integer.valueOf(orderId));

                if (null == isApply || isApply == 0) {


                    //如果返回1  则为 准产业+该申请节点为基装节点+没有申请开关面板
                    return "1-"+qcName;


                } else {

                    return "2";
                }


            }

       
        	
        	
     
        
        	
        	

//            return "2";
       


        return "2";

    }


}
