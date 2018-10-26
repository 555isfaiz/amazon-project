/**
 * 
 */
window.onload=viewPhotoService.getPhotos(function(ret) {
    	var i, datas = ret || [];
    	var tableData = "";
    	if(datas != null)
    	{
	    	tableData += '<table border="0" frame=void width="80%" align="center">';
	    	var contextPath = document.location.origin+"/photowork/";
	    	var detail = contextPath+"ViewDetail?id=";
	    	for(var i = 1; i < datas.length; i++)
	    	{
	    		 tableData+="<tr>";
	    		 tableData+="<td><image src="+contextPath+datas[i]["URL"]+' height="500" width="500"/>';
	    		 tableData+='<div><a href="'+detail+datas[i]["ID"]+'">'+datas[i]["ID"]+"</a></div></td>";
	    		 tableData+="</tr>";
	    	}
	    	tableData+="</table>"
    	}
    	else
    		tableData+="No photos found..";
    	document.getElementById("div1").innerHTML=tableData;
    });