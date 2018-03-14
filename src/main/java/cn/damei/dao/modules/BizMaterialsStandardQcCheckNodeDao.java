
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardQcCheckNode;


@MyBatisDao
public interface BizMaterialsStandardQcCheckNodeDao extends CrudDao2<BizMaterialsStandardQcCheckNode> {

	List<Map<String,String>>getMaterialsTypeByType(String type);

	List<Map<String,String>>getCheckNodeListByStoreIdAndMode(@Param("storeId")String storeId,@Param("projectMode")String projectMode);

	Integer getCheckNodeByOther(@Param("storeId")String storeId,@Param("projectMode")String projectMode,@Param("materialType")String materialType);
}