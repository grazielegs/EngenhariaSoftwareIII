package happypillsmvc;
import com.pengrad.telegrambot.model.Update;

public interface ControllerAdd {

	public void addAlarm(Update update, Alarm alarm);
	
}
