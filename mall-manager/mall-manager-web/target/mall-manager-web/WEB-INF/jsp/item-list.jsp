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
				<table id="dg"></table>
			</div>
			<br /><br />
			<table id="pg" style="width:300px"></table>
		</div>
		<script type="text/javascript">
		$('#dg').datagrid({
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
					title: 'id',
					width: 100,
					sortable: true
				}, {
					field: 'title',
					title: '标题',
					width: 100,
					sortable: true
				}, {
					field: 'price',
					title: '价格',
					width: 100,
					align: 'right',
					sortable: true
				}, {
					field: 'image',
					title: '图片',
					width: 100,
					align: 'right'
				}]
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