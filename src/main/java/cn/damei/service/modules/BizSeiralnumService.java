
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizSeiralnumDao;
import cn.damei.entity.modules.BizSeiralnum;


@Service
@Transactional(readOnly = true)
public class BizSeiralnumService extends CrudService2<BizSeiralnumDao, BizSeiralnum> {
	
	
    

	@Transactional(readOnly = false)
	public synchronized String getDateSequence(String bussinessType){
		if(StringUtils.isBlank(bussinessType)){
			return "";
		}
		
		BizSeiralnum seiralnum = dao.querySeiralnumByType(bussinessType);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		if(seiralnum == null){
			BizSeiralnum sei = new BizSeiralnum();
			sei.setBussinessType(bussinessType);
			sei.setLastSeiralnum(1);
			sei.setGenerateTime(date);
			dao.insert(sei);
			return bussinessType+format.format(date)+"0001";
		}
		if(format.format(date).equals(format.format(seiralnum.getGenerateTime()))){
			seiralnum.setLastSeiralnum(seiralnum.getLastSeiralnum()+1);
			seiralnum.setGenerateTime(date);
			dao.update(seiralnum);
			StringBuilder no = new StringBuilder();
			no.append("");
			int length = (String.valueOf(seiralnum.getLastSeiralnum())).length();
			for(int i=0;i<4-length;i++){
				no.append("0");
			}
			no = no.append(seiralnum.getLastSeiralnum()) ;
			return bussinessType+format.format(date)+no;
		}
		seiralnum.setGenerateTime(date);
		seiralnum.setLastSeiralnum(1);
		dao.update(seiralnum);
		return bussinessType+format.format(date)+"0001";
	}
}