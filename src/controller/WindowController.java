package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import datastructures.GraphMatrix;
import exceptions.MaximumRangeExceededException;
import exceptions.UnavaiableBoatsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Country;
import model.ShippingApp;
import model.ShippmentReport;

public class WindowController implements Initializable {
	private ShippingApp sa = new ShippingApp("Assoitas");
	
	GraphMatrix<Country> initial = sa.getCountrys();
	GraphMatrix<Country> covid = sa.getCountrys().kruskal();
	GraphMatrix<Country> inUse = initial;
	
	Country china = new Country("china", 002123);
	Country usa = new Country("usa", 002123);
	Country jamaica = new Country("jamaica", 002123);
	Country brasil = new Country("brasil", 002123);
	Country rusia = new Country("rusia", 002123);
	Country southcorea = new Country("southcorea", 002123);
	Country australia = new Country("australia", 002123);
	
	ObservableList<Country> items = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Country> origin;
	
	@FXML
	private ComboBox<Country> destiny;
	
	@FXML
	private ToggleButton coronaMode;
	
	@FXML
	private Label way;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sa.load();
		items.addAll(australia, brasil, china, jamaica, rusia, southcorea, usa);
		coronaMode.isArmed();
		origin.setItems(items);
		destiny.setItems(items);
		
	}
	
	public void aux(ActionEvent av) {
		System.out.println(origin.getValue());
	}
	
	public void setGraph(ActionEvent av) {
		if(coronaMode.isSelected()) {
			inUse = covid;
		}else {
			inUse = initial;
		}
	}
	
	public void send(ActionEvent av) throws MaximumRangeExceededException {
		try {
			int c1 = sa.findCountryIndex(origin.getValue());
			int c2 = sa.findCountryIndex(destiny.getValue());
			way.setText("Distance: " + inUse.dijkstra(inUse.getValues().get(c1), inUse.getValues().get(c2))+"km");
			Random rand = new Random(); 
	        int num = rand.nextInt(20000); 
			ShippmentReport sr = sa.makeShipment(origin.getValue().getName(), destiny.getValue().getName(), num);
			
			Label lorigin = new Label("Origin: ");
			Label ldestiny = new Label("Destiny: ");
			Label lweight = new Label("Total Load Size: ");
			Label ltime = new Label("Aproximate Delivery Time: ");
			
			Label origin = new Label(sr.getOriginCountry());
			Label destiny = new Label(sr.getDestityCountry());
			Label weight = new Label(sr.getTotalLoadSize()+" Containers");
			Label time = new Label(sr.getAproximateDeliveryTime()+" Days");
			
			Stage newWindow = new Stage();
			GridPane grid = new GridPane();
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setPadding(new Insets(80));
			StackPane secondaryLayout = new StackPane();
			Scene secondScene = new Scene(secondaryLayout, 500, 300);
			
			Button cancel = new Button();
			cancel.setText("Accept");
			cancel.setOnAction(event -> {
				newWindow.close();
			});
			
			secondaryLayout.getChildren().add(grid);
			grid.add(lorigin, 0, 0);
			grid.add(origin, 1, 0);
			grid.add(ldestiny, 0, 1);
			grid.add(destiny, 1, 1);
			grid.add(lweight, 0, 2);
			grid.add(weight, 1, 2);
			grid.add(ltime, 0, 3);
			grid.add(time, 1, 3);
			grid.add(cancel, 0, 4);
			
			newWindow.setTitle("Shippment Report");
			newWindow.initModality(Modality.APPLICATION_MODAL);
			newWindow.setScene(secondScene);

			newWindow.show();
			
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"Please select an origin and a destiny");
			e.printStackTrace();
		}catch (UnavaiableBoatsException e) {
			JOptionPane.showMessageDialog(null,"There are no boats avaible");
		}catch (MaximumRangeExceededException e) {
			JOptionPane.showMessageDialog(null,"The country's boats don't have enough range to make the shipping");
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error");
		}
		
	}
	
	public void showBoatsAustralia() {
		showBoats(australia, "Boats of Australia");
	}
	
	public void showBoatsBrazil() {
		showBoats(brasil, "Boats of Brazil");
	}
	
	public void showBoatsChina() {
		showBoats(china, "Boats of China");
	}
	
	public void showBoatsJamaica() {
		showBoats(jamaica, "Boats of Jamaica");
	}
	
	public void showBoatsRusia() {
		showBoats(rusia, "Boats of Rusia");
	}
	
	public void showBoatsKorea() {
		showBoats(southcorea, "Boats of South Korea");
	}
	
	public void showBoatsUSA() {
		showBoats(usa, "Boats of USA");
	}
	
	public void showBoats(Country country, String title) {
		Label lboats = new Label(title);
		lboats.setFont(new Font(50));
		
		Country c = sa.getCountrys().getValues().get(sa.findCountryIndex(country));
		String s = "";
		for (int i = 0; i < c.getBoats().size(); i++) {
			s = s + "Boat: " + c.getBoats().get(i).getName() + "\n";
		}
//		System.out.println(s);
		Label boats = new Label(s);
		
		Stage newWindow = new Stage();
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(30));
		StackPane secondaryLayout = new StackPane();
		Scene secondScene = new Scene(secondaryLayout, 500, 500);
		
		Button cancel = new Button();
		cancel.setText("Accept");
		cancel.setOnAction(event -> {
			newWindow.close();
		});
		
		secondaryLayout.getChildren().add(grid);
		grid.add(lboats, 0, 0);
		grid.add(boats, 0, 1);
		
		grid.add(cancel, 0, 2);
		
		newWindow.setTitle("Shippment Report");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	public void save() {
		sa.save();
		System.exit(0);
	}
}
