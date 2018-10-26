package com.amazons.photowork.upload.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazons.photowork.upload.UploadPhotoService;

import java.util.HashMap;
import java.util.Map;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * <p>
 * 
 * @author <a href="mailto:1244274837@qq.com">555isfaiz</a>
 * @version 1.0, 2018年10月23日
 */
public class UploadPhotoServiceImpl implements UploadPhotoService {
    @Override
    public String  FirstUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String rootPath = "resources/tmp/";
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request))
        {
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            Iterator<String> iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="/home/ec2-user/apache-tomcat-9.0.10/webapps/photowork/resources/tmp/"+file.getOriginalFilename();
                    File f = new File(path);
                    file.transferTo(f);
                    UploadToBucket(path);
                    Map<String, String> infos = new HashMap<String, String>();
                    Map<String, Map<String, String>> comments = new HashMap<String, Map<String, String>>();
                    infos.put("IP", request.getRemoteAddr());
                    infos.put("Date", date.format(new Date()));
                    infos.put("Time", time.format(new Date()));
                    infos.put("PhotoName", f.getName());
                    infos.put("PhotoURL", rootPath + f.getName());
                    UploadToDynamo(f.getName(), infos, comments);
                } 
            }
        }
    return "success"; 
    }
    
    @Override
    public void UploadToBucket(String file_path) throws AmazonServiceException
    {
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.CN_NORTH_1)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();
        File f = new File(file_path);
        s3.putObject("******My_Bucket_Name******", f.getName(), f);
    }
    
    @Override
    public String UploadToDynamo(String id, Map<String, String> infos, Map<String, Map<String, String>> comments)
    {
        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.CN_NORTH_1)
                .withCredentials(new ProfileCredentialsProvider("default"))
                .build();
        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("******My_Table_Name******");
        PutItemOutcome outcome = table
                .putItem(new Item().withPrimaryKey("ID", id)
                .withString("PhotoURL", infos.get("PhotoURL"))
                .withString("PhotoIP", infos.get("IP"))
                .withString("PhotoDate", infos.get("Date"))
                .withString("PhotoTime", infos.get("Time"))
                .withString("PhotoName", infos.get("PhotoName"))
                .withMap("Comments", comments));
        return outcome.getPutItemResult().toString();
    }
}
