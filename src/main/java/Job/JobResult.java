package Job;

/**
 * Created by zhouhao on 2017/6/16.
 */
public interface JobResult
{
    /**
     * 任务的名称
     * @return
     */
    String getJobName();

    /**
     * 任务组的名称
     * @return
     */
    String getJobGroupName();

    /**
     * 是否成功
     * @return
     */
    boolean isSuccess();
}

