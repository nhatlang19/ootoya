package com.vn.vietatech.model;

public class ItemModifier {
	private String ItemCode;
	private String Quantity;
	private String ModCode;
	private String ModDesc;
	private String UnitPrice;

	public String getItemCode() {
		return ItemCode;
	}

	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getModCode() {
		return ModCode;
	}

	public void setModCode(String modCode) {
		ModCode = modCode;
	}

	public String getModDesc() {
		return ModDesc;
	}

	public void setModDesc(String modDesc) {
		ModDesc = modDesc;
	}

	public String getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}
}
