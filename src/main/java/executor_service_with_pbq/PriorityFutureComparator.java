package executor_service_with_pbq;

import java.util.Comparator;

/**
 * @author Jakub Czajka
 */
class PriorityFutureComparator implements Comparator<Runnable> {

  @Override
  public int compare(Runnable o1, Runnable o2) {
    if (o1 == null && o2==null)
    return 0;
    else if(o1 == null)
      return -1;
    else if (o2 == null)
      return 1;
    else {
      int p1 = ((PriorityFuture<?>) o1).getPriority();
      int p2 = ((PriorityFuture<?>) o2).getPriority();
      return p1 - p2;
    }
  }
}
