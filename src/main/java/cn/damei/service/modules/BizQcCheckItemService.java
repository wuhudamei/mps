
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcCheckItem;
import cn.damei.entity.modules.BizQcCheckKind;
import cn.damei.dao.modules.BizQcCheckItemDao;


@Service
@Transactional(readOnly = true)
public class BizQcCheckItemService extends CrudService2<BizQcCheckItemDao, BizQcCheckItem> {

	public BizQcCheckItem get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcCheckItem> findList(BizQcCheckItem bizQcCheckItem) {
		return super.findList(bizQcCheckItem);
	}
	
	public Page<BizQcCheckItem> findPage(Page<BizQcCheckItem> page, BizQcCheckItem bizQcCheckItem) {
		return super.findPage(page, bizQcCheckItem);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcCheckItem bizQcCheckItem) {
        if(StringUtils.isEmpty(bizQcCheckItem.getTaskPackageTemplatId())){
            bizQcCheckItem.setTaskPackageTemplatId(null);
        }
		super.save(bizQcCheckItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcCheckItem bizQcCheckItem) {
		super.delete(bizQcCheckItem);
	}

	public List<BizQcCheckKind> findCheckKind(BizQcCheckKind kind) {
		return dao.findCheckKind(kind);
	}

	public String findName(int a) {
		return dao.findName(a);
	}

    public List<Map<String,String>> taskPackageTemplat(Integer id) {
		return dao.taskPackageTemplat(id);
    }

    public List<Map<String,String>> ueryQcCheckKindAll(BizQcCheckItem bizQcCheckItem) {

        List<Map<String,String>> resultlist = new ArrayList<>();
        BizQcCheckItem bizQcCheckItemper=new BizQcCheckItem ();
        bizQcCheckItemper.setQcCheckKindId(bizQcCheckItem.getId());
        Map<String,String>  qcCheckKindmap =  new HashMap<>();



        List<BizQcCheckItem> list = dao.findList(bizQcCheckItemper);
        int notQcCheckKind =0;
        int yesQcCheckKind =0;
        for (BizQcCheckItem qcCheckItem : list) {
            if(bizQcCheckItem.getTaskPackageTemplatId().equals(qcCheckItem.getTaskPackageTemplatId())){
                yesQcCheckKind++;
            }else{
                notQcCheckKind++;
            }
            qcCheckKindmap.put("yesQcCheckKind",yesQcCheckKind+"");
            qcCheckKindmap.put("notQcCheckKind",notQcCheckKind+"");
        }
        resultlist.add(qcCheckKindmap);
        return resultlist;
    }
    @Transactional(readOnly = false)
    public List<Map<String,String>> updatecheckItem(BizQcCheckItem bizQcCheckItem) {
        List<Map<String,String>> resultlist = new ArrayList<>();
        Map<String,String>  qcCheckKindmap =  new HashMap<>();

        int resulti= dao.updatecheckItem(bizQcCheckItem);

        return resultlist;
    }
}