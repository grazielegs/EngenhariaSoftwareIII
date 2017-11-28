package happypillsmvc;
import java.util.LinkedList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

public class Model implements Subject{
	
	private List<Observer> observers = new LinkedList<Observer>();
	
	private List<Alarm> alarm = new LinkedList<Alarm>();
	
	private static Model uniqueInstance;
	
	private Model(){}
	
	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}
	
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyObservers(long chatId, String alarmData){
		for(Observer observer:observers){
			observer.update(chatId, alarmData);
		}
	}
	
	public void add(Alarm addAlarm){
		this.alarm.add(addAlarm);
	}
	
	public void search(Update update){
		String alarmData = null;
		for(Alarm alarm: alarm){
			if(alarm.getAlarmName().equals(update.message().text())){
				alarmData = alarm.getAlarmTime().toString();
			}
		}
		
		if(alarmData != null){
			this.notifyObservers(update.message().chat().id(), alarmData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Alarm not found");
		}
		
	}
	
	public void delete(Update update, Alarm alarm){
		if(alarm != null){
			this.alarm.remove(alarm);
		} else {
			this.notifyObservers(update.message().chat().id(), "Alarm not found");
		}
	}

}
