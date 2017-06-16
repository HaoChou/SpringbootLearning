package Job.test;


import Job.AbstractExecutorJobPool;
import Job.JobPool;
import Job.JobPoolManager;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class TestExecutorJobPool extends AbstractExecutorJobPool
{
    private String name;
    private JobPoolManager jobPoolManager;
    @Override
    public int getMaxConcurrentJobSize()
    {
        return 10;
    }

    @Override
    public String getName() {
        return name;
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

    public TestExecutorJobPool(String name)
    {
        this.name=name;
        init();
    }

    @Override
    public JobPool newInstance(String name)
    {
        JobPool jobPool= new TestExecutorJobPool(name);
        return jobPool;
    }
}
