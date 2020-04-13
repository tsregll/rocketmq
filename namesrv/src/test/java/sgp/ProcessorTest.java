package sgp;

import org.apache.rocketmq.common.constant.LoggerName;
import org.apache.rocketmq.common.protocol.RequestCode;
import org.apache.rocketmq.common.protocol.ResponseCode;
import org.apache.rocketmq.common.protocol.header.namesrv.PutKVConfigRequestHeader;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;
import org.apache.rocketmq.remoting.exception.RemotingCommandException;
import org.apache.rocketmq.remoting.netty.NettyRequestProcessor;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;

import io.netty.channel.ChannelHandlerContext;

public class ProcessorTest implements NettyRequestProcessor {

	private static InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.NAMESRV_LOGGER_NAME);

	@Override
	public RemotingCommand processRequest(ChannelHandlerContext ctx, RemotingCommand request) throws Exception {
		switch (request.getCode()) {
		case RequestCode.PUT_KV_CONFIG:
			return this.putKVConfig(ctx, request);
		default:
			break;
		}
		return null;
	}

	@Override
	public boolean rejectRequest() {
		return false;
	}

	public RemotingCommand putKVConfig(ChannelHandlerContext ctx, RemotingCommand request)
			throws RemotingCommandException {
		final RemotingCommand response = RemotingCommand.createResponseCommand(null);
		final PutKVConfigRequestHeader requestHeader = (PutKVConfigRequestHeader) request
				.decodeCommandCustomHeader(PutKVConfigRequestHeader.class);

		System.out
				.println(requestHeader.getNamespace() + " " + requestHeader.getKey() + " " + requestHeader.getValue());

		response.setCode(ResponseCode.SUCCESS);
		response.setRemark(null);
		return response;
	}

}
