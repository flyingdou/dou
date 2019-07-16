//上传附件 弹出窗口       		
function showUploadDialog(height,fileSize,suffix){
   var height1 = 320;
   var fileSize1 = 100;
   var suffix1 = "";
   var data = new Object();
   if (height!=undefined && height!=null && height!="") {
		height1 = height;
   }
   if (fileSize!=undefined && fileSize!=null && fileSize!="") {
	   fileSize1 = fileSize;
   }
   if (suffix!=undefined && suffix!=null && suffix!="") {
	   suffix1 = suffix;
   }
   data["height"] = height1;
   data["fileSize"] = fileSize1;
   data["suffix"] = suffix1;
   
   $.ligerDialog.open({ 
	 url: _ctx+'/attachment/showUploadDialog.do', 				 
	 height:height1, 
	 width:600, 
	 title: '上传',
	 name:'上传',
	 allowClose:false,    
     urlParms : data,
     isResize: true,
     buttons : [ {
		text : "确定",
		onclick : function(item, dialog) {
			window.frames['上传'].ensure();
			$.ligerDialog.close(); // 关闭弹出窗;
			$(".l-dialog,.l-window-mask").css("display", "none"); // 只隐藏遮罩层
		}},{
		text : "取消",
		onclick : function(item, dialog) {
			window.frames['上传'].encancel();
			$.ligerDialog.close(); // 关闭弹出窗;
			$(".l-dialog,.l-window-mask").css("display", "none"); // 只隐藏遮罩层
		}}
	 ]
   });				
}

//根据附件id与存储路径下载附件
function download(id,filePath){
    //判断文件是否存在
 	$.ajax({				
		url: _ctx+'/attachment/isExist.do',
		type:'post',
		data : {'filePath':filePath},
		dataType : "json",
		async:false,
		success : function(data) {
		        if(data.status == 1){
		        	window.location=_ctx+'/attachment/download.do?id='+id+"&filePath="+filePath;
		        }else{
		        	MyMessage.warn("文件不存在");
		        }
		        
		}
	});		 
}
//根据附件id与存储路径视频在线预览
function preview(id,filePath){
  //判断文件是否存在
  $.ajax({				
 	url: _ctx+'/attachment/isExist.do',
	type:'post',
	data : {'filePath':filePath},
	dataType : "json",
	async:false,
	success : function(data) {
	        if(data.status == 1){
	        	window.open(_ctx+'/attachment/preview.do?id='+id+"&filePath="+filePath);
	        }else{
	        	MyMessage.warn("文件不存在");
	        }
	        
	}
  });
}

//根据ids字符串展示附件（删除图标）
function showAttdel(attIds){
 	$.ajax({				
		url: _ctx+'/attachment/findByIds.do',
		type:'post',
		data : {'attIds':attIds},
		dataType : "json",
		async:false,
		success : function(data) {
		   var attachmentList = data.attachmentList;
		   var content = "";
		   for(var i=0;i<attachmentList.length;i++){
	           var fileNameSub = attachmentList[i]['fileName'];
	           if(attachmentList[i]['fileName'].length>20){
	              fileNameSub = attachmentList[i]['fileName'].substring(0,20)+"...";
	           }  
               content+= "<div class='downloadDetail' style='float:left;border: 0.5px solid #DEDEDE;margin-left:5px'><span title='"+attachmentList[i]['fileName']+"'>";
               content+= fileNameSub+"</span><img ";
               content+= "src='"+_ctx+"/images/common/line/delete.png' title='删除' ";
               content+= "id='"+attachmentList[i]['id']+"' alt='"+attachmentList[i]['filePath']+"'";
               content+= "onclick='del1(this.id,this.alt);'></img></div>";  
		   }
		   $('#download').append(content);
		}
	});		
}

//根据ids字符串展示附件（下载,查看图标）
function showAttDownAndView(attIds){  
  	$.ajax({				
		url: _ctx+'/attachment/findByIds.do',
		type:'post',
		data : {'attIds':attIds},
		dataType : "json",
		async:false,
		success : function(data) {
		   var attachmentList = data.attachmentList;
		   var content = "";
		   for(var i=0;i<attachmentList.length;i++){
	           var fileNameSub = attachmentList[i]['fileName'];
	           if(attachmentList[i]['fileName'].length>20){
	              fileNameSub = attachmentList[i]['fileName'].substring(0,20)+"...";
	           }  
               content+= "<div class='downloadDetail'><img src='"+_ctx+"/images/common/line/view.png' ";
               content+= "id='"+attachmentList[i]['id']+"' title='查看' alt='"+attachmentList[i]['filePath']+"' ";
               content+= "onclick='preview(this.id,this.alt);'></img><img ";
               content+= "src='"+_ctx+"/images/common/line/audit.png' ";
               content+= "id='"+attachmentList[i]['id']+"' title='下载' alt='"+attachmentList[i]['filePath']+"' ";
               content+= "onclick='download(this.id,this.alt);'></img><span title='"+attachmentList[i]['fileName']+"'>";
               content+= fileNameSub+"</span></div>";  
		   }
		 
		   $('#download').append(content);
		}
	});
}

//根据ids字符串展示附件（下载图标）
function showAttDown(attIds){
  	$.ajax({				
		url: _ctx+'/attachment/findByIds.do',
		type:'post',
		data : {'attIds':attIds},
		dataType : "json",
		async:false,
		success : function(data) {
		   var attachmentList = data.attachmentList;
		   var content = "";
		   for(var i=0;i<attachmentList.length;i++){
	           var fileNameSub = attachmentList[i]['fileName'];
	           if(attachmentList[i]['fileName'].length>20){
	              fileNameSub = attachmentList[i]['fileName'].substring(0,20)+"...";
	           }  
               content+= "<div class='downloadDetail'><img ";
               content+= "src='"+_ctx+"/images/common/line/audit.png' ";
               content+= "id='"+attachmentList[i]['id']+"' title='下载' alt='"+attachmentList[i]['filePath']+"' ";
               content+= "onclick='download(this.id,this.alt);'></img><span title='"+attachmentList[i]['fileName']+"'>";
               content+= fileNameSub+"</span></div>";  
		   }
		 
		   $('#download').append(content);
		}
	});
}

//得到附件id字符串
function gainAttIds(){
  var attIds = "";
  if($("#download img")!=null && $("#download img")!=undefined && $("#download img")!=""){    
     $("#download img").each(function(){
       attIds += ","+$(this).attr("id");
     });
     attIds = attIds.substring(1);
  }
  return attIds;
}

//图片预览
function showImage(id){
  window.open(_ctx+'/attachment/showImage.do?id='+id);
}

//删除附件
function del(id,filePath) {
	var layer = layui.layer;
	layer.confirm('确定删除?', function(index){
			$.ajax({				
				url: _ctx+'/attachment/del.do',
				type:'post',
				data : {'filePath':filePath},
				dataType : "json",
				async:false,
				success : function(data) {
					if(data.status==1){
					   $('#'+id).parent().remove() ;
					   window.top.alert('删除成功！', {icon: 1}, null);	
					   layer.close(index);
					}
					
				}
			});
		
	});
}
//假删除附件
function del1(id,filePath) {
	layer.confirm('确定删除?', function(index){
		
					   $('#'+id).parent().remove() ;
					   ids+=","+id;
					   ids = ids.substring(1);
					   alert(ids);
					   window.top.alert('删除成功！', {icon: 1}, null);	
					   layer.close(index);
		
	});
}
//ids删除附件
function deleteFileIds(ids) {
			$.ajax({				
				url: _ctx+'/attachment/findByIds.do',
				type:'post',
				data : {'attIds':ids},
				dataType : "json",
				async:false,
				success : function(data) {
					var attachmentList = data.attachmentList;	
					for(var i=0;i<attachmentList.length;i++){
						$.ajax({				
							url: _ctx+'/attachment/del.do',
							type:'post',
							data : {'filePath':attachmentList[i]['filePath']},
							dataType : "json",
							async:false,
							success : function(data) {								
							}
						});
					}	

		} 
	});
}
//id删除附件
function deleteFileId(id) {
	alert(id);
			$.ajax({				
				url: _ctx+'/attachment/findById.do',
				type:'post',
				data : {'attIds':id},
				dataType : "json",
				async:false,
				success : function(data) {
					var attachment = data.attachment;						
						$.ajax({				
							url: _ctx+'/attachment/del.do',
							type:'post',
							data : {'filePath':attachment.filePath},
							dataType : "json",
							async:false,
							success : function(data) {								
							}
						});
						

		} 
	});
}