layui.config({
	base : "js/"
}).use(['form','element','layer','jquery','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		element = layui.element,table = layui.table;
		$ = layui.jquery;

		$(".zdylm a,.data_box a").on("click",function(){
			window.parent.addTab($(this));
		});
		
		$("#allMenuButtom").on("click",function(){
			window.parent.allMenu();
		});
});

function openTab(obj){
	window.parent.addTab($(obj));
}
