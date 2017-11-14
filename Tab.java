public class Tab extends Event {

    public Tab(int noOfTasks, String taskUrl) {
        super(noOfTasks, taskUrl);
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
