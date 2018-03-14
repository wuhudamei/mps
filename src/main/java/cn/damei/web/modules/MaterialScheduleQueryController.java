package cn.damei.web.modules;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.MaterialScheduleQueryEntity;
import cn.damei.service.modules.MaterialScheduleQueryService;
import cn.damei.common.utils.UserUtils;
/**
 * 
 * @author 梅浩
 * @2016年12月7日
 * @mdn美得你
 * @author_phone : 18610507472
 * @ClassInfo:材料进度看板
 */
@Controller
@RequestMapping(value="${adminPath}/materialScheduleQuery")
public class MaterialScheduleQueryController  extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(MaterialScheduleQueryController.class);//日志
	
	@Autowired
	private MaterialScheduleQueryService service;
	
	
	public MaterialScheduleQueryEntity get(@RequestParam(required = false) Integer id) {
		MaterialScheduleQueryEntity entity =null;
		if (StringUtils.isNotBlank(id+"")){
			entity = service.get(id);
		}
		if (entity == null){
			entity = new MaterialScheduleQueryEntity();
		}
		return entity;
	}
	
	@RequiresPermissions("materialScheduleQuery:materialScheduleQuery:view")
	@RequestMapping(value="list")
	public String    inspectSignList(MaterialScheduleQueryEntity checkEntity,HttpServletRequest request, HttpServletResponse response, Model model){
	
		return "modules/materialScheduleQuery/list";
	}
	@RequiresPermissions("materialScheduleQuery:materialScheduleQuery:view")
	@RequestMapping(value="listInfo")
	public String    inspectSignListCheck( MaterialScheduleQueryEntity checkEntity,HttpServletRequest request, HttpServletResponse response, Model model){
		if(checkEntity.getStatus()!=null){
			String[] status = checkEntity.getStatus().split(",");
			if(null!=status && status.length>0){
				checkEntity.setPurchaseStatusList(Arrays.asList(status));
			}
		}
		int x = 0;
		//不是管理员就不能查全部门店
		if(!UserUtils.getUser().getOffice().getId().equals("1")){
			//安心查自己门店吧
			checkEntity.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
		
		Page<MaterialScheduleQueryEntity> page = service.findPage(new Page<MaterialScheduleQueryEntity>(request, response), checkEntity); 
		if(x>0){
			checkEntity.setStoreId(null);
			
		}
		
		model.addAttribute("page", page);
		
		
		return "modules/materialScheduleQuery/list";
	}
	
	/*@RequiresPermissions("materialScheduleQuery:materialScheduleQuery:view")
	@RequestMapping(value="details")
	public String    purchaseDetails(String type,String id,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
	
		
		//1:辅材
			if(null!=id&&null!=type){
				if("1".equals(type)){
					return "redirect:" + Global.getAdminPath() + "	/purchase/bizPurchase/details?id="+id;	
				
			//2:开关面板		
				}else if("2".equals(type)){
					return "redirect:" + Global.getAdminPath() + "	/purchase/bizPurchaseMainPanel/mainPanelDetails?id="+id;		
				//5 墙地砖
				}else if("5".equals(type)){
					return "redirect:" + Global.getAdminPath() + "/purchase/bizPurchaseMainTile/mainTileDetails?id="+id;	
					
					
				}else{
					
					logger.warn("PC端采购单进度面板的点击详情中: id或者type数据异常  id为: "+id +"type为: "+type);
					addMessage(redirectAttributes, "您的数据格式不正确,请联系开发人员");
					return "modules/materialScheduleQuery/list";	
					
					
				}
				
				
			}else{
				
				logger.warn("PC端采购单进度面板的点击详情中: id或者type为空  id为: "+id +"type为: "+type);
				addMessage(redirectAttributes, "您的数据有误,请联系开发人员");
				return "modules/materialScheduleQuery/list";
				
			}
		
		
	
	}*/
	
	
}
