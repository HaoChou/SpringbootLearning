package Job.test;



import Job.JobGroup;

import java.util.List;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class TestJobGroup implements JobGroup
{
    private String groupName;
    private int totalSize;
    private List<TestJob> jobList;

    public TestJobGroup(String groupName,int totalSize,List<TestJob> jobList)
    {
        this.groupName=groupName;
        this.totalSize=totalSize;
        this.jobList=jobList;
    }
    @Override
    public String getGroupName()
    {
        return groupName;
    }

    @Override
    public int getTotalSize()
    {
        return totalSize;
    }

    @Override
    public List<TestJob> getJobList()
    {
        return jobList;
    }

//    public <J extends Job> void setJobList(List<J> jobList)
//    {
//        this.jobList ;
//    }
}
