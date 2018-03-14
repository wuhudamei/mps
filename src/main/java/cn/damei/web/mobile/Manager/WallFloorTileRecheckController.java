package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.PicturePathContantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.constantUtils.WallFloorTileRecheckConstantUtil;
import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.WallAndFloorService;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.service.mobile.Manager.WallAndFloorProblemService;
import cn.damei.entity.mobile.Manager.WallFloorTileRecheck;
import cn.damei.service.mobile.Manager.WallFloorTileRecheckService;
import cn.damei.entity.modules.BizBusinessStatusLog;

/**
 * 墙地砖实测面积复核Controller
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/app/manager/wallFloorTileRecheck")
public class WallFloorTileRecheckController {

	@Autowired
	private WallFloorTileRecheckService wallFloorTileRecheckService;
	@Autowired
	private WallAndFloorService wallAndFloorService;
	@Autowired
	private WallAndFloorProblemService wallAndFloorProblemService;
	@Autowired
	private ProblemService problemService;

	/**
	 * 墙地砖实测面积复核 页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(HttpServletRequest request) {

		return "mobile/modules/Manager/wallFloorTileRecheck/wallRecordList";
	}

	/**
	 * 动态加载 墙地砖实测面积复核列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "wallFloor_tile_recheck_ajax_list")
	public @ResponseBody List<WallFloorTileRecheck> wallFloorTileRecheckAjaxList(HttpServletRequest request) {

		// 获得项目经理
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		// 查询该项目经理下的墙地砖复尺单列表
		List<WallFloorTileRecheck> list = null;
		if (null != manager && null != manager.getId()) {
			list = wallFloorTileRecheckService.findWallFloorTileRecheckList(manager.getId());
		}

		return list;

	}

	/**
	 * 墙地砖实测面积复核页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "wallFloorTileRecheckApply", "" })
	public String wallFloorTileRecheckApply(String wallFloorTileRecheckId, String orderId, String flag, HttpServletRequest request, Model model) {

		WallFloorTileRecheck wallFloorTileRecheck = null;
		Double wallSquareCount = 0.00;
		Double floorSquareCount = 0.00;
		Double squareActual = 0.00;
		Double squareM = 0.00;
		if (StringUtils.isNotBlank(wallFloorTileRecheckId)) {

			// 1.根据墙地砖复尺单id查询相关信息
			wallFloorTileRecheck = wallFloorTileRecheckService.findWallFloorTileRecheckMessage(Integer.valueOf(wallFloorTileRecheckId));
			// 2.查询该订单的墙砖面积
			wallSquareCount = wallFloorTileRecheckService.findSquareCount(wallFloorTileRecheck.getOrderId(), PurchaseConstantUtil.PURCHASE_TYPE_4);
			// 3.查询该订单的地砖面积
			floorSquareCount = wallFloorTileRecheckService.findSquareCount(wallFloorTileRecheck.getOrderId(), PurchaseConstantUtil.PURCHASE_TYPE_3);
			if (!StringUtils.isEmpty(flag)) {
				wallFloorTileRecheck.setFlag("0");
			}
			// 如果订单id不为空就查现场交底的实测面积
			if (!StringUtils.isEmpty(orderId)) {
				// 8.根据订单id查询获取实测面积
				squareM = wallAndFloorService.querySquate(Integer.parseInt(orderId));
				squareActual = wallAndFloorService.findSquareActual(Integer.parseInt(orderId));
				if (null == squareActual) {
					wallFloorTileRecheck.setSquareMeasure(0.0);
				} else {
					if (null == squareM) {

						wallFloorTileRecheck.setSquareMeasure(squareActual);
					} else {
						wallFloorTileRecheck.setSquareMeasure(squareM);

					}

				}

			}
		}

		model.addAttribute("wallFloorTileRecheck", wallFloorTileRecheck);
		model.addAttribute("wallSquareCount", wallSquareCount);
		model.addAttribute("floorSquareCount", floorSquareCount);
		model.addAttribute("wallFloorSquareCount", wallSquareCount + floorSquareCount);

		return "mobile/modules/Manager/wallFloorTileRecheck/wallRecheckSub";
	}

	/**
	 * 墙地砖实测面积复核提交
	 * 
	 * @param orderId
	 * @param wallFloorTileRecheckId
	 * @param squareMeasure
	 * @param measureRemarks
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "wallFloor_tile_recheck_submit_ajax", method = RequestMethod.POST)
	public @ResponseBody String wallFloorTileRecheckSubmitAjax(String wallFloorTileRecheckId, String flag, String squareMeasure, String measureRemarks, String[] photo, HttpServletRequest request) {

		String result = "0";

		// 1.墙地砖复尺单id为空
		if (StringUtils.isBlank(wallFloorTileRecheckId)) {
			result = "1";
			return result;
		}
		// 2.实测面积为空
		if (StringUtils.isBlank(squareMeasure)) {
			result = "2";
			return result;
		}
		// 3.实测照片为空
		if (null == photo || photo.length < 1) {
			result = "3";
			return result;
		}
		// 4.当前登录的项目经理
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if (null == manager) {
			result = "4";
			return result;
		}
		// 5.根据墙地砖复尺单id查询相关信息，该复尺单项目经理已经确认过了
		WallFloorTileRecheck wallFloorTileRecheck = wallFloorTileRecheckService.findWallFloorTileRecheckMessage(Integer.valueOf(wallFloorTileRecheckId));
		// if
		// (!wallFloorTileRecheck.getStatus().equals(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_30))
		// {
		// result = "5";
		// return result;
		// }
		// 6.更新墙地砖复尺单
		boolean flag1 = wallFloorTileRecheckService.saveWallFloorTileRecheck(Integer.valueOf(wallFloorTileRecheckId), flag, squareMeasure, measureRemarks, manager);
		if (!flag1) {
			result = "6";
			return result;
		}
		// 7.保存日志
		Integer logId = wallAndFloorService.saveBusinessStatusLog(manager.getId(), Integer.valueOf(wallFloorTileRecheckId), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_50, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_50, null);
		if (null == logId || logId < 1) {
			result = "7";
			return result;
		}
		// 8.更新订单的相关信息
		boolean flagOrder = wallFloorTileRecheckService.saveWallFloorTileOrderCount(wallFloorTileRecheck.getOrderId(), squareMeasure);
		if (!flagOrder) {
			result = "8";
			return result;
		}
		// 9.保存图片
		wallAndFloorProblemService.saveProblemPic(Integer.valueOf(wallFloorTileRecheckId), PictureTypeContantUtil.PICTURE_TYPE_2073, photo, PicturePathContantUtil.UPLOAD_WALLFLOOR_TILE_RECHECK, request);

		return result;
	}

	/**
	 * 墙地砖实测面积复核 查看结果页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "wallRecheckResult", "" })
	public String wallRecheckResult(String wallFloorTileRecheckId, HttpServletRequest request, Model model) {

		WallFloorTileRecheck wallFloorTileRecheck = null;
		Double wallSquareCount = 0.00;
		Double floorSquareCount = 0.00;
		if (StringUtils.isNotBlank(wallFloorTileRecheckId)) {

			// 1.根据墙地砖复尺单id查询相关信息
			wallFloorTileRecheck = wallFloorTileRecheckService.findWallFloorTileRecheckMessage(Integer.valueOf(wallFloorTileRecheckId));
			// 2.查询该订单的墙砖面积
			wallSquareCount = wallFloorTileRecheckService.findSquareCount(wallFloorTileRecheck.getOrderId(), PurchaseConstantUtil.PURCHASE_TYPE_4);
			// 3.查询该订单的地砖面积
			floorSquareCount = wallFloorTileRecheckService.findSquareCount(wallFloorTileRecheck.getOrderId(), PurchaseConstantUtil.PURCHASE_TYPE_3);
		}

		model.addAttribute("wallFloorTileRecheck", wallFloorTileRecheck);
		model.addAttribute("wallSquareCount", wallSquareCount);
		model.addAttribute("floorSquareCount", floorSquareCount);
		model.addAttribute("wallFloorSquareCount", wallSquareCount + floorSquareCount);

		return "mobile/modules/Manager/wallFloorTileRecheck/wallRecheckResult";
	}

	/**
	 * 墙地砖实测面积复核 申请记录页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "wallRecheckDetails", "" })
	public String wallRecheckDetails(String wallFloorTileRecheckId, HttpServletRequest request, Model model) throws IOException {

		WallFloorTileRecheck wallFloorTileRecheck = null;
		List<ReportCheckDetailsPic> picList = null;
		List<BizBusinessStatusLog> list = null;
		if (StringUtils.isNotBlank(wallFloorTileRecheckId)) {

			// 1.根据墙地砖复尺单id查询相关信息
			wallFloorTileRecheck = wallFloorTileRecheckService.findWallFloorTileRecheckMessage(Integer.valueOf(wallFloorTileRecheckId));
			// 2.实测图片
			picList = problemService.findPic(Integer.valueOf(wallFloorTileRecheckId), PictureTypeContantUtil.PICTURE_TYPE_2073);
			// 3.复尺单日志
			list = wallFloorTileRecheckService.findOperationList(Integer.valueOf(wallFloorTileRecheckId), BusinessLogConstantUtil.BUSINESS_TYPE_2012);
		}

		String baseUrl = PicRootName.picPrefixName();

		model.addAttribute("wallFloorTileRecheck", wallFloorTileRecheck);
		model.addAttribute("picList", picList);
		model.addAttribute("list", list);
		model.addAttribute("baseUrl", baseUrl);

		return "mobile/modules/Manager/wallFloorTileRecheck/wallRecheckDetails";
	}

}