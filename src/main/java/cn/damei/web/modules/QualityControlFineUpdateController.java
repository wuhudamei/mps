/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.entity.modules.QualityControlFineUpdateEntity;
import cn.damei.service.modules.QualityControlFineUpdateService;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 质检罚款修改Controller
 * @author lzm
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/qualityControlFineUpdate")
public class QualityControlFineUpdateController extends BaseController {
	@Autowired
	private QualityControlFineUpdateService qualityControlFineUpdateService;
	
	@ModelAttribute
	public QualityControlFineUpdateEntity get(@RequestParam(required=false) String id) {
		QualityControlFineUpdateEntity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qualityControlFineUpdateService.get(id);
		}
		if (entity == null){
			entity = new QualityControlFineUpdateEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("qualityControlFineUpdate:qualityControlFineUpdate:view")
	@RequestMapping(value = {"list", ""})
	public String list(QualityControlFineUpdateEntity qualityControlFineUpdateEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==qualityControlFineUpdateEntity.getStoreId()){
            if(null!=user.getStoreId()){
                qualityControlFineUpdateEntity.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		model.addAttribute("page", page);
		model.addAttribute("qualityControlFineUpdateEntity", qualityControlFineUpdateEntity);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateList";
	}
	@RequiresPermissions("qualityControlFineUpdateLog:qualityControlFineUpdateLog:view")
	@RequestMapping(value = {"loglist", ""})
	public String loglist(QualityControlFineUpdateEntity qualityControlFineUpdateEntity, HttpServletRequest request, HttpServletResponse response, Model model) {
        //过滤门店
        User user = UserUtils.getUser();
        if(null==qualityControlFineUpdateEntity.getStoreId()){
            if(null!=user.getStoreId()){
                qualityControlFineUpdateEntity.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage1(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		for(QualityControlFineUpdateEntity qc:page.getList()){
			int picCount = qualityControlFineUpdateService.findPicListCountByCheckItemId(qc.getCheckItemId().toString());
			qc.setPicCount(picCount);
		}
		model.addAttribute("page", page);
		model.addAttribute("qualityControlFineUpdateEntity", qualityControlFineUpdateEntity);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLogList";
	}
	
	@RequiresPermissions("qualityControlFineUpdate:qualityControlFineUpdate:view")
	@RequestMapping(value = "listInfo")
	public String inspectSignListCheck(QualityControlFineUpdateEntity checkEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService
				.findPage(new Page<QualityControlFineUpdateEntity>(request, response), checkEntity);
		/*for (QualityControlFineUpdateEntity item : page.getList()) {
			StringBuilder sb = new StringBuilder();
			List<QualityControlFineUpdateEntity> name = qualityControlFineUpdateService.findName(item.getCheckItemId());

			for (QualityControlFineUpdateEntity qualityControlFineUpdateEntity : name) {
				sb.append(qualityControlFineUpdateEntity.getIllegalName());
				sb.append(",");
			}
			item.setIllegalName(sb.toString());

		}
*/
		if (x > 0) {
			checkEntity.setStoreId(null);

		}
		model.addAttribute("page", page);
		model.addAttribute("checkEntity", checkEntity);

		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateList";
	}
	@RequiresPermissions("qualityControlFineUpdate:qualityControlFineUpdate:view")
	@RequestMapping(value = "loglistInfo")
	public String inspectSignListLogCheck(QualityControlFineUpdateEntity checkEntity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (null != checkEntity.getBeginModifyDatetime()) {
			model.addAttribute("beginModifyDatetime", checkEntity.getBeginModifyDatetime());
		}
		if (null != checkEntity.getEndModifyDatetime()) {

			model.addAttribute("endModifyDatetime", checkEntity.getEndModifyDatetime());
		}
		int x = 0;
		// 不是管理员就不能查全部门店
		if (!UserUtils.getUser().getOffice().getId().equals("1")) {
			// 安心查自己门店吧
			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}

		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService
				.findPage1(new Page<QualityControlFineUpdateEntity>(request, response), checkEntity);
		/*for (QualityControlFineUpdateEntity item : page.getList()) {
			StringBuilder sb = new StringBuilder();
			List<QualityControlFineUpdateEntity> name = qualityControlFineUpdateService.findName(item.getCheckItemId());

			for (QualityControlFineUpdateEntity qualityControlFineUpdateEntity : name) {
				sb.append(qualityControlFineUpdateEntity.getIllegalName());
				sb.append(",");
			}
			item.setIllegalName(sb.toString());

		}
*/
		if (x > 0) {
			checkEntity.setStoreId(null);

		}
		for(QualityControlFineUpdateEntity qc:page.getList()){
			int picCount = qualityControlFineUpdateService.findPicListCountByCheckItemId(qc.getCheckItemId().toString());
			qc.setPicCount(picCount);
		}
		model.addAttribute("page", page);
		model.addAttribute("checkEntity", checkEntity);

		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLogList";
	}
	@RequestMapping(value = {"fineUpdate", ""})
	public String fineUpdate(String  qcBillCode,String qcCheckItemId, HttpServletRequest request, HttpServletResponse response, Model model){
		QualityControlFineUpdateEntity qualityControlFineUpdateEntity = new QualityControlFineUpdateEntity();
		qualityControlFineUpdateEntity.setReportCode(qcBillCode);
		qualityControlFineUpdateEntity.setQcCheckItemId(Integer.valueOf(qcCheckItemId));
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		QualityControlFineUpdateEntity qf = page.getList().get(0);
		BizPmSettleCategoryDetail bps = new BizPmSettleCategoryDetail();
		
		bps.setOrderId(Integer.valueOf(qf.getOrderId()));
		bps.setRelatedBusinessId(qf.getCheckItemId());
		bps.setSettleRole("1");
		bps.setSettleCategory("4");	///4代表质检罚款
		
		
		//查出结算内目表中项目经理对应的状态
		String managerSettleStatus = qualityControlFineUpdateService.findSettleStatus(bps);
		if(null ==managerSettleStatus){
			managerSettleStatus = "";
		}else if(managerSettleStatus.equals("null")){
			managerSettleStatus = "";
		}
		bps.setSettleRole("2");
		//查出结算内目表中质检员对应的状态
		String qcSettleStatus = qualityControlFineUpdateService.findSettleStatus(bps);
		
		boolean managerCanUpdate = true; //项目经理是否可以修改的标识，默认可修改
		boolean qcCanUpdate = true;      //质检员是否可以修改标识
		boolean workerCanUpdate = true;  //工人是否可以修改标识
		if(qf.getPunishMoney()!=null&&equal(qf.getPunishMoney(), 0)){ //如果罚款金额为0，则不能修改
			managerCanUpdate = false; 
		}else	if(managerSettleStatus!=null&&!managerSettleStatus.equals("10")){
			managerCanUpdate = false; 
		}
		if(qf.getInspectorMoney()!=null&&equal(qf.getInspectorMoney(),0)){ //如果罚款金额为0，则不能修改
			qcCanUpdate = false; 
		}else	if(qcSettleStatus!=null&&!qcSettleStatus.equals("10")){
			qcCanUpdate = false; 
		}
		if(qf.getWorkerMoney()==null){
			
			
		}
		String[] workerCanUpdateStatus = {"80","90","95","110","130"};//可以修改的状态
		if(qf.getPackageStateId()!=null&&Arrays.asList(workerCanUpdateStatus).contains(qf.getPackageStateId())&&(qf.getWorkerMoney()!=null&&!equal(qf.getWorkerMoney(), 0))){
			workerCanUpdate = true;
		}else{
			workerCanUpdate = false;
		}
		//查出来上传文件的路径
		List<BusinessPic> pList = qualityControlFineUpdateService.findPicListByCheckItemId(qf.getCheckItemId().toString());
		model.addAttribute("pList",pList);
		model.addAttribute("managerCanUpdate",managerCanUpdate);
		model.addAttribute("qcCanUpdate",qcCanUpdate);
		model.addAttribute("workerCanUpdate",workerCanUpdate);
		model.addAttribute("report", qf);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdate";
	}
	@RequestMapping(value = {"fineUpdateLog", ""})
	public String fineUpdateLog(String  qcBillCode,String qcCheckItemId, HttpServletRequest request, HttpServletResponse response, Model model){
		QualityControlFineUpdateEntity qualityControlFineUpdateEntity = new QualityControlFineUpdateEntity();
		qualityControlFineUpdateEntity.setReportCode(qcBillCode);
		qualityControlFineUpdateEntity.setQcCheckItemId(Integer.valueOf(qcCheckItemId));
		Page<QualityControlFineUpdateEntity> page = qualityControlFineUpdateService.findPage1(new Page<QualityControlFineUpdateEntity>(request, response), qualityControlFineUpdateEntity); 
		QualityControlFineUpdateEntity qf = page.getList().get(0);
		int picCount = qualityControlFineUpdateService.findPicListCountByCheckItemId(qf.getCheckItemId().toString());
		qf.setPicCount(picCount);
		/*//查出来上传文件的路径
		List<BusinessPic> pList = qualityControlFineUpdateService.findPicListByCheckItemId(qf.getCheckItemId().toString());
		model.addAttribute("pList",pList);*/
		model.addAttribute("report", qf);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLog";
	}
	public static boolean equal(double num1,double num2)
	{
		if((num1-num2>-0.000001)&&(num1-num2)<0.000001){
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping(value = {"saveData", ""})
	public  String saveData(QualityControlFineUpdateEntity report,String[] photo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		QualityControlFineUpdateEntity reportOld = qualityControlFineUpdateService.getBizQcBillCheckItemById(report.getCheckItemId().toString());
		String managerCanUpdate = request.getParameter("managerCanUpdate");
		String qcCanUpdate = request.getParameter("qcCanUpdate");
		String workerCanUpdate = request.getParameter("workerCanUpdate");
		///防止页面篡改
		if(managerCanUpdate.equals("false")){
			report.setManagerScore(reportOld.getManagerScore());
			report.setPunishMoney(report.getPunishMoney());
		}
		if(qcCanUpdate.equals("false")){
			report.setInspectorMoney(reportOld.getInspectorMoney());
			report.setInspectorScore(reportOld.getInspectorScore());
		}
		if(workerCanUpdate.equals("false")){
			report.setWorkerMoney(reportOld.getWorkerMoney());
			report.setWorkerScore(reportOld.getWorkerScore());
		}
		//如果有一处改动
		if(!equal((double)report.getPunishMoney(),(double)reportOld.getPunishMoney())||(double)report.getManagerScore()!=(double)reportOld.getManagerScore()||
			!equal((double)report.getWorkerMoney(),(double)reportOld.getWorkerMoney())||(double)report.getWorkerScore()!=(double)reportOld.getWorkerScore()||
			!equal((double)report.getInspectorMoney(),(double)reportOld.getInspectorMoney())||(double)report.getInspectorScore()!=(double)reportOld.getInspectorScore()){
			String no = UserUtils.getUser().getNo(); 
			Map<String,String> map = new HashMap<String,String>();
			map.put("checkedId", report.getCheckItemId().toString());
			map.put("no", no);
			qualityControlFineUpdateService.insertBizQcBillCheckItemLog(map);//记录日志
			qualityControlFineUpdateService.updateBizQcBillCheckItem(report);
		}
		//如果项目经理的罚金改为0，则将对应的结算类目明细的状态改为【18-罚款金额修改为0】
		Double punishMoney = report.getPunishMoney();
		if(!equal(report.getPunishMoney(),reportOld.getPunishMoney())){ //判断是否等于0
			BizPmSettleCategoryDetail bps = new BizPmSettleCategoryDetail();
			bps.setOrderId(Integer.valueOf(report.getOrderId()));
			bps.setRelatedBusinessId(report.getCheckItemId());
			bps.setSettleRole("1");
			bps.setSettleCategory("4");	///4代表质检罚款
			bps.setSettleAmount(punishMoney*(-1));//存负数
			if(punishMoney>-0.000001 && punishMoney < 0.000001){ //如果罚款为0，则改变状态
				bps.setSettleStatus("18"); //状态改为18
			}else{
				bps.setSettleStatus("10"); 
			}
			qualityControlFineUpdateService.updateBizPmSettleCategoryDetail(bps);
		}
		//如果质检员的罚金改为0，则将对应的结算类目明细的状态改为【18-罚款金额修改为0】
		Double inspectorMoney = report.getInspectorMoney();
		if(!equal(report.getInspectorMoney(),reportOld.getInspectorMoney())){ //判断是否有改变
			BizPmSettleCategoryDetail bps = new BizPmSettleCategoryDetail();
			bps.setOrderId(Integer.valueOf(report.getOrderId()));
			bps.setRelatedBusinessId(report.getCheckItemId());
			bps.setSettleRole("2");
			bps.setSettleCategory("4");	///4代表质检罚款
			bps.setSettleAmount(inspectorMoney*(-1));//存负数
			if(inspectorMoney>-0.000001 && inspectorMoney < 0.000001){
				bps.setSettleStatus("18"); //状态改为18
			}else{
				bps.setSettleStatus("10"); 
			}
			qualityControlFineUpdateService.updateBizPmSettleCategoryDetail(bps);
		}
		if(null != photo && photo.length>0){
			List<BusinessPic> pList = new ArrayList<BusinessPic>();
			for(String p : photo){
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//				String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				String picpath = ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				BusinessPic businessPic = new BusinessPic();
				businessPic.setId(null);
				businessPic.setBusinessIdInt(report.getCheckItemId());
				businessPic.setPicUrl(picpath);
				businessPic.setBusinessType(PictureTypeContantUtil.PICTURE_TYPE_2);
				businessPic.setPicDatetime(new Date());
			//	businessPic.set
				pList.add(businessPic);
			}
			
			qualityControlFineUpdateService.saveCheckitemPicAll(pList);
		}
		return "redirect:" + Global.getAdminPath() + "/qualityControlFineUpdate/list";
		/*JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();*/
	}
	@RequestMapping(value = {"delImgById", ""})
	public void delImgById(String id,String picUrl,HttpServletRequest request, HttpServletResponse response) throws IOException{
		qualityControlFineUpdateService.delImgById(id);
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("/").getPath());
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath().split("WEB-INF")[0];
		
		String url = (rootPath+picUrl).replace("/", File.separator).replace("\\", File.separator);
		File file = new File(url);
	    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		JSONObject json = new JSONObject();
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + picUrl + "成功！");
                json.put("status", "success");
                json.put("message", "删除成功！");
            } else {
            	json.put("status", "fail");
                json.put("message", "删除失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + picUrl + "不存在！");
            json.put("status", "fail");
            json.put("message", "删除失败，文件不存在！");
        }
        PrintWriter pw = response.getWriter();
		pw.print(json);
		pw.flush();
		pw.close();
	}
/*	//图片
	@RequestMapping(value="photo")
	public String photo(Integer checkItemId, String qcBillCode,String qcCheckItemId, Model model) throws IOException{
		
	*//*	List<BizPurchasePicture> pictures= bizPurchasePictureService.findPictureByPurchaseId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);*//*
		List<BusinessPic> pictures = qualityControlFineUpdateService.findPicListByCheckItemId(checkItemId.toString());
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("qcBillCode", qcBillCode);
		model.addAttribute("qcCheckItemId", qcCheckItemId);
		model.addAttribute("pictures", pictures);
		return "modules/PQC/QualityControlFineUpdate/qualityControlFineUpdateLogPhoto";
	}*/

	//图片Ajax
	@RequestMapping(value="/ajaxphoto")
	@ResponseBody
	public Map<Object, Object> ajaxphoto(Integer checkItemId, String qcBillCode,String qcCheckItemId, Model model) throws IOException{

	/*	List<BizPurchasePicture> pictures= bizPurchasePictureService.findPictureByPurchaseId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("pictures", pictures);*/
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		if(null!=checkItemId) {
			List<BusinessPic> pictures = qualityControlFineUpdateService.findPicListByCheckItemId(checkItemId.toString());
			model.addAttribute("pictures", pictures);
			mapObject.put("mapObject", pictures);
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("qcBillCode", qcBillCode);
		model.addAttribute("qcCheckItemId", qcCheckItemId);
		return mapObject;
	}
	public static void main(String[] args) {
		String[] workerCanUpdateStatus = {"80","90","95","110","120","130"};
		String s ="120";
		System.out.println(!Arrays.asList(workerCanUpdateStatus).contains(s));
		
	}
	
}