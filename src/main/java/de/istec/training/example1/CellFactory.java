package de.istec.training.example1;

import de.istec.training.example1.dto.ValueCell;
import de.istec.training.example1.source.Reference;
import de.istec.training.example1.source.TimeValue;
import de.istec.training.example1.source.ValueGroup;
import de.istec.training.example1.util.Month;
import de.istec.training.example1.util.Period;
import org.javatuples.Pair;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static de.istec.training.example1.source.ExampleData.TYPE_REFERENCE;
import static de.istec.training.example1.source.ExampleData.TYPE_CHARACTER;

public class CellFactory {

    Map<ValueGroup, List<TimeValue>> values;
    Period period;

    public CellFactory(Map<ValueGroup, List<TimeValue>> values, Period period) {
        this.values = values;
        this.period = period;
    }

    public Pair<TypedCell, TypedCell> getValueCells(ValueGroup valueGroup, Month month) {
        final var charTvHasChanged = hasChanged(valueGroup, month, false);
        final var refTvHasChanged = hasChanged(valueGroup, month, true);
        var charCell = getTimeValue(valueGroup, month, false)
                .map(tv -> {
                    var charTV = new TypedCell(TYPE_CHARACTER, ValueCell.of(tv.id(), tv.value(), false, charTvHasChanged));
                    if (Objects.equals(tv.type(), TYPE_REFERENCE)) {
                        var refTVValue = (Reference) tv.value();
                        var referencedGroup = new ValueGroup(refTVValue.groupId(), refTVValue.groupName());
                        var referencedTV = getTimeValue(referencedGroup, month, false);
                        charTV.setValueCell(referencedTV.map(tv1 -> ValueCell.of(tv1.id(), tv1.value(), true, hasChanged(referencedGroup, month, false)))
                                .orElse(ValueCell.empty(true, hasChanged(referencedGroup, month, false))));
                    }
                    return charTV;
                }).orElse(new TypedCell(TYPE_CHARACTER, ValueCell.empty(false, charTvHasChanged)));

        var refCell = new TypedCell(TYPE_REFERENCE, getTimeValue(valueGroup, month, true).map(tv -> ValueCell.of(tv.id(), tv.value(), false, refTvHasChanged)).orElse(ValueCell.empty(false, refTvHasChanged)));
        return Pair.with(charCell, refCell);
    }

    private Optional<TimeValue> getTimeValue(ValueGroup valueGroup, Month month, boolean ref) {
        return values.get(valueGroup).stream()
                .filter(tv -> {
                    Period tmpPeriod = Period.of(tv.validFrom(), tv.validTo());
                    if (ref)
                        return tmpPeriod.indexOf(month) >= 0 && tmpPeriod.indexOf(month) < tmpPeriod.stream().count() && tv.type().equals(TYPE_REFERENCE);
                    return tmpPeriod.indexOf(month) >= 0 && tmpPeriod.indexOf(month) < tmpPeriod.stream().count();
                })
                .findFirst();
    }

    private boolean hasChanged(ValueGroup valueGroup, Month month, boolean ref) {
        return !Objects.equals(getTimeValue(valueGroup, month, ref).map(TimeValue::value), getTimeValue(valueGroup, month.last(), ref).map(TimeValue::value)) && !Objects.equals(period.from(), month);
    }
}
