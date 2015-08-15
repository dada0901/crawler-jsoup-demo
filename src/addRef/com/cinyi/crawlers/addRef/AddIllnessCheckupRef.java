package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.CheckupDao;
import com.cinyi.crawlers.xywy.dao.IllnessCheckupRefDao;
import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.entity.Checkup;
import com.cinyi.crawlers.xywy.entity.Illness;
import com.cinyi.crawlers.xywy.entity.IllnessCheckupRef;

public class AddIllnessCheckupRef {

	private static final IllnessDao ILLNESSDAO = new IllnessDao();

	public static void main(String[] args) {

		List<Illness> illnesses = ILLNESSDAO.selectAll();

		ExecutorService service = Executors.newFixedThreadPool(100);
		for (Illness ill : illnesses) {
			service.execute(new MyThread2(ill));
		}
		service.shutdown();
	}
}

class MyThread2 extends Thread {

	private Illness ill;
	private static final CheckupDao CHECKUPDAO = new CheckupDao();
	private static final IllnessCheckupRefDao REFDAO = new IllnessCheckupRefDao();

	public MyThread2(Illness ill) {
		this.ill = ill;
	}

	public void run() {

		if (ill.getCheckupRef() == null)
			return;

		String[] checkups = ill.getCheckupRef().split(",");

		REFDAO.deleteByIllness(ill.getId());

		for (int i = 0; i < checkups.length; i++) {
			Checkup checkup = CHECKUPDAO.selectOneByName(checkups[i]);

			if (checkup == null)
				continue;

			IllnessCheckupRef ref = new IllnessCheckupRef(ill.getId(),
					checkup.getId());
			REFDAO.insert(ref);

			System.out.println("【" + ill.getName() + "】关联检查项："
					+ checkup.getName());
		}
		System.out.println(this.ill+"结束。");
	}
}
