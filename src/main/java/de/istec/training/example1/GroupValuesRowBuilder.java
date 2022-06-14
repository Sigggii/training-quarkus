package de.istec.training.example1;

import de.istec.training.example1.dto.GroupValuesRow;
import de.istec.training.example1.dto.ValueCell;
import de.istec.training.example1.source.ExampleData;
import de.istec.training.example1.source.ValueGroup;
import de.istec.training.example1.util.Month;
import de.istec.training.example1.util.Period;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupValuesRowBuilder {
  private final GroupValues groupValues;
  private final Period period;

  public GroupValuesRowBuilder(GroupValues groupValues, Period period) {
    this.groupValues = groupValues;
    this.period = period;
  }

  public Stream<GroupValuesRow> rowsOfGroup(ValueGroup group) {
    return Stream.of(ExampleData.TYPE_REFERENCE, ExampleData.TYPE_CHARACTER)
      .map(type -> createRowForType(group, type));
  }

  private GroupValuesRow createRowForType(ValueGroup group, String type) {
    return new GroupValuesRow(
      group.name(),
      type,
      period.stream()
        .map(month -> createValueCell(group.id(), type, month))
        .collect(Collectors.toList())
    );
  }

  private ValueCell createValueCell(long groupId, String type, Month month) {
    var isReferenced = groupValues.isReferenced(groupId, type, month);
    var isChanged = groupValues.isChanged(groupId, type, month);
    return groupValues.value(groupId, type, month)
      .map(x -> new ValueCell(x.id(), x.value(), isReferenced, isChanged))
      .orElseGet(() -> ValueCell.empty(isReferenced, isChanged));
  }
}
