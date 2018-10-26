package com.amazons.photowork;

/**
 * <p>
 * 
 * @author <a href="mailto:1244274837@qq.com">555isfaiz</a>
 * @version 1.0, 2018年10月25日
 */
public class Comment {
    private String Date;
    private String IP;
    private String Content;
    
    public String getDate()
    {
        return this.Date;
    }
    public void setDate(String Date)
    {
        this.Date = Date;
    }
    
    public String getIP()
    {
        return this.IP;
    }
    public void setIP(String IP)
    {
        this.IP = IP;
    }
    
    public String getContent()
    {
        return this.Content;
    }
    public void setContent(String Content)
    {
        this.Content = Content;
    }
}
