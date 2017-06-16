package Job;

import java.util.List;

/**
 * Created by zhouhao on 2017/6/16.
 */
public interface JobGroup
{
    String getGroupName();

    /**
     * 获取任务总数
     * @return
     */
    int getTotalSize();


    <J extends Job> List<J> getJobList();


}
