package com.springdemo.elasticsearchdemo.dao;

/**
 * @Author:sgyt
 * @Description:Spring Data 的强大之处，就在于你不用写任何DAO处理，自动根据方法名或类的信息进行CRUD操作。
 * 只要你定义一个接口，然后继承Repository提供的一些子接口，就能具备各种基本的CRUD功能
 * @Date:2019/11/7 9:28
 */

import com.springdemo.elasticsearchdemo.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:定义ItemRepository 接口
 * @Param:
 * 	Item:为实体类
 * 	Long:为Item实体类中主键的数据类型
 */
@Component
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    List<Item> findByPriceBetween(double price1, double price2);

    List<Item> findByTitle(String title);

}
