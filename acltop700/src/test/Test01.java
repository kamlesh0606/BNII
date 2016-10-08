 package test;

import java.util.Date;

import oracle_7600.Device;
import oracle_7600.NewDB;
import ui.ToTrayIcon;
import util.CommonUtil;

import com.tclab.actop700.Constant;
import com.tclab.actop700.Operator;

public class Test01 {
	public static void main(String[] args) {
		ToTrayIcon toTrayIcon = ToTrayIcon.getTray();
		toTrayIcon.init("通讯程序", "ACLTOP-700通讯");
		Device device = NewDB.getDevice("XSMK-CS510","");
		if (device.getBaudrate() != null) {
			String parity = null;
			if (device.getParity().contains("N")) {
				parity = "0";
			}
			Operator.getOperator().init(Constant.is_ack,
					"COM" + device.getComport(), device.getBaudrate( ),
					device.getDataBit(), device.getStopBit(), parity);
		} else {
			Operator.getOperator().init(Constant.is_ack);
		}	
	}
	
	public static void init() {
		CommonUtil.write2File("=====Program Start ======", Constant.resdata_ad,
				true, true);
		CommonUtil.log("", false);
		deletePastFile(10);
	}
	
	public static void deletePastFile(int n) {
		for (int i = n; i >n/2; i--) {
			CommonUtil.deleteFile(Constant.resdata_ad + CommonUtil.getPastDate(new Date(), -i)+ ".txt");
		}
	}
}