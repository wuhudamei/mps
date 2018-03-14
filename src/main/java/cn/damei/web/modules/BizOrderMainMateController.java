/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 主材订单Controller
 * 
 * @author qww
 * @version 2016-10-09
 */
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

		// System.out.println("orderId=" + bizOrderMainMate.getOrderId());
		// 1.根据订单ID查询订单信息
		BizMaterialsChoiceBill bizMaterialsChoiceBill = bizMaterialsChoiceBillService.getOrder(bizOrderMainMate.getOrderId());
		// 2.根据订单编号查询是否有选材清单
		BizMaterialsChoiceBill isMaterialsChoiceBill = bizOrderMainMateService.ismaterialschoicebill(bizOrderMainMate.getOrderNumber());
		// 3.替换页面俩标签 如果空代表没有选材清单否则就有选材清单
		if (isMaterialsChoiceBill == null) {
			model.addAttribute("isMaterialsChoiceBill", 0);
		} else {
			model.addAttribute("isMaterialsChoiceBill", 1);
		}
		// System.err.println(isMaterialsChoiceBill.getId() + ":" +
		// isMaterialsChoiceBill.getOrderId());
		// bizMaterialsChoiceBill.setCreateDatez(createDate);
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
			// 解析器解析request的上下文
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

			// 先判断request中是否包涵multipart类型的数据
			if (multipartResolver.isMultipart(request)) {
				// 再将request中的数据转化成multipart类型的数据
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				@SuppressWarnings("rawtypes")
				Iterator iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter.next());
					if (file != null) {

						// 先真删除orderId下的所有数据
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
											// ConstantUtils.FLOOR_BRICK_NUMBER_STRING31
										} else if (value.contains("踢脚线")) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER31);
										} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING32.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER32);
										} else if (ConstantUtils.FLOOR_BRICK_NUMBER_STRING33.contains(value)) {
											bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER33);
										}
										// 设置归类
										// if (value.contains("上墙砖") ||
										// value.contains("下墙砖") ||
										// value.contains("腰线") ||
										// value.contains("转角") ||
										// value.contains("花片") ||
										// value.contains("角花")) {
										// bizOrderMainMate.setMainMateType(ConstantUtils.WALL_BRICK_NUMBER);
										// } else {
										// bizOrderMainMate.setMainMateType(ConstantUtils.FLOOR_BRICK_NUMBER);
										//
										// }
										// 是否计算面积
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
									// int cellType = cell.getCellType();
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
									// case 11:
									// cell.setCellType(cell.CELL_TYPE_STRING);
									// bizOrderMainMate.setRemarks(cell.getStringCellValue()
									// + "");
									// break;
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
				// 批量处理申请数量
				bizOrderMainMateService.insertpurchaseCount(Integer.parseInt(orderId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 
	 * @Title: insertOrderMainMate
	 * @Description: TODO
	 * @param @param orderId订单ID
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "insertOrderMainMate")
	public String insertOrderMainMate(String orderId, RedirectAttributes redirectAttributes, Model model) {
		// 根据订单ID查询选材单ID
		BizMaterialsChoiceBill findChoiceBillId = projectOrderListService.findChoiceBillId(Integer.parseInt(orderId));
		List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
		if (findChoiceBillId != null && findChoiceBillId.getId() != null) {
			projectOrderListService.updateStatusWall(WallfloorConstant.WAll_FLOOR_ISTRUE5, Integer.parseInt(orderId));

			// 先真删除orderId下的所有数据
			bizOrderMainMateService.deleteByOrderId(Integer.parseInt(orderId));

			// 2.选材清单--墙地砖信息
			// BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new
			// BizMaterialsChoiceBillItem();
			// bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(findChoiceBillId.getId());
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
					// ConstantUtils.FLOOR_BRICK_NUMBER_STRING31
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
				// bizOrderMainMate.setRemarks(bizMaterialsChoiceBillItem.getStringCellValue());
				bizOrderMainMate.setCreateBy(UserUtils.getUser());
				bizOrderMainMate.setCreateDate(new Date());
				bizOrderMainMate.setUpdateBy(UserUtils.getUser());
				bizOrderMainMate.setUpdateDate(new Date());
				bizOrderMainMateService.insert(bizOrderMainMate);
			}
			// 批量处理申请数量
			bizOrderMainMateService.insertpurchaseCount(Integer.parseInt(orderId));
			addMessage(redirectAttributes, "导入了" + materialsChoiceList.size() + "条墙地砖数据");
			// model.addAttribute("tiaoshu", materialsChoiceList.size());

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

			// WallRecheck
			// String date = DateUtils.getDate("2017-09-08 11:59:59");
			Date date = DateUtils.parseDate("2017-09-08 11:59:59");
			// String formatDateTime = DateUtils.formatDateTime(date);
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
	// /ordermainmate/bizOrderMainMate/list
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