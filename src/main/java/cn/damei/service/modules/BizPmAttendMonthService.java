/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 考勤月度表Service
 * @author wl
 * @version 2017-08-02
 */
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
//第一步：查询项目经理下的所有订单
		List<BizPmAttendMonth> newbizPmAttendMonth = bizPmAttendMonthDao.getOrderList(bizPmAttendMonth);
		List<BizPmAttendMonth> newbizPmAttendMonth1 = new ArrayList<>();
		List<BizPmAttendMonth> oldManager = bizPmAttendMonthDao.findOldManagerOrder(bizPmAttendMonth.getItemManagerId());
		//查询换项目经理之前的订单信息
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

//第二步：查询考勤月下的应签到次数 和 实际签到次数
		BizPmAttendMonth qdcs = bizPmAttendMonthDao.getQdcs(bizPmAttendMonth);
		//定义一个当前时间
		Date currunt=new Date();
		String curruntMonth=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(currunt).substring(0, 7);
		for (BizPmAttendMonth bizAttendMonth : newbizPmAttendMonth1) {
			//换项目经理查询更换日期
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
			//主材竣工时间
			String zcOutTime = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(bizAttendMonth.getOrderId()), "9");
			String zcMonth = null;
			if (zcOutTime != null) {
				zcMonth = zcOutTime.substring(0, 7);
			}
			//基装竣工时间
			String jzOutTime = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(bizAttendMonth.getOrderId()), "6");
			String jzMonth = null;
			if (jzOutTime != null) {
				jzMonth = jzOutTime.substring(0, 7);
			}
			//标记基装开始时间
			int jzStartTime = 0;
			//标记基装结束时间
			int jzEndTime = 0;
			//标记基装总天数
			int jzCountDay = 0;
			//标记主材总天数
			int zcCountDay = 0;
			//开工时间小于考勤开始时间
			if (null != bizAttendMonth.getActualStartDate()) {
				if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
					jzStartTime = bizPmAttendMonth.getAttendStartDate().getDate();
				} else {
					jzStartTime = bizAttendMonth.getActualStartDate().getDate();
				}
			}
			//1.首先判断基装竣工时间 和 主材竣工时间 为空
			if (StringUtils.isEmpty(zcOutTime) && StringUtils.isEmpty(jzOutTime)) {
				if (null != changeManager || null != changeManagerOld) {
					//截取项目经理变更日期的年月
					if (null != changeManager) {
						changeManagerMonth = changeManager.substring(0, 7);
					}
					if (null != changeManagerOld) {
						changeManagerMonth = changeManagerOld.substring(0, 7);
					}
					String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
					int result = changeManagerMonth.compareTo(dangqianMonth);
					if (result == 0) {
						//判断开工时间不为空
						if(null!=bizAttendMonth.getActualStartDate()){
							//如果开工时间不为空且在考勤月
							if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
								int changeManagerDay = 0;
								//新的项目经理
								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
									//如果换项目经理在开工之后
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
										zcCountDay = 0;
									} else {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizAttendMonth.getActualStartDate().getDate();
										zcCountDay = 0;
									}
								}
								//原来的项目经理
								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
									//如果换项目经理在开工之后
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = changeManagerDay - bizAttendMonth.getActualStartDate().getDate();
										zcCountDay = 0;
									} else {
										jzCountDay = 0;
										zcCountDay = 0;
									}
								}
								//如果开工时间不为空且不在考勤月
							} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
								int changeManagerDay = 0;
								//新的项目经理
								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
									//如果换项目经理在开工之后
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = 0;
									} else {
										jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = 0;
									}
								}
								//原来的项目经理
								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
									//如果换项目经理在开工之后
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									} else {
										jzCountDay = 0;
										zcCountDay = 0;
									}
								}
							}
							//开工时间为空
						}else {
							jzCountDay=0;
							zcCountDay=0;
						}
					}
				} else {
					//考勤的下一个月没换过项目经理
					if(null==currentExist){
						jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
						jzCountDay = jzEndTime - jzStartTime+1;
						zcCountDay = 0;
					}
					//考勤的下一个月换过项目经理
					else{
						//原来的项目经理
						if (null != changeManagerOldNextMonth) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							jzCountDay = jzEndTime - jzStartTime;
							zcCountDay = 0;
						}
						//现在的项目经理
						if (null != changeManagerNextMonth) {
							jzCountDay = 0;
							zcCountDay = 0;
						}
					}
				}
				
				//2.主材为空 基装不为空
			} else if (StringUtils.isEmpty(zcOutTime)&& !StringUtils.isEmpty(jzOutTime)) {
				//考勤月份
				String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
				//截取项目经理变更日期的年月
				if (null != changeManager) {
					changeManagerMonth = changeManager.substring(0, 7);
				}
				if (null != changeManagerOld) {
					changeManagerMonth = changeManagerOld.substring(0, 7);
				}
				//考勤月换过项目经理
				if (null != changeManager || null != changeManagerOld) {
					//开工时间不为空
					int result = changeManagerMonth.compareTo(dangqianMonth);
					if (null != bizAttendMonth.getActualStartDate()) {
						//如果项目经理变更月份与考勤月份相同
						if (result == 0) {
							//开工时间与基装时间在考勤月
							if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()&&dangqianMonth.equals(jzMonth)){
								int changeManagerDay = 0;
								//换之后的项目经理
								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
									//如果在开工之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}
									//如果在开工之后基装之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}
									//如果在基装结束之后换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = 0;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
									}
								}
								//原来的项目经理
								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
									//如果在开工之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									}
									//如果在开工之后基装之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = changeManagerDay - bizAttendMonth.getActualStartDate().getDate()+1;
										zcCountDay = 0;
									}
									//如果在基装之后换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
										zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate()+1;
									}
								}
								//开工时间与基装时间不在同一个月，考勤月只有基装时间
							}else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&dangqianMonth.equals(jzMonth)){
								int changeManagerDay = 0;
								//换之后的项目经理
								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
									//如果在开工之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}
									// 开工之后考勤月初之前
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}
									//如果在月初之后基装之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
									}
									//如果在基装结束之后换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = 0;
										zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
									}
								}
								//原来的项目经理
								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
									//如果在开工之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									}
									// 开工之后考勤月初之前
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
										jzCountDay = 0;
										zcCountDay = 0;
									}
									//如果在月初之后基装之前换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = changeManagerDay - bizPmAttendMonth.getAttendStartDate().getDate();
										zcCountDay = 0;
									}
									//如果在基装之后换
									if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
										jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
										zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate();
									}
								}
							}
							//开工时间与基装时间不在同一个月，考勤月没有基装时间
							else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&& !dangqianMonth.equals(jzMonth)){
								int changeManagerDay = 0;
								//换之后的项目经理
								if (null != changeManager) {
									changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
									jzCountDay = 0;
									zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - changeManagerDay;
								}
								//原来的项目经理
								if (null != changeManagerOld) {
									changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
									jzCountDay = 0;
									zcCountDay = changeManagerDay - bizPmAttendMonth.getAttendStartDate().getDate()+1;
								}
							}
							
						}
					}
					//考勤月没换过项目经理
				} else {
					//考勤的下一个月没换过项目经理
					if(null==currentExist){
						//如果开工时间 基装时间与考勤月在同一个月
						if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
							zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
							//如果基装时间与考勤月在同一个月  开工时间小于考勤月
						} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
							zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
							//如果基装时间大于考勤月  开工时间小于考勤月
						} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && bizPmAttendMonth.getAttendEndDate().getTime()<new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() ) {
							jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
							zcCountDay = 0;
							//如果基装时间小于考勤月  开工时间小于考勤月
						}else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && bizPmAttendMonth.getAttendStartDate().getTime()>new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() ) {
							jzCountDay = 0;
							zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
						}
					}//考勤的下一个月换过项目经理
					else{
						//原来的项目经理
						if (null != changeManagerOldNextMonth) {
							//如果开工时间 基装时间与考勤月在同一个月
							if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
								//如果基装时间与考勤月在同一个月  开工时间不在同一个月
							} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && dangqianMonth.equals(jzMonth)) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - sdf.parse(jzOutTime).getDate();
								//如果基装时间与考勤月不在同一个月  开工时间不在同一个月
							} else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && !dangqianMonth.equals(jzMonth)) {
								jzCountDay = 0;
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
							}
						}
						//现在的项目经理
						if (null != changeManagerNextMonth) {
							jzCountDay = 0;
							zcCountDay = 0;
						}
					}
				}
				//3.基装、主材都不为空(开工时间在同一个月，不在同一个月)
			} else if (!StringUtils.isEmpty(zcOutTime) && !StringUtils.isEmpty(jzOutTime)) {
				String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
				if (null != changeManager || null != changeManagerOld) {
					//截取项目经理变更日期的年月
					if (null != changeManager) {
						changeManagerMonth = changeManager.substring(0, 7);
					}
					if (null != changeManagerOld) {
						changeManagerMonth = changeManagerOld.substring(0, 7);
					}
					int result = changeManagerMonth.compareTo(dangqianMonth);
					//第一种情况：开工、基装、主材在同一个月
					if (result == 0) {
						//如果全不为空 判断基装月份与主材月份是否一致并且开工时间大于考勤开始时间
						if (jzMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;
							//判断现在的项目经理
							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
								//如果在开工之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
								//如果在主材之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - changeManagerDay;
								}
								//如果在主材之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
							}
							//判断原来的项目经理
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
								//如果在开工之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = changeManagerDay - bizAttendMonth.getActualStartDate().getDate()+1;
									zcCountDay = 0;
								}
								//如果在主材之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
									zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate()+1;
								}
								//如果在主材之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
							}
							
							//第二种情况：基装月份与主材月份、换项目经理月份为同一个月，开工时间不在考勤月份
						} else if (jzMonth.equals(zcMonth) && jzMonth.equals(dangqianMonth)&&bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;
							//判断现在的项目经理
							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
								//如果在开工之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - changeManagerDay;
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
								//如果在主材之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - changeManagerDay;
								}
								//如果在主材之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
							}
							//判断原来的项目经理
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
								//如果在开工之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = changeManagerDay - bizPmAttendMonth.getAttendStartDate().getDate()+1;
									zcCountDay = 0;
								}
								//如果在主材之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
									zcCountDay = changeManagerDay - sdf.parse(jzOutTime).getDate()+1;
								}
								//如果在主材之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
									zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								}
							}
							//第三种情况：主材月份、换项目经理月份为同一个月，基装月份与开工时间小于考勤月份
						}else if (dangqianMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;
							//判断现在的项目经理
							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
								//如果在开工之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay =0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate();
								}
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() -bizPmAttendMonth.getAttendStartDate().getDate();
								}
								//如果在主材之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - changeManagerDay;
								}
								//如果在主材之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
							}
							//判断原来的项目经理
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
								//如果在开工之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < bizAttendMonth.getActualStartDate().getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay = 0;
								}
								//如果在主材之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay =0;
									zcCountDay = changeManagerDay -bizPmAttendMonth.getAttendStartDate().getDate() +1;
								}
								//如果在主材之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()) {
									jzCountDay =0;
									zcCountDay = sdf.parse(zcOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
								}
							}
							//第四种情况：基装月份、换项目经理月份为同一个月，开工时间小于考勤月份，主材月份大于考勤月份
						}else if(dangqianMonth.equals(jzMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()){
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;
							//判断现在的项目经理
							if (null != changeManager) {
								changeManagerDay = Integer.parseInt(changeManager.substring(8, 10));
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate()-changeManagerDay;
									zcCountDay =jzEndTime-sdf.parse(jzOutTime).getDate();
								}
								//如果在基装之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManager).getTime() >new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = 0;
									zcCountDay =jzEndTime-changeManagerDay;
								}
							}
							if (null != changeManagerOld) {
								changeManagerDay = Integer.parseInt(changeManagerOld.substring(8, 10));
								//如果在基装之前换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = changeManagerDay;
									zcCountDay =sdf.parse(jzOutTime).getDate()-changeManagerDay;
								}
								//如果在基装之后换
								if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() > bizAttendMonth.getActualStartDate().getTime() && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(changeManagerOld).getTime() >new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime()) {
									jzCountDay = sdf.parse(jzOutTime).getDate();
									zcCountDay =changeManagerDay-sdf.parse(jzOutTime).getDate();
								}
							}
							//第五种情况：开工基装比考勤月小，主材比考勤月大
						}else if(bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()){
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;
							//判断现在的项目经理
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
							//第六种情况:开工比考勤月小，主材集装比考勤月大
						}else if(sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&& bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()){
							jzEndTime = bizPmAttendMonth.getAttendEndDate().getDate();
							int changeManagerDay = 0;
							//判断现在的项目经理
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
					//考勤月没有更换项目经理
				} else {
					//考勤的下一个月没有更换项目经理
					if(null==currentExist){
						//判断基装月份 主材月份 开工月份在同一个月
						if (jzMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate()+1;
							zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
							//判断基装月份 主材月份 考勤月份在同一个月  开工时间不在同一个月
						} else if (jzMonth.equals(zcMonth) && jzMonth.equals(dangqianMonth)&& bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
							zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
							//判断考勤月份 主材月份 在同一个月  开工时间、集装时间不在同一个月
						} else if (dangqianMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
							jzCountDay = 0;
							zcCountDay = sdf.parse(zcOutTime).getDate() -bizPmAttendMonth.getAttendStartDate().getDate()+1;
							//开工时间小于考勤月份 考勤月份没有基装、主材时间
						}else if(bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime() && bizPmAttendMonth.getAttendEndDate().getTime()<new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() ){
							jzCountDay=bizPmAttendMonth.getAttendEndDate().getDate()-bizPmAttendMonth.getAttendStartDate().getDate();
							zcCountDay=0;
							//基装时间小于考勤月份  考勤月份没有主材时间
						}else if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jzOutTime).getTime() < bizPmAttendMonth.getAttendStartDate().getTime()  && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()){
							jzCountDay=0;
							zcCountDay=bizPmAttendMonth.getAttendEndDate().getDate()-bizPmAttendMonth.getAttendStartDate().getDate()+1;
							//基装月份在考勤月，开工小于考勤月，主材大于考勤月
						}else if(jzMonth.equals(dangqianMonth) && new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(zcOutTime).getTime() >bizPmAttendMonth.getAttendEndDate().getTime()&& bizAttendMonth.getActualStartDate().getTime()<bizPmAttendMonth.getAttendStartDate().getTime()){
							jzCountDay=bizPmAttendMonth.getAttendEndDate().getDate()-bizPmAttendMonth.getAttendStartDate().getDate();
							zcCountDay=0;
						}
						
					}else{
						//原来的项目经理
						if (null != changeManagerOldNextMonth) {
							//判断基装月份 主材月份 开工月份 考勤月份在同一个月
							if (jzMonth.equals(zcMonth) && jzMonth.equals(dangqianMonth)&&bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizAttendMonth.getActualStartDate().getDate();
								zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								//判断基装月份 主材月份 考勤月 在同一个月  开工时间不在同一个月
							} else if (jzMonth.equals(dangqianMonth)&& zcMonth.equals(dangqianMonth)  && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate() - bizPmAttendMonth.getAttendStartDate().getDate()+1;
								zcCountDay = sdf.parse(zcOutTime).getDate() - sdf.parse(jzOutTime).getDate();
								//判断考勤月份 主材月份 在同一个月  开工时间、集装时间不在同一个月
							} else if (dangqianMonth.equals(zcMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = 0;
								zcCountDay = sdf.parse(zcOutTime).getDate() -bizPmAttendMonth.getAttendStartDate().getDate();
							}
							//考勤、基装在一个月，开工小于考勤 主材大于考勤
							else if (dangqianMonth.equals(jzMonth) && bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate()-sdf.parse(jzOutTime).getDate();
							}
							//考勤、开工在一个月，主材、基装大于考勤月
							else if (bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()&&bizAttendMonth.getActualStartDate().getTime()< bizPmAttendMonth.getAttendEndDate().getTime() &&sdf.parse(jzOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate()-sdf.parse(jzOutTime).getDate();
							}
							//考勤、开工、基装在一个月，主材大于考勤月
							else if (dangqianMonth.equals(jzMonth) && bizAttendMonth.getActualStartDate().getTime() > bizPmAttendMonth.getAttendStartDate().getTime()&&bizAttendMonth.getActualStartDate().getTime()< bizPmAttendMonth.getAttendEndDate().getTime() &&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = sdf.parse(jzOutTime).getDate();
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate()-sdf.parse(jzOutTime).getDate();
							}
							//开工基装小于考勤时间，主材大于考勤月
							else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&&sdf.parse(jzOutTime).getTime()<bizPmAttendMonth.getAttendStartDate().getTime()) {
								jzCountDay = 0;
								zcCountDay = bizPmAttendMonth.getAttendEndDate().getDate();
							}
							//开工小于考勤时间，主材基装大于考勤月
							else if (bizAttendMonth.getActualStartDate().getTime() < bizPmAttendMonth.getAttendStartDate().getTime()&&sdf.parse(zcOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()&&sdf.parse(jzOutTime).getTime()>bizPmAttendMonth.getAttendEndDate().getTime()) {
								jzCountDay = bizPmAttendMonth.getAttendEndDate().getDate();
								zcCountDay = 0;
							}
						}
						//现在的项目经理
						if (null != changeManagerNextMonth) {
							jzCountDay = 0;
							zcCountDay = 0;
						}
					}
					
				}
			}
			//基装应签到次数
			int i = 0;
			if (qdcs != null && qdcs.getSignCycleDaysBasicwork() != 0) {
				i = jzCountDay / qdcs.getSignCycleDaysBasicwork();
			}
			//主材应签到次数
			int j = 0;
			if (qdcs != null && qdcs.getSignCycleDaysComplete() != 0) {
				j = zcCountDay / qdcs.getSignCycleDaysComplete();
			}
			//当前订单应签到总数
			bizAttendMonth.setMustSignTimes(String.valueOf(i + j));
			int signDateTimesCount = 0;
			int actualValue = 0;
			Date date = null;
// 第三步：统计实际签到次数
            //如果考勤月份换过项目经理
            if (null != changeManager || null != changeManagerOld) {
                //截取换完之后项目经理年月
                if (null != changeManager) {
                    changeManagerMonth = changeManager.substring(0, 7);
                }
                //截取换完之前项目经理年月
                if (null != changeManagerOld) {
                    changeManagerMonth = changeManagerOld.substring(0, 7);
                }
                String dangqianMonth = sdf.format(bizPmAttendMonth.getAttendStartDate()).substring(0, 7);
                int result = changeManagerMonth.compareTo(dangqianMonth);
                //如果项目经理变更月份与考勤月份相同
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
            //考勤的下一个月没有更换项目经理
            if (null == currentExist && null == changeManager && null == changeManagerOld) {
                signDateTimesCount = bizPmAttendMonthDao.getSignDateTimesCount(bizAttendMonth);
            }
            if (null != currentExist) {
                //考勤的下一个月换过项目经理
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
            //增加实际取值列
            bizAttendMonth.setActualValue(String.valueOf(actualValue));
        }
        return newbizPmAttendMonth1;
    }


    public List<BizPmAttendMonth> getBizPmAttendMonthCount(BizPmAttendMonth bizPmAttendMonth) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return bizPmAttendMonthDao.bizPmAttendSalaryBillDetail(bizPmAttendMonth);
    }

    /**
     * @param date1
     * @param date2
     * @return 获取相差时间天数
     */
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
        //List<BizPmAttendMonth> bizMonthOrder = bizPmAttendMonthDao.getBizMonthOrder(bizPmAttendMonth);
        //if(bizMonthOrder.size()==0){
        for (int i = 0; i < mustSignTimes.length; i++) {
            bizPmAttendMonth.setMustSignTimes(mustSignTimes[i]);
            bizPmAttendMonth.setRealSignTimes(realSignTimes[i]);
            bizPmAttendMonth.setOwedSginTime(owedSginTime[i]);
            bizPmAttendMonth.setOrderId(orderId[i]);
            bizPmAttendMonth.setActualValue(actualValueInput[i]);
            bizPmAttendMonth.preInsert();
            //List<BizPmAttendMonth> findMinthOrder = bizPmAttendMonthDao.findMinthOrder(bizPmAttendMonth);
            //if(findMinthOrder.size()<1){
            bizPmAttendMonthDao.insertMonthOrder(bizPmAttendMonth);
            //}
            //}
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

    /**
     * @param
     * @return
     * @Description: 得到订单的基桩和主材的开始时间和结束时间
     * @Author zhangkangjian
     * @Date 2017/12/18 17:44
     */
    public List<Map<String, Object>> getStartDateTimeAndEndDate(String managerId, String kqMonth, String orderId, Date startDate, Date endDate) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//      主材竣工时间
        String zcOutTimeStr = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(orderId), ConstantUtils.MANAGER_ATTEND_ZC_NODE);
        Date zcOutTime = DateUtils.parseDate(zcOutTimeStr);
//        基装竣工时间
        String jzOutTimeStr = bizPmAttendMonthDao.getCheckTimeByOrderIdAndNode(Integer.parseInt(orderId), ConstantUtils.MANAGER_ATTEND_JZ_NODE);
        Date jzOutTime = DateUtils.parseDate(jzOutTimeStr);
//        开工时间
        Date orderStartDate = bizPmAttendMonthDao.getOrderStartDate(orderId);
        /*查找订单换过项目经理的信息*/
        List<BizPmAttendMonth> managerDetail = bizPmAttendMonthDao.findChangeManagerDetail(orderId, kqMonth);
        /*换过*/
        if (managerDetail.size() > 0) {
          /*查询开始结束时间*/
            List<Map<String, Object>> listMap = changeManager(orderId, kqMonth, managerId, startDate, endDate, managerDetail,orderStartDate);
            for (int i = 0; i < listMap.size(); i++) {
                Map<String, Object> map = listMap.get(i);
                startDate = (Date) map.get("startDate");
                endDate = (Date) map.get("endDate");
                if("frist".equals((String)map.get("type"))){
                    /*基桩*/
                    Map<String, Object> jzdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_JZ, startDate, endDate, orderStartDate, jzOutTime,1);
                    /*主材*/
                    Map<String, Object> zcdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_ZC, startDate, endDate,jzOutTime, zcOutTime,1);
                    List<Map<String,Object>> checkedDatelistMap = checkedDate(jzdate,zcdate);
                    list.addAll(checkedDatelistMap);
                }else{
                    /*基桩*/
                    Map<String, Object> jzdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_JZ, startDate, endDate, orderStartDate, jzOutTime,1);
                    /*主材*/
                    Map<String, Object> zcdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_ZC, startDate, endDate,jzOutTime, zcOutTime,1);
                    list.add(jzdate);
                    list.add(zcdate);
                }
            }
        } else {
             /*基桩*/
             Map<String, Object> jzdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_JZ, startDate, endDate, orderStartDate, jzOutTime,0);
            /*主材*/
            Map<String, Object> zcdate = getDate(orderId, managerId, ConstantUtils.MANAGER_ATTEND_ZC, startDate, endDate, jzOutTime, zcOutTime,0);
            List<Map<String,Object>> listMap = checkedDate(jzdate,zcdate);
            list.addAll(listMap);
        }
        return list;
    }


    /**
     * @param
     * @return
     * @Description: 获取开始结束时间
     * @Author zhangkangjian
     * @Date 2017/12/19 18:42
     */
    public Map<String, Object> getDate(String orderId, String managerId, String type, Date startDate, Date endDate, Date orderStartDate, Date jzOutTime,Integer changeManagerType) {
        /*基桩开始时间*/
        Map<String, Object> jzStart = new HashMap<String, Object>();
        jzStart.put("orderId", orderId);
        jzStart.put("type", type);
        /*考勤月大于开工时间 取考勤月*/
        if (orderStartDate != null) {
            if (startDate.getTime() >= orderStartDate.getTime()) {
                jzStart.put("startDate", startDate);
            } else {
                jzStart.put("startDate", orderStartDate);
            }
//            基桩完成时间为空 或者 基桩时间 小于考勤月
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

    /**
     * @param
     * @return
     * @Description: 获得订单的应签到次数
     * @Author zhangkangjian
     * @Date 2017/12/20 18:41
     */
    public int getCountDay(BizPmAttendMonth bizPmAttendMonth) {
        String managerId = bizPmAttendMonth.getItemManagerId();
        String kqMonth = bizPmAttendMonth.getAttendMonth();
        String orderId = bizPmAttendMonth.getOrderId();
        Date startDate = bizPmAttendMonth.getAttendStartDate();
        Date endDate = bizPmAttendMonth.getAttendEndDate();
//
        if (bizPmAttendMonth == null || managerId == null || kqMonth == null || orderId == null || startDate == null || endDate == null) {
            return 0;
        }
//        查询订单的基桩、主材开始结束时间11111
        List<Map<String, Object>> list = getStartDateTimeAndEndDate(managerId, kqMonth, orderId, startDate, endDate);
//        统计应签到天数
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
        /*查询基桩和主材的基数*/
        BizPmAttendMonth qdcs = bizPmAttendMonthDao.getQdcs(bizPmAttendMonth);
        int jzTimes = 0;
        if (qdcs != null && qdcs.getSignCycleDaysBasicwork() != 0) {
            jzTimes = (int) jz / qdcs.getSignCycleDaysBasicwork();
        }
        //主材应签到次数
        int zcTimes = 0;
        if (qdcs != null && qdcs.getSignCycleDaysComplete() != 0) {
            zcTimes = (int) zc / qdcs.getSignCycleDaysComplete();
        }
        return jzTimes + zcTimes;

    }

    /**
     * @param
     * @return
     * @Description: 换项目经理的开始结束时间 查询这个订单换过项目经理的开始时间和结束时间
     * @Author zhangkangjian
     * @Date 2017/12/20 14:17
     */
    public List<Map<String, Object>> changeManager(String orderId, String kqMonth, String managerId, Date startDate, Date endDate, List<BizPmAttendMonth> managerDetail,Date orderStartDate) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        String strOrderStartDate = DateUtils.formatDate(orderStartDate,"yyyy-MM");
        for (int i = 0; i < managerDetail.size(); i++) {
            BizPmAttendMonth bm = managerDetail.get(i);
            Map<String, Object> bmmap = new HashMap<>();
//            a换b a的时间
            if (i == 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("empId", bm.getOldEmployeeId());
                if(strOrderStartDate.equals(kqMonth) && orderStartDate.getTime() < managerDetail.get(i).getChangeUpdateDate().getTime()){
//                    考勤周期 例如 startDate：12-12      orderStartDate 12-11 取12-12
                    if(startDate.getTime() > orderStartDate.getTime()){
                        map.put("startDate", DateUtils.addDate(startDate,-1));
                    }else{
                        map.put("startDate", orderStartDate);
                    }
                }else{
                    map.put("startDate", startDate);
                }
//                12-01到12-03 4天
                map.put("endDate",bm.getChangeUpdateDate());
                map.put("type","frist");
                if (bm.getOldEmployeeId().equals(managerId)) {
                    listMap.add(map);
                }
            }
            bmmap.put("empId", bm.getPmEmployeeId());
//                    考勤周期 例如 startDate：12-12      orderStartDate 12-11 取12-12
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

    /**
     * @param
     * @return
     * @Description: 应签到次数
     * @Author zhangkangjian
     * @Date 2017/12/20 19:09
     */
    public List<BizPmAttendMonth> findBizPmAttendMonthList(BizPmAttendMonth bizPmAttendMonth) {
        List<BizPmAttendMonth> newbizPmAttendMonth = new ArrayList<>();
        /*查询项目经理下所有的订单*/
        List<BizPmAttendMonth> managerOrder = bizPmAttendMonthDao.getOrderListByManagerId(bizPmAttendMonth);
//        换项目经理的订单
        List<BizPmAttendMonth> changeManagerOrder = bizPmAttendMonthDao.findChangeManagerList(bizPmAttendMonth);
        managerOrder.addAll(changeManagerOrder);
//        遍历所有订单
        Map<String,BizPmAttendMonth> map = new HashMap<>();
        for (BizPmAttendMonth order : managerOrder) {
            /*if(!order.getOrderId().equals("4840")){
                continue;
            }*/
            bizPmAttendMonth.setOrderId(order.getOrderId());
            /*查询应签到次数*/
            int count = getCountDay(bizPmAttendMonth);
            order.setMustSignTimes(String.valueOf(count));
            /*查询实际签到次数*/
            /*Date startDate = bizPmAttendMonth.getAttendStartDate();
            Date endDate = bizPmAttendMonth.getAttendEndDate();*/
//            Integer realSignTimes = bizPmAttendMonthDao.findRealSignTimes(order.getOrderId(), bizPmAttendMonth.getAttendMonth(), bizPmAttendMonth.getItemManagerId());
            Integer realSignTimes = bizPmAttendMonthDao.getRealSignTimes(bizPmAttendMonth);
            if (realSignTimes == null) {
                realSignTimes = 0;
            }
            order.setRealSignTimes(String.valueOf(realSignTimes));
            /*实际取值*/
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

    /**
    * @Description: 包含时间头尾计算
    * @Author zhangkangjian
    * @param
    * @return
    * @Date 2018/1/9 10:22
    */
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
	//根据项目经理ID查询所属的工程模式
    public String findProjectModeByManagerId(String itemManagerId) {
    	return bizPmAttendMonthDao.findProjectModeByManagerId(itemManagerId);
    }

	public BizPmAttendMonth findSalaryAllAttend(String findProjectMode, String storeId, Integer itemManagerStar) {
		return bizPmAttendMonthDao.findSalaryAllAttend(findProjectMode,storeId,itemManagerStar);
	}
}
