package org.yspr.yosoypuertoricomobil;

//import com.google.analytics.tracking.android.EasyTracker;

import org.yspr.yosoypuertoricomobil.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Somos Puerto Rico. This activity has
 * different presentations for handset and tablet-size devices. On handsets, the
 * activity presents a list of items, which when touched, lead to a
 * {@link SerPuertoRicoDetailActivity} representing item details. On tablets,
 * the activity presents the list of items and item details side-by-side using
 * two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link SerPuertoRicoListFragment} and the item details (if present) is a
 * {@link SerPuertoRicoDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link SerPuertoRicoListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class SerPuertoRicoListActivity extends FragmentActivity implements
SerPuertoRicoListFragment.Callbacks {
		/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_serpuertorico_list);

		if (findViewById(R.id.serpuertorico_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In `two-pane mode, list items should be given the
			// 'activated' state when touched.
			((SerPuertoRicoListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.serpuertorico_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		// The rest of your onStart() code.
	    //EasyTracker.getInstance().activityStart(this); // Add this method.
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		// The rest of your onStop() code.
	    //EasyTracker.getInstance().activityStop(this); // Add this method.
		super.onStop();
	}


	/**
	 * Callback method from {@link SerPuertoRicoListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(SerPuertoRicoDetailFragment.ARG_ITEM_ID, id);
			SerPuertoRicoDetailFragment fragment = new SerPuertoRicoDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.serpuertorico_detail_container, fragment)
			.commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this,
					SerPuertoRicoDetailActivity.class);
			detailIntent.putExtra(SerPuertoRicoDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	

}
