package com.kyn.myproject.demo.common.util;

import com.kyn.myproject.demo.common.entity.MessageTemplate;
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
public class FileUtils {
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
//                                    LoggerUtil.warn(logger, "processPhase = {} 没有加 switch.", processPhase);
                                    throw new Exception("模板定义错误");
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

}
