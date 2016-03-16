$(function(){
	  $("#provinceCode").on("change",provinceChange);
	  $("#cityCode").on("change",cityChange);
});
function provinceChange(){  
    try{  
        var pro=$(this).val();  
        var pro_city=$("#pro_city").val().split("#");
        
        var i,j,tmpprocity=new Array();  
        var tmpkeyvalue=new Array();  
        for(i=0;i<pro_city.length;i++){
        	tmpcity=pro_city[i].split("=");
            if(pro==tmpcity[0]){  
                tmpcity=tmpcity[1].split(";");  
                $("#cityCode").html("<option value='-1'>全部城市</option>");  
                for(j=0;j<tmpcity.length;j++){  
                	tmpkeyvalue=tmpcity[j].split("|");
                    $("#cityCode").append("<option value='"+tmpkeyvalue[0]+"'>"+tmpkeyvalue[1]+"</option>");     
                }
                break;
            }  
        }
        $("#regionCode").html(""); 
    }catch(e){  
        alert(e);     
    }  
};
function cityChange(){  
    try{  
        $("#regionCode").html("");  
    	var pro=$(this).val();  
        var pro_city=$("#city_region").val().split("#");
     
        var i,j,tmpprocity=new Array();  
        var tmpkeyvalue=new Array();  
        for(i=0;i<pro_city.length;i++){
        	tmpcity=pro_city[i].split("=");
            if(pro==tmpcity[0]){  
                tmpcity=tmpcity[1].split(";");  
                $("#regionCode").html("<option value='-1'>全部区县</option>");  
                for(j=0;j<tmpcity.length;j++){  
                	tmpkeyvalue=tmpcity[j].split("|");
                    $("#regionCode").append("<option value='"+tmpkeyvalue[0]+"'>"+tmpkeyvalue[1]+"</option>");     
                }  
            }  
     	 break;
        } 
    }catch(e){  
        alert(e);     
    }
};