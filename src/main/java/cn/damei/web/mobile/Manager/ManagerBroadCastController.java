package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.ManagerBroadCastEntity;
import cn.damei.service.mobile.Manager.ManagerBroadCastService;
import cn.damei.entity.mobile.Manager.Manager;

@Controller
@RequestMapping(value="${adminPath}/app/manager/broadcast")
public class ManagerBroadCastController {

	
	@Autowired
	private ManagerBroadCastService service;
	
	
	
	
	
	@RequestMapping(value="index")
	public String  index(HttpServletRequest request,Model model){
		Manager manager = SessionUtils.getManagerSession(request);
		ManagerBroadCastEntity entity = new ManagerBroadCastEntity();
		entity.setApplyBroadCastId(manager.getId());
		//根据登录的项目经理 查询名下订单并关联播报表查询相关信息
		List<ManagerBroadCastEntity> list = service.findBroadCastList(entity);
		
		if(null!=list&&list.size()>0){
			
			
			model.addAttribute("list", list);
		}else{
			
			
			model.addAttribute("error", "您目前暂时没有播报单");
		}
		
		
		return "mobile/modules/Manager/broadcast/broadcast";
		
	}
	@RequestMapping(value="query_ajax_list")
	public @ResponseBody List<ManagerBroadCastEntity>  queryAjaxList(HttpServletRequest request,Model model,String text){
		Manager manager = SessionUtils.getManagerSession(request);
		ManagerBroadCastEntity entity = new ManagerBroadCastEntity();
		entity.setApplyBroadCastId(manager.getId());
		entity.setText(text);
		//根据登录的项目经理 查询名下订单并关联播报表查询相关信息 (模糊搜索)
		List<ManagerBroadCastEntity> list = service.findBroadCastList(entity);
		
		if(null!=list&&list.size()>0){
		return list;
		}else{
		return null;
		}
		
		
	
		
	}
	
	@RequestMapping(value="broadcast_check")
	public String  broadcastCheck(HttpServletRequest request,Model model,String id) throws NumberFormatException, IOException{
		List<ManagerBroadCastEntity> list = service.findBroadCastInfoAndPic(isNum(id)?Integer.parseInt(id):0);
		if(null!=list&&list.size()>0){
			model.addAttribute("list", list);
			model.addAttribute("broadcast", list.get(0));
			model.addAttribute("picPrefix",request.getContextPath());
			return "mobile/modules/Manager/broadcast/broadcast_details";
			
		}else{
			
			model.addAttribute("error", "传入参数id不合法 或该播报单没有详情");
			return "mobile/modules/Manager/broadcast/broadcast_details";
		}
		
	}
	@RequestMapping(value="check_broadcast_pic_and_status.php")
	public @ResponseBody String  checkBroadcastPicAndStatus(String broadcastId,HttpServletRequest request,Model model,String[] photos,String [] picIds,String [] isShow){
	
		
			//根据id 和isShow  批量update状态
			//当id结束时,判断是否新上传了图片
			//如果有图片, 以picIds的length为isShow数组的角标开始
			//保存图片并设置是否展示
		//全有 isShow
		//部分有id  那么当角标越界时, 就是新上传了图片 没有id 主键   这时就插入数据 并设置remarks 为1 也就是不展示
		int x =isShow.length;
		for (String string : isShow) {
			if("1".equals(string)){
				
				break;
			}else{
				x--;
				if(x==0){
					
					return "2"; //不能一张图片都不展示
				}
				
			}
		}
		
		
		if(service.checkBroadcastPicAndStatus(broadcastId, request, model, photos, picIds, isShow)){
			
			return "1";
			
		}else{
			
			return "0";
		}
		
		
		
		
	}
	
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	

}
