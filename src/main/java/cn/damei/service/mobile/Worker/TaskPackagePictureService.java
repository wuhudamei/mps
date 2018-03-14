package cn.damei.service.mobile.Worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.TaskPackagePictureDao;
import cn.damei.entity.mobile.Worker.TaskPackagePicture;

@Service
@Transactional(readOnly = true)
public class TaskPackagePictureService extends CrudService2<TaskPackagePictureDao, TaskPackagePicture> {

	public List<TaskPackagePicture> findPicturesByPackageId(Integer id) {
		// TODO Auto-generated method stub
		return dao.findPicturesByPackageId(id);
	}

}
