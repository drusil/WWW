package executor_service_with_pbq;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Jakub Czajka
 */
class PriorityFuture<T> implements RunnableFuture<T> {
  RunnableFuture<T> src;
  int priority;

  public PriorityFuture(RunnableFuture<T> src, int priority) {
    this.src = src;
    this.priority = priority;
  }

  @Override
  public void run() {
  src.run();
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return src.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return src.isCancelled();
  }

  @Override
  public boolean isDone() {
    return src.isDone();
  }

  @Override
  public T get() throws InterruptedException, ExecutionException {
    return src.get();
  }

  @Override
  public T get(long timeout, TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    return src.get(timeout,unit);
  }

  public int getPriority() {
    return priority;
  }
}
