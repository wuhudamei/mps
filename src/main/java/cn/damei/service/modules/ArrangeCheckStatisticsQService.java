
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ArrangeCheckStatisticsQDao;
import cn.damei.entity.modules.ArrangeCheckStatisticsQ;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class ArrangeCheckStatisticsQService extends CrudService<ArrangeCheckStatisticsQDao, ArrangeCheckStatisticsQ> {
	@Autowired
	private ArrangeCheckStatisticsQDao arrangeCheckStatisticsQDao;

	public ArrangeCheckStatisticsQ get(String id) {
		return super.get(id);
	}

	public List<ArrangeCheckStatisticsQ> findList(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ) {

		return super.findList(arrangeCheckStatisticsQ);
	}

	public Page<ArrangeCheckStatisticsQ> findPage(Page<ArrangeCheckStatisticsQ> page, ArrangeCheckStatisticsQ arrangeCheckStatisticsQ) {
		arrangeCheckStatisticsQ.setQcBillType(UserUtils.getUser().getStoreId() == null ? arrangeCheckStatisticsQ.getQcBillType() : UserUtils.getUser().getStoreId());

		List<ArrangeCheckStatisticsQ> findList = arrangeCheckStatisticsQDao.findPage(arrangeCheckStatisticsQ);

		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		arrayList.add("6");
		arrayList.add("9");
		for (ArrangeCheckStatisticsQ string : findList) {
			arrayList.remove(string.getQcchecknodeindex());
		}

		for (String string : arrayList) {
			ArrangeCheckStatisticsQ arrangeCheckStatisticsQ2 = new ArrangeCheckStatisticsQ();
			arrangeCheckStatisticsQ2.setQcchecknodeindex(string);
			arrangeCheckStatisticsQ2.setDatetime(arrangeCheckStatisticsQ.getAcceptCheckDatetimeString());

			arrangeCheckStatisticsQ2.setStoreId(arrangeCheckStatisticsQ.getQcBillType());
			findList.add(arrangeCheckStatisticsQ2);
		}

		sortStringMethod(findList);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < findList.size(); i++) {
			findList.get(i).setCotid((i + 1) + "");
			findList.get(i).setIndustry(findList.get(i).getIndustry() == null ? "0" : findList.get(i).getIndustry());
			findList.get(i).setTradition(findList.get(i).getTradition() == null ? "0" : findList.get(i).getTradition());
			findList.get(i).setTotal((Integer.parseInt(findList.get(i).getIndustry() == null ? "0" : findList.get(i).getIndustry()) + Integer.parseInt(findList.get(i).getTradition() == null ? "0" : findList.get(i).getTradition())) + "");

			
			findList.get(i).setDatetime(sdf.format(arrangeCheckStatisticsQ.getAcceptCheckDatetimeStart())+"至"+sdf.format(arrangeCheckStatisticsQ.getAcceptCheckDatetimeEnd()));
			if ((findList.get(i).getQcchecknodeindex().equals("1")) && (i == 0) && (findList.get(i).getChecknodename() == null)) {


				findList.get(i).setStoreName(findList.get(i).getStoreName());
				findList.get(i).setChecknodename("水电隐蔽验收");
				findList.get(i).setIndustry("0");
				findList.get(i).setTradition("0");
				findList.get(i).setTotal("0");
				findList.get(i).setQcchecknodeindex("0");

			} else if ((findList.get(i).getQcchecknodeindex().equals("2")) && (i == 1) && (findList.get(i).getChecknodename() == null)) {

				findList.get(i).setStoreName(findList.get(i).getStoreName());
				findList.get(i).setChecknodename("闭水实验和瓦工隐蔽工程验收");
				findList.get(i).setIndustry("0");
				findList.get(i).setTradition("0");
				findList.get(i).setTotal("0");
				findList.get(i).setQcchecknodeindex("0");
			} else if ((findList.get(i).getQcchecknodeindex().equals("3")) && (i == 2) && (findList.get(i).getChecknodename() == null)) {

				findList.get(i).setStoreName(findList.get(i).getStoreName());
				findList.get(i).setChecknodename("墙地砖验收");
				findList.get(i).setIndustry("0");
				findList.get(i).setTradition("0");
				findList.get(i).setTotal("0");
				findList.get(i).setQcchecknodeindex("0");
			} else if ((findList.get(i).getQcchecknodeindex().equals("6")) && (i == 3) && (findList.get(i).getChecknodename() == null)) {

				findList.get(i).setStoreName(findList.get(i).getStoreName());
				findList.get(i).setChecknodename("涂饰工程及基装验收");
				findList.get(i).setIndustry("0");
				findList.get(i).setTradition("0");
				findList.get(i).setTotal("0");
				findList.get(i).setQcchecknodeindex("0");
			} else if ((findList.get(i).getQcchecknodeindex().equals("9")) && (i == 4) && (findList.get(i).getChecknodename() == null)) {

				findList.get(i).setStoreName(findList.get(i).getStoreName());
				findList.get(i).setChecknodename("竣工验收");
				findList.get(i).setIndustry("0");
				findList.get(i).setTradition("0");
				findList.get(i).setTotal("0");
				findList.get(i).setQcchecknodeindex("0");
			}
		}
		return page.setList(findList);
	}

	@Transactional(readOnly = false)
	public void save(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ) {
		super.save(arrangeCheckStatisticsQ);
	}

	@Transactional(readOnly = false)
	public void delete(ArrangeCheckStatisticsQ arrangeCheckStatisticsQ) {
		super.delete(arrangeCheckStatisticsQ);
	}

	@SuppressWarnings("unchecked")
	public static void sortStringMethod(List list) {
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				ArrangeCheckStatisticsQ stu1 = (ArrangeCheckStatisticsQ) o1;
				ArrangeCheckStatisticsQ stu2 = (ArrangeCheckStatisticsQ) o2;
				return stu1.getQcchecknodeindex().compareTo(stu2.getQcchecknodeindex());
			}
		});

	}

}