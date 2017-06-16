package Job;

/**
 * Created by zhouhao on 2017/6/16.
 * 负责创建workerGroup和销毁workerGroup
 * 管理workerGroup的最大个数
 *  多个工作组的好处是 防止一个工作组（线程池）的消费队列堆积太对（某一次提交） 从而导致后面的提交必须等待前面的队列元素消费完才能开始消费
 *
 */
public interface JobPoolManager
{
    /**
     * 获取最大工作组的个数
     * @return
     */
    int getMaxGroupSize();

    /**
     * 获取最空闲的工作组（如果都忙碌 就将剩余job最少的工作组 定义为最空闲的）
     * @return
     */
    JobPool getMostFreeJobPool();


    JobGroupResult getJobProgressResultByGroupName(String jobGroupName);


    /**
     * 初始化
     * 初始化 jobGroupList 根据大小创建相应的实例
     */
    void init();

    /**
     * 停止工作
     */
    void stop();

    /**
     * 向管理器推送jobResult
     * 当一个任务结束时会被调用
     * @param result
     */
    void pushJobResult(JobResult result);

    /**
     * 向管理器推送jobGroupResult
     * 当一个jobGroup被提交时会被调用
     * @param jobGroupResult
     */
    void pushJobGroupResult(JobGroupResult jobGroupResult);

    int getBiggestFreeQueueCount();

}
