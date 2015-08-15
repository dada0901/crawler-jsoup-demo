package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.SpecialtyDao;
import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.dao.SymptomSpecialtyRefDao;
import com.cinyi.crawlers.xywy.entity.Specialty;
import com.cinyi.crawlers.xywy.entity.Symptom;
import com.cinyi.crawlers.xywy.entity.SymptomSpecialtyRef;

public class AddSymptomSpecialtyRef {

	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public static void main(String[] args) {

		List<Symptom> symptoms = SYMPTOMDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(100);
		for (Symptom s : symptoms) {
			service.execute(new MyThread4(s));
		}
		service.shutdown();
	}
}

class MyThread4 extends Thread {

	private Symptom symptom;
	private static final SymptomSpecialtyRefDao REFDAO = new SymptomSpecialtyRefDao();
	private static final SpecialtyDao SPECIALTYDAO = new SpecialtyDao();

	public MyThread4(Symptom symptom) {
		this.symptom = symptom;
	}

	public void run() {

		REFDAO.deleteBySymptom(symptom.getId());

		if (symptom.getSpecialtyRef() == null)
			return;

		String[] specialties = symptom.getSpecialtyRef().split(",");
		for (int i = 0; i < specialties.length; i++) {

			String specialtyName = specialties[i];

			Specialty specialty = SPECIALTYDAO.selectOneByName(specialtyName);

			if (specialty == null) {
				System.out.println("专科：" + specialtyName);
				continue;
			}

			SymptomSpecialtyRef ref = new SymptomSpecialtyRef(symptom.getId(),
					specialty.getId());
			REFDAO.insert(ref);

			System.out.println("【" + symptom.getName() + "】关联专科"
					+ specialty.getName());
		}

		System.out.println("【" + symptom.getName() + "】结束。");
	}
}
