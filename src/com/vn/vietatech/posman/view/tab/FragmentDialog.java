package com.vn.vietatech.posman.view.tab;

import com.vn.vietatech.posman.R;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragment dialog displaying tab host...
 */
public class FragmentDialog extends DialogFragment {
	// ------------------------------------------------------------------------
	// members
	// ------------------------------------------------------------------------

	private SectionsPagerAdapter sectionsPagerAdapter;
	private ViewPager viewPager;
	private Button btnCloseCombo;
	private TextView lbTitleCombo;
	private int numberOfTab;
	
	public FragmentDialog() {
		super();
		
		this.numberOfTab = 1;
	}
	
	public FragmentDialog(int tabNum) {
		
		this.numberOfTab = tabNum;
	}

	// ------------------------------------------------------------------------
	// public usage
	// ------------------------------------------------------------------------

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// dialog.getWindow().setBackgroundDrawable(new
		// ColorDrawable(Color.YELLOW));
		dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		
		return dialog;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dialog, container);

		// tab slider
		sectionsPagerAdapter = new SectionsPagerAdapter(
				getChildFragmentManager());

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) view.findViewById(R.id.pager);
		viewPager.setAdapter(sectionsPagerAdapter);

		btnCloseCombo = (Button) view.findViewById(R.id.btnCloseCombo);
		lbTitleCombo = (TextView) view.findViewById(R.id.lbTitleCombo);

		registerEvents();
		return view;
	}

	private void registerEvents() {
		btnCloseCombo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	// ------------------------------------------------------------------------
	// inner classes
	// ------------------------------------------------------------------------

	/**
	 * Used for tab paging...
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new FragmenTab(position);
		}

		@Override
		public int getCount() {
			return numberOfTab;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Tab " + (position + 1);
		}
	}
}