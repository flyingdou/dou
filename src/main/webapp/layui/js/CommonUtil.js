//function subString(str, len, hasDot) {
//    var newLength = 0;
//    var newStr = "";
//    var chineseRegex = /[^/x00-/xff]/g;
//    var singleChar = "";
//    var strLength = str.replace(chineseRegex,"**").length;
//    for(var i = 0;i < strLength;i++) {
//        singleChar = str.charAt(i).toString();
//        if(singleChar.match(chineseRegex) != null){
//            newLength += 2;
//        } else{
//            newLength++;
//        }
//        if(newLength > len) break;
//        newStr += singleChar;
//    }
//    
//    if (hasDot && strLength > len){
//        newStr += "...";
//    }
//    return newStr;
//}
//
//function ContentProc(str, len) {
//	var newStr = str;
//	try {
//		newStr = subString(str, len, true);
//	} catch (ex) {
//		// 
//	}
//	return "<span title=\"" + str + "\">" + newStr + "</span>"
//}

String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
var MyMessage = {
		success: function(message) {
			$.ligerDialog.success(message);
		},
		successAndCloseDialog: function(message) {
			var closeDialog = function() {
				$.ligerDialog.close(); //关闭弹出窗 
				$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
			};
			$.ligerDialog.success(message, "提示", closeDialog);
		},
		warn: function(message) {
			$.ligerDialog.warn(message);
		},
		warnAndCloseDialog: function(message) {
			var closeDialog = function() {
				$.ligerDialog.close(); //关闭弹出窗 
				$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
			};
			$.ligerDialog.warn(message, "提示", closeDialog);
		},
		error: function(message) {
			$.ligerDialog.error(message);
		},
		errorAndCloseDialog: function(message) {
			var closeDialog = function() {
				$.ligerDialog.close(); //关闭弹出窗 
				$(".l-dialog,.l-window-mask").css("display","none"); //只隐藏遮罩层
			};
			$.ligerDialog.error(message, "提示", closeDialog);
		},
		confirm: function(message, callback) {
			$.ligerDialog.confirm(message, callback);
		},
		alert: function(message) {
			alert(message);
		}
	};

function UndoTab(txt,url) {
			var index = layui.layer.open({
				title : '<span style="font-size:20px;">'+txt+'</span>',
				type : 2,
				area: ['100%', '100%'],
				content : url= url,
				success : function(layero, index){
				setTimeout(function(){
				layui.layer.tips('点击此处返回列表', ' .layui-layer-close', {
					tips: 3
				});
			},500)
		}
		})			
		layui.layer.full(index);
}

function ratorTab(txt,url) {
			var index = layui.layer.open({
				title : '<span style="font-size:20px;">'+txt+'</span>',
				type : 2,
				area: ['100%', '100%'],
				content : url= url,
				success : function(layero, index){
				setTimeout(function(){
				layui.layer.tips('点击此处返回列表', ' .layui-layer-close', {
					tips: 3
				});
			},500)
		}
		})			
		layui.layer.full(index);
}
function UnTOPTab(txt,url) {
	var index = parent.layer.open({
		title : '<span style="font-size:20px;">'+txt+'</span>',
		type : 2,
		area: ['100%', '100%'],
		content : url= url,
		success : function(layero, index){
			setTimeout(function(){
				layui.layer.tips('点击此处返回列表', ' .layui-layer-close', {
					tips: 3
				});
			},500)
		}
	})			
	parent.layer.full(index);
}

function seachTab(txt,url) {
	layer.open({
		  type: 2,
		  title: txt,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['780px', '90%'],
		  content: url //iframe的url
		}); 
}
function seachDeptTab(txt,url) {
	layer.open({
		  type: 2,
		  title: txt,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['780px', '90%'],
		  content: url //iframe的url
		}); 
}
function seachDept1(txt,url) {
	layer.open({
		  type: 2,
		  title: txt,
		  //shadeClose: false,
		  //shade: 0.8,
		  area: ['300px', '90%'],
		  content: url //iframe的url
		}); 
}

function seachIndex(txt,url) {
	layer.open({
		  type: 2,
		  title: txt,
		  //shadeClose: false,
		  //shade: 0.8,
		  area: ['300px', '90%'],
		  content: url //iframe的url
		}); 
}
function finalily() {
	window.top.alert('保存成功！', {icon: 1}, null);
	parent.location.reload();
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}

