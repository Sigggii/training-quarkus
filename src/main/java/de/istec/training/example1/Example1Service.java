package de.istec.training.example1;

import de.istec.training.example1.target.GroupValuesRow;
import de.istec.training.example1.util.Period;

import java.util.Collection;
import java.util.Collections;

public class Example1Service {

  public Collection<GroupValuesRow> buildRows(Period period) {
    /*
        The logic should transform ExampleData.data() (simulates a select joining ValueGroup and TimeValue) in a List of GroupValuesRow.
        The UI should be enabled to display the data in a DataGrid with the following structure

        Group-Name | Value-Type | <Value-Month-1> | ... | <Value-Month-n>
        Group-1 | Reference | ...

        The UI expects the data sorted by Group-Name, Value-Type (Reference, Character)

        TODO implement the logic without implicit loops
     */
    return Collections.emptyList();
  }
}
