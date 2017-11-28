package happypillsmvc;
import com.pengrad.telegrambot.model.Update;

public interface ControllerDelete {

	public void deleteAlarm(Update update, Alarm alarm);
	
}