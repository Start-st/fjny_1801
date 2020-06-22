package org.sxj.service;

import java.util.List;

import org.sxj.pojo.TbItem;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.FjnyResult;

public interface TbItemService {
	//获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows);
	//添加商品
	public FjnyResult saveTbItem(TbItem tbItem, String desc, String itemParams);
	//更新商品
	public FjnyResult updateTbItem(TbItem tbItem,String desc);
	//删除商品
	public FjnyResult deleteItem(List<Long> ids);
	
	
}
