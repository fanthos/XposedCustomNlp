package me.fts.xposedcustomnlp;

import android.content.res.XResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by Boyi on 2016/7/28.
 */
public class CustomNlpModule implements IXposedHookLoadPackage, IXposedHookZygoteInit {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
		XposedBridge.log("Custom NLP: " + Utils.PROVIDER_NAME);
		if(!Utils.ENABLED) {
			XposedBridge.log("Custom NLP DISABLED: Disabled by user.");
			return;
		}
        if(Utils.PROVIDER_NAME == "") {
            XposedBridge.log("Custom NLP DISABLED: NLP Provider not set.");
            return;
        }
        XResources.setSystemWideReplacement("android", "bool", "config_enableNetworkLocationOverlay", false);
        XResources.setSystemWideReplacement("android", "bool", "config_enableGeocoderOverlay", false);
        XResources.setSystemWideReplacement("android", "string", "config_geocoderProviderPackageName", Utils.PROVIDER_NAME);
        XResources.setSystemWideReplacement("android", "string", "config_networkLocationProviderPackageName", Utils.PROVIDER_NAME);
		XposedBridge.log("Custom NLP ENABLED.");
    }
}
