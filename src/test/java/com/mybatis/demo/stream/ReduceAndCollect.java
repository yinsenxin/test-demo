package com.mybatis.demo.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * reduce 和 collect 的使用
 */
public class ReduceAndCollect {


    /**
     *  reduce 的使用
     */
    @Test
    public void testReduce(){

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Order{
            // 订单编号
            private Integer id;
            // 订单数量
            private Integer productCount;
            // 消费金额
            private Double totalAmount;
        }

        // 订单数据准备
        List<Order> list = new ArrayList<>();
        list.add(new Order(1,3, 22.1));
        list.add(new Order(2,4, 2322.01));
        list.add(new Order(3,5, 9000.11));

        // 求出此订单的平均价格 (所有 商品的总价/商品数量)
        // 方法一 : 一次性求出 总价 和  数量
        Order reduce = list.stream()
                .reduce(
                        // 初始化值
                        new Order(0, 0, 0.0),
                        // Stream 中两个元素的计算逻辑
                        (Order o1, Order o2) -> {
                            int productCount = o1.getProductCount() + o2.getProductCount();
                            double total = o1.getTotalAmount() + o2.getTotalAmount();
                            return new Order(0, productCount, total);
                        },
                        // 并行时, 多个并行结果如何合并
                        (Order o1, Order o2) -> {
                            int productCount = o1.getProductCount() + o2.getProductCount();
                            double total = o1.getTotalAmount() + o2.getTotalAmount();
                            return new Order(0, productCount, total);
                        }
                );

        // 方法二 : 单独求 数量 和 总价
        int sum = list.stream().filter(order -> order.getProductCount() != null)
                .mapToInt(Order::getProductCount).sum();
        double sum1 = list.stream().filter(order -> order.getTotalAmount() != null)
                .mapToDouble(Order::getTotalAmount).sum();
        System.out.println(sum1 / sum);


    }

    /**
     * collect 的使用
     */
    @Test
    public void testCollect(){
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Order{
            // 订单编号
            private Integer id;
            // 账号
            private String account;
            // 订单数量
            private Integer productCount;
            // 消费金额
            private Double totalAmount;
        }

        // 订单数据准备
        List<Order> list = new ArrayList<>();
        list.add(new Order(1, "lisi", 3, 22.1));
        list.add(new Order(2, "lisi", 4, 2322.01));
        list.add(new Order(3, "wangwu", 5, 9000.11));

        // 计算每个用户的平均商品价格
        /**
         * 方法一: 一次性 求出 Map<用户账号, 订单(数量, 总金额)>
         */
        HashMap<String, Order> maps = list.stream().collect(HashMap::new,

                (HashMap<String, Order> map, Order newOrder) -> {
                    String account = newOrder.getAccount();
                    if (map.containsKey(account)) {
                        Order order = map.get(account);
                        order.setProductCount(order.getProductCount() + newOrder.getProductCount());
                        order.setTotalAmount(order.getTotalAmount() + newOrder.getTotalAmount());
                        map.put(account, order);
                    } else {
                        map.put(account, newOrder);
                    }
                },
                (HashMap<String, Order> map1, HashMap<String, Order> map2) -> {
                    map2.forEach((key, value) -> {
                        map1.merge(key, value, ((order, order2) -> {
                            return new Order(0, key, order.getProductCount() + order2.getProductCount(),
                                    order.getTotalAmount() + order2.getTotalAmount());
                        }));
                    });
                });

        System.out.println(JSON.toJSONString(maps, true));


        /**
         * 方法二 : 分开求出 每个用户的数量 和 总价
         */
        Map<String, Order> map = new HashMap<>();
        Map<String, List<Order>> collect = list.stream().filter(order -> StringUtils.isNotBlank(order.getAccount())).collect(Collectors.groupingBy(Order::getAccount));




    }
}
