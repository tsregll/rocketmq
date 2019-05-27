package org.apache.rocketmq.store;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.rocketmq.store.AppendMessageCallback;
import org.apache.rocketmq.store.CommitLog;
import org.apache.rocketmq.store.DefaultMessageStore;
import org.apache.rocketmq.store.config.MessageStoreConfig;

public class CommitLogTest {

	AppendMessageCallback callback;

	DefaultMessageStore messageStore;

	public static void main(String[] args) throws Exception {
		String data = "abc";
		CommitLogTest commitLogTest = new CommitLogTest();
		commitLogTest.init();
		SocketAddress storeHost = new InetSocketAddress(InetAddress.getLocalHost(), 8123);
		SocketAddress bornHost = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 0);

		MessageExtBrokerInner msg = new MessageExtBrokerInner();
		msg.setTopic("testtest");
		msg.setBody(data.getBytes());
		msg.setKeys(String.valueOf(System.currentTimeMillis()));
		msg.setQueueId(0);
		msg.setSysFlag(0);
		msg.setBornTimestamp(System.currentTimeMillis());
		msg.setStoreHost(storeHost);
		msg.setBornHost(bornHost);
		commitLogTest.messageStore.putMessage(msg);

	}

	public CommitLog init() throws Exception {
		MessageStoreConfig messageStoreConfig = new MessageStoreConfig();
		messageStoreConfig.setMapedFileSizeCommitLog(1024 * 8);
		messageStoreConfig.setMapedFileSizeConsumeQueue(1024 * 4);
		messageStoreConfig.setMaxHashSlotNum(100);
		messageStoreConfig.setMaxIndexNum(100 * 10);
		messageStoreConfig.setStorePathRootDir(System.getProperty("user.home") + File.separator + "unitteststore");
		messageStoreConfig.setStorePathCommitLog(
				System.getProperty("user.home") + File.separator + "unitteststore" + File.separator + "commitlog");
		// too much reference
		messageStore = new DefaultMessageStore(messageStoreConfig, null, null, null);
		messageStore.start();
		messageStore.load();
		return messageStore.getCommitLog();
	}
}
