package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Main extends Application {

	
	DoubleList Dlist = new DoubleList();
	static String S1[];

	Martyers M;
	TableView martyersTable = new TableView();
	ObservableList<Martyers> martyerlist;
	

	DNode current;
	
	ComboBox<String> locationBox = new ComboBox<>();
	ComboBox<String> lmBox = new ComboBox<>();
	ComboBox<String> slBox = new ComboBox<>();
	ObservableList<Martyers> searchlist;
	
	

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("برنامج الشهداء");

//*******************************************************************************************************************//

		// Create the TabPane object
		TabPane tabPane = new TabPane();
		Scene tabScene = new Scene(tabPane, 900, 550);
		Pane PanForInsert = new Pane();

		Pane PanForUpdate = new Pane();
		Scene SceneForUpdate = new Scene(PanForUpdate, 900, 500);
		Pane PanForDelete = new Pane();
		Scene SceneForDelete = new Scene(PanForDelete, 900, 500);
		Pane PanForSearch = new Pane();
		Scene SceneForSearch = new Scene(PanForSearch, 900, 500);
		// Create the tabs and add them to the TabPane
		Tab tab1 = new Tab("Location Screen");
		Tab tab2 = new Tab("Martyrs Screen");
		Tab tab3 = new Tab("Statistics Screen");
		Tab tab4 = new Tab("Save Screen");
		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

		// *********************************

		Button ButFile = new Button("Load a file");
		TextArea textArea=new TextArea();

		martyersTable.setLayoutX(370);
		martyersTable.setLayoutY(100);
		martyersTable.setPrefSize(450, 270);

		textArea.setLayoutX(300);
		textArea.setLayoutY(300);
		textArea.setPrefSize(350, 150);
		  textArea.setEditable(false);

		ButFile.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose a file");
			File selectedFile = fileChooser.showOpenDialog(primaryStage);

			if (selectedFile != null) {
				try {
					Scanner input = new Scanner(selectedFile);
					while (input.hasNext()) {

						String s = input.nextLine();

						int age;
						S1 = s.split(",");
						if (S1[1].equals("")) {
							age = -1;
						} else
							age = Integer.valueOf(S1[1]);
						String date = S1[3];
						 String dateOfDeath = (S1.length > 3 && !S1[3].isEmpty()) ? S1[3] : "Unknown";

						M = new Martyers(S1[0], age, parseDate(dateOfDeath), S1[4]);
						if (!Dlist.contains(S1[2])) {

							Dlist.addLast(S1[2]);
						}
						
						if (Dlist.contains(S1[2])) {
							Dlist.getNode(Dlist.find(S1[2])).getFirstTree().insert(M);
							Dlist.getNode(Dlist.find(S1[2])).getStackTree().insert(M.getDod());
							if(Dlist.getNode(Dlist.find(S1[2])).getStackTree().find(M.getDod())!=null) {
								
								Dlist.getNode(Dlist.find(S1[2])).getStackTree().find(M.getDod()).getStack().push(M);
								
							}
							

						}

						// Add the line to listView2
						
					}
					input.close();
				} catch (FileNotFoundException ex) {
					System.out.println("File not found: " + selectedFile.getAbsolutePath());
				}
			} else {
				System.out.println("No file selected.");
			}
		});

//************************file read*****************************//

		Button butNext = new Button("Go to Tab Scene");
		butNext.setLayoutX(40);
		butNext.setLayoutY(230);
		butNext.setPrefSize(100, 50);
		butNext.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
		butNext.setLayoutX(120);
		butNext.setLayoutY(40);
		butNext.setOnAction(event -> {
		
			locationBox.getItems().clear();
			lmBox.getItems().clear();
			slBox.getItems().clear();

			DNode cur;
		cur = Dlist.first;
			while (cur != null) {
			locationBox.getItems().add((String) cur.element);
			lmBox.getItems().add((String) cur.element);
			slBox.getItems().add((String) cur.element);

			cur=cur.next;
			
			}
			
			primaryStage.setScene(tabScene);
			

		});

		// Create a scene with the button as its root
		Button Btnexit = new Button("Exit");
		Btnexit.setLayoutX(40);
		Btnexit.setLayoutY(210);
		Btnexit.setPrefSize(100, 50);
		Btnexit.setStyle("-fx-border-color: red; -fx-border-width: 3px;");
		Btnexit.setOnAction(e -> {
			primaryStage.close();
		});

		Pane MainPane = new Pane();
		MainPane.getChildren().addAll(ButFile, Btnexit, butNext);
		Scene MainScene = new Scene(MainPane, 900, 500);

		// **************************

		// Set the content of each tab
		Pane Pane1 = new Pane();

		Button ButBack1 = new Button("RETURN TO THE MAIN MENU");
		ButBack1.setLayoutX(120);
		ButBack1.setLayoutY(450);
		ButBack1.setStyle("-fx-border-color: red; -fx-border-width: 3px;");

		// ********************************comboBox***********************************************
		
		
		locationBox.setValue("Location");
		locationBox.setEditable(true);


		

		
		
		
		
		//*****************************************************************************************

		Button mainback = new Button("RETURN");
		mainback.setLayoutX(10);
		mainback.setLayoutY(200);
		mainback.setPrefSize(100, 30);
		mainback.setStyle("-fx-border-color: red; -fx-border-width: 3px;");
		
		
		

		TextField textf1 = new TextField();
		textf1.setText("Type here");
		locationBox.setLayoutX(130);
		locationBox.setLayoutY(30);
		// textf1.setPrefSize(220, 40);
		locationBox.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
		

		Button btn1 = new Button("Insert");
		btn1.setLayoutX(10);
		btn1.setLayoutY(30);
		btn1.setPrefSize(100, 30);
		btn1.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btn1.setOnAction(e -> {
			String selectedItem = locationBox.getSelectionModel().getSelectedItem();
			if (Dlist.contains(selectedItem)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("This is an information alert");
				alert.setContentText("البلدة موجودة بالفعل !");

				alert.showAndWait();
			} else {
				// Dlist.addFirst(textf1.getText());
				Dlist.addLast(selectedItem);
				locationBox.getItems().add(selectedItem);
			}
		});
		

		// *******************************************************************************
		// Button btn2 = new Button("Update");
		Button btn2 = new Button("Update");
		btn2.setLayoutX(10);
		btn2.setLayoutY(70);
		btn2.setPrefSize(100, 30);
		btn2.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btn2.setOnAction(e -> {
			Stage stage = new Stage();
			Pane pane = new Pane();
			Scene scene = new Scene(pane, 300, 200);

			TextField textField = new TextField("Enter new location");
			Button confirmButton = new Button("Confirm");
			confirmButton.setLayoutX(20);
			confirmButton.setLayoutY(30);
			confirmButton.setOnAction(ee -> {
				String selectedItem = locationBox.getSelectionModel().getSelectedItem();
				if (selectedItem != null) {
					String newLocation = textField.getText();
					Dlist.update(selectedItem, newLocation);
					  textArea.setText("update Succesfully");

					
					
				}
				stage.close();
			});

			pane.getChildren().addAll(textField, confirmButton);
			stage.setScene(scene);
			stage.show();
		});

		// *******************************************************************************

		Button btn3 = new Button("Delete");
		btn3.setLayoutX(10);
		btn3.setLayoutY(110);
		btn3.setPrefSize(100, 30);
		btn3.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btn3.setOnAction(e -> {
			textArea.clear();
			String selectedValue = locationBox.getValue();
			Dlist.remove(selectedValue);
			textArea.setText("Deleted Succesfully");
		});

		// *******************************************************************************

		Button btn4 = new Button("Search");
		btn4.setLayoutX(10);
		btn4.setLayoutY(150);
		btn4.setPrefSize(100, 30);
		btn4.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btn4.setOnAction(e -> {
			String selectedCity = locationBox.getSelectionModel().getSelectedItem();
			textArea.clear();
			if (selectedCity != null) {
				if(Dlist.contains(selectedCity)) {
					textArea.setText("Location fined");
				}
				else
					textArea.setText("not fined");
				
				}
				
			 else {
				System.out.println("No city selected.");
			}
		});

		
		

		// *******************************************************************************

		

		Pane1.getChildren().addAll( textArea, locationBox, mainback, btn1, btn2, btn3, btn4);
		tab1.setContent(Pane1);

//********************************Table View*********************************
		
		 TableColumn<Martyers, Object> nameColumn = new TableColumn<>("Name");
	        TableColumn<Martyers, Object> ageColumn = new TableColumn<>("Age");
	        TableColumn<Martyers, Object> dateOfDeathColumn = new TableColumn<>("Date of Death");
	        TableColumn<Martyers, Object> genderColumn = new TableColumn<>("Gender");

	        // Set up the data mapping for each column
	        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
	        dateOfDeathColumn.setCellValueFactory(new PropertyValueFactory<>("dod"));
	        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

	        // Add columns to the table view
	        martyersTable.getColumns().addAll(nameColumn, ageColumn, dateOfDeathColumn, genderColumn);
	        martyerlist=FXCollections.observableArrayList();
	//	*********************************************************************
		Pane Pane2 = new Pane();

		Button ButBack2 = new Button("RETURN TO THE MAIN MENU");
		ButBack2.setLayoutX(90);
		ButBack2.setLayoutY(450);
		ButBack2.setStyle("-fx-border-color: red; -fx-border-width: 3px;");

		Button btnInsert = new Button("new martyr");
		btnInsert.setLayoutX(10);
		btnInsert.setLayoutY(60);
		btnInsert.setPrefSize(100, 30);
		btnInsert.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btnInsert.setOnAction(e -> {
			Stage stage2 = new Stage();
			Pane pn2 = new Pane();
			Scene sn2 = new Scene(pn2, 300, 200);
			ComboBox<String> lBox = new ComboBox<>();
			lBox.getItems().clear();
			DNode cur;
		cur = Dlist.first;
			while (cur != null) {
			lBox.getItems().add((String) cur.element);
			cur=cur.next;
			
			}
			lBox.setValue("Location");
			lBox.setEditable(true);
			

			TextField txtf3 = new TextField("name");
			txtf3.setLayoutY(10);
			txtf3.setOnMouseClicked(event -> txtf3.clear());
			
			lBox.setLayoutY(44);

			TextField txtf4 = new TextField("age");
			txtf4.setLayoutY(85);
			txtf4.setOnMouseClicked(event -> txtf4.clear());

			TextField txtf5 = new TextField("MM/DD/YYYY");
			txtf5.setLayoutY(115);
			txtf5.setOnMouseClicked(event -> txtf5.clear());
			
			
			ComboBox<String> gBox = new ComboBox<>();
			gBox.getItems().add("F");
			gBox.getItems().add("M");
			gBox.setLayoutY(150);
			gBox.setValue("Gender");

			

			Button bts2 = new Button("confirm");
			bts2.setLayoutX(200);
			bts2.setLayoutY(120);

			bts2.setOnAction(e2 -> {
			//	String location = txtf2.getText();
				String name = txtf3.getText();
				int age =  Integer.valueOf(txtf4.getText());
				String date = txtf5.getText();
				String gender = gBox.getSelectionModel().getSelectedItem();

				M = new Martyers(name, age, parseDate(date), gender );
				String selectedCity = lBox.getSelectionModel().getSelectedItem();
				if (!Dlist.contains(selectedCity)) {

					Dlist.addLast(selectedCity);
				}
				
				if (Dlist.contains(selectedCity)) {
					Dlist.getNode(Dlist.find(selectedCity)).getFirstTree().insert(M);
					Dlist.getNode(Dlist.find(selectedCity)).getStackTree().insert(M.getDod());
					if(Dlist.getNode(Dlist.find(selectedCity)).getStackTree().find(M.getDod())!=null) {
						
						Dlist.getNode(Dlist.find(selectedCity)).getStackTree().find(M.getDod()).getStack().push(M);
						
					}
					locationBox.getItems().clear();
					lmBox.getItems().clear();
				
					
					current = Dlist.first;
						while (current != null) {
						locationBox.getItems().add((String) current.element);
						lmBox.getItems().add((String) current.element);
					
						current=current.next;
						
						}
					
					
				
				//Dlist.addLast(martyrInfo);
				stage2.close();
			}});

			pn2.getChildren().addAll( txtf3, lBox,txtf4, txtf5, gBox, bts2);
			stage2.setScene(sn2);
			stage2.show();
		});
		
		
		lmBox.setLayoutX(150);
		lmBox.setLayoutY(50);
		lmBox.setPrefSize(100, 30);
		lmBox.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		lmBox.setEditable(true);

		lmBox.setValue("Location");
		lmBox.setOnAction(e->{
			String selectedCity = lmBox.getValue();
			martyerlist=Dlist.getNode(Dlist.find(selectedCity)).getFirstTree().getTreeAsObservableList();
			martyersTable.setItems(martyerlist);
			
		});

		Button btnUpdate = new Button("Update");
		btnUpdate.setLayoutX(10);
		btnUpdate.setLayoutY(110);
		btnUpdate.setPrefSize(100, 30);
		btnUpdate.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btnUpdate.setOnAction(e -> {
			Martyers m = (Martyers) martyersTable.getSelectionModel().getSelectedItem();
			
			if (m != null) {
				// Open update dialog
				Stage stage3 = new Stage();
				Pane pn3 = new Pane();
				Scene sn3 = new Scene(pn3, 300, 200);

				TextField txtf00 = new TextField(m.getName());
				TextField txtf11 = new TextField(String.valueOf(m.getAge()));
				TextField txtf22 = new TextField(lmBox.getValue());
				String strDate=m.getDod().toString();
				TextField txtf33 = new TextField(strDate);
				TextField txtf44 = new TextField(m.getGender());

				txtf11.setLayoutY(30);

				txtf22.setLayoutY(60);

				txtf33.setLayoutY(90);

				txtf44.setLayoutY(120);

				Button bts99 = new Button("Confirm");
				bts99.setLayoutX(200);
				bts99.setLayoutY(120);

				bts99.setOnAction(e5 -> {
					Dlist.getNode(Dlist.find(txtf22)).getFirstTree().remove(m);
					//Dlist.getNode(Dlist.find(txtf22)).getStackTree().find(m.getDod()).getStack().pop();
					// Update the selected item with new values
					String newLocation = txtf22.getText();
					String newName = txtf00.getText();
					String newAge = txtf11.getText();
					String newDate = txtf33.getText();
					String newGender = txtf44.getText();
					Martyers m1=new Martyers(newName,Integer.valueOf(newAge),parseDate(newDate),newGender);
					if (!Dlist.contains(newLocation)) {

						Dlist.addLast(newLocation);
					}
					
					if (Dlist.contains(newLocation)) {
						Dlist.getNode(Dlist.find(newLocation)).getFirstTree().insert(m1);
						Dlist.getNode(Dlist.find(newLocation)).getStackTree().insert(m1.getDod());
						if(Dlist.getNode(Dlist.find(newLocation)).getStackTree().find(m1.getDod())!=null) {
							
							Dlist.getNode(Dlist.find(newLocation)).getStackTree().find(m1.getDod()).getStack().push(m1);
							
						}
					}
					
				
					stage3.close();
				});

				pn3.getChildren().addAll(txtf00, txtf11, txtf22, txtf33, txtf44, bts99);
				stage3.setScene(sn3);
				stage3.show();
			}
		});

		Button btnDelete = new Button("Delete");
		btnDelete.setLayoutX(10);
		btnDelete.setLayoutY(160);
		btnDelete.setPrefSize(100, 30);
		btnDelete.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btnDelete.setOnAction(e -> {
			Martyers m = (Martyers) martyersTable.getSelectionModel().getSelectedItem();
			String location=lmBox.getSelectionModel().getSelectedItem();
			Dlist.getNode(Dlist.find(location)).getFirstTree().remove(m);
			martyersTable.getItems().remove(m);
		});

		TextField txtFsearch = new TextField("search here!");
		txtFsearch.setLayoutX(120);
		txtFsearch.setLayoutY(215);
		txtFsearch.setOnMouseClicked(event -> txtFsearch.clear());

		Button btnSearch = new Button("Search");
		btnSearch.setLayoutX(10);
		btnSearch.setLayoutY(210);
		btnSearch.setPrefSize(100, 30);
		btnSearch.setStyle("-fx-border-color: blue; -fx-border-width: 3px;");
		btnSearch.setOnAction(e -> {
			String searchText = txtFsearch.getText();
			if (!searchText.isEmpty()) {
				// Clear previous search results
				martyersTable.getItems().clear();
				ObservableList<Martyers> searchlist;
				searchlist=	FXCollections.observableArrayList();
				searchlist=( Dlist.findNode(lmBox.getValue()).getFirstTree().searchPart(searchText));	
				
				
				martyersTable.setItems(searchlist);
				
			}
				
				
				
		});

		Label lblzz = new Label("هان كل التفاصيل");
		lblzz.setLayoutX(550);
		lblzz.setLayoutY(250);
		Pane2.getChildren().addAll(martyersTable,lmBox, ButBack2, btnInsert, btnUpdate, btnDelete, btnSearch, txtFsearch);
		tab2.setContent(Pane2);

		Scene scenemartyres = new Scene(Pane2);

//******************************************

		Pane Pane3 = new Pane();
		Button ButBack3 = new Button("RETURN TO THE MAIN MENU");
		
		ButBack3.setLayoutX(120);
		ButBack3.setLayoutY(450);
		ButBack3.setStyle("-fx-border-color: red; -fx-border-width: 3px;");
		
		TextField ttt=new TextField("type location");
		
		Button stat=new Button("Statics");
		stat.setLayoutX(350);
		stat.setLayoutY(90);


		TextArea tx = new TextArea();
		tx.setLayoutX(50);
		tx.setLayoutY(120);
		tx.setPrefSize(500, 300);
		
		ttt.setLayoutX(200);
		ttt.setLayoutY(90);
		stat.setOnAction(e->{
			StringBuilder sb = new StringBuilder();
			String location=ttt.getText();
			sb.append("number of martyr" +" "+Dlist.findNode(location).getFirstTree().count +"\n");
//			 List<Martyers> nodes=Dlist.findNode(location).getFirstTree().getNodesInLevelOrder();
//			 for (Martyers node : nodes) {
//				    textArea.appendText(node.toString() + "\n");
//			}
//		sb.append(Dlist.findNode(location).getElement()+"\n");
			//Dlist.findNode(location).getFirstTree().printLevelOrder(tx);
			Dlist.getNode(Dlist.find(location)).getStackTree().printDatesBackward( tx);
			sb.append("height of 2nd AVL   "+Dlist.getNode(Dlist.find(location)).getStackTree().displayHeight()+"\n");
			sb.append("height of 1nd AVL   "+Dlist.getNode(Dlist.find(location)).getFirstTree().displayHeight()+"\n");
			sb.append("Number of Martyer on gretter date   "+Dlist.getNode(Dlist.find(location)).getStackTree().max()+"\n");
			sb.append("Mximum Date    "+Dlist.getNode(Dlist.find(location)).getStackTree().maxDate+"\n");

			tx.appendText(sb.toString());
			
			
		});
	
		// ScrollBar scrbl = new ScrollBar();

		Button nextbtn = new Button("Next");
		nextbtn.setLayoutX(100);
		nextbtn.setLayoutY(90);
		Button prevbtn = new Button("Prev");
		prevbtn.setLayoutX(150);
		prevbtn.setLayoutY(90);
		Pane3.getChildren().addAll(ButBack3, tx, nextbtn, prevbtn,ttt,stat);
		tab3.setContent(Pane3);

//***************************************************************

		Pane Pane4 = new Pane();

		Button SelcFile = new Button("Select File");
		SelcFile.setStyle("-fx-border-color: green; -fx-border-width: 3px;");
		SelcFile.setPrefSize(100, 50);
		SelcFile.setLayoutX(40);
		SelcFile.setLayoutY(150);
		SelcFile.setOnAction(e -> {

		});
		Button ButBack4 = new Button("RETURN TO THE MAIN MENU");
		ButBack4.setLayoutX(120);
		ButBack4.setLayoutY(450);
		ButBack4.setStyle("-fx-border-color: red; -fx-border-width: 3px;");

		Pane4.getChildren().addAll(SelcFile, ButBack4);
		tab4.setContent(Pane4);

		mainback.setOnAction(e -> {
			primaryStage.setScene(MainScene);
		});

		ButBack1.setOnAction(e -> {
			primaryStage.setScene(MainScene);
		});

		ButBack2.setOnAction(e -> {
			primaryStage.setScene(MainScene);
		});

		ButBack3.setOnAction(e -> {
			primaryStage.setScene(MainScene);
		});

		ButBack4.setOnAction(e -> {
			primaryStage.setScene(MainScene);
		});
		// Set the main scene as the scene for the stage
		primaryStage.setScene(MainScene);
		primaryStage.show();
	}
	////////////////////////////////////////////////more methods////////////////////////////////////
	 private LocalDate parseDate(String dateStr) {
	        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	        try {
	            return formatter.parse(dateStr).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        } catch (ParseException e) {
	            return null;
	        }
	    }

	public static void main(String[] args) {
		launch(args);
	}
}
