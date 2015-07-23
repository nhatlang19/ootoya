package com.vn.vietatech.posman.view.tab;

import com.vn.vietatech.posman.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple counterpart for tab1 layout...
 */
public class FragmenTab extends Fragment
{
	private int position;
	public FragmenTab(int position) {
		this.position = position;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);
        TextView txtTabId = (TextView) view.findViewById(R.id.txtTabId);
        txtTabId.setText("Tab " + (this.position + 1));
        return view;
    }
}