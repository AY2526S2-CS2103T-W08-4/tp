package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
        "Names must be 1-50 characters long, start with a letter (including Unicode letters), "
            + "and may only contain letters, spaces, apostrophes ('), hyphens (-), and periods (.). "
            + "e.g. John Doe, Mary-Jane, O'Brien, Dr. Lim, Nguyễn, 毛泽东";
    
    public static final String VALIDATION_REGEX = "^(?=.{1,50}$)[\\p{L}]+(?:[.'/  -]\\s*[\\p{L}]+)*$";

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 50;

    public final String fullName;
    private final String normalizedFullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.fullName = name.trim().replaceAll("\\s+", " ");
        this.normalizedFullName = normalize(name);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        requireNonNull(test);
        String trimmedTest = test.trim();
        return trimmedTest.length() >= MIN_LENGTH
            && trimmedTest.length() <= MAX_LENGTH
            && trimmedTest.matches(VALIDATION_REGEX);
    }

    private static String normalize(String name) {
        return name
            .toLowerCase()
            .replaceAll("[.' -]+", " ")
            .replaceAll("\\s+", " ")
            .trim();
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    public String getNormalizedFullName() {
        return normalizedFullName;
    }
}
