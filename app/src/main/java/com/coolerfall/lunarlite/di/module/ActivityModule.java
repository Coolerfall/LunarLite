package com.coolerfall.lunarlite.di.module;

import android.app.Activity;
import android.content.Context;


import com.coolerfall.lunarlite.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 *
 * @author Vincent Cheung (coolingfall@gmail.com)
 */
@Module
public class ActivityModule {
	private final Activity mActivity;

	public ActivityModule(Activity activity) {
		mActivity = activity;
	}

	@Provides @PerActivity Context provideActivityContext() {
		return mActivity;
	}
}
