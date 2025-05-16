package cn.zpark.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import javax.swing.*;

/**
 * @Auther:BigData-aw
 * @ClassName:WatcherOperate
 * @功能描述:
 * @Version:1.0
 */
public class WatcherOperate {
     @Test
     public void testSystem() throws Exception{
         // TODO : 系统回调  创建、关闭
         ZooKeeper zooKeeper = new ZooKeeper("node-1:2181", 5000, new Watcher() {
             @Override
             public void process(WatchedEvent event) {
                 System.out.println("系统回调");
             }
         });
         Thread.sleep(3000);
         //zooKeeper.close();
     }
      @Test
      public void testChildren() throws Exception{
          // TODO : 监听 子节点变化
          ZooKeeper zooKeeper = new ZooKeeper("node-1:2181", 5000, null);

          zooKeeper.getChildren("/javaApi", new Watcher(){

              @Override
              public void process(WatchedEvent event) {
                  System.out.println("触发啦");
              }
          });

          Thread.sleep(Long.MAX_VALUE);
          zooKeeper.close();
      }

       @Test
       public void testWatchData() throws Exception{
           // TODO : 监听数据的变化
           ZooKeeper zooKeeper = new ZooKeeper("node-1:2181", 5000, null);

           zooKeeper.getData("/javaApi", new Watcher(){

               @Override
               public void process(WatchedEvent event) {
                   System.out.println("数据变化啦");
               }
           }, null);

           Thread.sleep(Long.MAX_VALUE);
           zooKeeper.close();

       }
        @Test
        public void testDefault() throws Exception{
            // TODO : 默认的回调
            ZooKeeper zooKeeper = new ZooKeeper("node-1:2181", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("默认的回调函数");
                }
            });

            zooKeeper.getData("/javaApi", true, null);

            Thread.sleep(Long.MAX_VALUE);
            zooKeeper.close();
        }

        // TODO : 监听一次   触发一次   实现 永久监听 某个节点的数据
        ZooKeeper zooKeeper = null;
         @Test
         public void testFinal() throws Exception{
             // TODO : 永久监听
             zooKeeper = new ZooKeeper("node-1:2181", 5000, new Watcher() {
                 @Override
                 public void process(WatchedEvent event) {
                     // 回调  进行 监听
                     try {
                         zooKeeper.getData("/javaApi", true, null);
                         System.out.println("数据开始变化！");
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             });
             Thread.sleep(Long.MAX_VALUE);
             zooKeeper.close();
         }



}
