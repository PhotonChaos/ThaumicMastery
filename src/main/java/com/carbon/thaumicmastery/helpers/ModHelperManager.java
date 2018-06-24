package com.carbon.thaumicmastery.helpers;

import java.util.ArrayList;

public class ModHelperManager {
	private ArrayList<IModHelper> helpers = new ArrayList<IModHelper>();

	public ModHelperManager() {
		// manually add all helpers
		helpers.add(new ThaumcraftHelper());
	}

	public void preInit() {
		for (IModHelper helper : helpers) {
			helper.preInit();
		}
	}

	public void init() {
		for (IModHelper helper : helpers) {
			helper.init();
		}
	}

	public void postInit() {
		for (IModHelper helper : helpers) {
			helper.postInit();
		}
	}

}
