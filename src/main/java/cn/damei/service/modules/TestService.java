
package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.Test;
import cn.damei.dao.modules.TestDao;


@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}
