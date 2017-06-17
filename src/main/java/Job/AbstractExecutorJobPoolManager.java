package Job;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


/**
 * Created by zhouhao on 2017/6/16.
 */
public abstract class AbstractExecutorJobPoolManager implements JobPoolManager
{
    private Log LOG = LogFactory.getLog(AbstractExecutorJobPoolManager.class);
    protected static List<JobPool> jobGroupList=new ArrayList<>();
//    private ConcurrentHashMap<String/*groupName*/,JobGroupResult> groupResultMap=new ConcurrentHashMap();

    private Cache<String, JobGroupResult> groupResultCache= CacheBuilder.newBuilder() .maximumSize(10000).expireAfterWrite(1, TimeUnit.DAYS).build();
    @Override
    public JobPool getMostFreeJobPool()
    {
        System.out.println(jobGroupList.size());
        //todo 检查顺序
        jobGroupList.sort(new Comparator<JobPool>()
        {
            @Override
            public int compare(JobPool o1, JobPool o2)
            {
                return o1.getBusyDegree()-o2.getBusyDegree();
            }
        });
        LOG.info("MostFreeJobGroup is" +jobGroupList.get(0).getName()+"Busy degree:"+jobGroupList.get(0).getBusyDegree());
        return jobGroupList.get(0);
    }

    public JobGroupResult getJobProgressResultByGroupName(String jobGroupName)
    {
        return groupResultCache.getIfPresent(jobGroupName);
    }

    public void  pushJobResult(JobResult result)
    {
        String groupName=result.getJobGroupName();
        if(null==groupName)
        {
            LOG.error("result的名称不能为空:"+groupName);
            return;
        }

        JobGroupResult jobGroupResult = groupResultCache.getIfPresent(groupName);
        if (null == jobGroupResult)
        {
            LOG.error("获取groupResult失败groupName:"+groupName);
            return;
        }
        if(result.isSuccess())
        {
            jobGroupResult.increaseSuccessCount();
        }
        else
        {
            jobGroupResult.increaseFailedCount();
            jobGroupResult.addFailedName(result.getJobName());
        }
    }


    public int getBiggestFreeQueueCount()
    {
        JobPool pool= getMostFreeJobPool();
        return pool.getMaxConcurrentJobSize()+pool.getMaxJobWaitingQueueSize()-pool.getBusyDegree();
    }
    public void pushJobGroupResult(JobGroupResult jobGroupResult)
    {
        groupResultCache.put(jobGroupResult.getGroupName(),jobGroupResult);
        LOG.info("********put groupResultMap :"+jobGroupResult.getGroupName());
    }

}
