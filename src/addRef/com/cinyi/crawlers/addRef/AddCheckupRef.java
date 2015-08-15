package com.cinyi.crawlers.addRef;

import java.util.List;

import com.cinyi.crawlers.xywy.dao.CheckupDao;
import com.cinyi.crawlers.xywy.dao.CheckupRefDao;
import com.cinyi.crawlers.xywy.entity.Checkup;
import com.cinyi.crawlers.xywy.entity.CheckupRef;

public class AddCheckupRef {

	private static final CheckupDao CHECKUPDAO = new CheckupDao();
	private static final CheckupRefDao REFDAO = new CheckupRefDao();

	public static void main(String[] args) {

		List<Checkup> checkups = CHECKUPDAO.selectAll();

		for (Checkup item : checkups) {

			if (item.getCheckupRef() != null) {

				REFDAO.deleteByCheckup(item.getId());

				String[] checkupRefs = item.getCheckupRef().split(",");
				for (int i = 0; i < checkupRefs.length; i++) {
					Checkup checkup = CHECKUPDAO
							.selectOneByName(checkupRefs[i]);

					if (checkup != null) {
						CheckupRef ref = new CheckupRef(item.getId(),
								checkup.getId());
						REFDAO.insert(ref);
						System.out.println("【" + item.getName() + "】相关检查项："
								+ checkup.getName());
					}
				}
			}
		}
		System.out.println("程序运行结束。");
	}
}
