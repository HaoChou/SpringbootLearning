package Job.test;



import Job.AbstractExecutorJobPoolManager;
import Job.JobGroupResult;
import Job.JobPool;
import Job.JobPoolManager;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class TestExecutorPoolManager extends AbstractExecutorJobPoolManager
{
    static int maxGroupSize=5;
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
            jobGroupList.add(new TestExecutorJobPool("pool"+i));
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

        for(int t=0;t<200;t++){
            String groupName="ZZZ"+t;
            List<TestJob> testJobList=new ArrayList<>();
            for(int i=0;i<40;i++)
            {
                testJobList.add(new TestJob(""+i,groupName));
            }
            TestExecutorPoolManager.getInstance().getMostFreeJobPool().commitJobs(new TestJobGroup(groupName,testJobList.size(),testJobList));
        }


        String groupName2="HHH";
        List<TestJob> testJobList2=new ArrayList<>();
        for(int i=0;i<40;i++)
        {
            testJobList2.add(new TestJob(""+i,groupName2));
        }
//        TestExecutorPoolManager.getInstance().getMostFreeJobPool().commitJobs(new TestJobGroup(groupName,testJobList.size(),testJobList));
//        TestExecutorPoolManager.getInstance().getMostFreeJobPool().commitJobs(new TestJobGroup(groupName2,testJobList.size(),testJobList2));


        for(int i=0;i<10;i++) {
            Thread.sleep(5000);
            JobGroupResult result = TestExecutorPoolManager.getInstance().getJobProgressResultByGroupName("ZZZ3");
            System.out.println(JSON.toJSON(result));
        }


    }
}
