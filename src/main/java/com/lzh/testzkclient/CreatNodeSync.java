package com.lzh.testzkclient;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

/***
 * 同步
 * 
 * @author Administrator
 *
 */
public class CreatNodeSync implements Watcher {

	private static ZooKeeper zooKeeper;

	public static void main(String[] args) throws IOException, InterruptedException {

		zooKeeper = new ZooKeeper("192.168.1.4:2181", 5000, new CreatNodeSync());

		System.out.println(zooKeeper.getState());

		Thread.sleep(Integer.MAX_VALUE);

	}

	@Override
	public void process(WatchedEvent event) {

		System.out.println("收到事件：" + event);

		if (event.getState() == KeeperState.SyncConnected) {

			doSomething();
		}

	}

	private void doSomething() {

		try {
			String path = zooKeeper.create("/node_1", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

			System.out.println("return path:" + path);
		} catch (KeeperException | InterruptedException e) {

			e.printStackTrace();
		}
	}

}
