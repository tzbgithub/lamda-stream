package create;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Create by zhatang on 2021/11/4.
 * 创建流的方式
 */
public class CreateStream {


    public static void main(String[] args) {
        /**创建Collection()流,Collection集合的stream和parallelStream*/
        ArrayList<Object> list = new ArrayList<>();
        Stream<Object> listStream = list.stream();
        Stream<Object> parallelListStream = list.parallelStream();//创建并行流
        /**创建数组流(Arrays的stream)*/
        IntStream arrStream = Arrays.stream(new int[]{1, 2, 3});
        /**Stream的静态方法（of,iterate,generate）*/
        Stream<String> stream = Stream.of("1", "2", "3");
        /**将每一行内容转换流(BufferReader.lines)*/
        try {
            BufferedReader reader = new BufferedReader(new FileReader("F:\\test.text"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**字符串分割成流*/
        Pattern pattern = Pattern.compile(",");
        Stream<String> stringStream = pattern.splitAsStream("a,b,c,d");
        stringStream.forEach(System.out::println);

    }
}
