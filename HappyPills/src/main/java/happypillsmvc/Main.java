package happypillsmvc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	private static Model model;
	
	public static void main(String[] args) throws Exception {

		model = Model.getInstance();
		initializeModel(model);
		View view = new View(model);
		model.registerObserver(view); //connection Model -> View
		view.receiveUsersMessages();

	}
	
	public static void initializeModel(Model model) throws Exception{
		Date data1=new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2017");
		Date data2=new SimpleDateFormat("dd/MM/yyyy").parse("22/08/2017");
		Date data3=new SimpleDateFormat("dd/MM/yyyy").parse("23/08/2017");
		model.add(new Alarm("alarme1", data1, "remedio1", 1));
		model.add(new Alarm("alarme2", data2, "remedio2", 2));
		model.add(new Alarm("alarme3", data3, "remedio3", 3));
	}

}