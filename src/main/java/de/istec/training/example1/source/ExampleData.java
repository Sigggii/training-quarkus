package de.istec.training.example1.source;

import org.javatuples.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ExampleData {

  public static final String TYPE_REFERENCE = "Reference";
  public static final String TYPE_CHARACTER = "Character";

  private static final Map<Long, ValueGroup> valueGroups = Stream.of(
        new ValueGroup(0, "Group-4"),
        new ValueGroup(1, "Group-2"),
        new ValueGroup(2, "Group-1")
      )
      .collect(toMap(ValueGroup::id, x -> x));

  private static Collection<TimeValue> timeValues() {
    return List.of(
      // Group-4 Parameter
      new TimeValue(40, 0, TYPE_CHARACTER, 202110, 202202, "Osterhase"),
      new TimeValue(41, 0, TYPE_CHARACTER, 202203, 999999, "Weihnachtsmann"),

      // Group-2 Parameter
      new TimeValue(20, 1, TYPE_REFERENCE, 202110, 202205, new Reference(List.of(TYPE_CHARACTER), 0, "Group-4")),
      new TimeValue(21, 1, TYPE_CHARACTER, 202206, 202206, "Nikolausi"),
      new TimeValue(22, 1, TYPE_CHARACTER, 202207, 999999, "Nikolausi"),

      // Group-1 Parameter
      new TimeValue(10, 2, TYPE_CHARACTER, 202207, 202212, "Nikolausi"),
      new TimeValue(10, 2, TYPE_CHARACTER, 202301, 999912, "Osterhase")
    );
  }

  public static Stream<ValueGroup> groups() {
    return valueGroups.values().stream();
  }

  public static Stream<Pair<ValueGroup, TimeValue>> data() {
    return timeValues()
      .stream()
      .map(
      v -> Pair.with(valueGroups.get(v.groupId()), v)
    );
  }
}
