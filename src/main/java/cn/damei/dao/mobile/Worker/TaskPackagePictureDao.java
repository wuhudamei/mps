package cn.damei.dao.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.TaskPackagePicture;

@MyBatisDao
public interface TaskPackagePictureDao extends CrudDao2<TaskPackagePicture>{

	List<TaskPackagePicture> findPicturesByPackageId(Integer id);

}
