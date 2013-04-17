package org.yspr.yosoypuertoricomobil;

import android.content.Intent;
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
import android.widget.TextView;

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

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SerPuertoRicoDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView;
		try {
			if (mItem.id == "Tu Retrato")
			{
				rootView = inflater.inflate(
						R.layout.insert_word, container, false);
				if (mItem != null) {
					((EditText) rootView.findViewById(R.id.editText1))
							.setText("Quien tu eres?");
				}
				final Button button = (Button) rootView.findViewById(R.id.button1);
				button.setOnTouchListener(new OnTouchListener(){

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						Intent i = new Intent(getActivity(), CustomIAPRActivity.class);
						i.putExtra("iam_word",((EditText) rootView.findViewById(R.id.editText1))
							.getText().toString());
						startActivity(i);
						return false;
					}}	
					);
				
			}else{
			rootView = inflater.inflate(
					R.layout.fragment_serpuertorico_detail, container, false);

			// Show the dummy content as text in a TextView.
			if (mItem != null) {
				((TextView) rootView.findViewById(R.id.serpuertorico_detail))
						.setText(mItem.content);
			}
			}
			return rootView;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("PORTRAIT_FRAG",e.toString());
			return null;
		}

		
	}
}
