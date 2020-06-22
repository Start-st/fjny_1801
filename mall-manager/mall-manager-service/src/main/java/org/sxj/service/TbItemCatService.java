package org.sxj.service;

import java.util.List;

import org.sxj.utils.EasyUITreeNodeBean;

public interface TbItemCatService {
	List<EasyUITreeNodeBean> getTbItemCatList(Long parendId);
}
