import java.util.ArrayList;

public abstract class DownloaderEvent implements ThreadCompletionListener{

    protected int noOfTasks;
    protected String taskUrl;
    protected int totalTaskTime;
    protected int totalTaskTimeCompleted = 0;
    protected ArrayList<Integer> taskTimes;

    @Override
    public void notifyOfThreadCompletion(Runnable runner) {
        onCompletionPrint(runner);
    }

    public abstract void onCompletionPrint(Runnable runner);

}
