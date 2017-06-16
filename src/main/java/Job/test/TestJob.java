package Job.test;


import Job.DefaultJobResult;
import Job.JobResult;

import java.util.Random;

/**
 * Created by zhouhao on 2017/6/16.
 */
public class TestJob implements Job
{
    private String name;
    private String groupName;
    public TestJob(String name,String groupName)
    {
        this.name=name;
        this.groupName=groupName;
    }
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getGroupName()
    {
        return groupName;
    }

    @Override
    public JobResult call() throws Exception
    {
        int r=new Random().nextInt(6)+2;
        System.out.println("Job"+name+"开始执行"+r);
        Thread.sleep(r*1000);
        if(r>4)
        {
            return new DefaultJobResult(name, groupName, true);//成功
        }
        else
        {
            return new DefaultJobResult(name, groupName, false);//失败
        }
    }

}
