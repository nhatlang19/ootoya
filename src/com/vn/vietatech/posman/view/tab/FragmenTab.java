package com.vn.vietatech.posman.view.tab;

import java.util.ArrayList;

import com.vn.vietatech.api.ItemModifierAPI;
import com.vn.vietatech.model.Item;
import com.vn.vietatech.model.ItemCombo;
import com.vn.vietatech.model.ItemModifier;
import com.vn.vietatech.posman.R;
import com.vn.vietatech.posman.view.ModifierRow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

/**
 * A simple counterpart for tab1 layout...
 */
public class FragmenTab extends Fragment {
	private int position;
	private Item item;
	private TableLayout tblCombo;

	public FragmenTab(int position, Item item) {
		this.position = position;
		this.item = item;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tab_1, container, false);
		tblCombo = (TableLayout) view.findViewById(R.id.tblCombo);
		ItemCombo itemCombo = item.getItemCombo().get(position);

		ArrayList<ItemModifier> itemModifiers = new ArrayList<ItemModifier>();
		boolean allowEdit = itemCombo.getQtyEditable().equals("Y");
		String modClass = itemCombo.getModClass();
		if (modClass.trim().isEmpty()) {
			ItemModifier child = new ItemModifier();
			child.setItemCode(itemCombo.getComboItem());
			child.setQuantity(itemCombo.getQuantity());
			child.setModCode("");
			child.setModDesc(itemCombo.getItemDesc());
			child.setUnitPrice("0");
			tblCombo.addView(new ModifierRow(view.getContext(), child,
					allowEdit));

			itemModifiers.add(child);
		} else {
			try {
				itemModifiers = new ItemModifierAPI(view.getContext())
						.getModifierByModifierItem(modClass);
				for (ItemModifier child : itemModifiers) {
					tblCombo.addView(new ModifierRow(view.getContext(), child,
							allowEdit));
				}
			} catch (Exception e) {
				Toast.makeText(view.getContext(), e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		}

		item.setItemModifiers(itemModifiers);

		return view;
	}

	public boolean isValid() {
		int count = tblCombo.getChildCount();
		for (int i = 0; i < count; i++) {
			ModifierRow row = (ModifierRow) tblCombo.getChildAt(i);
			
		}
		return true;
	}
}