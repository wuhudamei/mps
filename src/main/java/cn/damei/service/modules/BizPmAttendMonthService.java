
package cn.damei.service.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.BizPmAttendMonthDao;
import cn.damei.entity.modules.BizPmAttendMonth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(readOnly = true)
public class BizPmAttendMonthService extends CrudService<BizPmAttendMonthDao, BizPmAttendMonth> {
	
	@Autowired
	private BizPmAttendMonthDao bizPmAttendMonthDao;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BizQcBillService bizQcBillService;
	
	public BizPmAttendMonth get(String id) {
		return super.get(id);
	}
	
	public List<BizPmAttendMonth> findList(BizPmAttendMonth bizPmAttendMonth) {
		return super.findList(bizPmAttendMonth);
	}
	
	public Page<BizPmAttendMonth> findPage(Page<BizPmAttendMonth> page, BizPmAttendMonth bizPmAttendMonth) {
		return super.findPage(page, bizPmAttendMonth);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmAttendMonth bizPmAttendMonth) {
		super.save(bizPmAttendMonth);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmAttendMonth bizPmAttendMonth) {
		super.delete(bizPmAttendMonth);
	}
	
	public List<BizPmAttendMonth> getBizPmAttendMonthList(BizPmAttendMonth bizPmAttendMonth) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<BizPmAttendMonth> newbizPmAttendMonth = bizPmAttendMonthDao.getOrderList(bizPmAttendMonth);
		List<BizPmAttendMonth> newbizPmAttendMonth1 = new ArrayList<>();
		List<BizPmAttendMonth> oldManager = bizPmAttendMonthDao.findOldManagerOrder(bizPmAttendMonth.getItemManagerId());

		if (oldManager.size() > 0) {
			for (BizPmAttendMonth old : oldManager) {
				String ss = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(old.getChangeUpdateDate()).substring(0, 7);
				int resultChange = ss.compareTo(bizPmAttendMonth.getAttendMonth());
				if (resultChange == 0) {
					old = bizPmAttendMonthDao.getOldOrder(old.getOrderId());
					newbizPmAttendMonth1.add(old);
				}
				int yy=0;
				int kk=0;
				if(null!=old.getChangeUpdateDate()){
					yy= Integer.parseInt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(old.getChangeUpdateDate()).substring(5, 7));
					kk=Integer.parseInt(bizPmAttendMonth.getAttendMonth().substring(5, 7));
				}
				
				if(yy-kk==1){
					old = bizPmAttendMonthDao.getOldOrder(old.getOrderId());
					newbizPmAttendMonth1.add(old);
				}
			}
		}
		for (BizPmAttendMonth orderMonth : newbizPmAttendMonth) {
			if (null != orderMonth.getAcDate()) {
				String yuefen = sdf.format(orderMonth.getAcDate()).substring(0, 7);
				int result = yuefen.compareTo(bizPmAttendMonth.getAttendMonth());
				if (result == 0) {
					newbizPmAttendMonth1.add(orderMonth);
				} else if (result > 0) {
					newbizPmAttendMonth1.add(orderMonth);
				}
			} else {
				String kaigong = sdf.format(orderMonth.getActualStartDate()).substring(0, 7);
				int resultkaigong = kaigong.compareTo(bizPmAttendMonth.getAttendMonth());
				if (resultkaigong <= 0) {
					newbizPmAttendMonth1.add(orderMonth);
				}
			}
		}


		BizPmAttendMonth qdcs = bizPmAttendMonthDao.getQdcs(bizPmAttendMonth);

		Date currunt=new Date();
		String curruntMonth=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(currunt).substring(0, 7);
		for (BizPmAttendMonth bizAttendMonth : newbizPmAttendMonth1) {

			String changeManager = bizPmAttendMonthDao.changeManager(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),bizPmAttendMonth.getAttendMonth());
			String changeManagerNextMonth = bizPmAttendMonthDao.changeManager(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),curruntMonth);
			String changeManagerMonth = null;
			String changeManagerOld = bizPmAttendMonthDao.changeManagerOld(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),bizPmAttendMonth.getAttendMonth());
			String changeManagerOldNextMonth = bizPmAttendMonthDao.changeManagerOld(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),curruntMonth);
			String currentExist = null;
			if(null!=bizPmAttendMonthDao.changeManager(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),curruntMonth)){
				currentExist=bizPmAttendMonthDao.changeManager(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),curruntMonth);
			}
			if(null!=bizPmAttendMonthDao.changeManagerOld(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),curruntMonth)){
				currentExist=bizPmAttendMonthDao.changeManagerOld(bizAttendMonth.getOrderId(), bizPmAttendMonth.getItemManagerId(),curruntMonth);
			}
			bizAttendMonth.setAttendStartDate(bizPmAttendMonth.getAttendStartDate());
			bizAttendMonth.setAttendEndDate(bizPmAttendMonth.getAttendEndDate());

			String zcOutTime = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(bizAttendMonth.getOrderId()), "9");
			String zcMonth = null;
			if (zcOutTime != null) {
				zcMonth = zcOutTime.substring(0, 7);
			}

			String jzOutTime = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(bizAttendMonth.getOrderId()), "6");
			String jzMonth = null;
			if (jzOutTime != null) {
				jzMonth = jzOutTime.substring(0, 7);
			}

			int jzStartTime = 0;

			int jzEndTime = 0;

			int jzCountDay = 0;

			int zcCountDay = 0;

			if (null != bizAttendMonth.getActualStartDate()) {
				if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
					jzStartTime = bizPmAttendMonth.getAttendStartDate().getDate();
				} else {
					jzStartTime = bizAttendMonth.getActualStartDate().getDate();
				}
			}

			if (StringUtils.isEmpty(zcOutTime) && StringUtils.isEmpty(jzOutTime)) {
				if (null != changeManager || null != changeManagerOld) {

					if (null != changeManager) {
						changeManagerMonth = changeManager.substring(0, 7);
					}
					if (null != changeManagerOld) {
						changeManagerMonth = changeManagerOld.substring(0, 7);
					}
					String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
					int result = changeManagerMonth.compareTo(dangqianMonth);
					if (result == 0) {

						if(null!=bizAttendMonth.getActualStartDate()){

							if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
								int changeManagerDay = 0;

								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
										zcCountDay = 0;
									} else {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizAttendMonth.getActualStartDate().getDate();
										zcCountDay = 0;
									}
								}

								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = changeManagerDay - bizAttendMonth.getActualStartDate().getDate();
										zcCountDay = 0;
									} else {
										jzCountDay = 0;
										zcCountDay = 0;
									}
								}

							} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
								int changeManagerDay = 0;

								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = 0;
									} else {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = 0;
									}
								}

								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									} else {
										jzCountDay = 0;
										zcCountDay = 0;
									}
								}
							}

						}else {
							jzCountDay=0;
							zcCountDay=0;
						}
					}
				} else {

					if(null==currentExist){
						jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
						jzCountDay = jzEndTime - jzStartTime+1;
						zcCountDay = 0;
					}

					else{

						if (null != changeManagerOldNextMonth) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							jzCountDay = jzEndTime - jzStartTime;
							zcCountDay = 0;
						}

						if (null != changeManagerNextMonth) {
							jzCountDay = 0;
							zcCountDay = 0;
						}
					}
				}
				

			} else if (StringUtils.isEmpty(zcOutTime)&& !StringUtils.isEmpty(jzOutTime)) {

				String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);

				if (null != changeManager) {
					changeManagerMonth = changeManager.substring(0, 7);
				}
				if (null != changeManagerOld) {
					changeManagerMonth = changeManagerOld.substring(0, 7);
				}

				if (null != changeManager || null != changeManagerOld) {

					int result = changeManagerMonth.compareTo(dangqianMonth);
					if (null != bizAttendMonth.getActualStartDate()) {

						if (result == 0) {

							if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()&&dangqianMonth.equals(jzMonth)){
								int changeManagerDay = 0;

								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = 0;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
									}
								}

								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = changeManagerDay - bizAttendMonth.getActualStartDate().getDate()+1;
										zcCountDay = 0;
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
										zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate()+1;
									}
								}

							}else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&dangqianMonth.equals(jzMonth)){
								int changeManagerDay = 0;

								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = 0;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
									}
								}

								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = changeManagerDay - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = 0;
									}

									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
										zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate();
									}
								}
							}

							else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&& !dangqianMonth.equals(jzMonth)){
								int changeManagerDay = 0;

								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
									jzCountDay = 0;
									zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
								}

								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
									jzCountDay = 0;
									zcCountDay = changeManagerDay - bizPmAttendMonth.getAttendStartDate().getDate()+1;
								}
							}
							
						}
					}

				} else {

					if(null==currentExist){

						if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
							zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();

						} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
							zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();

						} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && bizPmAttendMonth.getAttendEndDate().getTime()<new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() ) {
							jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
							zcCountDay = 0;

						}else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && bizPmAttendMonth.getAttendStartDate().getTime()>new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() ) {
							jzCountDay = 0;
							zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
						}
					}
					else{

						if (null != changeManagerOldNextMonth) {

							if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();

							} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();

							} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && !dangqianMonth.equals(jzMonth)) {
								jzCountDay = 0;
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
							}
						}

						if (null != changeManagerNextMonth) {
							jzCountDay = 0;
							zcCountDay = 0;
						}
					}
				}

			} else if (!StringUtils.isEmpty(zcOutTime) && !StringUtils.isEmpty(jzOutTime)) {
				String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
				if (null != changeManager || null != changeManagerOld) {

					if (null != changeManager) {
						changeManagerMonth = changeManager.substring(0, 7);
					}
					if (null != changeManagerOld) {
						changeManagerMonth = changeManagerOld.substring(0, 7);
					}
					int result = changeManagerMonth.compareTo(dangqianMonth);

					if (result == 0) {

						if (jzMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;

							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - changeManagerDay;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
							}

							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = changeManagerDay - bizAttendMonth.getActualStartDate().getDate()+1;
									zcCountDay = 0;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
									zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate()+1;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
							}
							

						} else if (jzMonth.equals(zcMonth) && jzMonth.equals(dangqianMonth)&&bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;

							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - changeManagerDay;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
							}

							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = changeManagerDay - bizPmAttendMonth.getAttendStartDate().getDate()+1;
									zcCountDay = 0;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
									zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate()+1;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
							}

						}else if (dangqianMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;

							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay =0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() -bizPmAttendMonth.getAttendStartDate().getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - changeManagerDay;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
							}

							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay =0;
									zcCountDay = changeManagerDay -bizPmAttendMonth.getAttendStartDate().getDate() +1;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay =0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
								}
							}

						}else if(dangqianMonth.equals(jzMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()){
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;

							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate()-changeManagerDay;
									zcCountDay =jzEndTime-sdf.parse(jzOutTime).getDate();
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() >new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay =jzEndTime-changeManagerDay;
								}
							}
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = changeManagerDay;
									zcCountDay =sdf.parse(jzOutTime).getDate()-changeManagerDay;
								}

								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() >new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate();
									zcCountDay =changeManagerDay-sdf.parse(jzOutTime).getDate();
								}
							}

						}else if(bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()){
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;

							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
								jzCountDay = 0;
								zcCountDay =jzEndTime-changeManagerDay;
							}
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
								jzCountDay =0;
								zcCountDay =changeManagerDay;
							}

						}else if(sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&& bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()){
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;

							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
								jzCountDay = jzEndTime-changeManagerDay;
								zcCountDay =0;
							}
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
								jzCountDay = changeManagerDay;
								zcCountDay =0;
							}
						}
					}

				} else {

					if(null==currentExist){

						if (jzMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
							zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();

						} else if (jzMonth.equals(zcMonth) && jzMonth.equals(dangqianMonth)&& bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
							zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();

						} else if (dangqianMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzCountDay = 0;
							zcCountDay = sdf.parse(zcOutTime).getDate() -bizPmAttendMonth.getAttendStartDate().getDate()+1;

						}else if(bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && bizPmAttendMonth.getAttendEndDate().getTime()<new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() ){
							jzCountDay=bizPmAttendMonth.getAttendEndDate().getDate()-bizPmAttendMonth.getAttendStartDate().getDate();
							zcCountDay=0;

						}else if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() < bizPmAttendMonth.getAttendStartDate().getTime()  && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()){
							jzCountDay=0;
							zcCountDay=bizPmAttendMonth.getAttendEndDate().getDate()-bizPmAttendMonth.getAttendStartDate().getDate()+1;

						}else if(jzMonth.equals(dangqianMonth) && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime() >bizPmAttendMonth.getAttendEndDate().getTime()&& bizAttendMonth.getActualStartDate().getTime()<bizPmAttendMonth.getAttendStartDate().getTime()){
							jzCountDay=bizPmAttendMonth.getAttendEndDate().getDate()-bizPmAttendMonth.getAttendStartDate().getDate();
							zcCountDay=0;
						}
						
					}else{

						if (null != changeManagerOldNextMonth) {

							if (jzMonth.equals(zcMonth) && jzMonth.equals(dangqianMonth)&&bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
								zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();

							} else if (jzMonth.equals(dangqianMonth)&& zcMonth.equals(dangqianMonth)  && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
								zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();

							} else if (dangqianMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = 0;
								zcCountDay = sdf.parse(zcOutTime).getDate() -bizPmAttendMonth.getAttendStartDate().getDate();
							}

							else if (dangqianMonth.equals(jzMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate()-sdf.parse(jzOutTime).getDate();
							}

							else if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()&&bizAttendMonth.getActualStartDate().getTime()< bizPmAttendMonth.getAttendEndDate().getTime() &&sdf.parse(jzOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate()-sdf.parse(jzOutTime).getDate();
							}

							else if (dangqianMonth.equals(jzMonth) && bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()&&bizAttendMonth.getActualStartDate().getTime()< bizPmAttendMonth.getAttendEndDate().getTime() &&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate()-sdf.parse(jzOutTime).getDate();
							}

							else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = 0;
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate();
							}

							else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&&sdf.parse(jzOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate();
								zcCountDay = 0;
							}
						}

						if (null != changeManagerNextMonth) {
							jzCountDay = 0;
							zcCountDay = 0;
						}
					}
					
				}
			}

			int i = 0;
			if (qdcs != null && qdcs.getSignCycleDaysBasicwork() != 0) {
				i = jzCountDay / qdcs.getSignCycleDaysBasicwork();
			}

			int j = 0;
			if (qdcs != null && qdcs.getSignCycleDaysComplete() != 0) {
				j = zcCountDay / qdcs.getSignCycleDaysComplete();
			}

			bizAttendMonth.setMustSignTimes(String.valueOf(i + j));
			int signDateTimesCount = 0;
			int actualValue = 0;
			Date date = null;


            if (null != changeManager || null != changeManagerOld) {

                if (null != changeManager) {
                    changeManagerMonth = changeManager.substring(0, 7);
                }

                if (null != changeManagerOld) {
                    changeManagerMonth = changeManagerOld.substring(0, 7);
                }
                String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
                int result = changeManagerMonth.compareTo(dangqianMonth);

                if (result == 0) {
                    SimpleDateFormat changesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (null != changeManager) {
                        signDateTimesCount = bizPmAttendMonthDao.getSignDateTimesChangeManager(bizPmAttendMonth.getAttendEndDate(), bizAttendMonth.getOrderId(), changesdf.parse(changeManager), bizPmAttendMonth.getItemManagerId());
                    }
                    if (null != changeManagerOld) {
                        signDateTimesCount = bizPmAttendMonthDao.getSignDateTimesChangeManager(changesdf.parse(changeManagerOld), bizAttendMonth.getOrderId(), bizPmAttendMonth.getAttendStartDate(), bizPmAttendMonth.getItemManagerId());
                    }
                }
            } else if (null == changeManager && null == changeManagerOld) {
                signDateTimesCount = bizPmAttendMonthDao.getSignDateTimesCount(bizAttendMonth);
            }

            if (null == currentExist && null == changeManager && null == changeManagerOld) {
                signDateTimesCount = bizPmAttendMonthDao.getSignDateTimesCount(bizAttendMonth);
            }
            if (null != currentExist) {

                SimpleDateFormat changesdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (null != changeManagerOldNextMonth) {
                    signDateTimesCount = bizPmAttendMonthDao.getSignDateTimesCount(bizAttendMonth);
                } else {
                    signDateTimesCount = 0;
                }
            }

            if (null != bizAttendMonth.getMustSignTimes()) {
                if (signDateTimesCount > Integer.valueOf(bizAttendMonth.getMustSignTimes()).intValue()) {
                    actualValue = Integer.valueOf(bizAttendMonth.getMustSignTimes()).intValue();
                } else {
                    actualValue = signDateTimesCount;
                }

            }
            bizAttendMonth.setRealSignTimes(String.valueOf(signDateTimesCount));

            bizAttendMonth.setActualValue(String.valueOf(actualValue));
        }
        return newbizPmAttendMonth1;
    }


    public List<BizPmAttendMonth> getBizPmAttendMonthCount(BizPmAttendMonth bizPmAttendMonth) {

        return bizPmAttendMonthDao.getBizPmAttendMonthCount(bizPmAttendMonth);
    }

    public Page<BizPmAttendMonth> findPmAttendList(Page<BizPmAttendMonth> page, BizPmAttendMonth bizPmAttendMonth) {
        bizPmAttendMonth.setPage(page);
        page.setList(bizPmAttendMonthDao.findPmAttendList(bizPmAttendMonth));
        return page;
    }

    public List<BizPmAttendMonth> getBizMonthSalary(Page<BizPmAttendMonth> page, BizPmAttendMonth bizPmAttendMonth) {
        return bizPmAttendMonthDao.getBizMonthSalary(bizPmAttendMonth);
    }

    @Transactional(readOnly = false)
    public void updatePmAttendMonthStatus(BizPmAttendMonth bizPmAttendMonth) {
        bizPmAttendMonthDao.updatePmAttendMonthStatus(bizPmAttendMonth);
    }

    public BizPmAttendMonth bizPmAttendSalaryBillDetail(BizPmAttendMonth bizPmAttendMonth) {

        return bizPmAttendMonthDao.bizPmAttendSalaryBillDetail(bizPmAttendMonth);
    }


    public static int differentDaysByMillisecond(Date date1, Date date2) {

        int days = (int) (date2.getTime() - date1.getTime());
        if (days > 0) {
            return days / (1000 * 3600 * 24);
        } else {
            return 0;
        }
    }

    @Transactional(readOnly = false)
    public void saveMonthOrder(BizPmAttendMonth bizPmAttendMonth) {
        String[] mustSignTimes = bizPmAttendMonth.getMustSignTimes2().split(",");
        String[] realSignTimes = bizPmAttendMonth.getRealSignTimes2().split(",");
        String[] owedSginTime = bizPmAttendMonth.getOwedSginTime2().split(",");
        String[] orderId = bizPmAttendMonth.getOrderId2().split(",");
        String[] actualValueInput=bizPmAttendMonth.getActualValueInput().split(",");


        for (int i = 0; i < mustSignTimes.length; i++) {
            bizPmAttendMonth.setMustSignTimes(mustSignTimes[i]);
            bizPmAttendMonth.setRealSignTimes(realSignTimes[i]);
            bizPmAttendMonth.setOwedSginTime(owedSginTime[i]);
            bizPmAttendMonth.setOrderId(orderId[i]);
            bizPmAttendMonth.setActualValue(actualValueInput[i]);
            bizPmAttendMonth.preInsert();


            bizPmAttendMonthDao.insertMonthOrder(bizPmAttendMonth);


        }
    }

    @Transactional(readOnly = false)
    public void updatePmAttendMonth(BizPmAttendMonth bizPmAttendMonth) {
        bizPmAttendMonth.preUpdate();
        bizPmAttendMonthDao.updatePmAttendMonth(bizPmAttendMonth);
    }

    public BizPmAttendMonth findAttendMonth(BizPmAttendMonth bizPmAttendMonth) {
        return bizPmAttendMonthDao.findAttendMonth(bizPmAttendMonth);
    }

    public Page<BizPmAttendMonth> findPmAttendDetailList(Page<BizPmAttendMonth> page,
                                                         BizPmAttendMonth bizPmAttendMonth) {
        bizPmAttendMonth.setPage(page);
        page.setList(bizPmAttendMonthDao.findPmAttendDetailList(bizPmAttendMonth));
        return page;
    }

    public List<BizPmAttendMonth> getDetailFormList(String attendMonth, String itemManagerId, String date, String status) {
        return bizPmAttendMonthDao.getDetailFormList(attendMonth, itemManagerId, date, status);
    }

    @Transactional(readOnly = false)
    public void deleteMonth(BizPmAttendMonth bizPmAttendMonth) {
        bizPmAttendMonthDao.deleteMonth(bizPmAttendMonth);

    }

    public BizPmAttendMonth findAttendMonthOrder(BizPmAttendMonth bizPmAttendMonth) {
        return bizPmAttendMonthDao.findAttendMonthOrder(bizPmAttendMonth);
    }

    public String getOrderInsertDate(BizPmAttendMonth bizPmAttendMonth) {
        return bizPmAttendMonthDao.getOrderInsertDate(bizPmAttendMonth);
    }
    @Transactional(readOnly = false)
    public void insertOrderManagerId(){
        bizPmAttendMonthDao.insertOrderManagerId();
    }


    public List<Map<String, Object>> getStartDateTimeAndEndDate(String managerId, String kqMonth, String orderId, Date startDate, Date endDate) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        String zcOutTimeStr = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(orderId), ConstantUtils.MANAGER_ATTEND_ZC_NODE);
        Date zcOutTime = DateUtils.parseDate(zcOutTimeStr);

        String jzOutTimeStr = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(orderId), ConstantUtils.MANAGER_ATTEND_JZ_NODE);
        Date jzOutTime = DateUtils.parseDate(jzOutTimeStr);

        Date orderStartDate = bizPmAttendMonthDao.getOrderStartDate(orderId);

        List<BizPmAttendMonth> managerDetail = bizPmAttendMonthDao.findChangeManagerDetail(orderId, kqMonth);

        if (managerDetail.size() > 0) {

            List<Map<String, Object>> listMap = changeManager(orderId, kqMonth, managerId, startDate, endDate, managerDetail,orderStartDate);
            for (int i = 0; i < listMap.size(); i++) {
                Map<String, Object> map = listMap.get(i);
                startDate = (Date) map.get("startDate");
                endDate = (Date) map.get("endDate");
                if("frist".equals((String)map.get("type"))){

                    Map<String, Object> jzdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_JZ, startDate, endDate, orderStartDate, jzOutTime,1);

                    Map<String, Object> zcdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_ZC, startDate, endDate,jzOutTime, zcOutTime,1);
                    List<Map<String,Object>> checkedDatelistMap = checkedDate(jzdate,zcdate);
                    list.addAll(checkedDatelistMap);
                }else{

                    Map<String, Object> jzdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_JZ, startDate, endDate, orderStartDate, jzOutTime,1);

                    Map<String, Object> zcdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_ZC, startDate, endDate,jzOutTime, zcOutTime,1);
                    list.add(jzdate);
                    list.add(zcdate);
                }
            }
        } else {

             Map<String, Object> jzdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_JZ, startDate, endDate, orderStartDate, jzOutTime,0);

            Map<String, Object> zcdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_ZC, startDate, endDate, jzOutTime, zcOutTime,0);
            List<Map<String,Object>> listMap = checkedDate(jzdate,zcdate);
            list.addAll(listMap);
        }
        return list;
    }



    public Map<String, Object> getDate(String orderId, String managerId, String type, Date startDate, Date endDate, Date orderStartDate, Date jzOutTime,Integer changeManagerType) {

        Map<String, Object> jzStart = new HashMap<String, Object>();
        jzStart.put("orderId", orderId);
        jzStart.put("type", type);

        if (orderStartDate != null) {
            if (startDate.getTime() >= orderStartDate.getTime()) {
                jzStart.put("startDate", startDate);
            } else {
                jzStart.put("startDate", orderStartDate);
            }

            if (jzOutTime == null || endDate.getTime() <= jzOutTime.getTime()) {
                jzStart.put("endDate", endDate);
            } else {
                jzStart.put("endDate", jzOutTime);
            }
        } else {
            jzStart.put("startDate", startDate);
            jzStart.put("endDate", startDate);
        }
        return jzStart;
    }


    public int getCountDay(BizPmAttendMonth bizPmAttendMonth) {
        String managerId = bizPmAttendMonth.getItemManagerId();
        String kqMonth = bizPmAttendMonth.getAttendMonth();
        String orderId = bizPmAttendMonth.getOrderId();
        Date startDate = bizPmAttendMonth.getAttendStartDate();
        Date endDate = bizPmAttendMonth.getAttendEndDate();

        if (bizPmAttendMonth == null || managerId == null || kqMonth == null || orderId == null || startDate == null || endDate == null) {
            return 0;
        }

        List<Map<String, Object>> list = getStartDateTimeAndEndDate(managerId, kqMonth, orderId, startDate, endDate);

        double jz = 0;
        double zc = 0;
        for (Map<String, Object> map : list) {
            Date mapStartDate = (Date) map.get("startDate");
            Date mapEndDate = (Date) map.get("endDate");
            double days = DateUtils.daysBetween(mapStartDate, mapEndDate);
            if(days < 0){
                days = 0;
            }
            if (ConstantUtils.MANAGER_ATTEND_JZ.equals(map.get("type"))) {
                jz += days;
            } else {
                zc += days;
            }
        }

        BizPmAttendMonth qdcs = bizPmAttendMonthDao.getQdcs(bizPmAttendMonth);
        int jzTimes = 0;
        if (qdcs != null && qdcs.getSignCycleDaysBasicwork() != 0) {
            jzTimes = (int) jz / qdcs.getSignCycleDaysBasicwork();
        }

        int zcTimes = 0;
        if (qdcs != null && qdcs.getSignCycleDaysComplete() != 0) {
            zcTimes = (int) zc / qdcs.getSignCycleDaysComplete();
        }
        return jzTimes + zcTimes;

    }


    public List<Map<String, Object>> changeManager(String orderId, String kqMonth, String managerId, Date startDate, Date endDate, List<BizPmAttendMonth> managerDetail,Date orderStartDate) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        String strOrderStartDate = DateUtils.formatDate(orderStartDate,"yyyy-MM");
        for (int i = 0; i < managerDetail.size(); i++) {
            BizPmAttendMonth bm = managerDetail.get(i);
            Map<String, Object> bmmap = new HashMap<>();

            if (i == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("empId", bm.getOldEmployeeId());
                if(strOrderStartDate.equals(kqMonth) && orderStartDate.getTime() < managerDetail.get(i).getChangeUpdateDate().getTime()){

                    if(startDate.getTime() > orderStartDate.getTime()){
                        map.put("startDate", DateUtils.addDate(startDate,-1));
                    }else{
                        map.put("startDate", orderStartDate);
                    }
                }else{
                    map.put("startDate", startDate);
                }

                map.put("endDate",bm.getChangeUpdateDate());
                map.put("type","frist");
                if (bm.getOldEmployeeId().equals(managerId)) {
                    listMap.add(map);
                }
            }
            bmmap.put("empId", bm.getPmEmployeeId());

            if(startDate.getTime() > bm.getChangeUpdateDate().getTime()){
                bmmap.put("startDate", DateUtils.addDate(startDate,-1));
            }else{
                bmmap.put("startDate", bm.getChangeUpdateDate());
            }
            if ((i + 1) < managerDetail.size()) {
                bmmap.put("endDate",managerDetail.get(i + 1).getChangeUpdateDate());
            } else {
                bmmap.put("endDate", endDate);
            }
            if (bm.getPmEmployeeId().equals(managerId)) {
                listMap.add(bmmap);
            }
        }
        return listMap;
    }


    public List<BizPmAttendMonth> findBizPmAttendMonthList(BizPmAttendMonth bizPmAttendMonth) {
        List<BizPmAttendMonth> newbizPmAttendMonth = new ArrayList<>();

        List<BizPmAttendMonth> managerOrder = bizPmAttendMonthDao.getOrderListByManagerId(bizPmAttendMonth);

        List<BizPmAttendMonth> changeManagerOrder = bizPmAttendMonthDao.findChangeManagerList(bizPmAttendMonth);
        managerOrder.addAll(changeManagerOrder);

        Map<String,BizPmAttendMonth> map = new HashMap<>();
        for (BizPmAttendMonth order : managerOrder) {

            bizPmAttendMonth.setOrderId(order.getOrderId());

            int count = getCountDay(bizPmAttendMonth);
            order.setMustSignTimes(String.valueOf(count));



            Integer realSignTimes = bizPmAttendMonthDao.getRealSignTimes(bizPmAttendMonth);
            if (realSignTimes == null) {
                realSignTimes = 0;
            }
            order.setRealSignTimes(String.valueOf(realSignTimes));

            Integer actualValue = 0;
            if (count > 0) {
                if (realSignTimes > count) {
                    actualValue = count;
                } else {
                    actualValue = realSignTimes;
                }
            }
            order.setActualValue(String.valueOf(actualValue));
            map.put(order.getOrderId(),order);
        }
        for(String key : map.keySet()){
            BizPmAttendMonth value = map.get(key);
            value.setAttendStartDate(bizPmAttendMonth.getAttendStartDate());
            value.setAttendEndDate(bizPmAttendMonth.getAttendEndDate());
            Boolean boo = bizPmAttendMonthDao.isCheckedOrder(value);
            if(boo){
                newbizPmAttendMonth.add(value);
            }
        }
        return newbizPmAttendMonth;
    }


    public List<Map<String, Object>> checkedDate(Map<String, Object> jzmap,Map<String, Object> zcmap){
        List<Map<String, Object>> mapList = new ArrayList<>();
        Date mapStartDate = (Date) jzmap.get("startDate");
        Date mapEndDate = (Date) jzmap.get("endDate");
        Date zcmapEndDate = (Date)zcmap.get("endDate");
        Long tiem = mapEndDate.getTime() - mapStartDate.getTime();
        if(tiem > 0){
            jzmap.put("endDate",DateUtils.addDate(mapEndDate,1));
        }else {
            zcmap.put("endDate",DateUtils.addDate(zcmapEndDate,1));
        }
        mapList.add(jzmap);
        mapList.add(zcmap);
        return mapList;
    }

    public String findProjectModeByManagerId(String itemManagerId) {
    	return bizPmAttendMonthDao.findProjectModeByManagerId(itemManagerId);
    }

	public BizPmAttendMonth findSalaryAllAttend(String findProjectMode, String storeId, Integer itemManagerStar) {
		return bizPmAttendMonthDao.findSalaryAllAttend(findProjectMode,storeId,itemManagerStar);
	}
}
