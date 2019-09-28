package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class SampleController {

    // Importing excel file where all data is present.
	private static final String FILE_NAME = "PT1.xlsx";
    
    @FXML
    private TextArea outputText;
	
	
    @FXML
    private void numChange(ActionEvent event) {
        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        int value = Integer.parseInt(data);
        int i =0;
		try {
			
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            
            // To read first sheet of excel document
            Sheet datatypeSheet = workbook.getSheetAt(0);

                // Reading Row1
            	Row current1 = datatypeSheet.getRow(0);
            	
                // Reading the row, which element was clicked
                Row currentRow = datatypeSheet.getRow(value);
                Iterator<Cell> cellIterator = currentRow.iterator();
                Iterator<Cell> cellIterator2 = current1.iterator();
                outputText.setText("");
                while (cellIterator2.hasNext()) {
                    
                    // Moving to next cell
                    Cell currentCell = cellIterator.next();
                    
                    // Printing Values if Exists.
                    Cell c1 = cellIterator2.next();
                    if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
                    	outputText.appendText(c1.getStringCellValue()+" = "+currentCell.getStringCellValue()+"\t\t");
                    } else if (currentCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    	outputText.appendText(c1.getStringCellValue()+" = "+currentCell.getNumericCellValue()+"\t\t");
                    } else {
                    	continue;
                    }
                    /*
                    i++;
                    if(i%2==0) {
                    	outputText.appendText("\n");
                    }*/
                    outputText.appendText("\n");
                }
                
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
