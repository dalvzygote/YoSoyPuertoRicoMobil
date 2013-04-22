package org.yspr.yosoypuertoricomobil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
//import android.widget.TextView;
import android.widget.VideoView;

import org.yspr.yosoypuertoricomobil.dummy.DummyContent;

/**
 * A fragment representing a single Ser Puerto Rico detail screen. This fragment
 * is either contained in a {@link SerPuertoRicoListActivity} in two-pane mode
 * (on tablets) or a {@link SerPuertoRicoDetailActivity} on handsets.
 */
public class SerPuertoRicoDetailFragment extends Fragment {
	

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;
	private SelfPortraitImageAdapter selfPortraitImageAdapter;
	public static View rootView;
	public static VideoView myVid;

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
		if (myVid != null){
			myVid.requestFocus();
			myVid.start();
		}
		}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
			if (mItem.id == "Tu Retrato") {
				rootView = inflater.inflate(R.layout.insert_word, container,
						false);
				if (mItem != null) {
					((EditText) rootView.findViewById(R.id.editText1))
							.setText("Quien tu eres?");
				}
				final Button button = (Button) rootView
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

			} else if (mItem.id == "Nuestros Videos") {
				rootView = inflater.inflate(R.layout.video_gallery,
						container, false);
				if (mItem != null) {
					myVid = (VideoView) rootView
							.findViewById(R.id.videoView1);
					Uri vPath   = Uri.parse("android.resource://com.pac.myapp/raw/yspr_gamejam2");
					myVid.setVideoURI(vPath);
					

				}
			} else if(rootView != null){
			
				rootView = inflater.inflate(R.layout.portrait_gallery,
						container, false);

				// Show the dummy content as text in a TextView.
				if (mItem != null) {
					GridView gridView = (GridView) rootView
							.findViewById(R.id.grid_view);

					// Instance of ImageAdapter Class
					gridView.setAdapter(selfPortraitImageAdapter);
				}}
			
			return rootView;

	}
}
