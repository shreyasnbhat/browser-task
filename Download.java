public class Download extends Event {

    public Download(int noOfTasks, String taskUrl) {
        super(noOfTasks, taskUrl);
    }

    @Override
    public void postCompletionPrint(float percentage) {
        if ((int) percentage == 100) {
            System.out.println("  Download Completed");
        } else {
            System.out.println();

        }
    }
}
