package org.yspr.yosoypuertoricomobil;

import java.io.IOException;
import java.util.List;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
//import android.widget.MediaController;
//import android.widget.Toast;
//import android.widget.TextView;
import android.widget.VideoView;
import org.yspr.yosoypuertoricomobil.dummy.DummyContent;

import org.yspr.yosoypuertoricomobil.R;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


/**
 * A fragment representing a single Ser Puerto Rico detail screen. This fragment
 * is either contained in a {@link SerPuertoRicoListActivity} in two-pane mode
 * (on tablets) or a {@link SerPuertoRicoDetailActivity} on handsets.
 */
public class SerPuertoRicoDetailFragment extends  YouTubeFailureRecoveryActivity implements OnInitializedListener {
	private LinearLayout baseLayout;
	private YouTubePlayerSupportFragment playerFrag; 
	private YouTubePlayer player;
	private Button fullscreenButton;
	private CompoundButton checkbox;
	private View otherViews;

	private boolean fullscreen;

	private static final String PLAYLIST_ID =  "PLMKM4mcMnGX0mO7JygHaWntcd4EJ1sRAY";



	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		// TODO Auto-generated method stub
		if (!wasRestored) {
			player.cuePlaylist(PLAYLIST_ID);
		}
		
	}

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private View rootView;
	private YouTubePlayerSupportFragment playFrag;
	private DummyContent.DummyItem mItem;
	private SelfPortraitImageAdapter selfPortraitImageAdapter;
	private static Uri vPath;
	public static VideoView myVidView;
	private static final int REQ_START_STANDALONE_PLAYER = 1;
	private static final int REQ_RESOLVE_SERVICE_MISSING = 2;



	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SerPuertoRicoDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("FRAGMENTDETAIL", "before selfPortraitImage Adapter init");
		selfPortraitImageAdapter = new SelfPortraitImageAdapter(getActivity());
		Log.d("FRAGMENTDETAIL", "after selfPortraitImage Adapter init");
		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// TODO Auto-generated catch block
		/*if (myVidView != null){
			MediaController mc = new MediaController(getActivity());
			myVidView.setMediaController(mc);
			myVidView.requestFocus();
			myVidView.start();
		}*/

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.about,
				container, false);
		if (mItem != null) {


			if (mItem.id == "Tu Aportación") {
				rootView = inflater.inflate(R.layout.insert_word, container,
						false);
				((EditText) rootView.findViewById(R.id.editText1))
				.setText(R.string.portrait_bottom_title);
				Button button = (Button) rootView
						.findViewById(R.id.button1);
				button.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getActivity(),
								CustomIAPRActivity.class);
						i.putExtra("iam_word", ((EditText) rootView
								.findViewById(R.id.editText1)).getText()
								.toString());
						startActivity(i);
						return false;
					}
				});

			} else if (mItem.id == "Sitio Web" || mItem.id == "Instagram") {
				rootView = inflater.inflate(R.layout.yspr_web,
						container, false);
				WebView wv = (WebView) rootView
						.findViewById(R.id.ysprWebView);
				if(mItem.id == "Sitio Web")
					wv.loadUrl("http://www.yosoypr.org");
				else if (mItem.id == "Instagram"){
					wv.loadUrl("http://instagram.com/yosoypr");
				}

			} else if (mItem.id == "Nuestros Videos") {
				rootView = inflater.inflate(R.layout.video_gallery,
						container, false);
				myVidView = (VideoView)rootView.findViewById(R.id.videoView1);
				vPath = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.yspr_gamejam2);
				myVidView.setVideoURI(vPath);
			} else if (mItem.id == "Sobre Nosotros"){
				rootView = inflater.inflate(R.layout.about, container, false);
			} else if (mItem.id == "YouTube"){
				
				rootView = inflater.inflate(R.layout.youtube_fragment_layout, container,
						false);
				
				playFrag = ( YouTubePlayerSupportFragment ) getFragmentManager().findFragmentById(R.id.youtube_fragment);
				playFrag.initialize(DeveloperKey.DEVELOPER_KEY, this);
				
				
			}else if(mItem.id == "Somos Puerto Rico"){
				rootView = inflater.inflate(R.layout.portrait_gallery,
						container, false);
				GridView gridView = (GridView) rootView
						.findViewById(R.id.grid_view);
				// Instance of ImageAdapter Class
				gridView.setAdapter(selfPortraitImageAdapter);
			}
		}


		return rootView;

	}

	@Override
	protected Provider getYouTubePlayerProvider() {
		// TODO Auto-generated method stub
		return null;
	}

}
