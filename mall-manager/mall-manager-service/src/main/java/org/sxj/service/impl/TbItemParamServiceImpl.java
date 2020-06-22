package org.sxj.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.RecordCreator;
import org.springframework.stereotype.Service;
import org.sxj.mapper.TbItemCatMapper;
import org.sxj.mapper.TbItemParamMapper;
import org.sxj.pojo.TbItemCat;
import org.sxj.pojo.TbItemParam;
import org.sxj.pojo.TbItemParamExample;
import org.sxj.pojo.TbItemParamExample.Criteria;
import org.sxj.service.TbItemParamService;
import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.ExceptionUtil;
import org.sxj.utils.FjnyResult;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.inject.PrivateBinder;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public EasyUIDataGridResult getTbItemParamList(Integer page,Integer rows) {
		TbItemParamExample example =new TbItemParamExample();
		PageHelper .startPage(page,rows);
		List<org.sxj.pojo.TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		//添加商品类目名称
		for (int i = 0;i<list.size();i++) {
			TbItemParam tbItemParam = list.get(i);
			if(null !=tbItemParam.getItemCatId()) {
				String itemCatName = getItemCatName(tbItemParam.getItemCatId());
				tbItemParam.setItemCatName(itemCatName);
			}
		}
		PageInfo<TbItemParam> pageInfo =new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIDataGridResult gridResult=new EasyUIDataGridResult(total, list);
		return gridResult;
		
	}
	public String getItemCatName(Long id) {
		TbItemCat key =tbItemCatMapper.selectByPrimaryKey(id);
		System.out.println(key);
		return key.getName();
	}
	@Override
	public FjnyResult checkParam(Long itemCatId) {
		try{
			TbItemParamExample example = new TbItemParamExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andItemCatIdEqualTo(itemCatId);
			List<TbItemParam> withBLOBs = tbItemParamMapper.selectByExampleWithBLOBs(example);
			if(null == withBLOBs || withBLOBs.isEmpty()) {
				return FjnyResult.ok();
			}
			return FjnyResult.ok(withBLOBs.get(0));
		}catch (Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	@Override
	public FjnyResult addItemParam(Long cid, String paramData) {
		try {
			TbItemParam record = new TbItemParam();
			record.setItemCatId(cid);
			record.setParamData(paramData);
			record.setCreated(new Date());
			record.setUpdated(new Date());
			tbItemParamMapper.insert(record);
			return FjnyResult.ok();
		} catch (Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}

	}
}
