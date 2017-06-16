package Job.test;


import Job.AbstractExecutorJobPool;
import Job.JobPool;
import Job.JobPoolManager;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class TestExecutorJobPool extends AbstractExecutorJobPool
{
    private JobPoolManager jobPoolManager;
    @Override
    public int getMaxConcurrentJobSize()
    {
        return 10;
    }

    @Override
    public int getMaxJobWaitingQueueSize()
    {
        return 100;
    }

    @Override
    public JobPoolManager getJobPoolManager()
    {
        return TestExecutorPoolManager.getInstance();
    }

    public TestExecutorJobPool()
    {
        init();
    }

    @Override
    public JobPool newInstance(JobPoolManager jobPoolManager)
    {
        JobPool jobPool= new TestExecutorJobPool();
        return jobPool;
    }
}
