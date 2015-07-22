package com.vn.vietatech.posman.dialog;

import com.vn.vietatech.model.Item;
import com.vn.vietatech.posman.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class DialogCombo {
	private AlertDialog alertD;

	public DialogCombo(Context c, Item item) {
		LayoutInflater layoutInflater = LayoutInflater.from(c);
		View promptView = layoutInflater.inflate(R.layout.combo, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
		alertDialogBuilder.setView(promptView);
		alertD = alertDialogBuilder.create();
	}

	public void show() {
		alertD.show();
	}
	
	public void hide() {
		alertD.dismiss();
	}
}