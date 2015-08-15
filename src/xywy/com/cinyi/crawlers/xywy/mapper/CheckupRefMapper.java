package com.cinyi.crawlers.xywy.mapper;

import com.cinyi.crawlers.xywy.entity.CheckupRef;

public interface CheckupRefMapper extends BaseMapper<CheckupRef> {
	
	void deleteByCheckup(int checkupId);

}
