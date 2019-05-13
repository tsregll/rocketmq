package org.apache.rocketmq.store.ssssss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;


public class ByteBufferTest {

	public static void main(String[] args) throws IOException {
		int fileSize = 6;
		File file = new File("target/unit_test_store/MappedFileTest/000a");
		FileChannel fileChannel = null;
		MappedByteBuffer buffer = null;
		try {
		   fileChannel = new RandomAccessFile(file, "rw").getChannel();
		   buffer = fileChannel.map(MapMode.READ_WRITE, 0, fileSize);
		   
		}catch (FileNotFoundException e) {
			System.out.println("create file channel Failed"+e);
			throw e;
		}catch (IOException e) {
			System.out.println("map file failed" + e);
			throw e;
		}finally {
			if(fileChannel != null) {
				fileChannel.close();
			}
		}
		
	}
	
}
