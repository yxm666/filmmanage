package yxm.filmmanage.service.impl;

import yxm.filmmanage.entity.Film;
import yxm.filmmanage.jpa.FilmJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import yxm.filmmanage.service.FilmService;

import java.util.List;
import java.util.Optional;


@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmJpa filmJpa;

    /**
     * 添加影片
     * @param film
     * @return
     */
    @Override
    public int add(Film film) {

        filmJpa.save(film);
        return 1;
    }

    /**
     * 更新影片信息
     * @param film
     * @return
     */
    @Override
    public Film update(Film film) {
        return filmJpa.save(film);
    }

    /**
     * 根据id删除影片
     * @param id
     * @return
     */
    @Override
    public int delete(Integer id) {
        filmJpa.deleteById(id);
        return 1;
    }

    /**
     * 根据id查找影片
     * @param id
     * @return
     */
    @Override
    public Film findById(Integer id) {
        //使用getOne()返回的是代理对象，无法直接操作，会出现hibernate lazyxxx  no session 的错误
        //在测试方法上加入@Transactional注解可以解决报错的问题
        // Film u = filmJpa.getOne(id);
        //  发现可以使用findById()，先调用findById()返回封装后的对象，然后使用get()方法，返回实体对象。
        Optional<Film> filmOptional = filmJpa.findById(id);
        // 在Optional类中有很多内置的方法，其中isPresen()方法返回Optional对象是否为null的结果，
        // 如果当前Optional对象有值，则返回true，否则返回false，
        // 当结果有值时，然后调用它的get()方法，会返回一个<T>类型的对象，即我们要查询的实例结果。
        if(filmOptional.isPresent()){
            return filmOptional.get();
        }
        return null;
    }

    /**
     * 查看所有影片
     * @return
     */
    @Override
    public List<Film> findall() {
        return filmJpa.findAll();
    }

    /**
     * 多条件查询影片
     * @param film
     * @return
     */
    @Override
    public List<Film> findExample(Film film) {
        /**关于ExampleMatcher详见：https://blog.csdn.net/moshowgame/article/details/80282813
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("filmName", ExampleMatcher.GenericPropertyMatchers.contains());//全部模糊查询，即%{address}%
                //.withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.startsWith());//模糊查询匹配开头，即{username}%
                //.withIgnorePaths("password");//忽略字段，即不管password是什么值都不加入查询条件
         */
        System.out.println(film);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("filmName",ExampleMatcher.GenericPropertyMatchers.contains())        //电影名称设置模糊查询
                .withMatcher("director",ExampleMatcher.GenericPropertyMatchers.contains())        //导演设置模糊查询
                .withMatcher("classify",ExampleMatcher.GenericPropertyMatchers.contains())        //类型设置模糊查询
                .withMatcher("showtime",ExampleMatcher.GenericPropertyMatchers.contains());       //上映时间设置模糊查询
        //执行查询
        Example<Film> filmExample = Example.of(film,matcher);
        return filmJpa.findAll(filmExample);
    }
}
