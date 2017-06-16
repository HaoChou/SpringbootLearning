package Job;

/**
 * Created by zhouhao on 2017/6/16.
 */
public interface JobGroupResult
{
    String getGroupName();

    public int getTotalCount();

    int getCompletedCount();

    int getSuccessCount();

    void increaseSuccessCount();

    int getFailedCount();

    void increaseFailedCount();

    String[] getFailedNames();

    void addFailedName(String failedJobName);
}
