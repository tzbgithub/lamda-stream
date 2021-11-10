package operate;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Create by zhatang on 2021/11/9.
 * 流的中间操作
 */
public class OperateStream {
    public static void main(String[] args) {
        /**过滤和切割*/
        Stream<Integer> stream = Stream.of(100,23,45,2,4,5,1,3,2);

        Stream<Integer> newStream = stream.filter(s -> !s.equals("a"))
                .distinct()
                .skip(3)
                .limit(2);

        newStream.forEach(System.out::println);


        /**映射(map(元素转换),flatMap(流转换))*/

        List<String> list = Arrays.asList("a,b,c", "1,2,3");
        Stream<String> stream1 = list.stream().map(s -> s.replace(",", ""));
        stream1.forEach(System.out::println);

        Stream s3 = list.stream().flatMap(s -> {
            String[] split = s.split(",");
            Stream<String> stream2 = Arrays.stream(split);
            return stream2;
        });
        s3.forEach(System.out::println);

        /**排序*/
        List<String> noSortList = Arrays.asList("aa", "ff", "dd");
        noSortList.stream().sorted().forEach(System.out::println);


        SortEntity man1 = new SortEntity("tzb", 26, "man");
        SortEntity man2 = new SortEntity("zmy", 21, "man");
        SortEntity man3 = new SortEntity("zj", 26, "man");
        SortEntity man4 = new SortEntity("sjz", 25, "woman");

        List<SortEntity> sortEntities = Arrays.asList(man1, man2, man3, man4);
        sortEntities.stream().sorted(
                (o1,o2)->{
                    String name1 = o1.getName();
                    String name2 = o2.getName();
                    if (name1.equals(name1)) {
                        return o1.getAge() - o2.getAge();
                    } else {
                        return name1.compareTo(name2);
                    }
                }
        );

        /**消费（peek遍历流中每一个元素，接受函数，但是没有返回值）*/
        sortEntities.stream()
                .peek(o -> o.setAge(-1))
                .forEach(System.out::println);
        System.out.println();
    }
}
