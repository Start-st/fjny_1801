<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="super-theme-example">
	<div style="height: 500px;">
		<table id="dgTbItem"></table>
	</div>
	<br /> <br />
	<!-- <table id="pg" style="width:300px"></table> -->
	<div id="itemEditWindow" class="easyui-window" title="商品编辑"
		style="width: 80%; height: 80%"
		data-options="iconCls:'icon-save',modal:true,closed:true,href:'item-edit'"></div>
</div>
<script type="text/javascript">
	$('#dgTbItem').datagrid({
		url : 'item/getItem',
		fit : true,
		pagination : true,
		fitColumns : true,
		toolbar : [ {
			text : '添加',
			iconCls : 'fa fa-plus',
			handler : function() {
				$("#item-add").click();
			}
		}, {
			text : '编辑',
			iconCls : 'fa fa-edit',
			handler : function() {
				var ids = getSelectionsIds();
				//判断如果未选定，不执行，提示
				if (ids.length == 0) {
					$.messager.alert('提示', '必须选择一个商品才能编辑!');
					return;
				}
				//如果选定多行数据，提示：只能选择一个商品
				if (ids.indexOf(',') > 0) {
					$.messager.alert('提示', '只能选择一个商品!');
					return;
				}
				$('#itemEditWindow').window({
					onLoad : function() {
						var data = $("#dgTbItem").datagrid("getSelections")[0];
						console.log("data:" + data)
						$('#itemEidtForm').form('load', data);	
						//将商品描述进行显示
						$.getJSON("item/query/item-desc/" + data.id,function(result){
							if(result.status == 200){
								itemEditEditor.html(result.data.itemDesc);
							}
						});
						TT.init({
							"pics" : data.image,
							"cid" : data.cid,
							fun : function(node) {
							}
						});
					}
				}).window('open');
			}
		}, {
			text : '删除',
			iconCls : 'fa fa-remove',
			handler : function() {
				var ids = getSelectionsIds();
				if (ids.length == 0) {
				$.messager.alert('提示', "必须选择一个或者多个商品");
				return;
				}//提醒是否删除数据
			$.messager.confirm('确认','确定删除ID为 ' + ids + ' 的商品吗？',function(r){
					if (r) {
						//进行post跟服务端交互
						var params = {"ids" : ids};
						$.post("/item/delete",params,function(data){
							if (data.status == 200) {
								alert("删除成功");
								$("#dgTbItem").datagrid("reload");
							}else{
								alert("删除失败："+data.msg);
									}
							});
						}
					});
				}
			}, 						{
									text : '上架',
									iconCls : 'fa fa-save',
									handler : function() {
									}
								}, {
									text : '下架',
									iconCls : 'fa fa-save',
									handler : function() {
									}
								}, ],

						height : 400,
						columns : [ [
								{
									field : 'id',
									title : '商品id',
									width : 100,
									sortable : true,
									align : 'center',
								},
								{
									field : 'title',
									title : '商品标题',
									width : 100,
									sortable : true
								},
								{
									field : 'sell_point',
									title : '商品卖点',
									width : 100,
									sortable : true

								},
								{
									field : 'num',
									title : '库存数量',
									width : 100,
									sortable : true,
									align : 'center',
								},
								{
									field : 'barcode',
									title : '商品条形码',
									width : 100,
									sortable : true,
									align : 'center',

								},
								{
									field : 'price',
									title : '商品价格',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : TT.formatPrice
								},
								{
									field : 'image',
									title : '商品图片',
									width : 300,
									align : 'center',
									formatter : function(value, row) {
										return "<img src="+value+" width='100px' height='100px'>"
									}
								}, {
									field : 'cid',
									title : '商品类目',
									width : 100,
									align : 'center'
								}, {
									field : 'status',
									title : '商品状态',
									width : 100,
									align : 'center',
									formatter : TT.formatItemStatus
								}, {
									field : 'created',
									title : '创建时间',
									width : 100,
									align : 'center',
									formatter : TT.formatDateTime
								}, {
									field : 'update',
									title : '更新时间',
									width : 100,
									align : 'center',
									formatter : TT.formatDateTime
								}, ] ]
					});
	function getSelectionsIds() {
		var itemList = $("#dgTbItem");
		var sels = itemList.datagrid("getSelections");
		var ids = [];
		for ( var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}
	/*---------------属性表格*/
	var propertygridJson = {
		"total" : 4,
		"rows" : [ {
			"name" : "Name",
			"value" : "Bill Smith",
			"group" : "ID Settings",
			"editor" : "text"
		}, {
			"name" : "Address",
			"value" : "",
			"group" : "ID Settings",
			"editor" : "text"
		}, {
			"name" : "SSN",
			"value" : "123-456-7890",
			"group" : "ID Settings",
			"editor" : "text"
		}, {
			"name" : "Email",
			"value" : "bill@gmail.com",
			"group" : "Marketing Settings",
			"editor" : {
				"type" : "validatebox",
				"options" : {
					"validType" : "email"
				}
			}
		} ]
	};
</script>
