package com.kyn.myproject.demo.common.util;

import com.kyn.myproject.demo.common.entity.MessageTemplate;
import com.kyn.myproject.demo.common.enums.SystemErrorCode;
import com.kyn.myproject.demo.common.exception.ProjectException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Map;

/**
 * @author Kangyanan
 * @Description: 读取文件
 * @date 2021/1/18 18:41
 */
public class FileUtils extends org.apache.commons.io.FileUtils{
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 递归读取resource文件下模板
     * @param path
     * @param hashmap
     * @param messageTemplate
     * @throws Exception
     */
    public static void readFiles(String path, Map hashmap, MessageTemplate messageTemplate) throws Exception{
        String fileName="";
        File rootPath = ResourceUtils.getFile(path);
        if(rootPath.exists()){
            File[] files = rootPath.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    String directoryName=file.getName();
                    logger.info("directoryName={}" ,file.getName());
                    MessageTemplate messageTemplate1=new MessageTemplate();
                    hashmap.put(directoryName,messageTemplate1);
                    readFiles(file.getAbsolutePath(),hashmap,messageTemplate1);
                } else {
                    fileName=file.getName().substring(0,file.getName().indexOf("."));
                    logger.info("fileName={}" ,file.getName().substring(0,file.getName().indexOf(".")));
                    BufferedReader reader = null;
                    StringBuffer sbf = new StringBuffer();
                    try {
                        reader = new BufferedReader(new FileReader(file));
                        String tempStr;
                        while ((tempStr = reader.readLine()) != null) {
                            sbf.append(tempStr).append("\n");
                        }
                        if(sbf.length()>0){
                            switch (fileName) {
                                case "head":
                                    messageTemplate.setHeaderTemplate(sbf.substring(0, sbf.length() - 1));
                                    break;
                                case "main":
                                    messageTemplate.setMainTemplate(sbf.substring(0, sbf.length() - 1));
                                    break;
                                case "sub1":
                                    messageTemplate.setSubTemplate1(sbf.substring(0, sbf.length() - 1));
                                    break;
                                case "sub2":
                                    messageTemplate.setSubTemplate2(sbf.substring(0, sbf.length() - 1));
                                    break;
                                default:
                                    logger.warn("fileName = {} 没有加 switch.", fileName);
                                    throw new ProjectException(SystemErrorCode.SYSTEM_ERROR);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        }else{
            logger.info("path={},not exists!!!" ,path);
        }
    }
    /**
     * 拷贝输入流到本地文件
     * @param source 来源输入流
     * @param filePath 文件路径：包括目录+文件名
     * @return
     */
    public static boolean copyInputStreamToFile(InputStream source, String filePath){

        boolean result = true;

        try {
            File file = newFileAndautoMakeDir(filePath);

            // 文件创建成功，则将输入流拷贝到文件
            if(file!=null){
                logger.info("开始拷贝输入流到文件{}", filePath);
                copyInputStreamToFile(source, file);
                logger.info("拷贝输入流到文件{}结束", filePath);
            }

        } catch (IOException e) {
            result = false;
            logger.error("拷贝输入流到文件{}IO异常", filePath, e);
        } catch (Exception e) {
            result = false;
            logger.error("拷贝输入流到文件{}其他异常", filePath, e);
        } finally {
            IOUtils.closeQuietly(source);
        }

        return result;

    }

    public static File newFileAndautoMakeDir(String filePath) throws IOException {
        // 创建文件
        File file = new File(filePath);
        if(!file.exists()) {
            logger.info("文件{}不存在，创建文件", filePath);
            // 校验目录是否存在，不存在则循环创建父目录
            if(!file.getParentFile().exists()) {
                logger.info("文件{}所在目录不存在，循环创建父目录", filePath);
                if(file.getParentFile().mkdirs()){
                    logger.info("文件{}所在目录创建成功，创建新文件", filePath);
                    file.createNewFile();
                }
            }
        }
        return file;
    }
}
