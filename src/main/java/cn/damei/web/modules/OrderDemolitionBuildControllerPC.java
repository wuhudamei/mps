package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.service.modules.OrderDemolitionBuildServicePC;

/** 
* @ClassName: 
* @Description: pc端拆改交底
* @author zkj  
* @date 2017年10月23日 上午11:34:01 
* @version V1.0 
*/
@Controller
@RequestMapping(value="${adminPath}/modules/orderDemolitionBuildPC/web/OrderDemolitionBuildControllerPC")
public class OrderDemolitionBuildControllerPC {
	
	@Autowired
	private OrderDemolitionBuildServicePC orderDemolitionBuildServicePC;
	@Autowired
	private BusinessPictureService businessPictureService;
	
	@ModelAttribute
	public SignDetail get(@Param(value = "id") String id){
		return new SignDetail();
	}
	
	
	/** 
	* @Description: pc端拆改交底查询列表 
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @param signDetail
	* @param @return
	* @author zkj 
	* @date 2017年10月23日 下午1:21:53 
	*/
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model,SignDetail signDetail){
		
		Page<SignDetail> findPage = orderDemolitionBuildServicePC.findPage(new Page<SignDetail>(request, response),signDetail);
		
		model.addAttribute("page", findPage);
		
		return "/modules/orderDemolitionBuildPC/orderDemolitionBuildPCList";
	}
	
	/** 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param signDetail
	* @author zkj 
	* @date 2017年10月23日 下午5:03:10 
	*/
	@RequestMapping(value="/export")
	@ResponseBody
	public void export(SignDetail signDetail,HttpServletResponse response){
		orderDemolitionBuildServicePC.exportDetailExcel(signDetail,response);
	}
	
	
	/*@RequestMapping(value="/photo")
	public String findPictureByBusinessProblemId(Integer id,String picType,Model model) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryPictureByBussinessIdAndType(id,picType);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/bizorderinstallitemproblem/photo";
	}*/

	/**
	 * 查看图片
	 * @param
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/photo")
	@ResponseBody
	public Map<Object, Object> photo(Integer id, Model model, HttpServletRequest request) throws IOException{
		List<BusinessPicture> pictures = businessPictureService.queryPictureByBussinessIdAndType(id,"10020");
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", pictures);
		return mapObject;
	}
	
}
