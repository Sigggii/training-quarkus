package de.istec.training.example1.dto;

public record ValueCell(Long valueId, Object value, boolean referenced, boolean changed) {

  public static ValueCell of(long valueId, Object value, boolean referenced, boolean changed) {
    return new ValueCell(valueId, value, referenced, changed);
  }

  public static ValueCell empty() {
    return empty(false, false);
  }

  public static ValueCell empty(boolean referenced, boolean changed) {
    return new ValueCell(null, null, referenced, changed);
  }
}
