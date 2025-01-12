

import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rest.RestClient;

public class SolveCodeController implements Initializable {
	
	@FXML
	private ComboBox<String> languages;
	ObservableList<String> options = FXCollections.observableArrayList("C++","C","Python","Java");
	@FXML 
	TextArea input;
	@FXML
	Button reset;
	@FXML
	Button fullscreen;
	private  String questionId;
	
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		languages.setItems(options);
		
		ImageView imageViewReset = new ImageView(new Image("reload.jpg"));
		imageViewReset.setFitHeight(18);
		imageViewReset.setFitWidth(18);
		reset.setGraphic(imageViewReset);
		
		
		ImageView imageViewfs = new ImageView(new Image("fullscreen.png"));
		imageViewfs.setFitHeight(18);
		imageViewfs.setFitWidth(18);
		fullscreen.setGraphic(imageViewfs);
		
		RestClient client = new RestClient("http://127.0.0.1:5000/codecouch/question/", "Usn=s1&Q_id=1", "GET");
		client.run();

		Object obj = null;
		JSONParser parser = new JSONParser(); 
		try {
			obj = parser.parse(client.finalOutputString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject problem = (JSONObject) obj;
		/*
		 * input.setText("Objective\n" +
		 * "This is a simple challenge to help you practice printing to stdout. You may also want to complete Solve Me First in C++ before attempting this challenge.\n"
		 * + "\n" +
		 * "We're starting out by printing the most famous computing phrase of all time! In the editor below, use either printf or cout to print the string Hello, World! to stdout.\n"
		 * + "\n" + "Input Format\n" + "\n" +
		 * "You do not need to read any input in this challenge.\n" + "\n" +
		 * "Output Format\n" + "\n" + "Print Hello, World! to stdout.\n" + "\n" +
		 * "Sample Output\n" + "\n" + "Hello, World!");
		 */
		
		input.setText((String) problem.get("description"));
	}

}
