package org.sxj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sxj.pojo.TbItem;
import org.sxj.service.TbItemService;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.FjnyResult;

@Controller
@RequestMapping("/item")
public class TbItemController {
	
	@Autowired
	public TbItemService tbItemService;
	
	@RequestMapping("/getItem")
	@ResponseBody  
	public EasyUIDataGridResult getTbItemList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="10" )Integer rows) {
		return tbItemService.getTbItemList(page,rows);
	}
	@RequestMapping("/save")
	@ResponseBody  
	public FjnyResult saveTbItem(TbItem tbItem) {
		System.out.println("====saveTbItem====");
		return tbItemService.saveTbItem(tbItem);
	}
}
