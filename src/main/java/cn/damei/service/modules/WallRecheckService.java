
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.WallFloorTileRecheckConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.RecheckCnfgDao;
import cn.damei.dao.modules.WallRecheckDao;
import cn.damei.entity.modules.RecheckCnfg;
import cn.damei.entity.modules.WallRecheck;
import cn.damei.dao.modules.OrderDao;
import cn.damei.common.utils.SavelogUtils;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class WallRecheckService extends CrudService2<WallRecheckDao, WallRecheck> {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private RecheckCnfgDao recheckCnfgDao;

	public WallRecheck get(Integer id) {
		return super.get(id);
	}

	public List<WallRecheck> findList(WallRecheck wallRecheck) {
		return super.findList(wallRecheck);
	}

	public Page<WallRecheck> findPage(Page<WallRecheck> page, WallRecheck wallRecheck) {
		wallRecheck.setPage(page);
		List<RecheckCnfg> findAllList2 = recheckCnfgDao.findAllListz();
		List<WallRecheck> findAllList = dao.findListall(wallRecheck);


		Double squareActual = 0.00;
		if (null != findAllList && findAllList.size() > 0) {
			for (int i = 0; i < findAllList.size(); i++) {
				findAllList.get(i).setIncrease((i + 1) + "");
				findAllList.get(i).setOrderinspector(UserUtils.getUser().getLoginName());

				if (null != findAllList.get(i).getStatus()) {
					squareActual = dao.querySquate(findAllList.get(i).getOrderId());
					if ((findAllList.get(i).getStatus().equals("10")) || (findAllList.get(i).getStatus().equals("40")) || (findAllList.get(i).getStatus().equals("20"))) {
						if (null == squareActual) {
							WallRecheck squareActua = dao.findSquareActualtow(findAllList.get(i).getOrderId());
							findAllList.get(i).setSquareMeasure(squareActua.getSquareMeasure());
							findAllList.get(i).setRealMeasureDate(squareActua.getPlanMeasureDate());
						}
					}
				}
			}
		}



		if (null != findAllList2 && findAllList2.size() > 0) {
			for (WallRecheck wallRecheck2 : findAllList) {

				wallRecheck2.setPrice(Double.parseDouble(findAllList2.get(0).getPrice()));

				wallRecheck2.setAssessSquareError1(Double.parseDouble(String.format("%.2f", (wallRecheck2.getSquareMeasure() == null ? 0 : wallRecheck2.getSquareMeasure()) - (wallRecheck2.getSquareBudget() == null ? 0 : wallRecheck2.getSquareBudget()))));
				wallRecheck2.setAssessSquareError2(Double.parseDouble(String.format("%.2f", (wallRecheck2.getSquarePurchase() == null ? 0 : wallRecheck2.getSquarePurchase()) - (wallRecheck2.getSquareMeasure() == null ? 0 : wallRecheck2.getSquareMeasure()))));
				String format = String.format("%.2f", (wallRecheck2.getAssessSquareError1() == null ? 0 : wallRecheck2.getAssessSquareError1()) * (wallRecheck2.getPrice() == null ? 0 : wallRecheck2.getPrice()));

				wallRecheck2.setAssessAmountString1(format);
				wallRecheck2.setAssessAmount1(Double.parseDouble(format));

				String format2 = String.format("%.2f", (wallRecheck2.getAssessSquareError2() == null ? 0 : wallRecheck2.getAssessSquareError2()) * (wallRecheck2.getPrice() == null ? 0 : wallRecheck2.getPrice()));

				wallRecheck2.setAssessAmountString2(format2);
				wallRecheck2.setAssessAmount2(Double.parseDouble(format2));

			}
		}

		page.setList(findAllList);
		return page;
	}

	public Page<WallRecheck> findWallRecheckPage(Page<WallRecheck> page, WallRecheck wallRecheck) {
		wallRecheck.setPage(page);
		List<RecheckCnfg> findAllList2 = recheckCnfgDao.findAllListz();
		List<WallRecheck> findAllList = dao.findListall(wallRecheck);
		if (null != findAllList2 && findAllList2.size() > 0) {
			for (WallRecheck wallRecheck2 : findAllList) {

				wallRecheck2.setPrice(Double.parseDouble(findAllList2.get(0).getPrice()));

				wallRecheck2.setAssessSquareError1(Double.parseDouble(String.format("%.2f", (wallRecheck2.getSquareMeasure() == null ? 0 : wallRecheck2.getSquareMeasure()) - (wallRecheck2.getSquareBudget() == null ? 0 : wallRecheck2.getSquareBudget()))));
				wallRecheck2.setAssessSquareError2(Double.parseDouble(String.format("%.2f", (wallRecheck2.getSquarePurchase() == null ? 0 : wallRecheck2.getSquarePurchase()) - (wallRecheck2.getSquareMeasure() == null ? 0 : wallRecheck2.getSquareMeasure()))));
				String format = String.format("%.2f", (wallRecheck2.getAssessSquareError1() == null ? 0 : wallRecheck2.getAssessSquareError1()) * (wallRecheck2.getPrice() == null ? 0 : wallRecheck2.getPrice()));

				wallRecheck2.setAssessAmountString1(format);
				wallRecheck2.setAssessAmount1(Double.parseDouble(format));

				String format2 = String.format("%.2f", (wallRecheck2.getAssessSquareError2() == null ? 0 : wallRecheck2.getAssessSquareError2()) * (wallRecheck2.getPrice() == null ? 0 : wallRecheck2.getPrice()));

				wallRecheck2.setAssessAmountString2(format2);
				wallRecheck2.setAssessAmount2(Double.parseDouble(format2));

			}
		}
		Double squareActual = 0.00;
		if (null != findAllList && findAllList.size() > 0) {
			for (int i = 0; i < findAllList.size(); i++) {
				findAllList.get(i).setIncrease((i + 1) + "");
				findAllList.get(i).setOrderinspector(UserUtils.getUser().getLoginName());

				if (null != findAllList.get(i).getStatus()) {
					if ((findAllList.get(i).getStatus().equals("10")) || (findAllList.get(i).getStatus().equals("40")) || (findAllList.get(i).getStatus().equals("20"))) {
						WallRecheck wallRechecksw = dao.findWallRecheck(findAllList.get(i).getOrderId());
						findAllList.get(i).setSquareMeasure(wallRechecksw.getSquareActual());
					}
				}

			}
		}
		page.setList(findAllList);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(WallRecheck wallRecheck) {
		super.save(wallRecheck);
	}

	@Transactional(readOnly = false)
	public void delete(WallRecheck wallRecheck) {
		super.delete(wallRecheck);
	}

	@Transactional(readOnly = false)
	public void agreRecheckUpdate(WallRecheck wallRecheck) {

		dao.agreRecheckUpdate(wallRecheck);
		SavelogUtils.saveBusinessStatusLog(Integer.parseInt(UserUtils.getUser().getId()), wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_40, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_40);

	}

	public WallRecheck findbyid(WallRecheck wallRecheck) {
		WallRecheck recheck = new WallRecheck();
		recheck.setId(wallRecheck.getId());
		List<RecheckCnfg> findAllList2 = recheckCnfgDao.findAllListz();
		List<WallRecheck> findAllList = dao.findListall(recheck);
		WallRecheck wallRecheck2 = null;
		if (null != findAllList && findAllList.size() > 0) {

			wallRecheck2 = findAllList.get(0);
			wallRecheck2.setPrice(Double.parseDouble(findAllList2.get(0).getPrice()));
			wallRecheck2.setOrderinspector(UserUtils.getUser().getLoginName());

			wallRecheck2.setAssessSquareError1(Double.parseDouble(String.format("%.2f", (wallRecheck2.getSquareMeasure() == null ? 0 : wallRecheck2.getSquareMeasure()) - (wallRecheck2.getSquareBudget() == null ? 0 : wallRecheck2.getSquareBudget()))));
			wallRecheck2.setAssessSquareError2(Double.parseDouble(String.format("%.2f", (wallRecheck2.getSquarePurchase() == null ? 0 : wallRecheck2.getSquarePurchase()) - (wallRecheck2.getSquareMeasure() == null ? 0 : wallRecheck2.getSquareMeasure()))));
			String format = String.format("%.2f", (wallRecheck2.getAssessSquareError1() == null ? 0 : wallRecheck2.getAssessSquareError1()) * (wallRecheck2.getPrice() == null ? 0 : wallRecheck2.getPrice()));

			wallRecheck2.setAssessAmountString1(format);
			wallRecheck2.setAssessAmount1(Double.parseDouble(format));

			String format2 = String.format("%.2f", (wallRecheck2.getAssessSquareError2() == null ? 0 : wallRecheck2.getAssessSquareError2()) * (wallRecheck2.getPrice() == null ? 0 : wallRecheck2.getPrice()));

			wallRecheck2.setAssessAmountString2(format2);
			wallRecheck2.setAssessAmount2(Double.parseDouble(format2));
		}
		return wallRecheck2;
	}

	@Transactional(readOnly = false)
	public void agreRecheckUpdate1(WallRecheck wallRecheck) {
		dao.agreRecheckUpdate1(wallRecheck);
		SavelogUtils.saveBusinessStatusLog(Integer.parseInt(UserUtils.getUser().getId()), wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_65, wallRecheck.getStatusDescribe());

	}

	@Transactional(readOnly = false)
	public void agreRecheckUpdate2(WallRecheck wallRecheck) {
		dao.agreRecheckUpdate2(wallRecheck);
		SavelogUtils.saveBusinessStatusLog(Integer.parseInt(UserUtils.getUser().getId()), wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_60, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_60);

	}

	@Transactional(readOnly = false)
	public void NotagreRecheckUpdate2(WallRecheck wallRecheck) {
		dao.NotagreRecheckUpdate2(wallRecheck);
		SavelogUtils.saveBusinessStatusLog(Integer.parseInt(UserUtils.getUser().getId()), wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_30, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_30);

	}

	@Transactional(readOnly = false)
	public void updateExamine(WallRecheck wallRecheck) {
		dao.updateExamine(wallRecheck);
		SavelogUtils.saveBusinessStatusLog(Integer.parseInt(UserUtils.getUser().getId()), wallRecheck.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_2012, WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_70, wallRecheck.getRecheckRemarks());

	}

	public String getempName(int parseInt) {

		return dao.getempName(parseInt);
	}

}