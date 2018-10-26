package com.amazons.photowork.dwr;

import com.amazons.photowork.Comment;
import com.amazons.photowork.Photo;

/**
 * <p>
 * 
 * @author <a href="mailto:1244274837@qq.com">555isfaiz</a>
 * @version 1.0, 2018年10月23日
 */
public interface DwrViewPhotoService {

    public Photo[] getPhotos();
    
    public Photo getPhotoDetail(String ID);
    
    public Comment[] getComments(String ID);
    
    public String addComment(String ID, String IP, String comments);
    
    public String sharePhoto(String ID);
    
    public Photo[] searchPhotos(String query);
}
