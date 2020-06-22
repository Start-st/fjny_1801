package org.sxj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sxj.mapper.TbItemDescMapper;
import org.sxj.pojo.TbItemDesc;
import org.sxj.service.TbItemDescService;
import org.sxj.utils.FjnyResult;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {

	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	
	@Override
	public FjnyResult getTbItemDesc(Long itemId) {
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		return FjnyResult.ok(itemDesc);
	}

}
