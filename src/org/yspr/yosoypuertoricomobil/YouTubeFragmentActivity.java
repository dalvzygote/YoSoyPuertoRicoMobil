/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yspr.yosoypuertoricomobil;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

/**
 * A simple YouTube Android API demo application which shows how to create a simple application that
 * shows a YouTube Video in a {@link YouTubePlayerFragment}.
 * <p>
 * Note, this sample app extends from {@link YouTubeFailureRecoveryActivity} to handle errors, which
 * itself extends {@link YouTubeBaseActivity}. However, you are not required to extend
 * {@link YouTubeBaseActivity} if using {@link YouTubePlayerFragment}s.
 */
public class YouTubeFragmentActivity extends YouTubeFailureRecoveryActivity implements 
View.OnClickListener,
CompoundButton.OnCheckedChangeListener,	
YouTubePlayer.OnFullscreenListener {

	private static final int PORTRAIT_ORIENTATION =  Build.VERSION.SDK_INT < 9
			? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
					: ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

	private LinearLayout baseLayout;
	private YouTubePlayerView playerView;
	private YouTubePlayerSupportFragment playerFrag; 
	private YouTubePlayer player;
	private Button fullscreenButton;
	private CompoundButton checkbox;
	private View otherViews;

	private boolean fullscreen;

	private static final String PLAYLIST_ID =  "PLMKM4mcMnGX0mO7JygHaWntcd4EJ1sRAY";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

/*		setContentView(R.layout.youtube_fragment_layout);

		baseLayout = (LinearLayout) findViewById(R.id.layout);
		playerFrag = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
		fullscreenButton = (Button) findViewById(R.id.fullscreen_button);
		checkbox = (CompoundButton) findViewById(R.id.landscape_fullscreen_checkbox);
		otherViews = findViewById(R.id.other_views);

		checkbox.setOnCheckedChangeListener(this);
		// You can use your own button to switch to fullscreen too
		fullscreenButton.setOnClickListener(this);

		playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

		doLayout();
*/	}
	@Override
	public void onFullscreen(boolean isFullScreen) {
		// TODO Auto-generated method stub
		fullscreen = isFullScreen;
		doLayout();

	}
	@Override
	public void onClick(View v) {
		player.setFullscreen(!fullscreen);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		/*int controlFlags = player.getFullscreenControlFlags();
		if (isChecked) {
			// If you use the FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE, your activity's normal UI
			// should never be laid out in landscape mode (since the video will be fullscreen whenever the
			// activity is in landscape orientation). Therefore you should set the activity's requested
			// orientation to portrait. Typically you would do this in your AndroidManifest.xml, we do it
			// programmatically here since this activity demos fullscreen behavior both with and without
			// this flag).
			setRequestedOrientation(PORTRAIT_ORIENTATION);
			controlFlags |= YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
			controlFlags &= ~YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE;
		}
		player.setFullscreenControlFlags(controlFlags);*/
	}



	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
			boolean wasRestored) {
		//setControlsEnabled();
		player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
		player.setOnFullscreenListener(this);

		if (!wasRestored) {
			player.cuePlaylist(PLAYLIST_ID);
		}
	}
	private void doLayout() {
		LinearLayout.LayoutParams playerParams =
				(LinearLayout.LayoutParams) playerView.getLayoutParams();
		if (fullscreen) {
			// When in fullscreen, the visibility of all other views than the player should be set to
			// GONE and the player should be laid out across the whole screen.
			playerParams.width =  LayoutParams.MATCH_PARENT;
			playerParams.height = LayoutParams.MATCH_PARENT;

			otherViews.setVisibility(View.GONE);
		} else {
			// This layout is up to you - this is just a simple example (vertically stacked boxes in
			// portrait, horizontally stacked in landscape).
			otherViews.setVisibility(View.VISIBLE);
			ViewGroup.LayoutParams otherViewsParams = otherViews.getLayoutParams();
			if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				playerParams.width = otherViewsParams.width = 0;
				playerParams.height = LayoutParams.WRAP_CONTENT;
				otherViewsParams.height = LayoutParams.MATCH_PARENT;
				playerParams.weight = 1;
				baseLayout.setOrientation(LinearLayout.HORIZONTAL);
			} else {
				playerParams.width = otherViewsParams.width = LayoutParams.MATCH_PARENT;
				playerParams.height = LayoutParams.WRAP_CONTENT;
				playerParams.weight = 0;
				otherViewsParams.height = 0;
				baseLayout.setOrientation(LinearLayout.VERTICAL);
			}
			setControlsEnabled();
		}
	}


	private void setControlsEnabled() {
		checkbox.setEnabled(player != null
				&& getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
		fullscreenButton.setEnabled(player != null);
	}
	@Override
	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return playerView;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		doLayout();
	}

}
