package com.lzh.testzkclient;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;

public class CreatSession {

	private static ZooKeeper zooKeeper;

	public static void main(String[] args) throws IOException, InterruptedException {

		zooKeeper = new ZooKeeper("192.168.1.4:2181", 5000, new MyWatcher());

		System.out.println(zooKeeper.getState());

		Thread.sleep(Integer.MAX_VALUE);

	}

}
