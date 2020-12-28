/* JSP SCRIPT */
//var auctionId = ${auction.auctionId};
//var userId = ${loginUserId};
 
//var btn_like = document.getElementById("btn_like");
//btn_like.onclick = function(){ changeHeart(); }
 
/* 좋아요 버튼 눌렀을떄 */
function changeHeart(auctionId, userId){ 
     $.ajax({
            type : "POST",  
            url : "/clickLikeAuction.do",       
            dataType : "json",   
            data : "auctionId="+auctionId+"&userId="+userId,
			error : function(){
                alert("통신 에러","error","확인",function(){});
            },
            success : function(jdata) {
                if(jdata.resultCode == -1){
                    alert("좋아요 오류","error","확인",function(){});
                }
                else{
                	var classId = "#i_like_" + auctionId;
                    if(jdata.likeCheck == 1){
                        $(classId).attr("class","bx bxs-heart");
                    }
                    else if (jdata.likeCheck == 0){
                        $(classId).attr("class","bx bx-heart");
                    }
                }
            }
        });
 }
