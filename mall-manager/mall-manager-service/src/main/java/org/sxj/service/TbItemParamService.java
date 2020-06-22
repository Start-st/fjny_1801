package org.sxj.service;

import org.sxj.utils.EasyUIDataGridResult;
import org.sxj.utils.FjnyResult;


public interface TbItemParamService {
	public EasyUIDataGridResult getTbItemParamList(Integer page, Integer rows);

	public FjnyResult checkParam(Long itemCatId);

	public FjnyResult addItemParam(Long cid, String paramData);
}
