package cn.zpark.zk;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Auther:BigData-aw
 * @ClassName:BaseOperate
 * @功能描述:
 * @Version:1.0
 */
public class BaseOperate {
    public static void main(String[] args) throws Exception {

        try {
            ZooKeeper zooKeeper = new ZooKeeper("", 5000, null);
            System.out.println("创建成功:"+zooKeeper);
        }catch (Exception e){
            System.out.println("创建失败");
            //e.printStackTrace();  异常
            throw new RuntimeException(e);
        }
        System.out.println("哈哈");
    }


    ZooKeeper zooKeeper = null;
    String connectStr = null;
    Logger logger = null;

    @Before
     public void testInit() throws Exception{
        connectStr = "node-1:2181,node-2:2181,node-3:2181";

        logger = LogManager.getLogger(this.getClass());

        try {
            // TODO : 初始化 zk 对象
            zooKeeper = new ZooKeeper(connectStr, 5000, null);
            logger.info("初始化 zk 对象 成功");
        }catch (Exception e){
            logger.info("初始化 zk 对象 失败");
            e.printStackTrace();
        }
    }
     @After
     public void testClose() throws Exception{
         // TODO : 释放资源
         if (zooKeeper != null) {
             zooKeeper.close();
             logger.info("释放资源");
         }
         if (logger != null) {
             logger=null;
         }
     }

     // TODO : 业务测试

     @Test
     public void testGetChildren() throws Exception{
         // TODO : 获取子节点
         List<String> children = zooKeeper.getChildren("/", false);
         logger.info("节点下的子节点："+children);
     }

      @Test
      public void testGetData() throws Exception{
          // TODO : 获取节点数据
          byte[] data = zooKeeper.getData("/shell", false, null);
          logger.info(new String(data));
      }

       @Test
       public void testSetData() throws Exception{
           // TODO : 设置 数据
           Stat stat = zooKeeper.setData("/shell", "呵呵".getBytes(), -1);
           logger.info("结构体："+stat);
       }

        @Test
        public void testDelete() throws Exception{
            // TODO : 删除节点
            zooKeeper.delete("/shell", -1);
            logger.info("删除成功");
        }

         @Test
         public void testCreateZNode() throws Exception{
             // TODO : 创建节点
             String str = zooKeeper.create("/javaApi01", "java".getBytes(),
                     ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
             logger.info("创建节点后的数据："+str);
             System.out.println("long的最大值："+Long.MAX_VALUE);
             Thread.sleep(Long.MAX_VALUE);
         }



}
