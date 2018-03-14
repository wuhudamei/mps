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


@Service
@Transactional(readOnly = true)
public class BizAttendDayService extends CrudService2<BizAttendDayDao, BizAttendDay> {

	@Autowired
	private BizAttendDayDao bizAttendDayDao;


	public List<BizAttendDay> findAttendDays(Integer attendEmployeeId, String attendMonth) {

		return bizAttendDayDao.findAttendDays(attendEmployeeId, attendMonth);

	}


	@Transactional(readOnly = false)
	public int insertAttend(List<BizAttendDay> list) {
		int i = bizAttendDayDao.insertAttend(list);

		return i;
	}


	public List<BizAttendDay> pageList() {

		List<BizAttendDay> signs = new ArrayList<BizAttendDay>();

		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		List<BizAttendDay> selectBusinessSigns = bizAttendDayDao.selectBusinessSigns();

		List<BizAttendDay> selectSigns = bizAttendDayDao.selectSigns();

		for (BizAttendDay b : selectBusinessSigns) {

			for (BizAttendDay b2 : selectSigns) {
				BizAttendDay c = new BizAttendDay();
				if (b.getAttendEmployeeId().intValue() == b2.getAttendEmployeeId().intValue()
						&& b.getAttendDate().getTime() == b2.getAttendDate().getTime()) {

					long minDate1 = b.getEarlySignDate().getTime();

					long maxDate1 = b.getLateSignDate().getTime();

					long minDate2 = b2.getEarlySignDate().getTime();

					long maxDate2 = b2.getLateSignDate().getTime();

					if (minDate1 < minDate2) {

						c.setEarlySignDate(b.getEarlySignDate());

						c.setEarlySignReeorDistance(b.getEarlySignReeorDistance());
					} else {
						c.setEarlySignDate(b2.getEarlySignDate());

						c.setEarlySignReeorDistance(b2.getEarlySignReeorDistance());
					}

					if (maxDate1 > maxDate2) {
						c.setLateSignDate(b.getLateSignDate());

						c.setLateSignErrorDistance(b.getLateSignErrorDistance());
					} else {
						c.setLateSignDate(b2.getLateSignDate());

						c.setLateSignErrorDistance(b2.getLateSignErrorDistance());
					}



					c.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);

					c.setAttendEmployeeId(b.getAttendEmployeeId());

					c.setAttendDate(b.getAttendDate());



					int early = c.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					int late = c.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					if (early <= 0 && late <= 0) {


						long i1 = 0;
						try {
							i1 = f3.parse(AttendConstantUtil.getDate2(c.getEarlySignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}


						long i2 = 0;
						try {
							i2 = f3.parse(AttendConstantUtil.getDate2(c.getLateSignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}


						long m1 = 0;
						try {
							m1 = f2.parse(AttendConstantUtil.getDate(c.getEarlySignDate())
									+ AttendConstantUtil.MIN_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						long m2 = 0;
						try {
							m2 = f2.parse(AttendConstantUtil.getDate(c.getLateSignDate())
									+ AttendConstantUtil.MAX_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						if (i1 <= m1 && i2 >= m2) {

							c.setAttendType(AttendConstantUtil.ATTEND_ALL);
						} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {

							c.setAttendType(AttendConstantUtil.ATTEND_HALF);
						} else {

							c.setAttendType(AttendConstantUtil.ATTEND_LACK);
						}
					} else {

						c.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}

					signs.add(c);
				}
			}
		}
		return signs;
	}

	@Transactional
	public int pageList2() {

		List<BizAttendDay> signs = new ArrayList<BizAttendDay>();


		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");


		List<BizAttendDay> selectBusinessSigns = bizAttendDayDao.selectBusinessSigns();


		List<BizAttendDay> selectSigns = bizAttendDayDao.selectSigns();


		for (BizAttendDay b : selectBusinessSigns) {

			for (BizAttendDay b2 : selectSigns) {

				BizAttendDay c = new BizAttendDay();

				if (b.getAttendEmployeeId().intValue() == b2.getAttendEmployeeId().intValue()
						&& b.getAttendDate().getTime() == b2.getAttendDate().getTime()) {

					long minDate1 = b.getEarlySignDate().getTime();

					long maxDate1 = b.getLateSignDate().getTime();

					long minDate2 = b2.getEarlySignDate().getTime();

					long maxDate2 = b2.getLateSignDate().getTime();

					if (minDate1 < minDate2) {

						c.setEarlySignDate(b.getEarlySignDate());


						c.setEarlySignReeorDistance(b.getEarlySignReeorDistance());
					} else {
						c.setEarlySignDate(b2.getEarlySignDate());

						c.setEarlySignReeorDistance(b2.getEarlySignReeorDistance());
					}

					if (maxDate1 > maxDate2) {
						c.setLateSignDate(b.getLateSignDate());


						c.setLateSignErrorDistance(b.getLateSignErrorDistance());
					} else {
						c.setLateSignDate(b2.getLateSignDate());


						c.setLateSignErrorDistance(b2.getLateSignErrorDistance());
					}




					c.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);

					c.setAttendEmployeeId(b.getAttendEmployeeId());


					c.setAttendDate(b.getAttendDate());



					int early = c.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					int late = c.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					if (early <= 0 && late <= 0) {


						long i1 = 0;
						try {
							i1 = f3.parse(AttendConstantUtil.getDate2(c.getEarlySignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}


						long i2 = 0;
						try {
							i2 = f3.parse(AttendConstantUtil.getDate2(c.getLateSignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}


						long m1 = 0;
						try {
							m1 = f2.parse(
									AttendConstantUtil.getDate(c.getEarlySignDate()) + AttendConstantUtil.MIN_DATE)
									.getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						long m2 = 0;
						try {
							m2 = f2.parse(AttendConstantUtil.getDate(c.getLateSignDate()) + AttendConstantUtil.MAX_DATE)
									.getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						if (i1 <= m1 && i2 >= m2) {

							c.setAttendType(AttendConstantUtil.ATTEND_ALL);
						} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {

							c.setAttendType(AttendConstantUtil.ATTEND_HALF);
						} else {

							c.setAttendType(AttendConstantUtil.ATTEND_LACK);
						}
					} else {

						c.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}

					signs.add(c);
				}
			}
		}

		for (BizAttendDay sign : signs) {

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

				b.setSignTimes(bizAttendDay.getSignTimes());


				int early = bizAttendDay.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				int late = bizAttendDay.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				if (early <= 0 && late <= 0) {


					long i1 = 0;
					try {
						i1 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getEarlySignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}


					long i2 = 0;
					try {
						i2 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getLateSignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}


					long m1 = 0;
					try {
						m1 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getEarlySignDate())
								+ AttendConstantUtil.MIN_DATE).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}

					long m2 = 0;
					try {
						m2 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getLateSignDate())
								+ AttendConstantUtil.MAX_DATE).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}

					if (i1 <= m1 && i2 >= m2) {

						b.setAttendType(AttendConstantUtil.ATTEND_ALL);
					} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {

						b.setAttendType(AttendConstantUtil.ATTEND_HALF);
					} else {

						b.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
				} else {

					b.setAttendType(AttendConstantUtil.ATTEND_LACK);
				}

				signList.add(b);
			}
		}

		int i = insertAttend(signList);
		return i;
	}

	@Transactional
	public int pageList3() {

		List<BizAttendDay> signs = new ArrayList<BizAttendDay>();


		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		List<BizAttendDay> selectBusinessSigns = bizAttendDayDao.selectBusinessSigns();


		List<BizAttendDay> selectSigns = bizAttendDayDao.selectSigns();


		for (BizAttendDay b : selectBusinessSigns) {

			for (BizAttendDay b2 : selectSigns) {

				BizAttendDay c = new BizAttendDay();

				if (b.getAttendEmployeeId().intValue() == b2.getAttendEmployeeId().intValue()
						&& b.getAttendDate().getTime() == b2.getAttendDate().getTime()) {

					long minDate1 = b.getEarlySignDate().getTime();

					long maxDate1 = b.getLateSignDate().getTime();

					long minDate2 = b2.getEarlySignDate().getTime();

					long maxDate2 = b2.getLateSignDate().getTime();

					if (minDate1 < minDate2) {

						c.setEarlySignDate(b.getEarlySignDate());


						c.setEarlySignReeorDistance(b.getEarlySignReeorDistance());
					} else {
						c.setEarlySignDate(b2.getEarlySignDate());

						c.setEarlySignReeorDistance(b2.getEarlySignReeorDistance());
					}

					if (maxDate1 > maxDate2) {
						c.setLateSignDate(b.getLateSignDate());


						c.setLateSignErrorDistance(b.getLateSignErrorDistance());
					} else {
						c.setLateSignDate(b2.getLateSignDate());


						c.setLateSignErrorDistance(b2.getLateSignErrorDistance());
					}




					c.setAttendEmployeeRole(AttendConstantUtil.ATTEND_EMPLOYEE_ROLE);

					c.setAttendEmployeeId(b.getAttendEmployeeId());


					c.setAttendDate(b.getAttendDate());




					int early = c.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					int late = c.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

					if (early <= 0 && late <= 0) {


						long i1 = 0;
						try {
							i1 = f3.parse(AttendConstantUtil.getDate2(c.getEarlySignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}


						long i2 = 0;
						try {
							i2 = f3.parse(AttendConstantUtil.getDate2(c.getLateSignDate())).getTime();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}


						long m1 = 0;
						try {
							m1 = f2.parse(AttendConstantUtil.getDate(c.getEarlySignDate())
									+ AttendConstantUtil.MIN_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						long m2 = 0;
						try {
							m2 = f2.parse(AttendConstantUtil.getDate(c.getLateSignDate())
									+ AttendConstantUtil.MAX_DATE.toString()).getTime();
						} catch (ParseException e) {
							e.printStackTrace();
						}

						if (i1 <= m1 && i2 >= m2) {

							c.setAttendType(AttendConstantUtil.ATTEND_ALL);
						} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {

							c.setAttendType(AttendConstantUtil.ATTEND_HALF);
						} else {

							c.setAttendType(AttendConstantUtil.ATTEND_LACK);
						}
					} else {

						c.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}

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



				b.setSignTimes(bizAttendDay.getSignTimes());


				int early = bizAttendDay.getEarlySignReeorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				int late = bizAttendDay.getLateSignErrorDistance().compareTo(AttendConstantUtil.ATTEND_ERROR);

				if (early <= 0 && late <= 0) {


					long i1 = 0;
					try {
						i1 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getEarlySignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}


					long i2 = 0;
					try {
						i2 = f3.parse(AttendConstantUtil.getDate2(bizAttendDay.getLateSignDate())).getTime();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}


					long m1 = 0;
					try {
						m1 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getEarlySignDate())
								+ AttendConstantUtil.MIN_DATE.toString()).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}

					long m2 = 0;
					try {
						m2 = f2.parse(AttendConstantUtil.getDate(bizAttendDay.getLateSignDate())
								+ AttendConstantUtil.MAX_DATE.toString()).getTime();
					} catch (ParseException e) {
						e.printStackTrace();
					}

					if (i1 <= m1 && i2 >= m2) {

						b.setAttendType(AttendConstantUtil.ATTEND_ALL);
					} else if ((i1 <= m1 && i2 < m2) || (i1 > m1 && i2 >= m2)) {

						b.setAttendType(AttendConstantUtil.ATTEND_HALF);
					} else {

						b.setAttendType(AttendConstantUtil.ATTEND_LACK);
					}
				} else {

					b.setAttendType(AttendConstantUtil.ATTEND_LACK);
				}

				signList.add(b);
			}
		}

		int i = insertAttend(signList);
		return i;
	}


	public List<BizAttendDay> findAttendDaysByEmployeeId(Integer attendEmployeeId) {

		List<BizAttendDay> list = new ArrayList<BizAttendDay>();

		List<BizAttendDay> bizAttendDayList = bizAttendDayDao.findAttendDaysByEmployeeId(attendEmployeeId);

		for (BizAttendDay bizAttendDay : bizAttendDayList) {

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


	@Transactional
	public void upadteAttendDays(List<BizAttendDay> list) {
		bizAttendDayDao.upadteAttendDays(list);
	}

}