import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class ftpTest {

	public static void main(String[] args) {
		downFile();
	}

	public static void downFile() {
		// ftp登录用户名
		String userName = "yb30";
		// ftp登录密码
		String userPassword = "abc123$";
		// ftp地址:直接IP地址
		String server = "211.151.81.102";
		// 创建的文件
		String fileName = "yb30_20150416.txt";
		// 指定写入的目录
		//String path = "wd";
		// 指定本地写入文件
		//String localPath = "D:\\";

		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 1.连接服务器
			ftp.connect(server);
			// 2.登录服务器 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(userName, userPassword);
			// 3.判断登陆是否成功
			reply = ftp.getReplyCode();
			System.out.println("连接返回码："+reply);
			ftp.enterLocalPassiveMode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			// 4.指定要下载的目录
			//System.out.println(ftp.pwd());
			//ftp.changeWorkingDirectory("//");// 转移到FTP服务器目录
			// 5.遍历下载的目录
			FTPFile[] fs = ftp.listFiles();
			System.out.println(fs.length);
			List<List<String>> lists = null;
			for (FTPFile ff : fs) {
				// 解决中文乱码问题，两次解码
				byte[] bytes = ff.getName().getBytes("iso-8859-1");
				String fn = new String(bytes, "utf8");
				if (fn.equals(fileName)) {
					// 6.写操作，将其写入到本地文件中
					InputStream ins = null;
					StringBuilder builder = null;
					ins = ftp.retrieveFileStream(fileName);				
					//解析文件并返回Lists集合
					lists = TXTAnalysis.readTXTFile(ins);
					for(List<String> list:lists){
						if (list == null || list.size() == 0) {
							break;
						}
						for (int i = 0; i < list.size(); i++) {
							System.out.println(list.get(i));
									
						}
				}
			ftp.logout();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}
	}
