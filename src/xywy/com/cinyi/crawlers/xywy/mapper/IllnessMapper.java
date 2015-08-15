package com.cinyi.crawlers.xywy.mapper;

import com.cinyi.crawlers.xywy.entity.Illness;

public interface IllnessMapper extends BaseMapper<Illness> {

	public void updatePeopleAndBbbl(Illness ill);
}
