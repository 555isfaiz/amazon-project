package com.amazons.photowork;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amazons.photowork.upload.UploadPhotoService;
import com.amazons.photowork.view.ViewPhotosService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UploadPhotoService uploadPhotoService;
	@Autowired
	private ViewPhotosService viewPhotoService;
	
	public UploadPhotoService getUploadPhotoServicee() {
        return uploadPhotoService;
    }

    public void setUploadPhotoService(UploadPhotoService uploadPhotoService) {
        this.uploadPhotoService = uploadPhotoService;
    }
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "start";
	}
	@RequestMapping(value = "/Upload", method = RequestMethod.GET)
    public String upload(Locale locale, Model model) {
        return "upload";
    }
	@RequestMapping(value = "/View", method = RequestMethod.GET)
    public String view(Locale locale, Model model) {
        return "view";
    }
	@RequestMapping(value = "/ViewDetail", method = RequestMethod.GET)
    public String viewDetail(Locale locale, Model model) {
        return "detail";
    }
	@RequestMapping(value = "/Upload/FirstUpload", method = RequestMethod.POST)
	public String firstupload(HttpServletRequest request) throws IllegalStateException, IOException{
	    return uploadPhotoService.FirstUpload(request);
	}
	@RequestMapping(value = "/ViewDetail/addComment", method = RequestMethod.POST)
    public String addComment(HttpServletRequest request) throws IllegalStateException, IOException{
	    String ID = request.getParameter("photoid");
	    String IP = request.getRemoteAddr();
	    String content = request.getParameter("content");
        return viewPhotoService.addComment(ID, IP, content);
    }
}
