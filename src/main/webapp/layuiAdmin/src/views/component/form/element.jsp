<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" href="./layuiAdmin/start/layui/css/common.css"/>
<title>表单元素</title>

<div class="layui-card layadmin-header" style="background-color:#F2F2F2;">
  <div class="layui-breadcrumb" lay-filter="breadcrumb">
    <a lay-href="">主页</a>
    <a><cite>组件</cite></a>
    <a><cite>表单</cite></a>
    <a><cite>表单元素</cite></a>
  </div>
</div>

<style>
/* 这段样式只是用于演示 */

</style>

 <div class="layui-container">
    <div class="layui-btn-group" style="margin-top: 5px;">
           <button class="layui-btn" id="save"><i class="layui-icon">&#xe654;</i> 添加</button>
    </div>

    <table id="auth-table" class="layui-table" lay-filter="auth-table"></table>
 </div>
<!-- 操作列 -->
<script type="text/html" id="auth-state">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.config({
        base: 'layuiAdmin/start/layui/lay/modules/'
    }).extend({
        treetable: 'treetable'
    }).use(['table', 'treetable','layer'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var treetable = layui.treetable;
        var layer=layui.layer;
        // 渲染表格
        layer.load(2);
        treetable.render({
            treeColIndex: 1,
            treeSpid: 0,
            treeIdName: 'id',
            treePidName: 'pid',
            elem: '#auth-table',
            treeDefaultClose:true,//默认不展开
            url: '/menu/treetable',
            page: false,
            cols: [[
                {type: 'numbers'},
                {field: 'title', minWidth: 200, title: '名称'},
                {field: 'id', minWidth: 200, title: 'id'},
                {field: 'mno', minWidth: 200, title: '序号'},
                {field: 'function', title: '权限标识'},
                {field: 'jump', title: '菜单url'},
                {
                    field: 'type', width: 80, align: 'center', templet: function (d) {
                        if (d.type == 1) {
                            return '<span class="layui-badge layui-bg-gray">菜单</span>';
                        }
                        if (d.type == 2) {
                            return '<span class="layui-badge layui-bg-blue">功能</span>';
                        } else {
                            return '<span class="layui-badge-rim">未知</span>';
                        }
                    }, title: '类型'
                },
                {templet: '#auth-state', width: 120, align: 'center', title: '操作'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
        table.on('tool(auth-table)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'edit'){
            	layer.open({
              	  type: 2,
              	  title:'菜单修改',
              	  skin: 'layui-layer-demo', //样式类名
              	  closeBtn: 0, //不显示关闭按钮
              	  area:['35%','80%'],
              	  anim: 2,
              	  shadeClose: true, //开启遮罩关闭
              	  content: 'layuiAdmin/src/views/component/form/groupupdate.html',
              	  success: function(layero, index){
              		 // 获取子页面的iframe
                      var iframe = window['layui-layer-iframe' + index];
                      // 向子页面的全局函数child传参
                      console.log(data);
                      iframe.child(data);
              	  }
              	});
            } else if(layEvent === 'del'){
              layer.confirm('真的删除行么', function(index){
            	  $.ajax({
            		  url:'/menu/del',
            		  type:'post',
           		      dataType:'json',
           		      data:{"id":data.id,"level":data.level},
           		      success:function(res){
           		    	  if(res=="400"){
           		    		  layer.msg("请先删除下级菜单！");
           		    	  }
           		    	  if(res=="200"){
           		    		  obj.del();
           		    		  layer.msg("删除成功！");
           		    	  }
           		      }
            	  })
                 //删除对应行（tr）的DOM结构
                layer.close(index);
                //向服务端发送删除指令
              });
            }
          });
        
        $("#save").click(function(){
        	layer.open({
        	  type: 2,
        	  title:'菜单添加',
        	  skin: 'layui-layer-demo', //样式类名
        	  closeBtn: 0, //不显示关闭按钮
        	  area:['35%','80%'],
        	  anim: 2,
        	  shadeClose: true, //开启遮罩关闭
        	  content: 'layuiAdmin/src/views/component/form/group.html'
        	});
        });
        
        /* $('#btn-expand').click(function () {
        	
            treetable.expandAll('#auth-table');
        });

        $('#btn-fold').click(function () {
            treetable.foldAll('#auth-table');
        }); */
    });
</script>


<!-- <script>
layui.use(['admin', 'form'], function(){
  var $ = layui.$
  ,admin = layui.admin
  ,element = layui.element
  ,form = layui.form;
  
  form.render(null, 'component-form-element');
  element.render('breadcrumb', 'breadcrumb');
  
  form.on('submit(component-form-element)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  }); 
});
</script> -->
</body>
</html>