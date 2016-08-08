package me.fts.xposedcustomnlp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * Created by Boyi on 2016/8/3.
 */
public class NlpProviderPreference extends ListPreference {
	public NlpProviderPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public NlpProviderPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public NlpProviderPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NlpProviderPreference(Context context) {
		super(context);
	}

	@Override
	protected View onCreateDialogView() {
		Adapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_single_choice);
		View newView = new ListView(this.getContext());
		CharSequence[] data = getNlpAppList();
		this.setEntries(data);
		this.setEntryValues(data);
		return newView;
	}

	private CharSequence[] getNlpAppList() {
		PackageManager pm = getContext().getPackageManager();
		SortedSet<CharSequence> packageSet = new TreeSet<CharSequence>();
		for(String serverAction : Utils.SERVICE_ACTIONS) {
			Intent intent = new Intent(serverAction);
			List<ResolveInfo> providerList = pm.queryIntentServices(intent, 0);
			for(ResolveInfo resolveInfo : providerList) {
				packageSet.add(resolveInfo.serviceInfo.packageName);
				Log.i("CustomNlp", "Package Found: " + resolveInfo.serviceInfo.packageName);
			}
		}
		CharSequence[] newList = new CharSequence[packageSet.size()];
		packageSet.toArray(newList);
		return newList;
	}
}
