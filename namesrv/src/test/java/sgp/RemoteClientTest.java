package sgp;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.RequestCode;
import org.apache.rocketmq.common.protocol.header.namesrv.PutKVConfigRequestHeader;
import org.apache.rocketmq.remoting.RemotingClient;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.remoting.netty.NettyClientConfig;
import org.apache.rocketmq.remoting.netty.NettyRemotingClient;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;

public class RemoteClientTest {
	private final NettyClientConfig nettyClientConfig;
	private final RemotingClient remotingClient;

	public RemoteClientTest() {
		this.nettyClientConfig = new NettyClientConfig();
		this.nettyClientConfig.setClientCallbackExecutorThreads(4);
		this.remotingClient = new NettyRemotingClient(nettyClientConfig);
		this.remotingClient.start();
	}

	public static void main(String[] args)   throws RemotingException, MQClientException, InterruptedException{
		RemoteClientTest test = new RemoteClientTest();
		PutKVConfigRequestHeader requestHeader = new PutKVConfigRequestHeader();
		requestHeader.setNamespace("a");
		requestHeader.setKey("b");
		requestHeader.setValue("c");
		RemotingCommand request = RemotingCommand.createRequestCommand(RequestCode.PUT_KV_CONFIG, requestHeader);

		RemotingCommand response = test.remotingClient.invokeSync("localhost:9999", request, 10 * 1000);
		System.out.println(response);
	}
 
}
