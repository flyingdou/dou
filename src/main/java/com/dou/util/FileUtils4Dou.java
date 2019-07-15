package com.dou.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: dou
 * @ClassName: FileUtils4Dou.java
 * @Package: com.dou.util
 * @TODO: TODO
 * @Date: 2019-07-12 16:22:08.853
 */
@Slf4j
public class FileUtils4Dou {
	public static final int BUFSIZE = 1024 * 8;

    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<File>();
        if (folder.isFile())
            result.add(folder);
 
        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().contains(keyword)) {
                    return true;
                }
                return false;
            }
        });
 
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
 
        return result;
    }
    
    
    
    @SuppressWarnings("resource")
	public static void mergeFiles(String outFile, List<File> files) {
		FileChannel outChannel = null;
//	    log.info("Merge:{} --- > into:{}", Arrays.toString(files.size()), outFile);
	    try {
		    outChannel = new FileOutputStream(outFile).getChannel();
		    for(File f : files){
		    FileChannel fc = new FileInputStream(f).getChannel();
		    ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
		    while(fc.read(bb) != -1){
			    bb.flip();
			    outChannel.write(bb);
			    bb.clear();
		    }
		    	fc.close();
		    }
		    log.info("Merged!! ");
	    } catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
    }
 
    public static void main(String[] args) {
        List<File> files = searchFiles(new File("D:\\tmp"), ".ts");
        System.out.println("共找到:" + files.size() + "个文件");
        filesSort(files);
        for (File file : files) {
        	log.info("filePath: {}, fileName: {}", file.getAbsolutePath(), file.getName());
        }
        
        mergeFiles("D:/temp/tencent.ts", files);
    	

    }
    
    /**
     * 给list排序
     * @param files
     * @return
     */
    public static List<File> filesSort (List<File> files) {
    	for (int i = 0; i < files.size(); i++) {
			for (int j = 0; j < files.size() - i - 1; j++) {
				Integer n1 = Integer.valueOf(files.get(j).getName().substring(0,files.get(j).getName().lastIndexOf(".")));
				Integer n2 = Integer.valueOf(files.get(j + 1).getName().substring(0,files.get(j + 1).getName().lastIndexOf(".")));
				if (n1 > n2) {
					// 互换位置
				    File temx = null;
				    temx = files.get(j);
				    files.set(j, files.get(j + 1));
				    files.set(j + 1, temx);
				}
			}
		}
    	return files;
    }
    

}


