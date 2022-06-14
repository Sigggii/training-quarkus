package de.istec.training.example1;

import de.istec.training.example1.source.ExampleData;
import de.istec.training.example1.source.Reference;
import de.istec.training.example1.source.TimeValue;
import de.istec.training.example1.util.Month;

import java.util.Objects;
import java.util.Optional;

public interface GroupValues {
  Optional<TimeValue> rawValue(long groupId, String type, Month month);

  default Optional<TimeValue> value(long groupId, String type, Month month) {
    return rawValue(groupId, type, month)
      .or(() -> referencedValue(groupId, type, month));
  }

  default Optional<TimeValue> referencedValue(long groupId, String type, Month month) {
    return rawValue(groupId, ExampleData.TYPE_REFERENCE, month)
      .map(refValue -> (Reference) refValue.value())
      .filter(ref -> ref.type().contains(type))
      .flatMap(ref -> rawValue(ref.groupId(), type, month));
  }

  default boolean isReferenced(long groupId, String type, Month month) {
    return referencedValue(groupId, type, month).isPresent();
  }

  default boolean isChanged(long groupId, String type, Month month) {
    var currentValue = value(groupId, type, month).map(TimeValue::value).orElse(null);
    var valuePrevMonth = value(groupId, type, month.previous()).map(TimeValue::value).orElse(null);
    return !Objects.equals(currentValue, valuePrevMonth);
  }
}
