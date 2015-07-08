

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TXTAnalysis {
	
	public static List<List<String>> readTXTFile(InputStream file) throws IOException{
		List<List<String>> lists = new ArrayList<List<String>>(); 
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
				String encoding = "UTF-8"; // 字符编码(可解决中文乱码问题 )
           // if (file.isFile() && file.exists()) {
            	
             	read = new InputStreamReader(file, encoding);
                bufferedReader = new BufferedReader(read);
                while (bufferedReader.ready()) {
                	List<String> list = new ArrayList<String>();
                	String line = bufferedReader.readLine();
                	String [] s = line.split(",");
                	for(int i=0; i<s.length; i++){
                		list.add(s[i]);
                	}
                	lists.add(list);
                }
                
           /* }else{
                System.out.println("找不到指定的文件！");
            }*/
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        } finally {
        	if(bufferedReader != null)
        		bufferedReader.close();
        	if(read != null)
        		read.close();
        }
		return lists;
	}
	/**
	 * 解析文件
	 * @param file
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> readTXTFile(File file,String encoding) throws IOException{
		List<List<String>> lists = new ArrayList<List<String>>(); 
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
            if (file.isFile() && file.exists()) {
            	
                read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                bufferedReader = new BufferedReader(read);
                while (bufferedReader.ready()) {
                	List<String> list = new ArrayList<String>();
                	String line = bufferedReader.readLine();
                	String [] s = line.split(",");
                	for(int i=0; i<s.length; i++){
                		list.add(s[i]);
                	}
                	lists.add(list);
                }
                
            }else{
                System.out.println("找不到指定的文件！");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        } finally {
        	if(bufferedReader != null)
        		bufferedReader.close();
        	if(read != null)
        		read.close();
        }
		return lists;
	}
}
