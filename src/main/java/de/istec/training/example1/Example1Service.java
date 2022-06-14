package de.istec.training.example1;

import de.istec.training.example1.dto.GroupValuesRow;
import de.istec.training.example1.source.ExampleData;
import de.istec.training.example1.source.TimeValue;
import de.istec.training.example1.source.ValueGroup;
import de.istec.training.example1.util.Period;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.util.Collection;
import java.util.Comparator;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

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

    var data = fetchData(period);
    var rowBuilder = new GroupValuesRowBuilder(data, period);
    return ExampleData.groups()
      .sorted(Comparator.comparing(ValueGroup::name))
      .flatMap(rowBuilder::rowsOfGroup)
      .collect(toList());
  }

  private GroupValues fetchData(Period period) {
    var extendedPeriod = new Period(period.from().previous(), period.until());
    var map = ExampleData.data()
      .flatMap(pair -> valuePeriodInBounds(pair.getValue1(), extendedPeriod)
        .stream()
        .map(month -> Quartet.with(pair.getValue0().id(), pair.getValue1().type(), month, pair.getValue1()))
      )
      .collect(toMap(x -> Triplet.with(x.getValue0(), x.getValue1(), x.getValue2()), Quartet::getValue3));

    return (groupId, type, month) -> ofNullable(map.get(Triplet.with(groupId, type, month)));
  }

  private static Period valuePeriodInBounds(TimeValue value, Period bounds) {
    return Period.of(
      Math.max(value.validFrom(), bounds.from().asInt()),
      Math.min(value.validTo(), bounds.until().asInt())
    );
  }
}
