package de.istec.training.example1.dto;

import java.util.Comparator;
import java.util.List;

public record GroupValuesRow(String groupName, String type,
                             List<ValueCell> values) implements Comparable<GroupValuesRow> {
  @Override
  public int compareTo(GroupValuesRow o) {
    var result = Comparator.comparing(GroupValuesRow::groupName).compare(this, o);
    if (result == 0) result = Comparator.comparing(GroupValuesRow::type).compare(this, o);
    return result;
  }
}
