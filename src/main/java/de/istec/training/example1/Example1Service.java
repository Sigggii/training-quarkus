package de.istec.training.example1;

import de.istec.training.example1.dto.GroupValuesRow;
import de.istec.training.example1.dto.ValueCell;
import de.istec.training.example1.source.ExampleData;
import de.istec.training.example1.source.ValueGroup;
import de.istec.training.example1.util.Period;
import org.javatuples.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.istec.training.example1.source.ExampleData.TYPE_REFERENCE;
import static de.istec.training.example1.source.ExampleData.TYPE_CHARACTER;


public class Example1Service {

  public Collection<GroupValuesRow> buildRows(Period period) {
    /*
        The logic should transform ExampleData.data() (simulates a select joining ValueGroup and TimeValue) in a List of GroupValuesRow.
        The UI should be enabled to display the data in a DataGrid with the following structure

        Group-Name | Value-Type | <Value-Month-1> | ... | <Value-Month-n>
        Group-1 | Reference | ...

        The UI expects the data sorted by Group-Name, Value-Type (Reference, Character). In the UI we want to highlight the cell
        if the value has changed in comparison to the previous month. We also want to be able to render referenced values in a different way
        than unreferenced values.

        TODO implement the logic without implicit loops

        Hints:
        * you can create as many classes, interfaces, methods as you want
        * you can mutate all classes, interfaces, records, ... but ExampleData
        * assume that a real world example has more than 2 value-types
        * try to be declarative not imperative

     */

    var data = ExampleData.getTimeValuesForGroups();
    CellFactory cellFactory = new CellFactory(data, period);
    return data.keySet().stream()
            .map(group-> createRowsForGroup(group, period, cellFactory))
            .flatMap(rows -> Stream.of(rows.getValue0(), rows.getValue1()))
            .sorted()
            .toList();
  }

  private Pair<GroupValuesRow, GroupValuesRow> createRowsForGroup(ValueGroup valueGroup, Period period, CellFactory cellFactory){
    var cells = period.stream()
            .map(month ->
             cellFactory.getValueCells(valueGroup, month)
            )
            .flatMap(c -> Stream.of(c.getValue0(), c.getValue1()))
            .collect(Collectors.groupingBy(TypedCell::getType));

    var charRow = new GroupValuesRow(valueGroup.name(), TYPE_CHARACTER, cells.get(TYPE_CHARACTER).stream().map(TypedCell::getValueCell).toList());
    var refRow = new GroupValuesRow(valueGroup.name(), TYPE_REFERENCE, cells.get(TYPE_REFERENCE).stream().map(TypedCell::getValueCell).toList());
    return Pair.with(charRow, refRow);
  }
}
