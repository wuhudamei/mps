package cn.damei.service.modules;

import cn.damei.dao.modules.PrincipalInstallDao;
import cn.damei.entity.modules.PrincipalInstall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PrincipalInstallService {
    @Autowired
    private PrincipalInstallDao principalInstallDao;

    public List<PrincipalInstall> findPrincipalInstall(String orderno){
        try{
            return this.principalInstallDao.findPrincipalInstall(orderno);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
