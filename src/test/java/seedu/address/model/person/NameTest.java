package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid - empty / blank
        assertFalse(Name.isValidName(""));          // empty string
        assertFalse(Name.isValidName(" "));         // spaces only

        // invalid - starts with non-letter
        assertFalse(Name.isValidName("123"));       // digits only
        assertFalse(Name.isValidName("2nd"));       // starts with digit
        assertFalse(Name.isValidName("-Alice"));    // starts with hyphen
        assertFalse(Name.isValidName(".Bob"));      // starts with period
        assertFalse(Name.isValidName("'Brien"));    // starts with apostrophe

        // invalid - disallowed characters
        assertFalse(Name.isValidName("peter*"));    // asterisk
        assertFalse(Name.isValidName("^"));         // caret
        assertFalse(Name.isValidName("John/Doe"));  // forward slash (reserved for CLI)
        assertFalse(Name.isValidName("John@Doe"));  // at-sign

        // invalid - digits within name
        assertFalse(Name.isValidName("peter the 2nd")); // contains digit
        assertFalse(Name.isValidName("David Jr 2nd"));  // contains digit

        // invalid - exceeds 50 characters
        assertFalse(Name.isValidName("Abcdefghij Abcdefghij Abcdefghij Abcdefghij Abcdefgh")); // 51 chars

        // valid - plain letters
        assertTrue(Name.isValidName("Alice"));                  // single word
        assertTrue(Name.isValidName("peter jack"));             // two words
        assertTrue(Name.isValidName("Capital Tan"));            // with capital letters

        // valid - allowed separators
        assertTrue(Name.isValidName("Mary-Jane"));              // hyphen
        assertTrue(Name.isValidName("O'Brien"));                // apostrophe
        assertTrue(Name.isValidName("Dr. Lim"));                // period
        assertTrue(Name.isValidName("Jose de la Cruz"));        // multi-word with particles

        // valid - unicode / international names
        assertTrue(Name.isValidName("Nguyễn Tấn Dũng"));       // Vietnamese
        assertTrue(Name.isValidName("毛泽东"));                  // Chinese
        assertTrue(Name.isValidName("José"));                   // accented Latin

        // valid - boundary length
        assertTrue(Name.isValidName("A"));                      // 1 char (min)
        assertTrue(Name.isValidName("Abcdefghij Abcdefghij Abcdefghij Abcdefghij Abcdef")); // 50 chars (max)
    }

    @Test
    public void equals_usesNormalizedFullName() {
        // Names that differ only in separator style normalize to the same value
        assertTrue(new Name("O'Brien").equals(new Name("O Brien")));
        assertTrue(new Name("Mary-Jane").equals(new Name("Mary Jane")));

        // Normalization is case-insensitive
        assertTrue(new Name("alice tan").equals(new Name("Alice Tan")));
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));

        // valid - unicode / international names
        assertTrue(Name.isValidName("Nguyễn Tấn Dũng"));       // Vietnamese with diacritics
        assertTrue(Name.isValidName("毛泽东"));                  // Chinese characters
        assertTrue(Name.isValidName("José"));                   // accented Latin
        assertTrue(Name.isValidName("Björk Guðmundsdóttir"));  // Icelandic
        assertTrue(Name.isValidName("Наина Ельцина"));          // Cyrillic
        assertTrue(Name.isValidName("محمد"));                   // Arabic

        // valid - unicode with allowed separators
        assertTrue(Name.isValidName("Isa bin Osman"));          // Malay with particle
        assertTrue(Name.isValidName("José de la Cruz"));        // Spanish with particles

        // invalid - unicode starting with non-letter
        assertFalse(Name.isValidName("123毛泽东"));              // digit before CJK
    }

    @Test
    public void fullName_isDisplayNormalized() {
        // Leading/trailing whitespace trimmed, internal whitespace collapsed
        Name name = new Name("  Alice   Tan  ");
        assertTrue(name.fullName.equals("Alice Tan"));
    }
}
