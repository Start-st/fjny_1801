<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
.form-item {
	margin-bottom: 15px;
	width: 100%;
	float: left;
}

.form-item>label {
	min-width: 72px;
	display: inline-block;
}

.form-item input, select {
	width: 270px;
}
</style>
<body>
	<div class="super-theme-example">
		<form id="itemAddForm" method="post">
			<div class="form-item">
				<label for="" class="label-top">商品标题：</label>
				 <input id="title"
					name="titel" class="easyui-textbox" prompt="请输入商品名称"
					data-options="required:true">
			</div>
			<div class="form-item">
				<label for="" class="label-top">商品卖点：</label>
				 <input id="sellPoint"
					name="sellPoint" class="easyui-textbox" prompt="请输入文本"
					data-options="required:true,validType:'length[3,10]'">
			</div>
			<div class="form-item">
				<label for="" class="label-top">商品价格：</label>
				 <input id="price"
					name="price" class="easyui-numberbox"
					data-options="min:0" />
			</div>

			<div class="form-item">
				<label for="" class="label-top">库存数量：</label>
				 <input id="price"
					name="num" class="easyui-numberbox"
					data-options="min:0" />
			</div>

			<div class="form-item">
				<label for="" class="label-top">条形码：</label>
				 <input id="price"
					name="barcode" class="easyui-numberbox" value="100"
					data-options="required:true" />
			</div>

			<div class="form-item">
				<label for="" class="label-top">商品图片：</label> <input
					class="easyui-filebox"
					data-options="buttonText:'上传图片',buttonIcon:'fa fa-upload'">
			</div>
			<a href="javascript:void(0)" class="easyui-linkbutton warning" onclick="clearForm()">取消</a>
			<a href="javascript:void(0)" class="easyui-linkbutton success" onclick="submitForm()">上传</a>

		</form>
		<script type="text/javascript">
		//提交表单
			function submitForm() {
				//有效性验证
				if (!$('#itemAddForm').form('validate')) {
					$.messager.alert('提示', '表单还未填写完成!');
					return;
				}
				
				
				//ajax的post方式提交表单
				//$("#itemAddForm").serialize()将表单序列号为key - value形式的字符串
				alert($("#itemAddForm").serialize());
				$.post("/item/save", $("#itemAddForm").serialize(), function(
						data) {
					if (data.status == 200) {
						$("#dgTbItem").datagrid("reload");
						$.messager.alert('操作成功', '恭喜您添加产品成功', 'warning',
								function() {
									$('#item-list').click();
								});
					}
				});
			}
			function clearForm() {
				$('#itemAddForm').form('reset');
			}
		</script>
	</div>