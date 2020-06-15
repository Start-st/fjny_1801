package org.sxj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sxj.mapper.TbItemMapper;
import org.sxj.pojo.TbItem;
import org.sxj.pojo.TbItemExample;
import org.sxj.service.TbItemService;
import org.sxj.utils.EasyUIDataGridResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemServiceImpl implements TbItemService {

	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	
	@Override
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows) {
		//分页插件
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		 List<TbItem> list = tbItemMapper.selectByExample(example);
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		PageInfo<TbItem> pageInfo =new PageInfo<>(list);
		long total =pageInfo.getTotal();
		EasyUIDataGridResult easyUIDateGridResult=new EasyUIDataGridResult(list.size(),list);
		return easyUIDateGridResult;
	}

}
