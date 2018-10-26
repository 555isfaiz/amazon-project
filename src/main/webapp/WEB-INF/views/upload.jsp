<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Photo Share</title>
</head>
<body>
<h1>
	Welcom to Photo Share Center!
</h1>

  <form name="Form2" action="/photowork/Upload/FirstUpload" method="post"  enctype="multipart/form-data">
  <h1>If you want to upload a photo and share, please!</h1>
  <input type="file" name="file">
  <input type="submit" value="upload"/>
</form>
</body>
</html>
