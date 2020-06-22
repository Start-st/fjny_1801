package org.sxj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sxj.pojo.TbItem;
import org.sxj.service.TbItemCatService;
import org.sxj.service.TbItemDescService;
import org.sxj.service.TbItemParamService;
import org.sxj.service.TbItemService;
import org.sxj.service.impl.TbItemDescServiceImpl;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.EasyUITreeNodeBean;
import org.sxj.utils.FjnyResult;

@Controller
@RequestMapping("/item")
public class TbItemController {
	
	@Autowired
	public TbItemService tbItemService;
	@Autowired 
	public TbItemCatService tbItemCatService;
	@Autowired
	public TbItemDescService tbItemDescService;
	@Autowired
	public TbItemParamService tbItemParamService;
	
	
	@RequestMapping("/getItem")
	@ResponseBody  
	public EasyUIDataGridResult getTbItemList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="10" )Integer rows) {
		return tbItemService.getTbItemList(page,rows);
	}
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody  
	public FjnyResult saveTbItem(TbItem tbItem,String desc,String temParams) {
		System.out.println("====saveTbItem====");
		return tbItemService.saveTbItem(tbItem,desc,temParams);
	}
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUITreeNodeBean> getTbItemCatList(@RequestParam(defaultValue = "0") Long id) {
		return tbItemCatService.getTbItemCatList(id);
	}
	@RequestMapping("/query/item-desc/{id}")
	@ResponseBody
	public FjnyResult getTbItemDesc(@PathVariable Long id) {
		System.out.println("getTbItemDesc-id:"+id);
		return tbItemDescService.getTbItemDesc(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public FjnyResult updateTbItem(TbItem tbItem,String desc) {
		return tbItemService.updateTbItem(tbItem, desc);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public FjnyResult deleteTbItem(@RequestParam(value="ids")List<Long> ids) {
		System.out.println("ids"+ids);
		return tbItemService.deleteItem(ids);
	}
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public FjnyResult checkParam(@PathVariable Long itemCatId) {
		return tbItemParamService.checkParam(itemCatId);
	}
}
