package com.cinyi.crawlers.addRef;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.dao.MedicationDao;
import com.cinyi.crawlers.xywy.dao.MedicationIllnessRefDao;
import com.cinyi.crawlers.xywy.entity.Illness;
import com.cinyi.crawlers.xywy.entity.Medication;
import com.cinyi.crawlers.xywy.entity.MedicationIllnessRef;

public class AddMedicationIllnessRef {

	private static final MedicationDao MEDICATIONDAO = new MedicationDao();

	public static void main(String[] args) {

		List<Medication> medications = MEDICATIONDAO.selectAllZzjb();
			
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (Medication item : medications){
			service.execute(new MedicationThread(item));
		}
		service.shutdown();
	}
}

class MedicationThread extends Thread {
	private Medication medication;
	private IllnessDao ILLDAO = new IllnessDao();
	private MedicationIllnessRefDao REFDAO = new MedicationIllnessRefDao();

	public MedicationThread(Medication medication) {
		this.medication = medication;
	}

	public void run() {

		if (medication.getZzjb() != null) {

			REFDAO.deleteByMedication(medication.getId());

			String[] ills = medication.getZzjb().split(",");
			for (int i = 0; i < ills.length; i++) {

				Illness ill = ILLDAO.selectOneByName(ills[i]);
				if (ill != null) {
					MedicationIllnessRef ref = new MedicationIllnessRef(
							medication.getId(), ill.getId());
					REFDAO.insert(ref);
					System.out.println("【" + medication.getName() + "】关联疾病："
							+ ill.getName());
				}
			}
		}
		System.out.println("线程【"+medication.getName()+"】停止。");
	}
}
