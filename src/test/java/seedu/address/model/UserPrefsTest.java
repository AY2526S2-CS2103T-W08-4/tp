package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setAddressBookFilePath(null));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        UserPrefs userPrefs = new UserPrefs();
        assertTrue(userPrefs.equals(userPrefs));
    }

    @Test
    public void equals_nonUserPrefs_returnsFalse() {
        UserPrefs userPrefs = new UserPrefs();
        assertFalse(userPrefs.equals(null));
        assertFalse(userPrefs.equals("not user prefs"));
    }

    @Test
    public void hashCode_equalsUserPrefsSameHashCode() {
        UserPrefs first = new UserPrefs();
        UserPrefs second = new UserPrefs(first);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }
}
