package happypillsmvc;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class View implements Observer{

	
	TelegramBot bot = TelegramBotAdapter.build("427715911:AAE6kKWFeOz9ErwM6CXNged0baTnA2L27l4");

	//Object that receives messages
	GetUpdatesResponse updatesResponse;
	//Object that send responses
	SendResponse sendResponse;
	//Object that manage chat actions like "typing action"
	BaseResponse baseResponse;
			
	
	int queuesIndex=0;
	
	ControllerAdd controllerAdd; //Strategy Pattern -- connection View -> Controller
	ControllerSearch controllerSearch; //Strategy Pattern -- connection View -> Controller
	ControllerDelete controllerDelete; //Strategy Pattern -- connection View -> Controller
	
	boolean searchBehaviour = false;
	
	private Model model;
	
	public View(Model model){
		this.model = model; 
	}
	
	public void setControllerAdd(ControllerAdd controllerAdd){ //Strategy Pattern
		this.controllerAdd = controllerAdd;
	}
	
	public void setControllerSearch(ControllerSearch controllerSearch){ //Strategy Pattern
		this.controllerSearch = controllerSearch;
	}
	
	public void setControllerDelete(ControllerDelete controllerDelete){ //Strategy Pattern
		this.controllerDelete = controllerDelete;
	}
	
	public void receiveUsersMessages() {

		
		
		//infinity loop
		while (true){
		
			//taking the Queue of Messages
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(queuesIndex));
			System.out.println(updatesResponse);
			//Queue of messages
			List<Update> updates = updatesResponse.updates();

			//taking each message in the Queue
			for (Update update : updates) {
				System.out.println(update);
				//updating queue's index
				queuesIndex = update.updateId()+1;
				
				if(this.searchBehaviour==true){
					this.callController(update);
					
				}else if(update.message().text().equals("start")){
					setControllerAdd(new ControllerAddAlarm(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"O que deseja fazer? Digite 'adicionar' para criar um novo alarme, 'procurar' para pesquisar um alarme ou 'deletar' para remover um alarme."));
					
				} else if(update.message().text().equals("procurar")){
					setControllerSearch(new ControllerSearchAlarm(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Qual o nome do alarme ou remedio?"));
					this.searchBehaviour = true;
					
				}else if(update.message().text().equals("adicionar")){
					setControllerAdd(new ControllerAddAlarm(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Qual o nome do remedio para adicionar?"));
					this.searchBehaviour = true;
					
				}else if(update.message().text().equals("deletar")){
					setControllerDelete(new ControllerDeleteAlarm(model, this));
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Qual o nome do remedio para deletar?"));
					this.searchBehaviour = true;
					
				} else {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Digite 'start', 'procurar', adicionar' ou 'deletar' para navegar pelas opcoes."));
				}
				
				
				
			}

		}
		
		
	}
	
	
	public void callController(Update update){
		this.controllerSearch.search(update);
	}
	
	public void update(long chatId, String studentsData){
		sendResponse = bot.execute(new SendMessage(chatId, studentsData));
		this.searchBehaviour = false;
	}
	
	public void sendTypingMessage(Update update){
		baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
	}
	

}