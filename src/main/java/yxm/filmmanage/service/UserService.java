package yxm.filmmanage.service;

import yxm.filmmanage.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 判断用户登陆
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 查看所有用户
     * @return
     */
    List<User> findall();

    /**
     * 多条件查询用户
     * @param user
     * @return
     */
    List<User> findExample(User user);




}
