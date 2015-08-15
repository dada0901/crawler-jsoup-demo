package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.dao.SpecialtyDao;
import com.cinyi.crawlers.xywy.dao.SpecialtyHotIllnessRefDao;
import com.cinyi.crawlers.xywy.dao.SpecialtyIllnessRefDao;
import com.cinyi.crawlers.xywy.entity.Illness;
import com.cinyi.crawlers.xywy.entity.Specialty;
import com.cinyi.crawlers.xywy.entity.SpecialtyHotIllnessRef;
import com.cinyi.crawlers.xywy.entity.SpecialtyIllnessRef;

public class AddSpecialtyIllnessRef {

	private static final SpecialtyDao SPECIALTYDAO = new SpecialtyDao();

	public static void main(String[] args) {

		List<Specialty> specialties = SPECIALTYDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(specialties
				.size());
		for (Specialty item : specialties) {
			service.execute(new SpecialtyThread(item));
		}
		service.shutdown();
	}
}

class SpecialtyThread extends Thread {

	private Specialty specialty;
	private static final SpecialtyHotIllnessRefDao HOTREFDAO = new SpecialtyHotIllnessRefDao();
	private static final SpecialtyIllnessRefDao REFDAO = new SpecialtyIllnessRefDao();
	private static final IllnessDao ILLDAO = new IllnessDao();

	public SpecialtyThread(Specialty specialty) {
		this.specialty = specialty;
	}

	public void run() {

		// 解析热门疾病
		if (specialty.getHotIllnessRef() != null) {

			HOTREFDAO.deleteBySpecialty(specialty.getId());

			String[] hotIlls = specialty.getHotIllnessRef().split(",");
			for (int i = 0; i < hotIlls.length; i++) {

				Illness ill = ILLDAO.selectOneByName(hotIlls[i]);
				if (ill != null) {
					SpecialtyHotIllnessRef hotRef = new SpecialtyHotIllnessRef(
							specialty.getId(), ill.getId());
					HOTREFDAO.insert(hotRef);
					System.out.println("【" + specialty.getName() + "】热门疾病："
							+ ill.getName());
				}
			}
		}

		// 解析所有疾病
		if (specialty.getIllnessRef() != null) {

			REFDAO.deleteBySpecialty(specialty.getId());

			String[] ills = specialty.getIllnessRef().split(",");
			for (int i = 0; i < ills.length; i++) {

				Illness ill = ILLDAO.selectOneByName(ills[i]);
				if (ill != null) {
					SpecialtyIllnessRef ref = new SpecialtyIllnessRef(
							specialty.getId(), ill.getId());
					REFDAO.insert(ref);
					System.out.println("【" + specialty.getName() + "】关联疾病："
							+ ill.getName());
				}
			}
		}

		System.out.println("线程【" + specialty.getName() + "】停止。");
	}
}
