package de.istec.training.example1.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A period of consecutive month
 * @param from the first month of this period
 * @param until the last month inside this period
 */
public record Period(Month from, Month until) implements Iterable<Month> {

  public static Period of(int from, int until) {
    return new Period(Month.of(from), Month.of(until));
  }

  public Stream<Month> stream() {
    return StreamSupport.stream(this.spliterator(), false);
  }

  public int indexOf(Month m) {
    return (m.year() - from.year()) * 12 + m.month() - (this.from.month() - 1) - 1;
  }

  @Override
  public Iterator<Month> iterator() {
    return new Iterator<>() {

      Month current = from;
      Month next = current;

      @Override
      public boolean hasNext() {
        return !next.isAfter(until);
      }

      @Override
      public Month next() {
        if (!hasNext()) throw new NoSuchElementException();

        current = next;
        next = current.next();
        return current;
      }
    };
  }

  @Override
  public String toString() {
    return from + " - " + until;
  }
}
