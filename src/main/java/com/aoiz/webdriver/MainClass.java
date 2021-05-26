package com.aoiz.webdriver;

import java.io.File;
import java.util.List;

import com.aoiz.model.EpicModel;
import com.poiji.bind.Poiji;

public class MainClass {
	private static final int NUMBER_OF_THREAD = 5;
	private static final boolean RUN_MODE_CREATE = false;

	public static void main(String[] args) {
		List<EpicModel> epicModels = getMapModel();
		int elementSize = epicModels.size() <= 3 ? epicModels.size() : epicModels.size() / NUMBER_OF_THREAD;
		for (int i = 0; i < NUMBER_OF_THREAD; i++) {
			List<EpicModel> modelsThread = null;
			if (i == NUMBER_OF_THREAD - 1) {
				modelsThread = epicModels.subList(elementSize * i, epicModels.size());
			} else {
				modelsThread = epicModels.subList(elementSize * i, elementSize * i + elementSize);
			}
			if (RUN_MODE_CREATE) {
				EpicCreator creator = new EpicCreator(modelsThread);
				creator.start();
			} else {
				EpicLotto epic = new EpicLotto(modelsThread, i);
				epic.start();
			}
		}
	}

	private static List<EpicModel> getMapModel() {
		List<EpicModel> result = Poiji.fromExcel(new File("Relictum account.xlsx"),
				EpicModel.class);
		System.out.println(result.size());
		return result;
	}
}
