package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.dao.SymptomSymptomRefDao;
import com.cinyi.crawlers.xywy.entity.Symptom;
import com.cinyi.crawlers.xywy.entity.SymptomSymptomRef;

public class AddSymptomSymptomRef {

	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public static void main(String[] args) {

		List<Symptom> symptoms = SYMPTOMDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(100);
		for (Symptom s : symptoms) {
			service.execute(new MyThread7(s));
		}
		service.shutdown();
	}
}

class MyThread7 extends Thread {

	private Symptom symptom;
	private static final SymptomSymptomRefDao REFDAO = new SymptomSymptomRefDao();
	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public MyThread7(Symptom symptom) {
		this.symptom = symptom;
	}

	public void run() {

		REFDAO.deleteBySymptom(symptom.getId());

		if (symptom.getSymptomRef() == null)
			return;

		String[] symptoms = symptom.getSymptomRef().split(",");
		for (int i = 0; i < symptoms.length; i++) {

			String symptomName = symptoms[i];

			Symptom symptom = SYMPTOMDAO.selectOneByName(symptomName);

			if (symptom == null) {
				System.out.println("字典中没有该症状：" + symptomName);
				continue;
			}

			SymptomSymptomRef ref = new SymptomSymptomRef(symptom.getId(),
					symptom.getId());
			REFDAO.insert(ref);

			System.out.println("【" + symptom.getName() + "】关联症状"
					+ symptom.getName());
		}

		System.out.println("【" + symptom.getName() + "】结束。");
	}
}
