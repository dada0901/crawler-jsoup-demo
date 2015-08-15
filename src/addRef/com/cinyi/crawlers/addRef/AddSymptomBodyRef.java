package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.BobyPartDao;
import com.cinyi.crawlers.xywy.dao.SymptomBodyRefDao;
import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.entity.BodyPart;
import com.cinyi.crawlers.xywy.entity.Symptom;
import com.cinyi.crawlers.xywy.entity.SymptomBodyRef;

public class AddSymptomBodyRef {

	private static final SymptomDao SYMPTOMDAO = new SymptomDao();

	public static void main(String[] args) {

		List<Symptom> symptoms = SYMPTOMDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(100);
		for (Symptom s : symptoms) {
			service.execute(new MyThread3(s));
		}
		service.shutdown();
	}
}

class MyThread3 extends Thread {

	private Symptom symptom;
	private static final SymptomBodyRefDao REFDAO = new SymptomBodyRefDao();
	private static final BobyPartDao BODYDAO = new BobyPartDao();

	public MyThread3(Symptom symptom) {
		this.symptom = symptom;
	}

	public void run() {

		REFDAO.deleteBySymptom(symptom.getId());

		if (symptom.getBodyRef() == null)
			return;

		String[] bodyRefs = symptom.getBodyRef().split(" ");
		for (int i = 0; i < bodyRefs.length; i++) {

			String bodyPart = bodyRefs[i];

			if (bodyPart.equals("眼") || bodyPart.equals("耳")
					|| bodyPart.equals("鼻") || bodyPart.equals("口")) {
				bodyPart = bodyPart + "部";
			}

			BodyPart body = BODYDAO.selectOneByName(bodyPart);

			if (body == null) {
				System.out.println("部位：" + bodyRefs[i]);
			}

			SymptomBodyRef ref = new SymptomBodyRef(symptom.getId(),
					body.getId());
			REFDAO.insert(ref);

			System.out.println("【" + symptom.getName() + "】关联部位"
					+ body.getName());
		}

		System.out.println("【" + symptom.getName() + "】结束。");
	}
}