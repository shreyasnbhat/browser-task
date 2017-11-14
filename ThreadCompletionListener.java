public interface ThreadCompletionListener {

    void notifyOfThreadCompletion(Runnable runner);
    void notifyOfThreadHalfCompletion(Runnable runner);

}
