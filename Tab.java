import java.util.ArrayList;
import java.util.Random;

public class Tab extends DownloaderEvent {

    public Tab(int noOfTasks, String taskUrl) {

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

        System.out.println("Total Size of Tasks= " + this.totalTaskTime);

        for (int i = 0; i < noOfTasks; i++) {
            Task task = new Task(taskTimes.get(i), taskUrl);
            task.addOnCompletionListener(this);
            Thread taskThread = new Thread(task);
            taskThread.start();
        }

    }

    @Override
    public void postCompletionPrint(float percentage) {
        if ((int) percentage == 100) {
            System.out.println("  Tab Idle");
        } else {
            System.out.println();

        }
    }
}
