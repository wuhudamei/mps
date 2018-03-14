
package cn.damei.service.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.QcItemGroupMangerDao;
import cn.damei.entity.modules.QcItemGroupManger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class QcItemGroupMangerService extends CrudService<QcItemGroupMangerDao, QcItemGroupManger> {

    public QcItemGroupManger get(String id) {
        return super.get(id);
    }

    public List<QcItemGroupManger> findList(QcItemGroupManger qcItemGroupManger) {
        return super.findList(qcItemGroupManger);
    }

    public Page<QcItemGroupManger> findPage(Page<QcItemGroupManger> page, QcItemGroupManger qcItemGroupManger) {

        qcItemGroupManger.setPage(page);
        List<QcItemGroupManger> list = dao.findList(qcItemGroupManger);

        List<QcItemGroupManger> listCountAndIllegal = dao.queryWorkerGrouPunishCountAndIllegal(qcItemGroupManger);
        if (null != list && null != listCountAndIllegal) {
            for (QcItemGroupManger itemGroupManger : list) {
                for (QcItemGroupManger groupManger : listCountAndIllegal) {
                    if (!StringUtils.isEmpty(itemGroupManger.getWorkerGroupId()) && !StringUtils.isEmpty(groupManger.getWorkerGroupId()) && itemGroupManger.getWorkerGroupId().equals(groupManger.getWorkerGroupId())) {
                        itemGroupManger.setWorkerGrouPunishCount(groupManger.getWorkerGrouPunishCount());
                        itemGroupManger.setWorkerPunishAmount(groupManger.getWorkerPunishAmount());
                    }
                }
            }
        }
        page.setList(list);

        return page;
    }

    @Transactional(readOnly = false)
    public void save(QcItemGroupManger qcItemGroupManger) {
        super.save(qcItemGroupManger);
    }

    @Transactional(readOnly = false)
    public void delete(QcItemGroupManger qcItemGroupManger) {
        super.delete(qcItemGroupManger);
    }

    public Page<QcItemGroupManger> findManagerPage(Page<QcItemGroupManger> page, QcItemGroupManger qcItemGroupManger) {


        qcItemGroupManger.setPage(page);
        List<QcItemGroupManger> list = dao.findManagerPage(qcItemGroupManger);
        List<QcItemGroupManger> queryMaPunishCountAndIllegallist = dao.queryMaPunishCountAndIllegal(qcItemGroupManger);
        if (null != list && null != queryMaPunishCountAndIllegallist) {
            for (QcItemGroupManger itemGroupManger : list) {
                for (QcItemGroupManger itemGroupMangerCountAndIllega : queryMaPunishCountAndIllegallist) {
                    if (!StringUtils.isEmpty(itemGroupManger.getProjectManagerId()) && !StringUtils.isEmpty(itemGroupMangerCountAndIllega.getProjectManagerId()) && itemGroupManger.getProjectManagerId().equals(itemGroupMangerCountAndIllega.getProjectManagerId())) {
                        itemGroupManger.setMnagerPersonPunishCount(itemGroupMangerCountAndIllega.getMnagerPersonPunishCount());
                        itemGroupManger.setPunishMoneyAmountRealSum(itemGroupMangerCountAndIllega.getPunishMoneyAmountRealSum());
                    }
                }
            }
        }
        page.setList(list);

        return page;
    }

    public Page<QcItemGroupManger> queryQcItemMangerIllegalDetails(Page<QcItemGroupManger> page, QcItemGroupManger qcItemGroupManger) {

        qcItemGroupManger.setPage(page);
        List<QcItemGroupManger> list = dao.queryQcItemMangerIllegalDetails(qcItemGroupManger);
        page.setList(list);
        return page;
    }

    public Page<QcItemGroupManger> queryQcItemMangerPunishDetails(Page<QcItemGroupManger> page, QcItemGroupManger qcItemGroupManger) {
        qcItemGroupManger.setPage(page);
        List<QcItemGroupManger> list = dao.queryQcItemMangerPunishDetails(qcItemGroupManger);
        page.setList(list);
        return page;
    }

    public Page<QcItemGroupManger> queryQcItemGroupIllegalDetails(Page<QcItemGroupManger> qcItemGroupMangerPage, QcItemGroupManger qcItemGroupManger) {
        qcItemGroupManger.setPage(qcItemGroupMangerPage);
        List<QcItemGroupManger> list = dao.queryQcItemGroupIllegalDetails(qcItemGroupManger);
        qcItemGroupMangerPage.setList(list);
        return qcItemGroupMangerPage;
    }

    public Page<QcItemGroupManger> queryQcItemGroupPunishDetails(Page<QcItemGroupManger> qcItemGroupMangerPage, QcItemGroupManger qcItemGroupManger) {
        qcItemGroupManger.setPage(qcItemGroupMangerPage);
        List<QcItemGroupManger> list = dao.queryQcItemGroupPunishDetails(qcItemGroupManger);
        qcItemGroupMangerPage.setList(list);
        return qcItemGroupMangerPage;
    }
}