package application;


import java.time.LocalDate;
import java.util.Date;

import javafx.scene.control.DatePicker;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;



/****
 * 
 * @author Saurabh Gupta
 *
 */
public class KeyEventHandler {
	
//		Main mainClass=new Main();
public static void dateKeyEvent(DatePicker datePicker) {
		
		final KeyCombination monthUpCombo= new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN);
		final KeyCombination monthDownCOmbo= new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN);
		final KeyCombination yearUpCombo= new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN);
		final KeyCombination yearDownCombo= new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN);
		
		
		datePicker.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event-> {
			
			   LocalDate value = datePicker.getValue();
			   if (value == null) {
			      //give default value if there is no one
			      value = LocalDate.now();
//			      event.consume();
			   }
			   Date utilDate =null;
			   
				
			   if (yearUpCombo.match(event)) {
			      //UP pressed, the control and shift are hold down, increase the year
				   if(value.isAfter(LocalDate.now())) {
					   datePicker.setValue(LocalDate.now());
				   }else {
				  
			      datePicker.setValue(value.plusYears(1));
				   }
				   event.consume();
			   } 
			   else if (monthUpCombo.match(event)) {
			      //UP pressed, the control key is hold down, increase the month
				   if(value.isAfter(LocalDate.now())) {
					   datePicker.setValue(LocalDate.now());
				   }else {
			      datePicker.setValue(value.plusMonths(1));
				   }
				   event.consume();
			   }
			   else if (event.getCode() == KeyCode.UP) {
			      //UP pressed, no modifier keys hold down, increase the day
				   if(value.isAfter(LocalDate.now())) {
					   datePicker.setValue(LocalDate.now());
				   }else {
			      datePicker.setValue(value.plusDays(1));
			
				   }
				    event.consume();
			   } 
			   
			   else if (yearDownCombo.match(event)) {
			      //DOWN pressed, the control and shift are hold down, decrease the year
			      datePicker.setValue(value.minusYears(1));
			      event.consume();
			   } 
			   else if (monthDownCOmbo.match(event)) {
			      //DOWN pressed, the control key is hold down, decrease the month
			      datePicker.setValue(value.minusMonths(1));
			      event.consume();
			   } 
			   else if(event.getCode() == KeyCode.DOWN) {
			      // DOWN pressed, no modifier keys hold, decrease the day
//				   SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd");
				   value=value.minusDays(1);
				  datePicker.setValue(value);
//				  System.out.println("value"+value);
				  event.consume();
			   }
			   
//			   System.out.println("Reached");
			});
	}
	
}
