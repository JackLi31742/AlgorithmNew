package tree;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HuffmanZip {

	public static List<Map<Byte, String>> codeMapList=null;
	public static List<Integer> lenList=null;
	public static void main(String[] args) {
		String srcFilePath="H://1831_1.png";
		String zipFilePath="H://result.zip";
		zip(srcFilePath,zipFilePath);
		
		String unzipFilePath="H://unzip.png";
		
		unzip(zipFilePath, unzipFilePath);
		
	}
	
	public static void zip(String srcFilePath,String destFilePath) {
		
		FileInputStream in=null;
		BufferedInputStream bufferedIn=null;
		FileOutputStream out=null;
		BufferedOutputStream bufferedOut=null;
		byte[] buffer = new byte[1024];
		try {
			in=new FileInputStream(srcFilePath);
			bufferedIn=new BufferedInputStream(in);
			out=new FileOutputStream(destFilePath);
			bufferedOut=new BufferedOutputStream(out);
			lenList=new ArrayList<Integer>();
			codeMapList=new ArrayList<Map<Byte,String>>();
			int len = 0;
			//从文件中按字节读取内容，到文件尾部时read方法将返回-1
            while ((len = bufferedIn.read(buffer)) != -1) {

                //将读取的字节转为字符串对象
//            	String s = new String(buffer, 0, len);
            	Map<Byte, String> codeMap=HuffmanCode.getCodeMap(HuffmanCode.getHuffmanTree(HuffmanCode.getCounts(buffer)));
            	byte[] result=HuffmanCode.zip(buffer, codeMap);
            	lenList.add(result.length);
            	codeMapList.add(codeMap);
            	bufferedOut.write(result);
            	bufferedOut.flush();
            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				bufferedIn.close();
				bufferedOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	public static void unzip(String zipFilePath,String unzipFilePath) {

		
		FileInputStream in=null;
		BufferedInputStream bufferedIn=null;
		FileOutputStream out=null;
		BufferedOutputStream bufferedOut=null;
		try {
			in=new FileInputStream(zipFilePath);
			bufferedIn=new BufferedInputStream(in);
			out=new FileOutputStream(unzipFilePath);
			bufferedOut=new BufferedOutputStream(out);
			int len = 0;
			for (int i = 0; i < lenList.size(); i++) {
				//每次读取的长度是不一样的
				byte[] buffer = new byte[lenList.get(i)];
				if ((len = bufferedIn.read(buffer)) != -1) {
					
					Map<String, Byte> decodeMap=HuffmanCode.getDecodeMap(codeMapList.get(i));
					byte[] result=HuffmanCode.unzip(buffer, decodeMap);
					bufferedOut.write(result);
					bufferedOut.flush();
				}
			}
			//从文件中按字节读取内容，到文件尾部时read方法将返回-1
//            while ((len = bufferedIn.read(buffer)) != -1) {
//            	String s = new String(buffer, 0, len);
//            }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				bufferedIn.close();
				bufferedOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	
	}
	
	
}
