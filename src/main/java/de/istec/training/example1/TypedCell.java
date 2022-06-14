package de.istec.training.example1;

import de.istec.training.example1.dto.ValueCell;

public class TypedCell {
    private String type;
    private ValueCell valueCell;
    public TypedCell(String type, ValueCell valueCell){
        this.type = type;
        this.valueCell = valueCell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ValueCell getValueCell() {
        return valueCell;
    }

    public void setValueCell(ValueCell valueCell) {
        this.valueCell = valueCell;
    }
}
