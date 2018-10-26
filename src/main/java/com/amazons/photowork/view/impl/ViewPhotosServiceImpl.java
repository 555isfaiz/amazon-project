package com.amazons.photowork.view.impl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazons.photowork.Comment;
import com.amazons.photowork.Photo;
import com.amazons.photowork.view.ViewPhotosService;


/**
 * <p>
 * 
 * @author <a href="mailto:1244274837@qq.com">555isfaiz</a>
 * @version 1.0, 2018年10月23日
 */
public class ViewPhotosServiceImpl implements ViewPhotosService{

    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.CN_NORTH_1)
            .withCredentials(new ProfileCredentialsProvider("default"))
            .build();
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.CN_NORTH_1)
            .withCredentials(new ProfileCredentialsProvider("default"))
            .build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private Table table = dynamoDB.getTable("******My_Table_Name******");
    private String bucketName = "******My_Bucket_Name******";
    
    @Override
    public Photo[] getPhotos() {
        ScanSpec scanSpec = new ScanSpec().withProjectionExpression("ID, PhotoURL, PhotoIP, PhotoDate, PhotoTime, PhotoName, Comments");
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);

        Iterator<Item> iter = items.iterator();
        List<Photo> photos = new ArrayList<Photo>();
        while (iter.hasNext()) {
            Photo tmp = new Photo();
            Item item = iter.next();
            tmp.setID(item.getString("ID"));
            tmp.setURL(item.getString("PhotoURL"));
            tmp.setIP(item.getString("PhotoIP"));
            tmp.setDate(item.getString("PhotoDate"));
            tmp.setTime(item.getString("PhotoTime"));
            tmp.setName(item.getString("PhotoName"));
            photos.add(tmp);
        }
        return photos.toArray(new Photo[photos.size()]);
    }

    @Override
    public String addComment(String ID, String IP, String comment) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        SimpleDateFormat time2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = date.format(new Date());
        String timeuseful = time2.format(new Date());
        Map<String, String> tmp = new HashMap<String, String>();
        tmp.put("IP", IP);
        tmp.put("Time", time);
        tmp.put("Comment", comment);
        String exp = "set Comments.New_Comment" + timeuseful + " = :r";
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("ID", ID)
                .withUpdateExpression(exp)
                .withValueMap(new ValueMap().withMap(":r", tmp))
                .withReturnValues(ReturnValue.UPDATED_NEW);
        table.updateItem(updateItemSpec);
        return "success";
    }

    @Override
    public String sharePhoto(String ID) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60;
        expiration.setTime(expTimeMillis);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = 
                new GeneratePresignedUrlRequest(bucketName, ID)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    @Override
    public Photo[] searchPhotos(String query) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Photo getPhotoDetail(String ID) {
        Photo photo = new Photo();
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("ID", ID);
        Item outcome = table.getItem(spec);
        photo.setID(ID);
        photo.setDate(outcome.getString("PhotoDate"));
        photo.setTime(outcome.getString("PhotoTime"));
        photo.setIP(outcome.getString("PhotoIP"));
        photo.setURL(outcome.getString("PhotoURL"));
        photo.setName(outcome.getString("PhotoName"));
        return photo;
    }

    @Override
    public Comment[] getComments(String ID) {
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("ID", ID);
        Item outcome = table.getItem(spec);
        Map<String, Map<String, String>> mp = outcome.getMap("Comments");
        List<Comment> ls = new ArrayList<Comment>();
        for(Map<String, String> value : mp.values())
        {
            Comment tmp = new Comment();
            tmp.setIP(value.get("IP"));
            tmp.setDate(value.get("Time"));
            tmp.setContent(value.get("Comment"));
            ls.add(tmp);
        }
        return ls.toArray(new Comment[ls.size()]);
    }

}
