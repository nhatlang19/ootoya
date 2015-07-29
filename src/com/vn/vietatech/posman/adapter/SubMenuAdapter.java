package com.vn.vietatech.posman.adapter;

import java.io.IOException;
import java.util.ArrayList;

import com.vn.vietatech.api.ItemAPI;
import com.vn.vietatech.api.ItemComboAPI;
import com.vn.vietatech.api.PosMenuAPI;
import com.vn.vietatech.model.Item;
import com.vn.vietatech.model.ItemCombo;
import com.vn.vietatech.model.PosMenu;
import com.vn.vietatech.model.SubMenu;
import com.vn.vietatech.posman.POSMenuActivity;
import com.vn.vietatech.utils.SettingUtil;
import com.vn.vietatech.utils.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Button;
import android.widget.Toast;

public class SubMenuAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<SubMenu> listSubMenu = new ArrayList<SubMenu>();
	
	private PosMenu selectedPOSMenu;
	private int numberClick = 0;
	
	ArrayList<Button> listButtonMenu = new ArrayList<Button>();

	public SubMenuAdapter(Context c, PosMenu selectedPOSMenu) {
		this.mContext = c;
		this.selectedPOSMenu = selectedPOSMenu;

		String POSGroup;
		try {
			POSGroup = SettingUtil.read(mContext).getPosGroup();
			if(selectedPOSMenu.getSubMenu().size() == 0) {
				// load form server
				listSubMenu = new PosMenuAPI(mContext).getSubMenu(selectedPOSMenu.getDefaultValue(), POSGroup);
				selectedPOSMenu.setSubMenu(listSubMenu);
			} else {
				// load from local
				listSubMenu = selectedPOSMenu.getSubMenu();
			}
		} catch (IOException e) {
			Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this.mContext, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
	}

	public int getCount() {
		return listSubMenu.size();
	}

	public SubMenu getItem(int position) {
		return listSubMenu.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Button btn;
		final SubMenu subMenu = listSubMenu.get(position);

		btn = new Button(mContext);
		btn.setLayoutParams(new GridView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		GradientDrawable drawable = new GradientDrawable();
	    drawable.setShape(GradientDrawable.RECTANGLE);
	    drawable.setStroke(2, Color.BLACK);
	    drawable.setColor(Utils.parseColor(selectedPOSMenu.getBtnColor()));
	    btn.setBackgroundDrawable(drawable);
	    btn.setTextColor(Utils.parseColor(selectedPOSMenu.getFontColor()));
		btn.setTextSize(12);
		btn.setText(subMenu.getDescription());
		btn.setLines(2);
		
		btn.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				numberClick++;
				POSMenuActivity activity = (POSMenuActivity) mContext;
				try {
					Item item = new ItemAPI(mContext).getItemBySubMenuSelected(subMenu.getDefaultValue(), activity.getPriceLevel(), String.valueOf(numberClick));
					String comboPack = item.getComboPack();
					item.setItemType(comboPack);
					item.setNumberClick(numberClick);
					if(comboPack.equals("N") || comboPack.equals("R")) {
						subMenu.setItem(item);
						activity.addItem(item);
					} else if(comboPack.equals("C")) {
						ArrayList<ItemCombo> itemComboList = new ItemComboAPI(mContext).getItemComboComboBySubMenuSelected(item.getItemCode());
						item.setItemCombo(itemComboList);
						subMenu.setItem(item);
						activity.onOpenDialogCombo(item);
					}
				} catch (Exception e) {
					Toast.makeText(mContext, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
				
				setColorButton(btn);
				numberClick = 0;
				return false;
			}
		});
		
		btn.setOnTouchListener(new OnTouchListener() {
			long lastDuration = 0;
			long lastDown = 0;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					lastDown = System.currentTimeMillis();
		        } else if (event.getAction() == MotionEvent.ACTION_UP) {
		        	lastDuration = System.currentTimeMillis() - lastDown;
		        	if(lastDuration < 200) {
		        		numberClick++;
		           }
		        }
				return false;
			}
		});
		
		listButtonMenu.add(btn);

		return btn;
	}
	
	private void setColorButton(Button btn) {
	    for(Button button: listButtonMenu) {
	    	GradientDrawable drawable = (GradientDrawable) button.getBackground();
		    drawable.setStroke(2, Color.BLACK);
		    button.setBackgroundDrawable(drawable);
		}
	    
	    GradientDrawable drawable = (GradientDrawable) btn.getBackground();
		drawable.setStroke(10, Color.BLACK);
		btn.setBackgroundDrawable(drawable);
	}

}