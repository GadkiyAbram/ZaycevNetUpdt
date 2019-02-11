import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        //Исходные данные для решения задачи
//        list.addAll(Arrays.asList(0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11));
//        list.addAll(Arrays.asList(0, 2, 7, 0));
        list.addAll(Arrays.asList(2000000000, 0));
//        list.addAll(Arrays.asList(0, 0, 0, 0, 2000000000));
//        list.addAll(Arrays.asList(0, 0, 0, 0, 2000000000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
//        list.addAll(Arrays.asList(1000000000, 0, 0));
//        list.addAll(Arrays.asList(1000000000, 0, 0, 0, 0));
//        list.addAll(Arrays.asList(999999999, 0, 0, 0, 0));
//        list.addAll(Arrays.asList(0, 10, 42, 5, 3));

        InfinityLooking infinityLooking = new InfinityLooking(list);

        //Время старта алгоритма
        long start = System.currentTimeMillis();
        infinityLooking.countUntilMeet(list);

        //Время выполнения алгоритма
        System.out.println("Done in: " + (System.currentTimeMillis() - start));
        infinityLooking.showResult();
    }
}
