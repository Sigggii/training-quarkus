package de.istec.training.example1;

import de.istec.training.example1.dto.GroupValuesRow;
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

        The UI expects the data sorted by Group-Name, Value-Type (Reference, Character). In the UI we want to highlight the cell
        if the value has changed in comparison to the previous month. We also want to be able to render referenced values in a different way
        than unreferenced values.

        TODO implement the logic without implicit loops
     */
    return Collections.emptyList();
  }
}
