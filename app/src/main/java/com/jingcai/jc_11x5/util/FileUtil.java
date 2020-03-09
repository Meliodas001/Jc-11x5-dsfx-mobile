package com.jingcai.jc_11x5.util;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

/**
 * Created by yangsen on 2016/7/20.
 */
public class FileUtil {

    /**
     * 如果文件不存在，就创建文件
     * @param path 文件路径
     * @return
     */
    public static File createIfNotExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                return null;
            }
        }
        return file;
    }

    public File createFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        int index = filePath.lastIndexOf(File.separator);
        if (index != -1) {
            String path = filePath.substring(0, index);
            if (!TextUtils.isEmpty(path)) {
                File file = new File(path.toString());
                // 如果文件夹不存在
                if (!file.exists() && !file.isDirectory()) {
                    boolean flag = file.mkdirs();
                }
            }
        }
        return new File(filePath);
    }

    /**
     * 判断文件是否存在
     * @param strPath
     * @return
     */
    public boolean isExists(String strPath) {
        if (strPath == null) {
            return false;
        }
        final File strFile = new File(strPath);
        if (strFile.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件大小
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 删除指定目录下文件及目录
     * @param deleteThisPath
     * @return
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) throws IOException {
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.isDirectory()) {// 处理目录
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFolderFile(files[i].getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {// 如果是文件，删除
                    file.delete();
                } else {// 目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }
                }
            }
        }
    }

    /**
     * 获取文件夹大小
     * @param file File实例
     * @return long 单位为M
     * @throws Exception
     */

    public static long getFolderSize(File file) throws Exception{
        long size = 0;
        if(file==null || !file.exists()){
            return size;
        }
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                size = size + getFolderSize(fileList[i]);
            } else {
                size = size + fileList[i].length();
            }
        }
        return size;
    }

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (oldfile.exists()) { //文件存在时
                if(newfile.exists()){
                    newfile.delete();
                }
                if(newfile.getParentFile().exists()){
                    newfile.getParentFile().mkdirs();
                }
                newfile.createNewFile();
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 文件复制
     * @param s
     * @param t
     * @throws IOException
     */
    public static void imageCopy(File s, File t) throws IOException{
        if(!t.getParentFile().exists()){
            t.getParentFile().mkdirs();
        }
        if(!t.exists()){
            t.createNewFile();
        }
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 追加内容到文件
     * @param file
     * @param content
     */
    public static synchronized void appendTo(File file,String content){
        try {
            //TODO 判断存储空间
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //
            RandomAccessFile raf=new RandomAccessFile(file, "rw");
            long len = raf.length();
            raf.seek(len);
            String s = content+"\r\n";
            byte[] b=s.getBytes("GBK");
            raf.writeBytes(new String(b,"ISO8859_1"));
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
