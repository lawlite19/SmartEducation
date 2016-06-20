package com.hhit.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

public class FtpUtil {
	
	FtpClient ftpClient;

	/**  
     * connectServer  
     * 连接ftp服务器  
     * @throws java.io.IOException  
     * @param path 文件夹，空代表根目录  
     * @param password 密码  
     * @param user    登陆用户  
     * @param server 服务器地址  
	 * @throws FtpProtocolException 
     */   
  public void connectServer(String server, String user, String password,  String path)   
  throws IOException, FtpProtocolException   
   {   
     // server：FTP服务器的IP地址；user:登录FTP服务器的用户名   
     // password：登录FTP服务器的用户名的口令；path：FTP服务器上的路径   
      ftpClient = FtpClient.create();
      SocketAddress sa=new InetSocketAddress(server, 0);
      ftpClient.connect(sa);
      ftpClient.login(user, password.toCharArray());   
     //path是ftp服务下主目录的子目录   
     if (path.length() != 0)   ftpClient.changeDirectory(path);   
     //用2进制上传、下载   
      ftpClient.setBinaryType();       
}   
  
/**  
     * upload  
     * 上传文件  
     * @throws java.lang.Exception  
     * @return -1 文件不存在  
     *           -2 文件内容为空  
     *           >0 成功上传，返回文件的大小  
     * @param newname 上传后的新文件名  
     * @param filename 上传的文件  
     */   
public long upload(String filename,String newname) throws Exception   
{   
     long result = 0;   
      TelnetOutputStream os = null;   
      FileInputStream is = null;   
     try {            
          java.io.File file_in = new java.io.File(filename);   
         if (!file_in.exists()) return -1;   
         if (file_in.length()==0) return -2;   
          os = (TelnetOutputStream) ftpClient.putFileStream(newname);   
          result = file_in.length();   
          is = new FileInputStream(file_in);   
         byte[] bytes = new byte[1024];   
         int c;   
         while ((c = is.read(bytes)) != -1) {   
               os.write(bytes, 0, c);   
          }   
      } finally {   
         if (is != null) {   
              is.close();   
          }   
         if (os != null) {   
             os.close();   
          }   
      }   
    return result;   
}   
/**  
     * upload  
     * @throws java.lang.Exception  
     * @return  
     * @param filename  
     */   
public long upload(String filename)   
throws Exception   
{   
    String newname = "";   
    if (filename.indexOf("/")>-1)   
     {   
        newname = filename.substring(filename.lastIndexOf("/")+1);   
     }else   
     {   
        newname = filename;   
     }   
    return upload(filename,newname);   
}   
  
/**  
     *   download  
     *   从ftp下载文件到本地  
     * @throws java.lang.Exception  
     * @return  
     * @param newfilename 本地生成的文件名  
     * @param filename 服务器上的文件名  
     */   
public long download(String filename,String newfilename)   
throws Exception   
{     
    long result = 0;   
     TelnetInputStream is = null;   
     FileOutputStream os = null;   
    try   
     {   
        is = (TelnetInputStream) ftpClient.getFileStream(filename);          
        java.io.File outfile = new java.io.File(newfilename);   
        os = new FileOutputStream(outfile);   
       byte[] bytes = new byte[1024];   
       int c;   
       while ((c = is.read(bytes)) != -1) {   
            os.write(bytes, 0, c);   
            result = result + c;   
        }   
     } catch (IOException e)   
     {   
        e.printStackTrace();   
     }   
    finally {   
         if (is != null) {   
              is.close();   
          }   
         if (os != null) {   
             os.close();   
          }   
      }   
     return result;   
}   
/**  
   * 取得某个目录下的所有文件列表  
   *  
   */   
@SuppressWarnings({ "deprecation", "unchecked", "rawtypes"})
public List getFileList(String path)   
{   
     List list = new ArrayList();   
    try   
     {   
       DataInputStream dis = new   DataInputStream(ftpClient.nameList(path));   
       String filename = "";   
       while((filename=dis.readLine())!=null)     
        {     
          list.add(filename);           
        }     
      
     } catch (Exception e)   
     {   
        e.printStackTrace();   
     }   
    return list;   
}   
  
/**  
     * closeServer  
     * 断开与ftp服务器的链接  
     * @throws java.io.IOException  
     */   
public void closeServer()   
throws IOException   
{      
   try   
    {   
      if (ftpClient != null)   
       {   
         ftpClient.close();    
       }   
    } catch (IOException e) {   
       e.printStackTrace();   
    }   
}   
     
  public static void main(String [] args) throws Exception   
   {   
     FtpUtil ftp = new FtpUtil();   
    try {   
         //连接ftp服务器   
          ftp.connectServer("192.168.8.32", "admin", "123456", "201212");   
         /**   上传文件到 info2 文件夹下 */   
          System.out.println("filesize:"+ftp.upload("f:/download/Install.exe")+"字节");   
         /** 取得info2文件夹下的所有文件列表,并下载到 E盘下 */   
          List list = ftp.getFileList(".");
         for (int i=0;i<list.size();i++)   
          {   
            String filename = (String)list.get(i);   
             System.out.println(filename);   
             ftp.download(filename,"E:/"+filename);   
          }   
     } catch (Exception e) {
     }finally   
     {   
        ftp.closeServer();   
     }   
   }
  
} 