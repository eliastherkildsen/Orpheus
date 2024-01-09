package mediaplayer.orpheus.model.Database;

public class DatabaseHandler {
    private String column;
    private String conditionString;
    private int coniditionInt;
    private String valueString;
    private int valueInt;

//region Getter and setter
    public DatabaseHandler() {

    }
    public int getConiditionInt() {
        return coniditionInt;
    }

    public void setConiditionInt(int coniditionInt) {
        this.coniditionInt = coniditionInt;
    }
    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getConditionString() {
        return conditionString;
    }

    public void setConditionString(String conditionString) {
        this.conditionString = conditionString;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public int getValueInt() {
        return valueInt;
    }

    public void setValueInt(int valueInt) {
        this.valueInt = valueInt;
    }
//endregion
}
