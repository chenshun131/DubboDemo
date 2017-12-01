package wusc.edu.facade.user.service;

import wusc.edu.common.page.PageBean;
import wusc.edu.common.page.PageParam;
import wusc.edu.facade.user.entity.PmsUser;

import java.util.Map;

/**
 * User: chenshun131 <p />
 * Time: 17/11/12 14:46  <p />
 * Version: V1.0  <p />
 * Description: 用户服务接口 <p />
 */
public interface PmsUserFacade {

    /**
     * 保存用户信息
     *
     * @param pmsUser
     */
    void create(PmsUser pmsUser);

    /**
     * 根据ID获取用户信息
     *
     * @param userId
     * @return
     */
    PmsUser getById(Long userId);

    /**
     * 根据登录名取得用户对象
     */
    PmsUser findUserByUserNo(String userNo);

    /**
     * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除
     *
     * @param userId
     *         用户ID
     */
    void deleteUserById(long userId);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(PmsUser user);

    /**
     * 根据用户ID更新用户密码
     *
     * @param userId
     * @param newPwd
     *         (已进行SHA1加密)
     */
    void updateUserPwd(Long userId, String newPwd, boolean isTrue);

    /**
     * 查询并分页列出用户信息
     *
     * @param pageParam
     * @param paramMap
     * @return
     */
    PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);

}
