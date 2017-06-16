package Job.test;



import Job.AbstractExecutorJobPoolManager;
import Job.JobGroupResult;
import Job.JobPool;
import Job.JobPoolManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class TestExecutorPoolManager extends AbstractExecutorJobPoolManager
{
    static int maxGroupSize=3;
    static TestExecutorPoolManager instance=new TestExecutorPoolManager();

    public TestExecutorPoolManager()
    {

        init();
    }
    @Override
    public int getMaxGroupSize()
    {
        return maxGroupSize;
    }

    @Override
    public void init()
    {
        for(int i=0;i<maxGroupSize;i++)
        {
            System.out.println("add "+i);
            jobGroupList.add(new TestExecutorJobPool());
        }
    }

    public List<JobPool> getJobGroupList()
    {
        return jobGroupList;
    }

    @Override
    public void stop()
    {

    }

    static public JobPoolManager getInstance()
    {
        return instance;
    }


    public static void main(String[] args) throws InterruptedException
    {
        String groupName="zhou";
        List<TestJob> testJobList=new ArrayList<>();
        for(int i=0;i<40;i++)
        {
            testJobList.add(new TestJob(""+i,groupName));
        }

        String groupName2="zhou2";
        List<TestJob> testJobList2=new ArrayList<>();
        for(int i=0;i<40;i++)
        {
            testJobList2.add(new TestJob(""+i,groupName2));
        }
        TestExecutorPoolManager.getInstance().getMostFreeJobGroup().commitJobs(new TestJobGroup(groupName,testJobList.size(),testJobList));
        TestExecutorPoolManager.getInstance().getMostFreeJobGroup().commitJobs(new TestJobGroup(groupName,testJobList.size(),testJobList2));


        Thread.sleep(4000);
        JobGroupResult result= TestExecutorPoolManager.getInstance().getJobProgressResultByGroupName(groupName);
        System.out.println(JSON.toJSON(result));
    }
}
