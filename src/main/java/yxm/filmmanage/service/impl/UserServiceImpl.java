package yxm.filmmanage.service.impl;

import yxm.filmmanage.entity.User;
import yxm.filmmanage.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import yxm.filmmanage.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpa userJpa;

    /**
     * 判断用户登陆
     * @param user
     * @return
     */
    @Override
    public User register(User user) {
        // 根据用户对象创建查询条件
        Example<User> example = Example.of(user);
        // 根据创建的查询条件进行查询
        Optional<User> userOptional = userJpa.findOne(example);
        // 判断返回的结果是否为空
        if (userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public int add(User user) {
        userJpa.save(user);
        return 1;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        return userJpa.save(user);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        userJpa.deleteById(id);
        return 1;
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        //使用getOne()返回的是代理对象，无法直接操作，会出现hibernate lazyxxx  no session 的错误
        //在测试方法上加入@Transactional注解可以解决报错的问题
        // User u = userJpa.getOne(id);
        //  发现可以使用findById()，先调用findById()返回封装后的对象，然后使用get()方法，返回实体对象。
        Optional<User> userOptional = userJpa.findById(id);
        // 在Optional类中有很多内置的方法，其中isPresen()方法返回Optional对象是否为null的结果，
        // 如果当前Optional对象有值，则返回true，否则返回false，
        // 当结果有值时，然后调用它的get()方法，会返回一个<T>类型的对象，即我们要查询的实例结果。
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        return null;
    }

    /**
     * 查看所有用户
     * @return
     */
    @Override
    public List<User> findall() {
        return userJpa.findAll();
    }

    /**
     * 多条件查询用户
     * @param user
     * @return
     */
    @Override
    public List<User> findExample(User user) {
        System.out.printf("UserServiceImpl/findExample?user="+user+"\n");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("userName",ExampleMatcher.GenericPropertyMatchers.contains())         //用户姓名设置模糊查询
                .withMatcher("tel",ExampleMatcher.GenericPropertyMatchers.contains())              //用户电话设置模糊查询
                .withMatcher("address",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("sex",ExampleMatcher.GenericPropertyMatchers.contains());         //用户地址设置模糊查询
        Example<User> userExample = Example.of(user,matcher);
        return userJpa.findAll(userExample);
    }
}
