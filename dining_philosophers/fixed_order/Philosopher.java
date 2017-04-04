
import java.util.Random;

class Philosopher extends Thread {
  private Chopstick first, second;
  private Random random;
  private int thinkCount;

  public Philosopher(Chopstick left, Chopstick right) {
    if(left.getId() < right.getId()) {
      first = left; second = right;
    } else {
      first = right; second = left;
    }
    random = new Random();
  }

  public void run() {
    try {
      while(true) {
        ++thinkCount;
        if (thinkCount % 10 == 0)
          System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");
        Thread.sleep(random.nextInt(10));     // Think for a while
        synchronized(first) {                   // Grab first chopstick
          synchronized(second) {                // Grab second chopstick
            Thread.sleep(random.nextInt(10)); // Eat for a while
          }
        }
      }
    } catch(InterruptedException e) {}
  }
}
