<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >
    <style type="text/css">
        li{list-style:none}
        li img{width:200px; height: 200px;}
        .tips{color:red}
    </style>


    <script language="javascript">
        function imgLoaded() {
//判断图片大小
            var image = document.getElementById("filesInput").files[0];
            var fileSize = image.size;
            if(fileSize/1024 > 1024){
                info.innerHTML = "图片大小超过1M!";
                document.getElementById("imageBox").innerHTML = "";
                img.src="";
                filesInput.value = "";
            }
            else{
                var reader = new FileReader();
                reader.readAsDataURL(image);
                reader.onload = function(e){
                    imageBox.innerHTML = "<li><img src='" + this.result + "'>"  + "</li>";
                    info.innerHTML = "";
                }
            }
        }
    </script>
    <title>图片上传预览chrome, firefox</title>
</head>


<body>
<div style="margin-top:10px;">
    <p style="font-size: 10px;">支持jpg、png、bmp格式</p>
    <div>
        <input style="width:150px;" type="file" accept="image/*" id="filesInput" οnchange="imgLoaded()">
    </div>
    <div >
        <p id="info" style="color:red;"></p>
    </div><br><br>
</div>
<!--以下为头像空间 -->
<div>
    <ul id="imageBox"></ul>
</div>
</body>
</html>