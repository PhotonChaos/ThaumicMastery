package com.carbon.thaumicmastery.helpers;

import java.util.ArrayList;

public class ModHelperManager {
	private ArrayList<IModHelper> helpers;

	public ModHelperManager() {
		// manually add all helpers
		helpers.add(new ThaumcraftHelper());
	}

	public void preInit() {

	}

	public void init() {

	}

	public void postInit() {

	}

}
