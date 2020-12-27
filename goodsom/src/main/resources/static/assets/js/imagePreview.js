/**
 * 
 */
var fileArr;
var fileInfoArr=[];

// 썸네일 클릭시 삭제.
function fileRemove(index) {
    console.log("index: "+index);
    fileInfoArr.splice(index,1);
 
    var imgId="#img_id_"+index;
    $(imgId).remove();
    console.log(fileInfoArr);
}

// 썸네일 미리보기.
function previewImage(targetObj, View_area) {
    var files=targetObj.files;
    fileArr=Array.prototype.slice.call(files);
    
    var preview = document.getElementById(View_area); // span id
    var ua = window.navigator.userAgent;

    const parent = document.getElementById(View_area);
    while (parent.firstChild) {
    	while (parent.firstChild.firstChild) {
    		parent.firstChild.removeChild(parent.firstChild.firstChild);
    	}
        parent.removeChild(parent.firstChild);
    }

    // ie일때(IE8 이하에서만 작동)
    if (ua.indexOf("MSIE") > -1) {
        targetObj.select();
        try {
            var src = document.selection.createRange().text; // get file full
																// path(IE9,
																// IE10에서 사용 불가)
            var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);
 
 
            if (ie_preview_error) {
                preview.removeChild(ie_preview_error); // error가 있으면 delete
            }
 
            var img = document.getElementById(View_area); // 이미지가 뿌려질 곳
 
            // 이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
        } catch (e) {
            if (!document.getElementById("ie_preview_error_" + View_area)) {
                var info = document.createElement("<p>");
                info.id = "ie_preview_error_" + View_area;
                info.innerHTML = e.name;
                preview.insertBefore(info, null);
            }
        }
        // ie가 아닐때(크롬, 사파리, FF)
    } else {
        var files = targetObj.files;
        for ( var i = 0; i < files.length; i++) {
            var file = files[i];
            fileInfoArr.push(file);
 
            var imageType = /image.*/; // 이미지 파일일경우만.. 뿌려준다.
            if (!file.type.match(imageType))
                continue;
 
            var span=document.createElement('span');
            span.id="img_id_" +i;
            span.style.width = '100px';
            span.style.height = '100px';
            preview.appendChild(span);
 
            var img = document.createElement("img");
            img.className="addImg";
            img.classList.add("obj");
            img.file = file;
            img.style.width='inherit';
            img.style.height='inherit';
            img.style.cursor='pointer';
            const idx=i;
            img.onclick=()=>fileRemove(idx);   // 이미지를 클릭했을 때.
            span.appendChild(img);
 
            if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                var reader = new FileReader();
                reader.onloadend = (function(aImg) {
                    return function(e) {
                        aImg.src = e.target.result;
                    };
                })(img);
                reader.readAsDataURL(file);
            } else { // safari is not supported FileReader
                // alert('not supported FileReader');
                if (!document.getElementById("sfr_preview_error_"
                    + View_area)) {
                    var info = document.createElement("p");
                    info.id = "sfr_preview_error_" + View_area;
                    info.innerHTML = "not supported FileReader";
                    preview.insertBefore(info, null);
                }
            }
        }
    }
}

function previewExistingImgAuction() {
	var is_check = $("#checkExistingImage").is(":checked");
	if (is_check == true) {
		$("#addImg").hide();
		$("#ExistingImg_View_area").show();
	    $("#View_area").hide();
	    $("#useExistingImage").val("yes");
	} else {
		$("#addImg").show();
	    $("#ExistingImg_View_area").hide();
	    $("#View_area").show();
	    $("#useExistingImage").val("no");
	}
}

//첨부파일 용량 확인
function checkImgSize(obj, size) { 
	var check = false; 
	if(window.ActiveXObject) {//IE용인데 IE8이하는 안됨... 
		var fso = new ActiveXObject("Scripting.FileSystemObject"); //var filepath = document.getElementById(obj).value; 
		for (var i = 0; i < files.length; i++)
		var filepath = obj[0].value; 
		var thefile = fso.getFile(filepath); 
		sizeinbytes = thefile.size; 
	} else {//IE 외 
		//sizeinbytes = document.getElementById(obj).files[0].size; 
		sizeinbytes = obj[0].files[0].size; 
	} 
	
	var fSExt = new Array('Bytes', 'KB', 'MB', 'GB'); 
	var i = 0; 
	var checkSize = size;
	
	while(checkSize>900) { 
		checkSize/=1024; i++; 
	} 
	
	checkSize = (Math.round(checkSize*100)/100)+' '+fSExt[i]; 
	
	var fSize = sizeinbytes; 
	if(fSize > size) { 
		alert("첨부파일은 "+ checkSize + " 이하로 등록가능합니다."); 
		check = false; 
	} else { 
		check = true;
	}
	
	return check; 
}
