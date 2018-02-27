package com.lzh.testzkclient;

import java.io.IOException;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

/***
 * 异步
 * 
 * @author Administrator
 *
 */
public class CreatNodeASync implements Watcher {

	private static ZooKeeper zooKeeper;

	public static void main(String[] args) throws IOException, InterruptedException {

		zooKeeper = new ZooKeeper("192.168.1.4:2181", 5000, new CreatNodeASync());

		System.out.println(zooKeeper.getState());

		Thread.sleep(Integer.MAX_VALUE);

	}

	@Override
	public void process(WatchedEvent event) {

		System.out.println("收到事件：" + event);

		if (event.getState() == KeeperState.SyncConnected) {

			try {
				doSomething();
			} catch (KeeperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void doSomething() throws KeeperException {

		zooKeeper.create("/node_2", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new IStringCallback(),"创建");
	}
	
	
	
	
	static class IStringCallback implements AsyncCallback.StringCallback{

		@Override
		public void processResult(int rc, String path, Object ctx, String name) {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			sb.append("rc="+rc).append("\n");
			sb.append("path="+path).append("\n");
			sb.append("ctx="+ctx).append("\n");
			sb.append("name="+name);
			System.out.println(sb.toString());
			
		}
		
		
	}

}
