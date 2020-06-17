package org.sxj.service;

import org.sxj.pojo.TbItem;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.FjnyResult;

public interface TbItemService {
	//获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows);
	//添加商品
	public FjnyResult saveTbItem(TbItem tbItem);
}
