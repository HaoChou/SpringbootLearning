package Job;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class DefaultJobGroupResult implements JobGroupResult
{
    private AtomicInteger successCount=new AtomicInteger(0);
    private AtomicInteger failedCount=new AtomicInteger(0);
    private ConcurrentHashSet<String> failedJobNames=new ConcurrentHashSet();
    private int totalCount;
    private String groupName;

    public DefaultJobGroupResult(String groupName,int totalCount)
    {
        this.totalCount=totalCount;
        this.groupName=groupName;
    }
    @Override
    public String getGroupName()
    {
        return groupName;
    }

    @Override
    public int getTotalCount()
    {
        return totalCount;
    }

    @Override
    public int getCompletedCount()
    {
        return getSuccessCount()+getFailedCount();
    }

    @Override
    public int getSuccessCount()
    {
        return successCount.get();
    }

    @Override
    public void increaseSuccessCount()
    {
        successCount.incrementAndGet();
    }

    @Override
    public int getFailedCount()
    {
        return failedCount.get();
    }

    @Override
    public void increaseFailedCount()
    {
        failedCount.incrementAndGet();
    }

    @Override
    public String[] getFailedNames()
    {
        return failedJobNames.toArray(new String[0]);
    }

    @Override
    public void addFailedName(String failedJobName)
    {
        failedJobNames.add(failedJobName);
    }
}
