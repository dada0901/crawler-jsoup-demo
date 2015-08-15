package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.DoctorDao;
import com.cinyi.crawlers.xywy.dao.DoctorIllnessRefDao;
import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.entity.Doctor;
import com.cinyi.crawlers.xywy.entity.DoctorIllnessRef;
import com.cinyi.crawlers.xywy.entity.Illness;

public class AddDoctorIllnessRef {

	private static final DoctorDao DOCTORDAO = new DoctorDao();

	public static void main(String[] args) {

		List<Doctor> doctors = DOCTORDAO.selectAll();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (Doctor doctor : doctors) {
			service.execute(new MyThread(doctor));
		}
		service.shutdown();
		System.out.println("程序结束。");
	}

}

class MyThread extends Thread {

	private static final IllnessDao ILLNESSDAO = new IllnessDao();
	private static final DoctorIllnessRefDao REFDAO = new DoctorIllnessRefDao();

	private Doctor doctor;

	public MyThread(Doctor doctor) {
		this.doctor = doctor;
	}

	public void run() {
		String cureIllness = doctor.getCureIllness();

		if (cureIllness.length() == 0)
			return;

		REFDAO.deleteByDoctor(doctor.getId());

		String[] illnesses = cureIllness.split(",");
		for (int i = 0; i < illnesses.length; i++) {
			String illName = illnesses[i];
			Illness ill = ILLNESSDAO.selectOneByName(illName);

			if (ill != null) {
				DoctorIllnessRef ref = new DoctorIllnessRef(doctor.getId(),
						ill.getId());
				REFDAO.insert(ref);
				System.out.println("【" + doctor.getName() + "】关联疾病：" + illName);
			}
		}
	}
}
