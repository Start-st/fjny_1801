package org.sxj.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sxj.mapper.TbItemDescMapper;
import org.sxj.mapper.TbItemMapper;
import org.sxj.mapper.TbItemParamItemMapper;
import org.sxj.pojo.TbItem;
import org.sxj.pojo.TbItemDesc;
import org.sxj.pojo.TbItemExample;
import org.sxj.pojo.TbItemParam;
import org.sxj.pojo.TbItemParamItem;
import org.sxj.service.TbItemDescService;
import org.sxj.service.TbItemService;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.ExceptionUtil;
import org.sxj.utils.FjnyResult;
import org.sxj.utils.IDUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemServiceImpl implements TbItemService {

	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	
	@Override
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows) {
		//分页插件
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		//彻底删除数据库内容
		example.createCriteria().andStatusNotEqualTo((byte)3);
		 List<TbItem> list = tbItemMapper.selectByExample(example);
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		PageInfo<TbItem> pageInfo =new PageInfo<>(list);
		long total =pageInfo.getTotal();
		EasyUIDataGridResult easyUIDateGridResult=new EasyUIDataGridResult(list.size(),list);
		return easyUIDateGridResult;
	}

	public FjnyResult saveTbItem(TbItem tbItem,String desc,String itemParams) {
		try {
		Date date = new Date();
		long genItemId = IDUtils.genItemId();
		tbItem.setId(genItemId);
		tbItem.setCreated(date);
		tbItem.setUpdated(date);	
		tbItem.setStatus((byte)1);
		tbItemMapper.insertSelective(tbItem);
	
		//商品描述添加
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDescMapper.insertSelective(tbItemDesc);
		
		//商品规格数据添加
		TbItemParamItem record =new TbItemParamItem();
		record.setItemId(genItemId);
		record.setParamData(itemParams);
		record.setCreated(date);
		record.setUpdated(date);
		tbItemParamItemMapper.insert(record);
		
		}catch(Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return FjnyResult.ok();
	}

	@Override
	public FjnyResult updateTbItem(TbItem tbItem, String desc) {
		//更新商品信息
		tbItem.setUpdated(new Date());//更新时间
		tbItemMapper.updateByPrimaryKeySelective(tbItem);
		//更新商品描述
		TbItemDesc record = new TbItemDesc();
		record.setItemId(tbItem.getId());
		record.setItemDesc(desc);
		record.setUpdated(new Date());
		tbItemDescMapper.updateByPrimaryKeySelective(record);
		return FjnyResult.ok();
	}

	@Override
	public FjnyResult deleteItem(List<Long> ids) {
		try {
			TbItem record = new TbItem();
			record.setStatus((byte)3);
			TbItemExample example =new TbItemExample();
			example.createCriteria().andIdIn(ids);
			tbItemMapper.updateByExampleSelective(record,example);	
		} catch (Exception e) {
			return FjnyResult.build(500, "删除失败");
		}
		return FjnyResult.ok();
	}
	
}
