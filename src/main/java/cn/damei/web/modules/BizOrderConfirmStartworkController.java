package cn.damei.web.modules;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizConfirmStartOrder;
import cn.damei.entity.modules.BizOrderConfirmStartworkPic;
import cn.damei.entity.modules.BizOrderConfirmStartworkPicService;

/**
 * @author llp
 * @version 创建时间：2016年10月27日 下午5:05:38 类说明
 */
@Controller
@RequestMapping(value = "${adminPath}/bizconfirmstartwork/bizConfirmStartwork")
public class BizOrderConfirmStartworkController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BizOrderConfirmStartworkController.class);

	@Autowired
	private BizOrderConfirmStartworkPicService bizOrderConfirmStartworkPicService;

	/**
	 * 确认开工查看图片
	 * 
	 * @return photoList
	 */
	@RequestMapping(value = { "confirmPhotos", "" })
	public String confirmPhotos(BizConfirmStartOrder bizConfirmStartOrder, Model model, String startWorkID, HttpServletRequest request) throws IOException {
		logger.info("biz_order_confirm_startwork主键：" + startWorkID);
		List<BizOrderConfirmStartworkPic> picList = null;

		if (null != startWorkID) {
			picList = bizOrderConfirmStartworkPicService.getByConfirmStartWorkID(Integer.valueOf(startWorkID));
		}
		if (picList != null) {
			logger.info("图片个数：" + picList.size());
		}

		model.addAttribute("picList", picList);
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());
		return "modules/bizconfirmstart/confirmPicPhotos";
	}

	/**
	 * 确认开工查看图片
	 * 
	 * @return photoList
	 */
	@RequestMapping(value = "/ajaxConfirmPhotos")
	@ResponseBody
	public Map<Object, Object> ajaxConfirmPhotos(BizConfirmStartOrder bizConfirmStartOrder, Model model, String startWorkID, HttpServletRequest request) throws IOException {
		logger.info("biz_order_confirm_startwork主键：" + startWorkID);
		List<BizOrderConfirmStartworkPic> picList = null;

		if (null != startWorkID) {
			picList = bizOrderConfirmStartworkPicService.getByConfirmStartWorkID(Integer.valueOf(startWorkID));
		}
		if (picList != null) {
			logger.info("图片个数：" + picList.size());
		}

		model.addAttribute("picList", picList);
		model.addAttribute("picPrefixName", PicRootName.picPrefixName());

		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", picList);

		return mapObject;
	}
}
