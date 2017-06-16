package Job;

import java.util.concurrent.Callable;

/**
 * Created by zhouhao on 2017/6/16.
 */
 public interface Job<R extends JobResult> extends Callable
{
    /**
     * job名称 一个worker在一个时间段内只能处理一个job
     * @return
     */
    String getName();

    String getGroupName();

    @Override
    R call() throws Exception;
}
