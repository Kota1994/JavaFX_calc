package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField textDisplay;
    private int oldNumber;
    private double leftNumber;
    private double x;
    private double y;
    private OptState state = null;
    private int numberCount(int newNumber){
        if(oldNumber >= 10000){
            return oldNumber;
        }
        oldNumber *= 10;
        oldNumber += newNumber;
        return oldNumber;
    }

    @FXML
    private void handleOnAnyButtonClicked(ActionEvent evt) {

        Button source = (Button) evt.getSource();
        if(source.getText().matches("^\\d$")) {
            numberCount(Integer.valueOf(source.getText()));
            textDisplay.setText(String.valueOf(oldNumber));
        }

    }
    @FXML
  private void changeZero (ActionEvent evt_valuezero){
        Button source = (Button) evt_valuezero.getSource();
        if (source.getText().equals("AC")){
            textDisplay.setText(String.valueOf(leftNumber));
            leftNumber = 0;
            oldNumber = 0;
        }
    }
    interface Opr {
        double operate(double x, double y);
    }
    enum OptState implements Opr{
        ADD {
            @Override
            public double operate(double x, double y) {

                return x + y;
            }
        },
        SUB {
            @Override
            public double operate(double x, double y) {
                return x - y;
            }
        },
        MULTI {
            @Override
            public double operate(double x, double y) {
                return x * y;
            }
        },
        DIV {
            @Override
            public double operate(double x, double y) {
                return x / y;
            }
        }

    }
    public void pressOperator(ActionEvent actionEvent) {

        Button source = (Button) actionEvent.getSource();
            double tempLeft = leftNumber;
        switch (source.getText()){
            case "+":
                if (state != null )
                   leftNumber = state.operate(leftNumber,y);

                state = OptState.ADD;
                break;
            case "-":
                if (state != null )
                    leftNumber = state.operate(leftNumber,y);

                state = OptState.SUB;
                break;
            case "×":
                if (state != null )
                    leftNumber = state.operate(leftNumber,y);

                state = OptState.MULTI;
                break;
            case "÷":
                if (state != null )
                    leftNumber = state.operate(leftNumber,y);

                state = OptState.DIV;
                break;
        }


        if (state == null)
            leftNumber = oldNumber;
        else
            y = oldNumber;
        oldNumber = 0;

        textDisplay.setText(String.valueOf(leftNumber));
    }
}
