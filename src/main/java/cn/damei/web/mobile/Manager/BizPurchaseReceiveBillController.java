package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBill;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBillVo;
import cn.damei.entity.mobile.Manager.BusinessPicture;
import cn.damei.entity.mobile.Manager.ReceivedAuxiliary;
import cn.damei.entity.mobile.Manager.ReceivedPanel;
import cn.damei.entity.mobile.Manager.ReceivedTile;
import cn.damei.service.mobile.Manager.BizPurchaseReceiveBillService;
import cn.damei.service.mobile.Manager.BusinessPictureService;
import cn.damei.service.mobile.Manager.ReceivedAuxiliaryService;
import cn.damei.service.mobile.Manager.ReceivedPanelService;
import cn.damei.service.mobile.Manager.ReceivedTileService;
import cn.damei.entity.modules.BizPurchaseMainPanel;
import cn.damei.entity.modules.BizPurchaseMainTile;
import cn.damei.entity.modules.BizPurchaseVo;
import cn.damei.service.modules.BizPurchaseMainPanelService;
import cn.damei.service.modules.BizPurchaseMainTileService;
import cn.damei.service.modules.BizPurchaseVoService;
import cn.damei.common.utils.DictUtils;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class BizPurchaseReceiveBillController extends BaseController {

	@Autowired
	private BizPurchaseVoService bizPurchaseVoService;
	@Autowired
	private BizPurchaseMainTileService bizPurchaseMainTileService;
	@Autowired
	private BizPurchaseMainPanelService bizPurchaseMainPanelService;
	@Autowired
	private ReceivedAuxiliaryService receivedAuxiliaryService;
	@Autowired
	private ReceivedTileService receivedTileService;
	@Autowired
	private ReceivedPanelService receivedPanelService;
	@Autowired
	private BusinessPictureService businessPictureService;
	@Autowired
	private BizPurchaseReceiveBillService bizPurchaseReceiveBillService;

	@RequestMapping(value = "purchaseList")
	public String purchaseList(HttpServletRequest request, Model model) {

		Manager manager = SessionUtils.getManagerSession(request);

		List<BizPurchaseVo> auxiliaryPurchases = bizPurchaseVoService.findList1(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_1);

		List<BizPurchaseMainTile> tilePurchases = bizPurchaseMainTileService.findList1(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_5);

		List<BizPurchaseMainPanel> panelPurchases = bizPurchaseMainPanelService.findList1(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_2);

		List<BizPurchaseVo> sandPurchases = bizPurchaseVoService.findList1(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_6);

		model.addAttribute("auxiliaryPurchases", auxiliaryPurchases);
		model.addAttribute("tilePurchases", tilePurchases);
		model.addAttribute("panelPurchases", panelPurchases);
		model.addAttribute("sandPurchases", sandPurchases);
		return "mobile/modules/Manager/materi_get";
	}


	@RequestMapping(value = "materialList")
	public String materialList(Integer id, String purchaseType, Model model) throws IOException {

		if (PurchaseConstantUtil.PURCHASE_TYPE_1.equals(purchaseType)) {

			List<ReceivedAuxiliary> list = receivedAuxiliaryService.queryAuxiliaryByPurchase(id);
			for (ReceivedAuxiliary receivedAuxiliary : list) {
				if (receivedAuxiliary.getReceivedCount() == null) {
					receivedAuxiliary.setReceivedCount(0.0);
				}
			}
			model.addAttribute("list", list);
		} else if (PurchaseConstantUtil.PURCHASE_TYPE_5.equals(purchaseType)) {
			List<ReceivedTile> list = receivedTileService.queryTileByPurchaseId(id);
			for (ReceivedTile receivedTile : list) {
				String dictLabel = DictUtils.getDictLabel(receivedTile.getMainMateType(), "main_material_type", null);
				receivedTile.setName(dictLabel);




				if (receivedTile.getReceivedCount() == null) {
					receivedTile.setReceivedCount(0.0);
				}
			}
			model.addAttribute("list", list);
		} else if (PurchaseConstantUtil.PURCHASE_TYPE_2.equals(purchaseType)) {
			List<ReceivedPanel> list = receivedPanelService.queryPanelByPurchaseId(id);
			for (ReceivedPanel receivedPanel : list) {
				if (receivedPanel.getReceivedCount() == null) {
					receivedPanel.setReceivedCount(0.0);
				}
			}
			model.addAttribute("list", list);
		} else {

			List<ReceivedAuxiliary> list = receivedAuxiliaryService.queryAuxiliaryByPurchase(id);
			for (ReceivedAuxiliary receivedAuxiliary : list) {
				if (receivedAuxiliary.getReceivedCount() == null) {
					receivedAuxiliary.setReceivedCount(0.0);
				}
			}
			model.addAttribute("list", list);
		}

		List<BusinessPicture> picList = businessPictureService.queryPicture(id, ConstantUtils.PICTURE_BUSINESS_TYPE_5);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("picList", picList);
		model.addAttribute("id", id);
		model.addAttribute("purchaseType", purchaseType);
		return "mobile/modules/Manager/materi_goods";
	}

	@RequestMapping(value = "comfirmReceive", method = RequestMethod.POST)
	public @ResponseBody String comfirmReceive(String[] photo, String[] ids, String[] receivingCounts, String txtBeginDate, String purchaseType, Model model, HttpServletRequest request, String purchaseId) throws ParseException {

		Manager manager = SessionUtils.getManagerSession(request);
		String result = "0";

		BizPurchaseReceiveBillVo purchaseReceiveBillVo = bizPurchaseReceiveBillService.findNewReceiveBill(Integer.valueOf(purchaseId));
		if (null != purchaseReceiveBillVo && null != purchaseReceiveBillVo.getCreateDate()) {
			if (purchaseReceiveBillVo.getCreateDate().getTime() + 300 * 1000 > new Date().getTime()) {

				result = "1";
				return result;
			}

		}

		bizPurchaseReceiveBillService.insert(request, photo, ids, receivingCounts, txtBeginDate, purchaseId, manager, purchaseType);
		return result;
	}

	@RequestMapping(value = "deletePic", method = RequestMethod.POST)
	public @ResponseBody String deletePic(Integer id) {
		businessPictureService.deletePic(id);
		return "success";
	}


	@RequestMapping(value = "receivedBillList")
	public String receivedBillList(HttpServletRequest request, Model model) {
		Manager manager = SessionUtils.getManagerSession(request);
		List<BizPurchaseReceiveBillVo> auxiBills = bizPurchaseReceiveBillService.queryReceiveBill(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_1);
		List<BizPurchaseReceiveBillVo> tileBills = bizPurchaseReceiveBillService.queryReceiveBill(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_5);
		List<BizPurchaseReceiveBillVo> panelBills = bizPurchaseReceiveBillService.queryReceiveBill(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_2);
		List<BizPurchaseReceiveBillVo> sandBills = bizPurchaseReceiveBillService.queryReceiveBill(manager.getId(), PurchaseConstantUtil.PURCHASE_TYPE_6);
		model.addAttribute("auxiBills", auxiBills);
		model.addAttribute("tileBills", tileBills);
		model.addAttribute("panelBills", panelBills);
		model.addAttribute("sandBills", sandBills);
		return "mobile/modules/Manager/materi_record";
	}


	@RequestMapping(value = "receivedBillDetail")
	public String receivedBillDetail(Integer id, String type, Model model) throws IOException {


		BizPurchaseReceiveBill receiveBill = bizPurchaseReceiveBillService.queryById(id);


		List<BusinessPicture> pictures = businessPictureService.queryByReceiveBillId(id);


		if (PurchaseConstantUtil.PURCHASE_TYPE_1.equals(type)) {

			List<ReceivedAuxiliary> list = receivedAuxiliaryService.queryAuxiliaryByReceiveBillId(id);
			model.addAttribute("list", list);
		} else if (PurchaseConstantUtil.PURCHASE_TYPE_5.equals(type)) {
			List<ReceivedTile> list = receivedTileService.queryTileByReceiveBillId(id);
			for (ReceivedTile receivedTile : list) {
				if (receivedTile.getMainMateType().equals(ConstantUtils.FLOOR_BRICK_NUMBER)) {
					receivedTile.setName("地砖");
				} else {
					receivedTile.setName("墙砖");
				}
			}
			model.addAttribute("list", list);
		} else if (PurchaseConstantUtil.PURCHASE_TYPE_2.equals(type)) {
			List<ReceivedPanel> list = receivedPanelService.queryPanelByReceiveBillId(id);
			model.addAttribute("list", list);
		} else {
			List<ReceivedAuxiliary> list = receivedAuxiliaryService.queryAuxiliaryByReceiveBillId(id);
			model.addAttribute("list", list);
		}

		String baseUrl = PicRootName.picPrefixName();

		model.addAttribute("receiveBill", receiveBill);
		model.addAttribute("pictures", pictures);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Manager/materi_details";
	}
}
