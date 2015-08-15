package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.dao.SymptomIllnessRefDao;
import com.cinyi.crawlers.xywy.entity.Illness;
import com.cinyi.crawlers.xywy.entity.Symptom;
import com.cinyi.crawlers.xywy.entity.SymptomIllnessRef;

public class AddSymptomIllnessRef {

	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public static void main(String[] args) {

		List<Symptom> symptoms = SYMPTOMDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(100);
		for (Symptom s : symptoms) {
			service.execute(new MyThread5(s));
		}
		service.shutdown();
	}
}

class MyThread5 extends Thread {

	private Symptom symptom;
	private static final SymptomIllnessRefDao REFDAO = new SymptomIllnessRefDao();
	private static final IllnessDao ILLNESSDAO = new IllnessDao();

	public MyThread5(Symptom symptom) {
		this.symptom = symptom;
	}

	public void run() {

		REFDAO.deleteBySymptom(symptom.getId());

		if (symptom.getIllnessRef() == null)
			return;

		String[] illnesses = symptom.getIllnessRef().split(",");
		for (int i = 0; i < illnesses.length; i++) {

			String illName = illnesses[i];

			Illness ill = ILLNESSDAO.selectOneByName(illName);

			if (ill == null) {
				System.out.println("症状：" + illName);
				continue;
			}

			SymptomIllnessRef ref = new SymptomIllnessRef(symptom.getId(),
					ill.getId());
			REFDAO.insert(ref);

			System.out.println("【" + symptom.getName() + "】关联疾病"
					+ ill.getName());
		}

		System.out.println("【" + symptom.getName() + "】结束。");
	}
}
