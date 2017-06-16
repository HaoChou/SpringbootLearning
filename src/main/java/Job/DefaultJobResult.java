package Job;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class DefaultJobResult implements JobResult
{
    private String jobName;
    private String jobGroupName;
    private boolean isSuccess;
    public DefaultJobResult(String jobName,String jobGroupName,boolean isSuccess)
    {
        this.jobGroupName=jobGroupName;
        this.jobName=jobName;
        this.isSuccess=isSuccess;
    }

    @Override
    public String getJobName()
    {
        return jobName;
    }

    @Override
    public String getJobGroupName()
    {
        return jobGroupName;
    }

    @Override
    public boolean isSuccess()
    {
        return isSuccess;
    }
}
