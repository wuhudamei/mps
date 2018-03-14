
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardNumberSquare;


@MyBatisDao
public interface BizMaterialsStandardNumberSquareDao extends CrudDao2<BizMaterialsStandardNumberSquare> {

	List<String> getIdByMaterialsId(String materialsId);
}