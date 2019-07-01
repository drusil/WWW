package executor_service_with_pbq;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jakub Czajka
 */
class PriorityTask implements Callable<Object>{
 int priotity;
 String message;

  public PriorityTask() {
    this.priotity = ThreadLocalRandom.current().nextInt(1,10);
    this.message = "Costume message";
  }

  @Override
  public Object call() throws Exception {
    System.out.printf("Priority <%d> message: %s%n",priotity,message);
    return null;
  }

  public int getPriotity() {
    return priotity;
  }
}
