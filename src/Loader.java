import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Loader
{

    public static void main(String[] args) throws Exception
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ArrayList<Callable<Long>> taskList = new ArrayList<>();

        taskList.add(new Task("./result/numbers1.txt"));
        taskList.add(new Task("./result/numbers2.txt"));

        long start = System.currentTimeMillis();

        List<Future<Long>> results = executor.invokeAll(taskList);

        System.out.println("Общее вермя выполнения - " + (System.currentTimeMillis() - start));
        executor.shutdown();

        results.forEach(result -> {
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        }


}
