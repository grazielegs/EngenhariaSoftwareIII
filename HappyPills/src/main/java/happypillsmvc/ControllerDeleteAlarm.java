package happypillsmvc;

import com.pengrad.telegrambot.model.Update;

public class ControllerDeleteAlarm implements ControllerDelete{

	private Model model;
	private View view;
	
	public ControllerDeleteAlarm(Model model, View view){
		this.model = model; //connection Controller -> Model
		this.view = view; //connection Controller -> View
	}
	
	public void deleteAlarm(Update update, Alarm alarm){
		view.sendTypingMessage(update);
		model.delete(update, alarm);
	}
	
}
