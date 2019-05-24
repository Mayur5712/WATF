/**
 * 
 */
package com.cloud.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.cloud.ObjRepo.DeliveryAddressObj;
import com.cloud.ObjRepo.ItemDetailsObj;
import com.cloud.fileHandler.JsonParser;

/**
 * @author mayur.waghmare
 *
 */
public class Utils
{


	private static final DateFormat df = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss.SSS");

	public static String getCurrentTime()
	{
		Date date = new Date();
		Log.info("Current Time is, "+df.format(date));
		return df.format(date);
	}

	/**
	 * Filling the Delivery address object which can be used at the time of checkout
	 * @return
	 */
	public static DeliveryAddressObj fillDeliveryAddObj(){
		DeliveryAddressObj deliveryAddr = new DeliveryAddressObj();
		String deliveryDetailsJson = "./TestData/DeliveryAddress.json";
		JsonParser jsonParser = new JsonParser(deliveryDetailsJson);
		deliveryAddr.FullName = jsonParser.getObjectfromJSON().getString("FullName");
		deliveryAddr.MobileNo = jsonParser.getObjectfromJSON().getString("MobileNumber");
		deliveryAddr.PinCode = jsonParser.getObjectfromJSON().getString("PinCode");
		deliveryAddr.AddrLine1 = jsonParser.getObjectfromJSON().getString("AddrLine1");
		deliveryAddr.Town = jsonParser.getObjectfromJSON().getString("Town");
		deliveryAddr.State = jsonParser.getObjectfromJSON().getString("State");

		return deliveryAddr;
	}

	/**
	 * Filling the Delivery address object which can be used at the time of checkout
	 * @return
	 */
	public static ItemDetailsObj itemDetailsObj(){
		ItemDetailsObj itemDetailsObj = new ItemDetailsObj();
		String iteDetailsJsonPath = "./TestData/ItemDetails.json";
		JsonParser jsonParser = new JsonParser(iteDetailsJsonPath);
		itemDetailsObj.ItemName = jsonParser.getObjectfromJSON().getString("ItemName");
		itemDetailsObj.ItemQty = jsonParser.getObjectfromJSON().getString("ItemQuantity");
		itemDetailsObj.Memory = jsonParser.getObjectfromJSON().getString("Memory");
		itemDetailsObj.Color = jsonParser.getObjectfromJSON().getString("Color");

		return itemDetailsObj;
	}






}
