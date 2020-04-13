package sgp;

import java.util.concurrent.Executors;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.rocketmq.common.ThreadFactoryImpl;
import org.apache.rocketmq.namesrv.routeinfo.BrokerHousekeepingService;
import org.apache.rocketmq.remoting.RemotingServer;
import org.apache.rocketmq.remoting.netty.NettyRemotingServer;
import org.apache.rocketmq.remoting.netty.NettyServerConfig;

public class RemoteServerTest {

	private RemotingServer remotingServer;

	private final NettyServerConfig nettyServerConfig;

	private BrokerHousekeepingService brokerHousekeepingService;

	public RemoteServerTest() {
		this.nettyServerConfig = new NettyServerConfig();
		this.nettyServerConfig.setListenPort(9999);

	}

	public boolean initialize() {
		this.remotingServer = new NettyRemotingServer(nettyServerConfig);
		return true;
	}

	public void start() throws Exception {
		remotingServer.registerDefaultProcessor(new ProcessorTest(), Executors.newFixedThreadPool(
				nettyServerConfig.getServerWorkerThreads(), new ThreadFactoryImpl("RemotingExecutorThread_")));
		this.remotingServer.start();

	}

	public static void main(String[] args) throws Exception {
		RemoteServerTest remoteServerTest = new RemoteServerTest();
		remoteServerTest.initialize();
		remoteServerTest.start();
		Thread.sleep(DateUtils.MILLIS_PER_DAY);
	}
}
