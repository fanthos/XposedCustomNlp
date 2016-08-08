package me.fts.xposedcustomnlp;

import de.robv.android.xposed.XSharedPreferences;

import java.util.Map;

/**
 * Created by Boyi on 2016/7/29.
 */
public final class Utils {
	static {
		boolean xposedEnabled = true;
		try {
			Class.forName("de.robv.android.xposed.XSharedPreferences");
		} catch (ClassNotFoundException e) {
			xposedEnabled = false;
		}
		if(xposedEnabled) {
			Map<String, ?> pref_data;
			XSharedPreferences pref = new XSharedPreferences(Utils.PACKAGE_NAME, Utils.PREF_NAME);
			prefs = pref;
			PROVIDER_NAME = pref.getString("customnlp_provider", "");
			ENABLED = pref.getBoolean("customnlp_enabled", false);
			pref.makeWorldReadable();
		} else {
			prefs = null;
			PROVIDER_NAME = "";
			ENABLED = false;
		}
	};
	public static final XSharedPreferences prefs;
	public static final String PACKAGE_NAME = "me.fts.xposedcustomnlp";
	public static final String PROVIDER_NAME;
	public static final String PREF_NAME = "nlp_config";
	public static final String[] SERVICE_ACTIONS = {"com.android.location.service.v2.NetworkLocationProvider",
		"com.android.location.service.v3.NetworkLocationProvider"};
	public static final boolean ENABLED;
}
