package com.cinyi.crawlers.addRef;

import java.util.List;

import com.cinyi.crawlers.xywy.dao.BobyPartDao;
import com.cinyi.crawlers.xywy.dao.BodyHotIllnessRefDao;
import com.cinyi.crawlers.xywy.dao.BodyHotSymptomRefDao;
import com.cinyi.crawlers.xywy.dao.BodyIllnessRefDao;
import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.entity.BodyHotIllnessRef;
import com.cinyi.crawlers.xywy.entity.BodyHotSymptomRef;
import com.cinyi.crawlers.xywy.entity.BodyIllnessRef;
import com.cinyi.crawlers.xywy.entity.BodyPart;
import com.cinyi.crawlers.xywy.entity.Illness;
import com.cinyi.crawlers.xywy.entity.Symptom;

public class AddBodyRef {

	private static final BobyPartDao BODYDAO = new BobyPartDao();
	private static final IllnessDao ILLNESSDAO = new IllnessDao();
	private static final BodyHotIllnessRefDao HOTILLNESSREFDAO = new BodyHotIllnessRefDao();
	private static final BodyIllnessRefDao ILLNESSREFDAO = new BodyIllnessRefDao();
	private static final BodyHotSymptomRefDao HOTSYMPTOMREFDAO = new BodyHotSymptomRefDao();
	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public static void main(String[] args) {

		List<BodyPart> bodies = BODYDAO.selectAll();

		for (BodyPart body : bodies) {

			if (body.getHotIllnessRef() != null) {

				HOTILLNESSREFDAO.deleteByBody(body.getId());

				String[] hotIlls = body.getHotIllnessRef().split(",");
				for (int i = 0; i < hotIlls.length; i++) {

					Illness illness = ILLNESSDAO.selectOneByName(hotIlls[i]);

					if (illness != null) {
						BodyHotIllnessRef hotIllRef = new BodyHotIllnessRef(
								body.getId(), illness.getId());
						HOTILLNESSREFDAO.insert(hotIllRef);

						System.out.println("【" + body.getName() + "】热门疾病："
								+ illness.getName());
					}
				}
			}

			if (body.getIllnessRef() != null) {

				ILLNESSREFDAO.deleteByBody(body.getId());

				String[] ills = body.getIllnessRef().split(",");
				for (int j = 0; j < ills.length; j++) {

					Illness illness = ILLNESSDAO.selectOneByName(ills[j]);
					if (illness != null) {
						BodyIllnessRef illRef = new BodyIllnessRef(
								body.getId(), illness.getId());
						ILLNESSREFDAO.insert(illRef);
						System.out.println("【" + body.getName() + "】关联疾病："
								+ illness.getName());
					}
				}
			}

			if (body.getHotSymptomRef() != null) {

				HOTSYMPTOMREFDAO.deleteByBody(body.getId());

				String[] hotSymptoms = body.getHotSymptomRef().split(",");
				for (int k = 0; k < hotSymptoms.length; k++) {

					Symptom symptom = SYMPTOMDAO
							.selectOneByName(hotSymptoms[k]);
					if (symptom != null) {
						BodyHotSymptomRef hotSyptomRef = new BodyHotSymptomRef(
								body.getId(), symptom.getId());
						HOTSYMPTOMREFDAO.insert(hotSyptomRef);
						System.out.println("【" + body.getName() + "】热门症状："
								+ symptom.getName());
					}
				}

			}
		}
		System.out.println("程序结束。");
	}
}
