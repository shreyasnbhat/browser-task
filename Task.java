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

    @Override
    public void run() {

        int timeRemaining = this.size;

        while (timeRemaining > 0) {
            try {
                if (timeRemaining > 10) {
                    Thread.sleep(10);
                    timeRemaining -= 10;
                } else {
                    Thread.sleep(timeRemaining);
                    timeRemaining = 0;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyThreadCompletion();
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }
}
