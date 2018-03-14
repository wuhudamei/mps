
package cn.damei.web.modules;

import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.WallfloorConstant;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.excel.ExportExcel;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.service.modules.BizMaterialsChoiceBillService;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.BizOrderMainMate;
import cn.damei.service.modules.BizOrderMainMateService;
import cn.damei.service.modules.ProjectOrderListService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/ordermainmate/bizOrderMainMate")
public class BizOrderMainMateController extends BaseController {

	@Autowired
	private ProjectOrderListService projectOrderListService;

	@Autowired
	private BizMaterialsChoiceBillService bizMaterialsChoiceBillService;

	@Autowired
	private BizOrderMainMateService bizOrderMainMateService;

	@ModelAttribute
	public BizOrderMainMate get(@RequestParam(required = false) Integer id) {
		BizOrderMainMate entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = bizOrderMainMateService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderMainMate();
		}
		return entity;
	}

	@RequiresPermissions("ordermainmate:bizOrderMainMate:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderMainMate bizOrderMainMate, HttpServletRequest request, HttpServletResponse response, Model model) {



		BizMaterialsChoiceBill bizMaterialsChoiceBill = bizMaterialsChoiceBillService.getOrder(bizOrderMainMate.getOrderId());

		BizMaterialsChoiceBill isMaterialsChoiceBill = bizOrderMainMateService.ismaterialschoicebill(bizOrderMainMate.getOrderNumber());

		if (isMaterialsChoiceBill == null) {
			model.addAttribute("isMaterialsChoiceBill", 0);
		} else {
			model.addAttribute("isMaterialsChoiceBill", 1);
		}



		Page<BizOrderMainMate> page = bizOrderMainMateService.findPage(new Page<BizOrderMainMate>(request, response), bizOrderMainMate);
		model.addAttribute("bizMaterialsChoiceBill", bizMaterialsChoiceBill);
		model.addAttribute("page", page);
		String isxin = projectOrderListService.queryDealedwallfloor(bizOrderMainMate.getOrderNumber());
		model.addAttribute("isxin", isxin);
		return "modules/ordermainmate/bizOrderMainMateList";
	}

	@RequestMapping(value = "uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, String orderId) throws Exception {

		try {

			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());


			if (multipartResolver.isMultipart(request)) {

				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				@SuppressWarnings("rawtypes")
				Iterator iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter.next());
					if (file != null) {


						bizOrderMainMateService.deleteByOrderId(Integer.parseInt(orderId));

						InputStream is = file.getInputStream();
						Workbook book = WorkbookFactory.create(is);
						Sheet sheet = book.getSheetAt(0);
						int lastRowNum = sheet.getLastRowNum();
						Row row = null;
						Cell cell = null;
						for (int i = 2; i <= lastRowNum; i++) {
							row = sheet.getRow(i);
							if (row == null) {
								break;
							}
							BizOrderMainMate bizOrderMainMate = new BizOrderMainMate();
							bizOrderMainMate.setOrderId(Integer.parseInt(orderId));
							for (int j = 0; j < 11; j++) {
								cell = row.getCell(j);
								if (j == 0 && cell == null) {
									break;
								}

								switch (j) {
								case 0:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										String value = cell.getStringCellValue().replace(" ", "");
										if (ConstantUtils.AUXILIARY_NUMBER_STRING.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.AUXILIARY_NUMBER);
										} else if (ConstantUtils.SWITCH_PANEL_NUMBER_STRING.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.SWITCH_PANEL_NUMBER);
										} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING.equals(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER);
										} else if (ConstantUtils.WALL_BRICK_NUMBER_STRING.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER);
										} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_FLOOR_BRICK_NUMBER);
										} else if (value.contains(ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING41)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER41);
										} else if (value.contains(ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING42)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER42);
										} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING43.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER43);
										} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING44.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER44);
										} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING45.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER45);
										} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING46.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER46);

										} else if (value.contains("踢脚线")) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER31);
										} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING32.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER32);
										} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING33.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER33);
										}













										if (cell.getStringCellValue().contains("转角") || cell.getStringCellValue().contains("瓷砖配件") || cell.getStringCellValue().contains("波导线") || cell.getStringCellValue().contains("角花") || cell.getStringCellValue().contains("腰线") || cell.getStringCellValue().contains("踢脚线") || cell.getStringCellValue().contains("花片")) {
											bizOrderMainMate.setIscountsquare("0");
										} else {
											bizOrderMainMate.setIscountsquare("1");
										}
										break;
									}
								case 1:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setPosition(cell.getStringCellValue());
										String stringCellValue = cell.getStringCellValue();
										System.err.println(stringCellValue);

										break;
									}
								case 2:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setBrandCombo(cell.getStringCellValue());
										break;
									}
								case 3:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setModel(cell.getStringCellValue());
										break;
									}
								case 4:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setAttribute(cell.getStringCellValue());
										break;
									}
								case 5:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setSupplier(cell.getStringCellValue());
										break;
									}
								case 6:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setSpecification(cell.getStringCellValue());
										if (null != cell.getStringCellValue() && cell.getStringCellValue().contains("*") && (!cell.getStringCellValue().contains("m") && !cell.getStringCellValue().contains("M"))) {
											String[] split = cell.getStringCellValue().split("\\*");
											String num1 = null;
											String num2 = null;
											if (split.length == 2) {
												for (int k = 0; k < split.length; k++) {
													if (k == 0) {
														num1 = split[k];
													} else {
														num2 = split[k];
													}
												}
												double parseDouble1 = Double.parseDouble(num1);
												double parseDouble2 = Double.parseDouble(num2);
												bizOrderMainMate.setUnitsquare((parseDouble1 * parseDouble2) + "");
											}
										} else if (null != cell.getStringCellValue() && cell.getStringCellValue().contains("m")) {
											String[] split = cell.getStringCellValue().split("\\*");
											String num1 = null;
											String num2 = null;
											if (split.length == 2) {
												for (int k = 0; k < split.length; k++) {
													if (k == 0) {
														num1 = split[k].replaceAll("m", "");
													} else {
														num2 = split[k].replaceAll("m", "");
													}
												}
												double parseDouble1 = Double.parseDouble(num1);
												double parseDouble2 = Double.parseDouble(num2);
												bizOrderMainMate.setUnitsquare((parseDouble1 * parseDouble2) + "");
											}
										} else if (null != cell.getStringCellValue() && cell.getStringCellValue().contains("M")) {
											String[] split = cell.getStringCellValue().split("\\*");
											String num1 = null;
											String num2 = null;
											if (split.length == 2) {
												for (int k = 0; k < split.length; k++) {
													if (k == 0) {
														num1 = split[k].replaceAll("M", "");
													} else {
														num2 = split[k].replaceAll("M", "");
													}
												}
												double parseDouble1 = Double.parseDouble(num1);
												double parseDouble2 = Double.parseDouble(num2);
												bizOrderMainMate.setUnitsquare((parseDouble1 * parseDouble2) + "");
											}
										}
										break;
									}

								case 7:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setUnit(cell.getStringCellValue());
										break;
									}
								case 8:

									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setCount(cell.getStringCellValue() + "");
										break;
									}
								case 9:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setLossxs(Double.parseDouble(StringUtils.isEmpty(cell.getStringCellValue()) == true ? "0" : cell.getStringCellValue()));
										break;
									}
								case 10:
									if (cell != null) {
										cell.setCellType(Cell.CELL_TYPE_STRING);
										bizOrderMainMate.setIncludLossCount(cell.getStringCellValue() + "");
										break;
									}





								default:
									break;
								}
							}
							bizOrderMainMate.setCreateBy(UserUtils.getUser());
							bizOrderMainMate.setCreateDate(new Date());
							bizOrderMainMate.setUpdateBy(UserUtils.getUser());
							bizOrderMainMate.setUpdateDate(new Date());
							bizOrderMainMateService.insert(bizOrderMainMate);
						}
					}
				}

				bizOrderMainMateService.insertpurchaseCount(Integer.parseInt(orderId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}


	@RequestMapping(value = "insertOrderMainMate")
	public String insertOrderMainMate(String orderId, RedirectAttributes redirectAttributes, Model model) {

		BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(Integer.parseInt(orderId));
		List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
		if (findChoiceBillId != null && findChoiceBillId.getId() != null) {
			projectOrderListService.updateStatusWall(WallfloorConstant.WAll_FLOOR_ISTRUE5, Integer.parseInt(orderId));


			bizOrderMainMateService.deleteByOrderId(Integer.parseInt(orderId));





			materialsChoiceList = projectOrderListService.findMaterialsChoicez(findChoiceBillId.getId());
			for (BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem : materialsChoiceList) {
				BizOrderMainMate bizOrderMainMate = new BizOrderMainMate();
				bizOrderMainMate.setOrderId(Integer.parseInt(orderId));
				String value = bizMaterialsChoiceBillItem.getCategoryName();
				if (ConstantUtils.AUXILIARY_NUMBER_STRING.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.AUXILIARY_NUMBER);
				} else if (ConstantUtils.SWITCH_PANEL_NUMBER_STRING.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.SWITCH_PANEL_NUMBER);
				} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER);
				} else if (ConstantUtils.WALL_BRICK_NUMBER_STRING.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER);
				} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_FLOOR_BRICK_NUMBER);
				} else if (value.contains(ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING41)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER41);
				} else if (value.contains(ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING42)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER42);
				} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING43.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER43);
				} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING44.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER44);
				} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING45.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER45);
				} else if (ConstantUtils.WALL_FLOOR_BRICK_NUMBER_STRING46.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER46);

				} else if (value.contains("踢脚线")) {
					bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER31);
				} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING32.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER32);
				} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING33.contains(value)) {
					bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER33);
				}
				bizOrderMainMate.setPosition(bizMaterialsChoiceBillItem.getPosition());
				if (bizMaterialsChoiceBillItem.getCategoryName().contains("腰线") || bizMaterialsChoiceBillItem.getCategoryName().contains("踢脚线") || bizMaterialsChoiceBillItem.getCategoryName().contains("花片")) {
					bizOrderMainMate.setIscountsquare("0");
				} else {
					bizOrderMainMate.setIscountsquare("1");
				}
				bizOrderMainMate.setBrandCombo(bizMaterialsChoiceBillItem.getBrand());
				bizOrderMainMate.setModel(bizMaterialsChoiceBillItem.getModel());
				bizOrderMainMate.setAttribute(bizMaterialsChoiceBillItem.getAttribute());
				bizOrderMainMate.setSupplier(bizMaterialsChoiceBillItem.getSupplierName());
				bizOrderMainMate.setSpecification(bizMaterialsChoiceBillItem.getSpec());
				String spec = bizOrderMainMate.getSpecification();
				if (null != spec && spec.contains("*") && (!spec.contains("m") && !spec.contains("M"))) {
					String[] split = spec.split("\\*");
					String num1 = null;
					String num2 = null;
					if (split.length == 2) {
						for (int k = 0; k < split.length; k++) {
							if (k == 0) {
								num1 = split[k];
							} else {
								num2 = split[k];
							}
						}
						double parseDouble1 = Double.parseDouble(num1);
						double parseDouble2 = Double.parseDouble(num2);
						bizOrderMainMate.setUnitsquare((parseDouble1 * parseDouble2) + "");
					}
				} else if (null != spec && spec.contains("m")) {
					String[] split = spec.split("\\*");
					String num1 = null;
					String num2 = null;
					if (split.length == 2) {
						for (int k = 0; k < split.length; k++) {
							if (k == 0) {
								num1 = split[k].replaceAll("m", "");
							} else {
								num2 = split[k].replaceAll("m", "");
							}
						}
						double parseDouble1 = Double.parseDouble(num1);
						double parseDouble2 = Double.parseDouble(num2);
						bizOrderMainMate.setUnitsquare((parseDouble1 * parseDouble2) + "");
					}
				} else if (null != spec && spec.contains("M")) {
					String[] split = spec.split("\\*");
					String num1 = null;
					String num2 = null;
					if (split.length == 2) {
						for (int k = 0; k < split.length; k++) {
							if (k == 0) {
								num1 = split[k].replaceAll("M", "");
							} else {
								num2 = split[k].replaceAll("M", "");
							}
						}
						double parseDouble1 = Double.parseDouble(num1);
						double parseDouble2 = Double.parseDouble(num2);
						bizOrderMainMate.setUnitsquare((parseDouble1 * parseDouble2) + "");
					}
				}
				bizOrderMainMate.setUnit(bizMaterialsChoiceBillItem.getUnit());
				bizOrderMainMate.setCount(bizMaterialsChoiceBillItem.getBudgetNumber1());
				bizOrderMainMate.setLossxs(bizMaterialsChoiceBillItem.getLossRatio());
				bizOrderMainMate.setIncludLossCount(bizMaterialsChoiceBillItem.getIncludeLossNumber() + "");
				bizOrderMainMate.setApplyCounta(bizMaterialsChoiceBillItem.getBudgetNumber2());

				bizOrderMainMate.setCreateBy(UserUtils.getUser());
				bizOrderMainMate.setCreateDate(new Date());
				bizOrderMainMate.setUpdateBy(UserUtils.getUser());
				bizOrderMainMate.setUpdateDate(new Date());
				bizOrderMainMateService.insert(bizOrderMainMate);
			}

			bizOrderMainMateService.insertpurchaseCount(Integer.parseInt(orderId));
			addMessage(redirectAttributes, "导入了" + materialsChoiceList.size() + "条墙地砖数据");


		}
		return "redirect:" + Global.getAdminPath() + "/ordermainmate/bizOrderMainMate/list?orderId=" + orderId + "&orderNumber=" + findChoiceBillId.getOrderNumber();

	}

	@RequiresPermissions("ordermainmate:bizOrderMainMate:view")
	@RequestMapping(value = "form")
	public String form(BizOrderMainMate bizOrderMainMate, Model model) {
		model.addAttribute("bizOrderMainMate", bizOrderMainMate);
		return "modules/ordermainmate/bizOrderMainMateForm";
	}

	@RequestMapping(value = "exportWallFloor", method = RequestMethod.POST)
	public String exportWallFloor(BizOrderMainMate bizOrderMainMate, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "墙地砖导入模板" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";



			Date date = DateUtils.parseDate("2017-09-08 11:59:59");

			bizOrderMainMate.setCreateDatez(date);
			bizOrderMainMate.setAttribute("xxx");
			List<BizOrderMainMate> list = bizOrderMainMateService.findListOne(bizOrderMainMate);

			new ExportExcel("墙地砖导入模板", BizOrderMainMate.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出墙地砖模板失败！失败信息：" + e.getMessage());
		}

		model.addAttribute("bizOrderMainMate", bizOrderMainMate);

		return "redirect:" + Global.getAdminPath() + "/ordermainmate/bizOrderMainMate/list?orderId=" + bizOrderMainMate.getOrderId() + "&orderNumber=" + bizOrderMainMate.getOrderNumber();
	}

	@RequestMapping(value = "save")
	public String save(BizOrderMainMate bizOrderMainMate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizOrderMainMate)) {
			return form(bizOrderMainMate, model);
		}
		bizOrderMainMateService.save(bizOrderMainMate);
		addMessage(redirectAttributes, "保存主材订单成功");
		return "redirect:" + Global.getAdminPath() + "/ordermainmate/bizOrderMainMate/?repage";
	}

	@RequiresPermissions("ordermainmate:bizOrderMainMate:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOrderMainMate bizOrderMainMate, RedirectAttributes redirectAttributes) {
		bizOrderMainMateService.delete(bizOrderMainMate);
		addMessage(redirectAttributes, "删除主材订单成功");
		return "redirect:" + Global.getAdminPath() + "/ordermainmate/bizOrderMainMate/?repage";
	}

	@RequestMapping(value = "ajaxUpdateistf")

	public String ajaxUpdateistf(BizOrderMainMate bizOrderMainMate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {
		bizOrderMainMate.setUpdateBy(UserUtils.getUser());
		bizOrderMainMate.setUpdateDate(new Date());
		if ((null == bizOrderMainMate.getUnitsquare() || bizOrderMainMate.getUnitsquare().equals("")) && bizOrderMainMate.getIscountsquare().equals("1")) {
			addMessage(redirectAttributes, "只有单位面积不为空的才允许勾选");
		} else {
			bizOrderMainMateService.savebyid(bizOrderMainMate);
			addMessage(redirectAttributes, "修改成功");
		}
		Page<BizOrderMainMate> page = bizOrderMainMateService.findPage(new Page<BizOrderMainMate>(request, response), bizOrderMainMate);
		model.addAttribute("page", page);
		return "redirect:" + Global.getAdminPath() + "/ordermainmate/bizOrderMainMate/list?orderId=" + bizOrderMainMate.getOrderId() + "&orderNumber=" + bizOrderMainMate.getOrderNumber();
	}

	@RequestMapping(value = "updateUnitsquare")
	public String updateUnitsquare(BizOrderMainMate bizOrderMainMateq, RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("bizOrderMainMate", bizOrderMainMateq);
		return "modules/ordermainmate/bizOrderMainMateForm";
	}

	@RequestMapping(value = "updateuni")
	public String updateuni(BizOrderMainMate bizOrderMainMate, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {
		bizOrderMainMate.setUpdateBy(UserUtils.getUser());
		bizOrderMainMate.setUpdateDate(new Date());
		bizOrderMainMateService.updateuni(bizOrderMainMate);
		Page<BizOrderMainMate> page = bizOrderMainMateService.findPage(new Page<BizOrderMainMate>(request, response), bizOrderMainMate);
		model.addAttribute("page", page);
		addMessage(redirectAttributes, "保存主材订单成功");
		return "modules/ordermainmate/bizOrderMainMateList";
	}

}