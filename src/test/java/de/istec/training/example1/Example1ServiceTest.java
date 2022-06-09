package de.istec.training.example1;

import de.istec.training.example1.source.Reference;
import de.istec.training.example1.dto.GroupValuesRow;
import de.istec.training.example1.dto.ValueCell;
import de.istec.training.example1.util.Period;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.istec.training.example1.source.ExampleData.TYPE_CHARACTER;
import static de.istec.training.example1.source.ExampleData.TYPE_REFERENCE;
import static org.assertj.core.api.Assertions.assertThat;

class Example1ServiceTest {

  @Test
  void buildRows() {
    var OuT = new Example1Service();
    var actual = OuT.buildRows(Period.of(202201, 202212));

    assertThat(actual).containsExactly(
      // Group-1
      new GroupValuesRow("Group-1", TYPE_REFERENCE, List.of(
        ValueCell.empty(), // 1
        ValueCell.empty(), // 2
        ValueCell.empty(), // 3
        ValueCell.empty(), // 4
        ValueCell.empty(), // 5
        ValueCell.empty(), // 6
        ValueCell.empty(), // 7
        ValueCell.empty(), // 8
        ValueCell.empty(), // 9
        ValueCell.empty(), // 10
        ValueCell.empty(), // 11
        ValueCell.empty() // 12
      )),
      new GroupValuesRow("Group-1", TYPE_CHARACTER, List.of(
        ValueCell.empty(), // 1
        ValueCell.empty(), // 2
        ValueCell.empty(), // 3
        ValueCell.empty(), // 4
        ValueCell.empty(), // 5
        ValueCell.empty(), // 6
        ValueCell.of(10, "Nikolausi", false, true), // 7
        ValueCell.of(10, "Nikolausi", false, false), // 8
        ValueCell.of(10, "Nikolausi", false, false), // 9
        ValueCell.of(10, "Nikolausi", false, false), // 10
        ValueCell.of(10, "Nikolausi", false, false), // 11
        ValueCell.of(10, "Nikolausi", false, false) // 12
      )),
      // Group-2
      new GroupValuesRow("Group-2", TYPE_REFERENCE, List.of(
        ValueCell.of(20, new Reference(List.of(TYPE_CHARACTER), 0, "Group-4"), false, false), // 1
        ValueCell.of(20, new Reference(List.of(TYPE_CHARACTER), 0, "Group-4"), false, false), // 2
        ValueCell.of(20, new Reference(List.of(TYPE_CHARACTER), 0, "Group-4"), false, false), // 3
        ValueCell.of(20, new Reference(List.of(TYPE_CHARACTER), 0, "Group-4"), false, false), // 4
        ValueCell.of(20, new Reference(List.of(TYPE_CHARACTER), 0, "Group-4"), false, false), // 5
        ValueCell.empty(false, true), // 6
        ValueCell.empty(), // 7
        ValueCell.empty(), // 8
        ValueCell.empty(), // 9
        ValueCell.empty(), // 10
        ValueCell.empty(), // 11
        ValueCell.empty() // 12
      )),
      new GroupValuesRow("Group-2", TYPE_CHARACTER, List.of(
        ValueCell.of(40, "Osterhase", true, false), // 1
        ValueCell.of(40, "Osterhase", true, false), // 2
        ValueCell.of(41, "Weihnachtsmann", true, true), // 3
        ValueCell.of(41, "Weihnachtsmann", true, false), // 4
        ValueCell.of(41, "Weihnachtsmann", true, false), // 5
        ValueCell.of(21, "Nikolausi", false, true), // 6
        ValueCell.of(22, "Nikolausi", false, false), // 7
        ValueCell.of(22, "Nikolausi", false, false), // 8
        ValueCell.of(22, "Nikolausi", false, false), // 9
        ValueCell.of(22, "Nikolausi", false, false), // 10
        ValueCell.of(22, "Nikolausi", false, false), // 11
        ValueCell.of(22, "Nikolausi", false, false) // 12
      )),
      // Group-4
      new GroupValuesRow("Group-4", TYPE_REFERENCE, List.of(
        ValueCell.empty(), // 1
        ValueCell.empty(), // 2
        ValueCell.empty(), // 3
        ValueCell.empty(), // 4
        ValueCell.empty(), // 5
        ValueCell.empty(), // 6
        ValueCell.empty(), // 7
        ValueCell.empty(), // 8
        ValueCell.empty(), // 9
        ValueCell.empty(), // 10
        ValueCell.empty(), // 11
        ValueCell.empty() // 12
      )),
      new GroupValuesRow("Group-4", TYPE_CHARACTER, List.of(
        ValueCell.of(40, "Osterhase", false, false), // 1
        ValueCell.of(40, "Osterhase", false, false), // 2
        ValueCell.of(41, "Weihnachtsmann", false, true), // 3
        ValueCell.of(41, "Weihnachtsmann", false, false), // 4
        ValueCell.of(41, "Weihnachtsmann", false, false), // 5
        ValueCell.of(41, "Weihnachtsmann", false, false), // 6
        ValueCell.of(41, "Weihnachtsmann", false, false), // 7
        ValueCell.of(41, "Weihnachtsmann", false, false), // 8
        ValueCell.of(41, "Weihnachtsmann", false, false), // 9
        ValueCell.of(41, "Weihnachtsmann", false, false), // 10
        ValueCell.of(41, "Weihnachtsmann", false, false), // 11
        ValueCell.of(41, "Weihnachtsmann", false, false) // 12
      ))
    );
  }
}