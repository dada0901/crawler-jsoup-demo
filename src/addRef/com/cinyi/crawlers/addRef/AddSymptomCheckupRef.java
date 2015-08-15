package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.CheckupDao;
import com.cinyi.crawlers.xywy.dao.SymptomCheckupRefDao;
import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.entity.Checkup;
import com.cinyi.crawlers.xywy.entity.Symptom;
import com.cinyi.crawlers.xywy.entity.SymptomCheckupRef;

public class AddSymptomCheckupRef {

	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public static void main(String[] args) {

		List<Symptom> symptoms = SYMPTOMDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(100);
		for (Symptom s : symptoms) {
			service.execute(new MyThread6(s));
		}
		service.shutdown();
	}
}

class MyThread6 extends Thread {

	private Symptom symptom;
	private static final SymptomCheckupRefDao REFDAO = new SymptomCheckupRefDao();
	private static final CheckupDao CHECKUPDAO = new CheckupDao();

	public MyThread6(Symptom symptom) {
		this.symptom = symptom;
	}

	public void run() {

		REFDAO.deleteBySymptom(symptom.getId());

		if (symptom.getCheckupRef() == null)
			return;

		String[] checkups = symptom.getCheckupRef().split(",");
		for (int i = 0; i < checkups.length; i++) {

			String checkupName = checkups[i];

			Checkup checkup = CHECKUPDAO.selectOneByName(checkupName);

			if (checkup == null) {
				System.out.println("字典中没有该检查项：" + checkupName);
				continue;
			}

			SymptomCheckupRef ref = new SymptomCheckupRef(symptom.getId(),
					checkup.getId());
			REFDAO.insert(ref);

			System.out.println("【" + symptom.getName() + "】关联检查项"
					+ checkup.getName());
		}

		System.out.println("【" + symptom.getName() + "】结束。");
	}
}
