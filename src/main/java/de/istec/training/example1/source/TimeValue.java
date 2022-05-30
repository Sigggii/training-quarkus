package de.istec.training.example1.source;

public record TimeValue(long id, long groupId, String type, int validFrom, int validTo, Object value) {
}
