import java.util.ArrayList;
import java.util.Random;

public abstract class DownloaderEvent implements ThreadCompletionListener {

    protected int noOfTasks;
    protected String taskUrl;
    protected int totalTaskTime;
    protected int totalTaskTimeCompleted = 0;
    protected ArrayList<Integer> taskTimes;

    public DownloaderEvent(int noOfTasks, String taskUrl) {
        this.noOfTasks = noOfTasks;
        this.taskUrl = taskUrl;
        this.totalTaskTime = 0;
        this.taskTimes = new ArrayList<>();

        Random rand = new Random();

        // The task should also spawn threads = noOfTasks
        for (int i = 0; i < noOfTasks; i++) {
            int taskSize = rand.nextInt(901) + 100;
            taskTimes.add(taskSize);
            this.totalTaskTime += taskSize;
        }

        System.out.println("Total Size of Tasks for " + taskUrl + " = " + this.totalTaskTime);

        for (int i = 0; i < noOfTasks; i++) {
            Task task = new Task(taskTimes.get(i), taskUrl);
            task.addOnCompletionListener(this);
            Thread taskThread = new Thread(task);
            taskThread.start();
        }

    }

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
