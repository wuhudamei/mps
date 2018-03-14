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


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class QualityControlController {

    @Autowired
    private QualityControlService qualityControlService;
    @Autowired
    private PhoneMessageDao messageDao;
    private static Logger logger = LoggerFactory.getLogger(PackTimeChangeController.class);


    @RequestMapping(value = {"qualityControlList", ""})
    public String qualityControlList(HttpServletRequest request, Model model) {
        request.getSession().removeAttribute("reportManagerText");
        return "mobile/modules/Manager/quality_manage";
    }



    @RequestMapping(value = {"qualityApply"})
    public String qualityApply(QualityControl qualityControl, HttpServletRequest request, Model model) {

        Manager manager = (Manager) request.getSession().getAttribute("manager");
        qualityControl.setItemManagerId(manager.getId());


        List<QualityControl> order = qualityControlService.findOrderByItemManagerId(qualityControl.getItemManagerId());


        model.addAttribute("order", order);
        return "mobile/modules/Manager/quality_apply";
    }


    @RequestMapping(value = {"qualityCheck"})
    public String qualityCheck(Integer id, HttpServletRequest request, Model model) {

        Manager manager = (Manager) request.getSession().getAttribute("manager");
        String managerInfo = manager.getRealname() + ":" + manager.getPhone();
        String orderInfo = "";
        String check = "";
        int checkId = 0;
        String qcBillStatus = "";
        List<BizQcCheckNode> traditionalNodeList = null;
        Date planCheckDate = null;

        QualityControl order = qualityControlService.findOrderById(id);



        if (null != order && null != order.getProjectMode()) {
        	if(!order.getProjectMode().equals("2")){
        		

        		BizQcBill bizQcBill = new BizQcBill();
        		bizQcBill.setOrderId(id);
        		bizQcBill.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
        		bizQcBill.setIsRecheck(ConstantUtils.IS_RECHECK_0);
        		BizQcBill max = qualityControlService.findMax(bizQcBill);

        		orderInfo = order.getCustomerName()+"-"+order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName();
        		
        		

        		QualityControl quality = new QualityControl();
        		quality.setStoreId(order.getStoreId());
        		quality.setOrderId(id);
        		List<BizQcCheckNode> bizQcCheckNodeList = qualityControlService.findBizQcCheckNodeByStoreId(quality);
        		

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
        		

        		

        		traditionalNodeList = qualityControlService.findTraditionalNode(id);

        		Integer count = qualityControlService.findNumber(id);
        		if (null != count && count > 0) {

        			qcBillStatus = "5";
        		} else {

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



        model.addAttribute("managerInfo", managerInfo);
        model.addAttribute("orderId", id);
        model.addAttribute("info", orderInfo);
        return "mobile/modules/Manager/quality_check";
    }



    @Autowired
    private ToDoItemService toDoItemService;



    @RequestMapping(value = "qualityCheckSubmit", method = RequestMethod.POST)
    public @ResponseBody
    String qualityCheckSubmit(String planCheckDate, String delayReasonPm, String checkId, String selectCheck, String input_date, String remarks, String orderId, String[] photo, HttpServletRequest request, Model model) {

        Date date = new Date();
        String result = "0";

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








        Integer countExists = qualityControlService.checkIdIsExists(bizQcBill);
        if (null != countExists && countExists > 0) {

            return "2";
        }

        Integer count = qualityControlService.findNumber(Integer.valueOf(orderId));
        if (null != count && count > 0) {

            return "3";
        }


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

        qualityControlService.insertQcBillLog(bizQcBill,input_date);



        String id = toDoItemService.selectId(ApplyCheckToDoConstatntUtil.APPLY_CHECK_TO_DO_BUSINESS_TYPE, String.valueOf(bizQcBill.getQcCheckNodeId()), orderId);
        if (null != id) {



            toDoItemService.updateViewdOrSolvedByObj(id, "1");

        }


        if (null != qcBillId && qcBillId > 0) {



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

            result = "4";
        }
        if (null != photo && photo.length > 0) {
            List<BusinessPic> pList = new ArrayList<BusinessPic>();
            for (String p : photo) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");

                String rootPath = request.getSession().getServletContext().getRealPath("");
                File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1());

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

                pList.add(businessPic);
            }
            qualityControlService.saveCheckitemPicAll(pList);
        }
        return result;
    }



    @RequestMapping(value = {"qualityCheckRecord", ""})
    public String qualityCheckRecord(Integer id, Model model) {

        List<BizQcBill> list = qualityControlService.findBizQcBillRecordByOrderId(id);
        for (BizQcBill b : list) {
            int picCount = qualityControlService.fingdPicNum(b.getId());
            b.setPicCount(picCount);
        }

        model.addAttribute("list", list);


        return "mobile/modules/Manager/quality_check_record";
    }



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


    @RequestMapping(value = "viewPic")
    private String viewPic(int qcBillId, HttpServletRequest request, Model model) throws Exception {


        List<BusinessPic> picList = qualityControlService.findPic(qcBillId);

        String baseUrl = PicRootName.picPrefixName();

        model.addAttribute("picList", picList);
        model.addAttribute("baseUrl", baseUrl);
        return "mobile/modules/pqc/qualityCheck/managercheckdetail/photo";
    }



    @RequestMapping(value = "checkOrderApply")
    @ResponseBody
    public String checkOrderApply(@RequestParam String orderId) {

        Map<String, String> map = new HashMap<>(12);

        String maxNodeId = qualityControlService.findMaxNodeIdByOrderId(orderId);

       
        
       
            map.put("nodeId", maxNodeId==null?qualityControlService.findFirstNodeIdByOrderId(orderId).toString():maxNodeId);
            map.put("orderId", orderId);

           Map<String,String>returnMap = qualityControlService.checkIsForBasicNodeByMap(map);

           String isBasic = returnMap.get("isForBasicwork");
           String qcName = returnMap.get("qcName");


            if (null != isBasic && "1".equals( isBasic)) {



                Integer isApply = qualityControlService.checkIsApplyPanelByOrderId(Integer.valueOf(orderId));

                if (null == isApply || isApply == 0) {



                    return "1-"+qcName;


                } else {

                    return "2";
                }


            }

       
        	
        	
     
        
        	
        	


       


        return "2";

    }


}
