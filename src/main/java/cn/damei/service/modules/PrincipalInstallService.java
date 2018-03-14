package cn.damei.service.modules;

import cn.damei.dao.modules.PrincipalInstallDao;
import cn.damei.entity.modules.PrincipalInstall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dd>描述:主材台账Service</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/14</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
@Service
public class PrincipalInstallService {
    @Autowired
    private PrincipalInstallDao principalInstallDao;
    /**
     * 主材台账
     * @param orderno 订单号
     */
    public List<PrincipalInstall> findPrincipalInstall(String orderno){
        try{
            return this.principalInstallDao.findPrincipalInstall(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
