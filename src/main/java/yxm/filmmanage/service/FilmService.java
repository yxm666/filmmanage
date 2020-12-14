package yxm.filmmanage.service;

import yxm.filmmanage.entity.Film;

import java.util.List;

public interface FilmService {
    /**
     * 添加影片
     * @param film
     * @return
     */
    int add(Film film);

    /**
     * 更新影片信息
     * @param film
     * @return
     */
    Film update(Film film);

    /**
     * 根据id删除影片
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据id查找影片
     * @param id
     * @return
     */
    Film findById(Integer id);

    /**
     * 查看所有影片
     * @return
     */
    List<Film> findall();

    /**
     * 多条件查询影片
     * @param film
     * @return
     */
    List<Film> findExample(Film film);
}
