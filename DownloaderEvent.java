import java.util.ArrayList;

public abstract class DownloaderEvent implements ThreadCompletionListener {

    protected int noOfTasks;
    protected String taskUrl;
    protected int totalTaskTime;
    protected int totalTaskTimeCompleted = 0;
    protected ArrayList<Integer> taskTimes;

    @Override
    public void notifyOfThreadCompletion(Runnable runner) {
        if (runner instanceof Task) {
            Task taskRunner = (Task) runner;
            this.totalTaskTimeCompleted += taskRunner.getSize();
            float percentage = ((float) this.totalTaskTimeCompleted / (float) this.totalTaskTime) * 100;
            System.out.print(taskRunner.getName() + " Loaded " + (int) percentage + "%");

            postCompletionPrint(percentage);
        }
    }

    public abstract void postCompletionPrint(float percentage);

}
