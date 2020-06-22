package org.sxj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sxj.service.TbItemParamService;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.EasyUITreeNodeBean;
import org.sxj.utils.FjnyResult;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {
	
	@Autowired
	private TbItemParamService tbItemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult TbItemParamList(@RequestParam(defaultValue="1")
	Integer page,
	@RequestParam(defaultValue="30") Integer rows){
		return tbItemParamService.getTbItemParamList(page,rows);
		
	}
	//查询类目是否存在规格模板 item/param/query/itemcatid
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public FjnyResult checkParam(@PathVariable Long itemCatId) {
		return tbItemParamService.checkParam(itemCatId);
	}
	//保存添加类目规格模板
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public FjnyResult addItemParam(@PathVariable Long cid,String paramData) {
		return tbItemParamService.addItemParam(cid,paramData);
	}
}
