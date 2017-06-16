package Job;

/**
 * Created by zhouhao on 2017/6/16.
 * 工作组 对应线程池
 */
public interface JobPool
{
    /**
     * 工作组中最多的并行数 线程数
     * @return
     */
    int getMaxConcurrentJobSize();

    String getName();

    /**
     * 最大等待队列数值 超过该数值 拒绝接受任务
     * @return
     */
    int getMaxJobWaitingQueueSize();

    /**
     * 获取当前工作组繁忙程度 越大越忙
     * @return
     */
    int getBusyDegree();

    /**
     * 向工作组提交任务
     */
    <T extends JobGroup> boolean commitJobs(T jobGroup);


    public void init();

    JobPoolManager getJobPoolManager();

    JobPool newInstance(String name);

}
