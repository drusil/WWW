package executor_service_with_pbq;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jakub Czajka
 */
class App {

  static BlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();

  public static void main(String[] args) {

    ExecutorService executorService = new ThreadPoolExecutor(5, 5,
        1L
        , MILLISECONDS
        , new PriorityBlockingQueue<>(10, new PriorityFutureComparator())) {
      protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        RunnableFuture<T> newTaskFor = super.newTaskFor(callable);
        return new PriorityFuture<>(newTaskFor, ((PriorityTask) callable).getPriotity());
      }

    };

    List<PriorityTask> collect = Stream.generate(PriorityTask::new).limit(200)
        .collect(Collectors.toList());
    try {
      executorService.invokeAll(collect);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    executorService.shutdown();
  }
}
