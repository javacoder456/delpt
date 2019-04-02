package com.zh.common.taskmanger.elastic;

import com.zh.common.taskmanger.entity.Foo;
import io.elasticjob.lite.api.ShardingContext;
import io.elasticjob.lite.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyElasticDataFlowJob implements DataflowJob<Foo> {

    private static Logger logger = LoggerFactory.getLogger(MyElasticDataFlowJob.class);
    //作业数据
    private Map<String, Foo> busData = new ConcurrentHashMap<>(200, 1);
    @Override
    public List<Foo> fetchData(ShardingContext shardingContext) {
        logger.info("-----{}开始作业-抓取业务数据-----",shardingContext);
        return findToData(3);
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Foo> data) {
        logger.info("-----开始处理业务数据{}-----",data);
        for (Foo foo : data) {
            //作业数据处理
            setCompleted(foo.getId());
        }

    }


    public List<Foo> findToData(final int limit) {
        List<Foo> result = new ArrayList<>(limit);
        int count = 0;
        for (Map.Entry<String, Foo> each : busData.entrySet()) {
            Foo foo = each.getValue();
            if (foo.getStatus() == Foo.Status.TODO) {
                result.add(foo);
                count++;
                if (count == limit) {
                    break;
                }
            }
        }
        return result;
    }

    public void setCompleted(final String id) {
        //设置状态，不让一直抓取
        busData.get(id).setStatus(Foo.Status.COMPLETED);
    }
}
