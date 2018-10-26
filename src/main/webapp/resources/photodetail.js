/**
 * 
 */
var LocString = String(window.document.location.href); 
function getQueryStr(str) { 
    var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp; 
    if (tmp = rs) { 
        return tmp[2]; 
    } 
    // parameter cannot be found 
    return ""; 
} 
var id = getQueryStr("id");
window.onload = function(){
    if(id != ""){
    	document.getElementById("photoid").setAttribute("value", id);
        viewPhotoService.getPhotoDetail(id, function(ret) {
        	var tableData = '<table border="0" frame=void width="80%" align="center">';
        	var contextPath = document.location.origin+"/photowork/";
	        tableData+='<tr><td>';
	        tableData+="<image src="+contextPath+ret["URL"]+' height="800" width="800"/></td>';
	        tableData+='<td><div><p>Photo Name: '+ret["name"]+'</p></div>';
	        tableData+='<div><p>Uploder IP: '+ret["IP"]+'</p></div>';
	        tableData+='<div><p>Uplode Time: '+ret["date"]+"-"+ret["time"]+'</p></div>';
	        tableData+='<div><input type="button" value="Share this photo" onclick="share(\''+ret["name"]+'\');" /></div></td></tr></table>';
        	document.getElementById("infos").innerHTML=tableData;
        });
        viewPhotoService.getComments(id, function(ret){
        	var commentData = '<table border="0" frame=void width="80%" align="left">';
        	if(ret!=null)
        	{
        		var comments = ret || [];
        		for(var i=0; i < comments.length; i++)
        		{
        			commentData+="<tr><div><p>";
        			commentData+=comments[i]["IP"]+":  "+comments[i]["content"]+"</p>";
        			commentData+="<p>"+comments[i]["date"]+"</p></div></tr>";
        		}
        		commentData+="</table>";
        		document.getElementById("comments").innerHTML=commentData;
        	}
        	else
        		document.getElementById("comments").innerHTML="No comments";
        })
    }
    else
    {
    	document.getElementById("infos").innerHTML="Invalid Photo ID";
    	document.getElementById("comments").innerHTML="No comments";
    }
}
function share(id){
	viewPhotoService.sharePhoto(id, function(ret){
		alert("Share link: " + ret);
	});
}