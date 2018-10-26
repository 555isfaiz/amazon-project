<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/viewPhotoService.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/photodetail.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Photo Share</title>
</head>
<body>
<h1>
  Welcom to Photo Share Center!
</h1>
<form name="Form">
  <h1>Here is the photo you want!</h1>
</form>
<div id="infos">
  <table id="tbody1">
  </table>
</div>
<div>
Comment of this photo:
</div>
<div id="comments">

</div>
<div id="addComment">
<form name="Form2" action="/photowork/ViewDetail/addComment" method="post">
<br>Add your comment here:<br>
<input type="text" name="content" size="200" />
<input type="hidden" name="photoid" id="photoid" value=""/>
<br>
<input type="submit" value="Submit">
</form> 
</div>
</body>
</html>