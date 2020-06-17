<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body><div class="super-theme-example">
			<div style="height: 500px;">
				<table id="dgTbItem"></table>
			</div>
			<br /><br />
			<table id="pg" style="width:300px"></table>
		</div>
		<script type="text/javascript">
		$('#dgTbItem').datagrid({
			url: 'item/getItem',
			fit: true,
			pagination: true,
			fitColumns: true,
			toolbar: [{
				text: '添加',
				iconCls: 'fa fa-plus',
				handler: function() {}
			}, {
				text: '编辑',
				iconCls: 'fa fa-edit',
				handler: function() {}
			}, {
				text: '保存',
				iconCls: 'fa fa-save',
				handler: function() {}
			},{
				text: '删除',
				iconCls: 'fa fa-remove',
				handler: function() {}
			}],

			height: 400,
			columns: [
				[{
					field: 'id',
					title: '商品id',
					width: 100,
					sortable: true,
					align: 'center',
				}, {
					field: 'title',
					title: '商品标题',
					width: 100,
					sortable: true
				},  {
					field: 'sell_point',
					title: '商品卖点',
					width: 100,
					sortable: true
					
				}, {
					field: 'num',
					title: '库存数量',
					width: 100,
					sortable: true,
					align: 'center',
				}, {
					field: 'barcode',
					title: '商品条形码',
					width: 100,
					sortable: true,
					align: 'center',
				
				},{
					field: 'price',
					title: '商品价格',
					width: 100,
					align: 'center',
					sortable: true,
					formatter:TT.formatPrice
				}, {
					field: 'image',
					title: '商品图片',
					width: 300,
					align: 'center',
					formatter:function(value,row){
						return"<img src="+value+" width='100px' height='100px'>"
					}
				},{
					field: 'cid',
					title: '商品类目',
					width: 100,
					align: 'center'
				},{
					field: 'status',
					title: '商品状态',
					width: 100,
					align: 'center',
					formatter:TT.formatItemStatus
				},{
					field: 'created',
					title: '创建时间',
					width: 100,
					align: 'center',
					formatter:TT.formatDateTime
				},{
					field: 'update',
					title: '更新时间',
					width: 100,
					align: 'center',
					formatter:TT.formatDateTime
				},
				]
			]
		});
		/*---------------属性表格*/
		var propertygridJson = {
			"total": 4,
			"rows": [{
					"name": "Name",
					"value": "Bill Smith",
					"group": "ID Settings",
					"editor": "text"
				},
				{
					"name": "Address",
					"value": "",
					"group": "ID Settings",
					"editor": "text"
				},
				{
					"name": "SSN",
					"value": "123-456-7890",
					"group": "ID Settings",
					"editor": "text"
				},
				{
					"name": "Email",
					"value": "bill@gmail.com",
					"group": "Marketing Settings",
					"editor": {
						"type": "validatebox",
						"options": {
							"validType": "email"
						}
					}
				}
			]
		};
			</script>
</body>
</html>