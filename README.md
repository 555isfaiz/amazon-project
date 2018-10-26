# amazon-project

#BEFORE YOU USE

 This project is a online technical assessment from NWCD company for AWS Cloud Support Associate/Engineer, which is aimed to create a 
 web server on an amazon ec2 instance. The server support people share photos online. People can uplode photos, add comments to any
 photo and generate a pre-signed share URL with a pre-set expire time. The server will record uploder's ip, upload time and photo's 
 name. Photos uploaded will be stored in WEB-PROJ/resources/tmp and the Amazon S3 bucket that you specified. Server will put photo's
 information into DynamoDB, which also means you need to tell the server the exact name of the table in DynamoDB.
 
#FRAMEWORK
 
 Maven + Spring MVC + Dwr + Amazon S3 + DynamoDB.
 
#FILES

 HomeController is the only controller class in this project.
 Services in upload package who support upload service will be used to serve upload.jsp. 
 View package contains the services to support view.jsp who will show a list of photos that are uploaded by everyone.
 Dwr services are in the dwr package.
 Photo and Comment class are pojo class used to return json data to JavaScripts.
 
#HOW TO USE

 This is a eclipse project. But before you import it to your own eclipse, you should install AWS Toolkit for Eclipse.
 About AWS Toolkit for Eclipse:
 https://docs.aws.amazon.com/toolkit-for-eclipse/v1/user-guide/welcome.html
 
#AT LAST
 
 They gave me a week to finish this project. At first I thought it was a test limited in a short time, like 90 mins. So I waited for a
 few days till I have a long spare time to do this, then I regreted XDDDD!
 It doesn't matter whether I can pass this test, I push these code to share my work with you. If you are trying to learn Amazon S3 and 
 DynamoDB, this can be a good example. I hope it will help you!
