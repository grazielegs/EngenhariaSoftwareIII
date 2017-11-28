package happypillsmvc;

import java.util.Date;

public class Alarm {
	
	private String alarmName;
	private Date alarmTime;
	private String medicineName;
	private int medicineQuantity;
	
	public Alarm(String alarmName,  Date alarmTime, String medicineName, int medicineQuantity) {
		this.alarmName = alarmName;
		this.alarmTime = alarmTime;
		this.medicineName = medicineName;
		this.medicineQuantity = medicineQuantity;
	}
	
	public String getAlarmName() {
		return alarmName;
	}
	
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	
	public Date getAlarmTime() {
		return alarmTime;
	}
	
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	
	public String getMedicineName() {
		return medicineName;
	}
	
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	public int getMedicineQuantity() {
		return medicineQuantity;
	}
	
	public void setMedicineQuantity(int medicineQuantity) {
		this.medicineQuantity = medicineQuantity;
	}
}
