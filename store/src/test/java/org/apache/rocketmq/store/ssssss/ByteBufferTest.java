package org.apache.rocketmq.store.ssssss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;


public class ByteBufferTest {

	public static void main(String[] args) throws IOException {
		int fileSize = 6;
		String dataStr = "asdfsd";
		File file = new File("target/000a");
		FileChannel fileChannel = null;
		MappedByteBuffer buffer = null;
		try {
		   fileChannel = new RandomAccessFile(file, "rw").getChannel();
		   buffer = fileChannel.map(MapMode.READ_WRITE, 0, fileSize);
		   /*fileChannel.position(0);
		   fileChannel.write(ByteBuffer.wrap(dataStr.getBytes()));*/
		   buffer.put(dataStr.getBytes());
//		   ByteBuffer byteBuffer = buffer.slice();
//		   byteBuffer.position(0);
//		   byte[] data = new byte[3] ;
//		   byteBuffer.get( data);
//		   System.out.println(new String(data));
		}catch (FileNotFoundException e) {
			System.out.println("create file channel Failed"+e);
			throw e;
		}catch (IOException e) {
			System.out.println("map file failed" + e);
			throw e;
		}finally {
			//buffer.force();
			if(fileChannel != null) {
				fileChannel.close();
			}
		}
		
	}
	
}
