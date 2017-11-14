public class Task implements Runnable {

    // This consists the sizeof the task in milliseconds
    private int size;
    private String name;
    private ThreadCompletionListener listener;

    public Task(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public void addOnCompletionListener(ThreadCompletionListener listener) {
        this.listener = listener;
    }

    public void notifyThreadCompletion() {
        if (listener != null) {
            listener.notifyOfThreadCompletion(this);
        }
    }

    public void notifyThreadHalfCompletion(){
      if(listener != null){
        listener.notifyOfThreadHalfCompletion(this);
      }
    }

    @Override
    public void run() {

        int timeRemaining = this.size;

        try {
              Thread.sleep(this.size);
        } catch (InterruptedException e) {
              e.printStackTrace();
        } finally {
          notifyThreadCompletion();
        }
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }
}
