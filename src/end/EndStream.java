package end;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create by zhatang on 2021/11/9.
 * 流的终止操作
 */
public class EndStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        /**流中元素的判断*/
        boolean flag1 = list.stream().allMatch(s -> s > 10); //所有元素符合才返回true
        boolean flag2 = list.stream().noneMatch(s -> s > 10);//所有元素不符合才返回true
        boolean flag3 = list.stream().allMatch(s -> s > 4);//有一个元素复合就返回true
        /**返回元素*/
        Integer first = list.stream().findFirst().get();
        Integer max = list.stream().max(Integer::compareTo).get();
        long count = list.stream().count();
        /**规约操作（运算结果作为下次输入参数）*/
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        Integer integer = list1.stream().reduce((x1, x2) -> x1 + x2).get();
        System.out.println(integer);
        //串行流，第三个参数不生效
        Integer integer2 = list1.stream().reduce(0, (x1, x2) -> {
            System.out.println("stream 累加器 :x1:" + x1 +" "+ "x2:" + x2);
            return x1 - x2;
        }, (x1, x2) -> {
            System.out.println("stream 累乘器 :x1" + x1 +" "+ "x2:" + x2);
            return x1 * x2;
        });
        System.out.println(integer2);
        //并行流，第三个参数生效
        Integer integer3 = list1.parallelStream().reduce(0, (x1, x2) -> {
            System.out.println("parallelStram 累加器 x1:" + x1 + " " + "x2:" + x2);
            return x1 - x2;
        }, (x1, x2) -> {
            System.out.println("parallelStream 累乘器 x1:" + x1 + " " + "x2:" + x2);
            return x1 * x2;
        });
        System.out.println(integer3);
        //collect接受Collector实例（Collectors封装方法产生Collector实例）
        Entity e1 = new Entity("zhatang", 26, "man");
        Entity e2 = new Entity("wly",25,"woman");
        //转换各种数据结构
        List<Entity> entityList = Arrays.asList(e1, e2);
        List<Entity> collectList = entityList.stream().collect(Collectors.toList());
        Set<Entity> collectSet = entityList.stream().collect(Collectors.toSet());
        Map<String, Integer> collectMap = entityList.stream().collect(Collectors.toMap(Entity::getName, Entity::getAge));
        String collectString = entityList.stream().map(Entity::getName).collect(Collectors.joining(",","(",")"));
        //其他collect的操作
        //聚合操作
//1.实体总数
        Long c = entityList.stream().collect(Collectors.counting());// 3
//2.最大年龄 (最小的minBy同理)
        Integer maxAge = entityList.stream().map(Entity::getAge).collect(Collectors.maxBy(Integer::compare)).get();
//3.所有人的年龄
        Integer sumAge = entityList.stream().collect(Collectors.summingInt(Entity::getAge));
//4.平均年龄
        Double averageAge = entityList.stream().collect(Collectors.averagingDouble(Entity::getAge));
// 带上以上所有方法
        DoubleSummaryStatistics statistics = entityList.stream().collect(Collectors.summarizingDouble(Entity::getAge));
        System.out.println("count:" + statistics.getCount() + ",max:" + statistics.getMax() + ",sum:" + statistics.getSum() + ",average:" + statistics.getAverage());
//分组
        Map<Integer, List<Entity>> ageMap = entityList.stream().collect(Collectors.groupingBy(Entity::getAge));
//多重分组,先根据类型分再根据年龄分
        Map<Integer, Map<Integer, List<Entity>>> typeAgeMap = entityList.stream().collect(Collectors.groupingBy(Entity::getAge, Collectors.groupingBy(Entity::getAge)));

//分区
        Map<Boolean, List<Entity>> partMap = entityList.stream().collect(Collectors.partitioningBy(v -> v.getAge() > 10));

//规约
        Integer allAge = entityList.stream().map(Entity::getAge).collect(Collectors.reducing(Integer::sum)).get(); //40　　

    }
}
