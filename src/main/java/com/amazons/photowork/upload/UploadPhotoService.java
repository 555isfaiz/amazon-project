package com.amazons.photowork.upload;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.AmazonServiceException;

/**
 * <p>
 * 
 * @author <a href="mailto:1244274837@qq.com">555isfaiz</a>
 * @version 1.0, 2018年10月23日
 */
public interface UploadPhotoService {
    public String  FirstUpload(HttpServletRequest request) throws IllegalStateException, IOException;
    
    public void UploadToBucket(String file_path);
    
    public String UploadToDynamo(String id, Map<String, String> infos, Map<String, Map<String, String>> comments) throws AmazonServiceException;
}
