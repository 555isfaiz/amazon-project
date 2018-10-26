package com.amazons.photowork.dwr.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazons.photowork.Comment;
import com.amazons.photowork.Photo;
import com.amazons.photowork.dwr.DwrViewPhotoService;
import com.amazons.photowork.view.ViewPhotosService;

/**
 * <p>
 * 
 * @author <a href="mailto:1244274837@qq.com">555isfaiz</a>
 * @version 1.0, 2018年10月23日
 */
public class DwrViewPhotoServiceImpl implements DwrViewPhotoService{
    @Autowired
    private ViewPhotosService viewPhotoService;
    
    public ViewPhotosService getViewPhtotService()
    {
        return this.viewPhotoService;
    }
    public void setViewPhotoService(ViewPhotosService viewPhotoService)
    {
        this.viewPhotoService = viewPhotoService;
    }
    
    @Override
    public Photo[] getPhotos() {
        return viewPhotoService.getPhotos();
    }

    @Override
    public String addComment(String ID, String IP, String comments) {
        return viewPhotoService.addComment(ID, IP, comments);
    }

    @Override
    public String sharePhoto(String ID) {
        return viewPhotoService.sharePhoto(ID);
    }

    @Override
    public Photo[] searchPhotos(String query) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Photo getPhotoDetail(String ID) {
        return viewPhotoService.getPhotoDetail(ID);
    }
    @Override
    public Comment[] getComments(String ID) {
        return viewPhotoService.getComments(ID);
    }

}
