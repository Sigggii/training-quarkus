package de.istec.training.example1.dto;

import java.util.List;

public record GroupValuesRow(String groupName, String type, List<ValueCell> values) {
}
