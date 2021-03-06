package ca.eatto.android.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;
import ca.eatto.android.Configure;
import ca.eatto.android.home.collect.HomeCollectFragment;
import ca.eatto.android.home.list.HomeListFragment;
import ca.eatto.android.home.map.HomeMapFragment;

public class HomeFragmentAdapter extends FragmentPagerAdapter {

	private static final String TAG = "HomeFragmentAdapter";
	private static final boolean DEBUG = true;
	private FragmentManager mFragmentManager;
	private FragmentTransaction mCurTransaction;
	private Fragment mCurrentPrimaryItem;

	public HomeFragmentAdapter(FragmentManager fm) {
		super(fm);
		this.mFragmentManager = fm;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment ft = null;
		switch (position) {
		case Configure.HOME_LIST_FRAGMENT:
			ft = new HomeListFragment();
			break;
		case Configure.HOME_MAP_FRAGMENT:
			ft = new HomeMapFragment();
			break;
		case Configure.HOME_COLLECT_FRAGMENT:
			ft = new HomeCollectFragment();
			break;
		default:
			break;
		}
		return ft;
	}

	@Override
	public int getCount() {
		return 3;
	}

	public String makeFragmentName(int id, long itemId) {

		return id + "" + itemId;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (mCurTransaction == null) {
			mCurTransaction = mFragmentManager.beginTransaction();
		}

		final long itemId = getItemId(position);

		// Do we already have this fragment?
		String name = makeFragmentName(container.getId(), itemId);
		Fragment fragment = mFragmentManager.findFragmentByTag(name);
		if (fragment != null) {
			if (DEBUG)
				Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
			mCurTransaction.attach(fragment);
		} else {
			fragment = getItem(position);
			if (DEBUG)
				Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
			mCurTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(), itemId));
		}
		if (fragment != mCurrentPrimaryItem) {
			fragment.setMenuVisibility(false);
			fragment.setUserVisibleHint(false);
		}

		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if (mCurTransaction == null) {
			mCurTransaction = mFragmentManager.beginTransaction();
		}
		if (DEBUG)
			Log.v(TAG, "Detaching item #" + getItemId(position) + ": f=" + object + " v=" + ((Fragment) object).getView());
		mCurTransaction.detach((Fragment) object);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		Fragment fragment = (Fragment) object;
		if (fragment != mCurrentPrimaryItem) {
			if (mCurrentPrimaryItem != null) {
				mCurrentPrimaryItem.setMenuVisibility(false);
				mCurrentPrimaryItem.setUserVisibleHint(false);
			}
			if (fragment != null) {
				fragment.setMenuVisibility(true);
				fragment.setUserVisibleHint(true);
			}
			mCurrentPrimaryItem = fragment;
		}
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		if (mCurTransaction != null) {
			mCurTransaction.commitAllowingStateLoss();
			mCurTransaction = null;
			mFragmentManager.executePendingTransactions();
		}
	}

}
