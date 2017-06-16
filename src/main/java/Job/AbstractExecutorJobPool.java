package Job;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhouhao on 2017/6/16.
 */
public abstract class AbstractExecutorJobPool implements JobPool
{
    private Log LOG = LogFactory.getLog(AbstractExecutorJobPool.class);

    private ExecutorService pool ;
    private CompletionService<JobResult> completionService;
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(getMaxJobWaitingQueueSize());
    private AtomicInteger busyDegree=new AtomicInteger(0);
    private JobPoolManager jobPoolManager;


    public void init()
    {

        pool = new ThreadPoolExecutor(getMaxConcurrentJobSize(), getMaxConcurrentJobSize(), 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy()
        {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e)
            {
//                LOG.error("类型是"+r.getClass());
//                Job j = (Job) r;
                busyDegree.decrementAndGet();
                LOG.error("线程池和等待队列都满了，任务：" + "被拒绝");
            }
        });
        completionService = new ExecutorCompletionService<JobResult>(pool);

        new Thread()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Future<JobResult> f = completionService.take();
                        JobResult result = f.get();
                        busyDegree.decrementAndGet();
                        getJobPoolManager().pushJobResult(result);
                    } catch (Exception e)
                    {
                        LOG.error("统计任务结果出错，任务结果为", e);
                    }
                }
            }
        }.start();
    }

    public <T extends JobGroup> boolean commitJobs(T jobGroup){
        List<Job> jobList=jobGroup.getJobList();
        if (null!=jobList&&jobList.size()>0)
        {
            for (Job job :jobList)
            {
                completionService.submit(job);
                busyDegree.incrementAndGet();
            }
        }
        else
        {
            return false ;
        }
        getJobPoolManager().pushJobGroupResult(new DefaultJobGroupResult(jobGroup.getGroupName(),jobList.size()));
        return true;
    }

    public int getBusyDegree()
    {
        return busyDegree.get();
    }




}
