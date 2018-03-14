package cn.damei.service.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.AttendConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAttendDayDao;
import cn.damei.entity.modules.BizAttendDay;

/**
 * Service
 * 
 * @author cgh
 */
@Service
@Transactional(readOnly = true)
public class BizAttendDayService extends CrudService2<BizAttendDayDao, BizAttendDay> {

	@Autowired
	private BizAttendDayDao bizAttendDayDao;

	/**
	 * 根据 项目经理id 和 考勤月份 获取list
	 * 
	 * @param attendEmployeeId
	 * @param attendMonth
	 * @return
	 */
	public List<BizAttendDay> findAttendDays(Integer attendEmployeeId, String attendMonth) {

		return bizAttendDayDao.findAttendDays(attendEmployeeId, attendMonth);

	}

	/**
	 * insert service
	 * 
	 * @param list
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertAttend(List<BizAttendDay> list) {
		int i = bizAttendDayDao.insertAttend(list);

		return i;
	}

	/**
	 * 从2张签到表取得符和条件的list
	 * 
	 * @return
	 */
	public List<BizAttendDay> pageList() {
		// 封装的list
		List<BizAttendDay> signs = new ArrayList<BizAttendDay>();
		// 时分秒
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 任务包验收签到
		List<BizAttendDay> selectBusinessSigns = bizAttendDayDao.selectBusinessSigns();
		// 现场签到
		List<BizAttendDay> selectSigns = bizAttendDayDao.selectSigns();
		// 循环
		for (BizAttendDay b : selectBusinessSigns) {

			for (BizAttendDay b2 : selectSigns) {
				BizAttendDay c = new BizAttendDay();
				if (b.getAttendEmployeeId().intValue() == b2.getAttendEmployeeId().intValue()
						&& b.getAttendDate().getTime() == b2.getAttendDate().getTime()) {
					// 任务包验收最早时间
					long minDate1 = b.getEarlySignDate().getTime();
					// 任务包验收最晚时间
					long maxDate1 = b.getLateSignDate().getTime();
					// 现场签到最早时间
					long minDate2 = b2.getEarlySignDate().getTime();
					// 现场签到最晚时间
					long maxDate2 = b2.getLateSignDate().getTime();

					if (minDate1 < minDate2) {
						// 如果小于 则是最早时间
						c.setEarlySignDate(b.getEarlySignDate());
						// 最早签到误差
						c.setEarlySignReeorDistance(b.getEarlySignReeorDistance());
					} else {
						c.setEarlySignDate(b2.getEarlySignDate());
						// 最早签到误差
						c.setEarlySignReeorDistance(b2.getEarlySignReeorDistance());
					}
					// 如果大于 则是最晚时间
					if (maxDate1 > maxDate2) {
						c.setLateSignDate(b.getLateSignDate());
						// 最晚签到误差
						c.setLateSignErrorDistance(b.getLateSignErrorDistance());
					} else {
						c.setLateSignDate(b2.getLateSignDate());
						// 最晚签到误差
						c.setLateSignErrorDistance(b2.getLateSignErrorDistance());
					}
					// 项目经理名称
					// c.setManagerName(b2.getManagerName());
					// 项目经理角色 1
					c.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);
					// 项目经理id
					c.setAttendEmployeeId(b.getAttendEmployeeId());
					// 考勤日期年月日
					c.setAttendDate(b.getAttendDate());
					// 考勤状态
					// 签到误差
					// 误差
					int early = c.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					int late = c.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					if (early <= 0 && late <= 0) {

						// 签到最早时间 精确到分
						long i1 = 0;
						try {
							i1 = f3.parse(AttendConstantUtil.getDate2(c.getEarlySignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						// 签到最晚时间 精确到分
						long i2 = 0;
						try {
							i2 = f3.parse(AttendConstantUtil.getDate2(c.getLateSignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						// 上班标准时间"09:00:00"
						long m1 = 0;
						try {
							m1 = f2.parse(AttendConstantUtil.getDate(c.getEarlySignDate())
									+ AttendConstantUtil.MIN_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 下班标准时间 18 00 00
						long m2 = 0;
						try {
							m2 = f2.parse(AttendConstantUtil.getDate(c.getLateSignDate())
									+ AttendConstantUtil.MAX_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 最早时间
						if (i1 <= m1 && i2 >= m2) {
							// 全勤
							c.setAttendType(AttendConstantUtil.ATTEND_ALL);
						} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {
							// 半勤
							c.setAttendType(AttendConstantUtil.ATTEND_HALF);
						} else {
							// 缺勤
							c.setAttendType(AttendConstantUtil.ATTEND_LACK);
						}
					} else {
						// 缺勤
						c.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
					// 添加进list
					signs.add(c);
				}
			}
		}
		return signs;
	}

	@Transactional
	public int pageList2() {
		// 封装的list
		List<BizAttendDay> signs = new ArrayList<BizAttendDay>();

		// 时分秒
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		// 任务包验收签到
		List<BizAttendDay> selectBusinessSigns = bizAttendDayDao.selectBusinessSigns();

		// 现场签到
		List<BizAttendDay> selectSigns = bizAttendDayDao.selectSigns();

		// 循环
		for (BizAttendDay b : selectBusinessSigns) {

			for (BizAttendDay b2 : selectSigns) {

				BizAttendDay c = new BizAttendDay();

				if (b.getAttendEmployeeId().intValue() == b2.getAttendEmployeeId().intValue()
						&& b.getAttendDate().getTime() == b2.getAttendDate().getTime()) {
					// 任务包验收最早时间
					long minDate1 = b.getEarlySignDate().getTime();
					// 任务包验收最晚时间
					long maxDate1 = b.getLateSignDate().getTime();
					// 现场签到最早时间
					long minDate2 = b2.getEarlySignDate().getTime();
					// 现场签到最晚时间
					long maxDate2 = b2.getLateSignDate().getTime();

					if (minDate1 < minDate2) {
						// 如果小于 则是最早时间
						c.setEarlySignDate(b.getEarlySignDate());

						// 最早签到误差
						c.setEarlySignReeorDistance(b.getEarlySignReeorDistance());
					} else {
						c.setEarlySignDate(b2.getEarlySignDate());
						// 最早签到误差
						c.setEarlySignReeorDistance(b2.getEarlySignReeorDistance());
					}
					// 如果大于 则是最晚时间
					if (maxDate1 > maxDate2) {
						c.setLateSignDate(b.getLateSignDate());

						// 最晚签到误差
						c.setLateSignErrorDistance(b.getLateSignErrorDistance());
					} else {
						c.setLateSignDate(b2.getLateSignDate());

						// 最晚签到误差
						c.setLateSignErrorDistance(b2.getLateSignErrorDistance());
					}
					// 项目经理名称
					// c.setManagerName(b2.getManagerName());

					// 项目经理角色 1
					c.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);
					// 项目经理id
					c.setAttendEmployeeId(b.getAttendEmployeeId());

					// 考勤日期年月日
					c.setAttendDate(b.getAttendDate());

					// 考勤状态
					// 签到误差
					int early = c.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					int late = c.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					if (early <= 0 && late <= 0) {

						// 签到最早时间 精确到分
						long i1 = 0;
						try {
							i1 = f3.parse(AttendConstantUtil.getDate2(c.getEarlySignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						// 签到最晚时间 精确到分
						long i2 = 0;
						try {
							i2 = f3.parse(AttendConstantUtil.getDate2(c.getLateSignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						// 上班标准时间"09:00:00"
						long m1 = 0;
						try {
							m1 = f2.parse(
									AttendConstantUtil.getDate(c.getEarlySignDate()) + AttendConstantUtil.MIN_DATE)
									.getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 下班标准时间 18 00 00
						long m2 = 0;
						try {
							m2 = f2.parse(AttendConstantUtil.getDate(c.getLateSignDate()) + AttendConstantUtil.MAX_DATE)
									.getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 最早时间
						if (i1 <= m1 && i2 >= m2) {
							// 全勤
							c.setAttendType(AttendConstantUtil.ATTEND_ALL);
						} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {
							// 半勤
							c.setAttendType(AttendConstantUtil.ATTEND_HALF);
						} else {
							// 缺勤
							c.setAttendType(AttendConstantUtil.ATTEND_LACK);
						}
					} else {
						// 缺勤
						c.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
					// 添加进list
					signs.add(c);
				}
			}
		}

		for (BizAttendDay sign : signs) {
			// 任务包验收签到
			Iterator<BizAttendDay> iterator = selectBusinessSigns.iterator();

			while (iterator.hasNext()) {

				BizAttendDay a = iterator.next();

				if (sign.getAttendEmployeeId().intValue() == a.getAttendEmployeeId().intValue()
						&& sign.getAttendDate().getTime() == a.getAttendDate().getTime()) {
					iterator.remove();
				}
			}
		}

		List<BizAttendDay> signList = new ArrayList<BizAttendDay>();

		for (BizAttendDay bizAttendDay : selectBusinessSigns) {

			BizAttendDay b = new BizAttendDay();

			if (bizAttendDay.getZaoDate().getTime() == bizAttendDay.getWanDate().getTime()) {

				b.setAttendDate(bizAttendDay.getZaoDate());

				b.setEarlySignDate(bizAttendDay.getEarlySignDate());
				b.setLateSignDate(bizAttendDay.getLateSignDate());

				b.setAttendEmployeeId(bizAttendDay.getAttendEmployeeId());

				b.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);
				b.setEarlySignReeorDistance(bizAttendDay.getEarlySignReeorDistance());

				b.setLateSignErrorDistance(bizAttendDay.getLateSignErrorDistance());
				// b.setManagerName(bizAttendDay.getManagerName());
				b.setSignTimes(bizAttendDay.getSignTimes());
				// 考勤状态
				// 签到误差
				int early = bizAttendDay.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				int late = bizAttendDay.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				if (early <= 0 && late <= 0) {

					// 签到最早时间 精确到分
					long i1 = 0;
					try {
						i1 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getEarlySignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					// 签到最晚时间 精确到分
					long i2 = 0;
					try {
						i2 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getLateSignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					// 上班标准时间"09:00:00"
					long m1 = 0;
					try {
						m1 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getEarlySignDate())
								+ AttendConstantUtil.MIN_DATE).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// 下班标准时间 18 00 00
					long m2 = 0;
					try {
						m2 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getLateSignDate())
								+ AttendConstantUtil.MAX_DATE).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// 最早时间
					if (i1 <= m1 && i2 >= m2) {
						// 全勤
						b.setAttendType(AttendConstantUtil.ATTEND_ALL);
					} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {
						// 半勤
						b.setAttendType(AttendConstantUtil.ATTEND_HALF);
					} else {
						// 缺勤
						b.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
				} else {
					// 缺勤
					b.setAttendType(AttendConstantUtil.ATTEND_LACK);
				}
				// 添加进list
				signList.add(b);
			}
		}

		int i = insertAttend(signList);
		return i;
	}

	@Transactional
	public int pageList3() {
		// 封装的list
		List<BizAttendDay> signs = new ArrayList<BizAttendDay>();

		// 时分秒
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 任务包验收签到
		List<BizAttendDay> selectBusinessSigns = bizAttendDayDao.selectBusinessSigns();

		// 现场签到
		List<BizAttendDay> selectSigns = bizAttendDayDao.selectSigns();

		// 循环
		for (BizAttendDay b : selectBusinessSigns) {

			for (BizAttendDay b2 : selectSigns) {
				// 封装对象
				BizAttendDay c = new BizAttendDay();

				if (b.getAttendEmployeeId().intValue() == b2.getAttendEmployeeId().intValue()
						&& b.getAttendDate().getTime() == b2.getAttendDate().getTime()) {
					// 任务包验收最早时间
					long minDate1 = b.getEarlySignDate().getTime();
					// 任务包验收最晚时间
					long maxDate1 = b.getLateSignDate().getTime();
					// 现场签到最早时间
					long minDate2 = b2.getEarlySignDate().getTime();
					// 现场签到最晚时间
					long maxDate2 = b2.getLateSignDate().getTime();

					if (minDate1 < minDate2) {
						// 如果小于 则是最早时间
						c.setEarlySignDate(b.getEarlySignDate());

						// 最早签到误差
						c.setEarlySignReeorDistance(b.getEarlySignReeorDistance());
					} else {
						c.setEarlySignDate(b2.getEarlySignDate());
						// 最早签到误差
						c.setEarlySignReeorDistance(b2.getEarlySignReeorDistance());
					}
					// 如果大于 则是最晚时间
					if (maxDate1 > maxDate2) {
						c.setLateSignDate(b.getLateSignDate());

						// 最晚签到误差
						c.setLateSignErrorDistance(b.getLateSignErrorDistance());
					} else {
						c.setLateSignDate(b2.getLateSignDate());

						// 最晚签到误差
						c.setLateSignErrorDistance(b2.getLateSignErrorDistance());
					}
					// 项目经理名称
					// c.setManagerName(b2.getManagerName());

					// 项目经理角色 1
					c.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);
					// 项目经理id
					c.setAttendEmployeeId(b.getAttendEmployeeId());

					// 考勤日期年月日
					c.setAttendDate(b.getAttendDate());

					// 考勤状态
					// 签到误差
					// 误差
					int early = c.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					int late = c.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					if (early <= 0 && late <= 0) {

						// 签到最早时间 精确到分
						long i1 = 0;
						try {
							i1 = f3.parse(AttendConstantUtil.getDate2(c.getEarlySignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						// 签到最晚时间 精确到分
						long i2 = 0;
						try {
							i2 = f3.parse(AttendConstantUtil.getDate2(c.getLateSignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}

						// 上班标准时间"09:00:00"
						long m1 = 0;
						try {
							m1 = f2.parse(AttendConstantUtil.getDate(c.getEarlySignDate())
									+ AttendConstantUtil.MIN_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 下班标准时间 18 00 00
						long m2 = 0;
						try {
							m2 = f2.parse(AttendConstantUtil.getDate(c.getLateSignDate())
									+ AttendConstantUtil.MAX_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}
						// 最早时间
						if (i1 <= m1 && i2 >= m2) {
							// 全勤
							c.setAttendType(AttendConstantUtil.ATTEND_ALL);
						} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {
							// 半勤
							c.setAttendType(AttendConstantUtil.ATTEND_HALF);
						} else {
							// 缺勤
							c.setAttendType(AttendConstantUtil.ATTEND_LACK);
						}
					} else {
						// 缺勤
						c.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
					// 添加进list
					signs.add(c);

				}
			}
		}

		for (BizAttendDay sign : signs) {

			Iterator<BizAttendDay> iterator = selectSigns.iterator();

			while (iterator.hasNext()) {
				BizAttendDay a = iterator.next();
				if (sign.getAttendEmployeeId().intValue() == a.getAttendEmployeeId().intValue()
						&& sign.getAttendDate().getTime() == a.getAttendDate().getTime()) {
					iterator.remove();
				}
			}
		}

		List<BizAttendDay> signList = new ArrayList<BizAttendDay>();

		for (BizAttendDay bizAttendDay : selectSigns) {

			BizAttendDay b = new BizAttendDay();

			if (bizAttendDay.getZaoDate().getTime() == bizAttendDay.getWanDate().getTime()) {

				b.setAttendDate(bizAttendDay.getZaoDate());

				b.setEarlySignDate(bizAttendDay.getEarlySignDate());

				b.setLateSignDate(bizAttendDay.getLateSignDate());

				b.setAttendEmployeeId(bizAttendDay.getAttendEmployeeId());

				b.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);

				b.setEarlySignReeorDistance(bizAttendDay.getEarlySignReeorDistance());

				b.setLateSignErrorDistance(bizAttendDay.getLateSignErrorDistance());

				// b.setManagerName(bizAttendDay.getManagerName());

				b.setSignTimes(bizAttendDay.getSignTimes());
				// 考勤状态
				// 签到误差
				int early = bizAttendDay.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				int late = bizAttendDay.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				if (early <= 0 && late <= 0) {

					// 签到最早时间 精确到分
					long i1 = 0;
					try {
						i1 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getEarlySignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					// 签到最晚时间 精确到分
					long i2 = 0;
					try {
						i2 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getLateSignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					// 上班标准时间"09:00:00"
					long m1 = 0;
					try {
						m1 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getEarlySignDate())
								+ AttendConstantUtil.MIN_DATE.toString()).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// 下班标准时间 18 00 00
					long m2 = 0;
					try {
						m2 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getLateSignDate())
								+ AttendConstantUtil.MAX_DATE.toString()).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}
					// 最早时间
					if (i1 <= m1 && i2 >= m2) {
						// 全勤
						b.setAttendType(AttendConstantUtil.ATTEND_ALL);
					} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {
						// 半勤
						b.setAttendType(AttendConstantUtil.ATTEND_HALF);
					} else {
						// 缺勤
						b.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
				} else {
					// 缺勤
					b.setAttendType(AttendConstantUtil.ATTEND_LACK);
				}
				// 添加进list
				signList.add(b);
			}
		}

		int i = insertAttend(signList);
		return i;
	}

	/**
	 * 获取managerID的list 以便于批量更新考勤单状态
	 * 
	 * @param attendEmployeeId
	 * @return
	 */
	public List<BizAttendDay> findAttendDaysByEmployeeId(Integer attendEmployeeId) {
		// 封装的list
		List<BizAttendDay> list = new ArrayList<BizAttendDay>();

		List<BizAttendDay> bizAttendDayList = bizAttendDayDao.findAttendDaysByEmployeeId(attendEmployeeId);

		for (BizAttendDay bizAttendDay : bizAttendDayList) {
			// 以生成考勤单
			bizAttendDay.setIsGeneratedAttendBill(AttendConstantUtil.IS_CREATE_SHEET);
			bizAttendDay.setAttendDate(bizAttendDay.getAttendDate());
			bizAttendDay.setAttendEmployeeId(bizAttendDay.getAttendEmployeeId());
			bizAttendDay.setAttendEmployeeRole(bizAttendDay.getAttendEmployeeRole());
			bizAttendDay.setAttendType(bizAttendDay.getAttendType());
			bizAttendDay.setEarlySignDate(bizAttendDay.getEarlySignDate());
			bizAttendDay.setEarlySignReeorDistance(bizAttendDay.getEarlySignReeorDistance());
			bizAttendDay.setId(bizAttendDay.getId());
			bizAttendDay.setLateSignDate(bizAttendDay.getLateSignDate());
			bizAttendDay.setLateSignErrorDistance(bizAttendDay.getLateSignErrorDistance());

			bizAttendDay.setCreateBy(bizAttendDay.getCreateBy());
			bizAttendDay.setCreateDate(bizAttendDay.getCreateDate());
			bizAttendDay.setDelFlag(bizAttendDay.getDelFlag());
			bizAttendDay.setRemarks(bizAttendDay.getRemarks());
			bizAttendDay.setUpdateBy(bizAttendDay.getUpdateBy());
			bizAttendDay.setUpdateDate(bizAttendDay.getUpdateDate());
			list.add(bizAttendDay);
		}

		return list;
	}

	/**
	 * 批量更新以生成考勤单状态
	 * 
	 * @param list
	 */
	@Transactional
	public void upadteAttendDays(List<BizAttendDay> list) {
		bizAttendDayDao.upadteAttendDays(list);
	}

}