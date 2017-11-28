package happypillsmvc;
import com.pengrad.telegrambot.model.Update;

public class ControllerAddAlarm implements ControllerAdd{
	
	private Model model;
	private View view;
	
	public ControllerAddAlarm(Model model, View view){
		this.model = model; //connection Controller -> Model
		this.view = view; //connection Controller -> View
	}
	
	public void addAlarm(Update update, Alarm alarm){
		view.sendTypingMessage(update);
		model.add(alarm);
	}

}
