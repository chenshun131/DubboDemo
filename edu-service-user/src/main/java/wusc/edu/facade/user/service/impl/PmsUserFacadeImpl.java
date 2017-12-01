package wusc.edu.facade.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wusc.edu.common.page.PageBean;
import wusc.edu.common.page.PageParam;
import wusc.edu.facade.user.entity.PmsUser;
import wusc.edu.facade.user.service.PmsUserFacade;
import wusc.edu.service.user.dao.PmsUserDao;

import java.util.Map;

/**
 * User: chenshun131 <p />
 * Time: 17/11/12 14:52  <p />
 * Version: V1.0  <p />
 * Description: 用户 Dubbo 服务接口实现 <p />
 */
@Service("pmsUserFacade")
public class PmsUserFacadeImpl implements PmsUserFacade {

    @Autowired
    private PmsUserDao pmsUserDao;

    /**
     * 保存用户信息
     *
     * @param pmsUser
     */
    @Override
    public void create(PmsUser pmsUser) {
        pmsUserDao.insert(pmsUser);
    }

    /**
     * 根据ID获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public PmsUser getById(Long userId) {
        return pmsUserDao.getById(userId);
    }

    /**
     * 根据登录名取得用户对象
     */
    @Override
    public PmsUser findUserByUserNo(String userNo) {
        return pmsUserDao.findByUserNo(userNo);
    }

    /**
     * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除
     *
     * @param userId
     *         用户ID
     */
    @Override
    public void deleteUserById(long userId) {
        PmsUser pmsUser = pmsUserDao.getById(userId);
        if (pmsUser != null) {
            if ("1".equals(pmsUser.getUserType())) {
                throw new RuntimeException("【" + pmsUser.getUserNo() + "】为超级管理员，不能删除！");
            }
            pmsUserDao.deleteById(pmsUser.getId());
        }
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void update(PmsUser user) {
        pmsUserDao.update(user);
    }

    /**
     * 根据用户ID更新用户密码
     *
     * @param userId
     * @param newPwd
     *         (已进行SHA1加密)
     */
    @Override
    public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
        PmsUser pmsUser = pmsUserDao.getById(userId);
        pmsUser.setUserPwd(newPwd);
        pmsUser.setPwdErrorCount(0); // 密码错误次数重置为0
        pmsUser.setIsChangedPwd(isTrue); // 设置密码为已修改过
        pmsUserDao.update(pmsUser);
    }

    /**
     * 查询并分页列出用户信息
     *
     * @param pageParam
     * @param paramMap
     * @return
     */
    @Override
    public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
        return pmsUserDao.listPage(pageParam, paramMap);
    }

}
