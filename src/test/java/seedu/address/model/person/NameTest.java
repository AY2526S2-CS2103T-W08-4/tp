@Test
public void isValidName() {
    // null name
    assertThrows(NullPointerException.class, () -> Name.isValidName(null));

    // invalid - empty / blank
    assertFalse(Name.isValidName(""));
    assertFalse(Name.isValidName(" "));

    // invalid - starts with non-letter
    assertFalse(Name.isValidName("123"));
    assertFalse(Name.isValidName("2nd"));
    assertFalse(Name.isValidName("-Alice"));
    assertFalse(Name.isValidName(".Bob"));
    assertFalse(Name.isValidName("'Brien"));

    // invalid - disallowed characters
    assertFalse(Name.isValidName("peter*"));
    assertFalse(Name.isValidName("^"));
    assertFalse(Name.isValidName("John/Doe"));
    assertFalse(Name.isValidName("John@Doe"));

    // invalid - digits within name
    assertFalse(Name.isValidName("peter the 2nd"));
    assertFalse(Name.isValidName("David Jr 2nd"));

    // invalid - exceeds 50 characters
    assertFalse(Name.isValidName("Abcdefghij Abcdefghij Abcdefghij Abcdefghij Abcdefg"));

    // valid - plain letters
    assertTrue(Name.isValidName("Alice"));
    assertTrue(Name.isValidName("peter jack"));
    assertTrue(Name.isValidName("Capital Tan"));

    // valid - allowed separators
    assertTrue(Name.isValidName("Mary-Jane"));
    assertTrue(Name.isValidName("O'Brien"));
    assertTrue(Name.isValidName("Dr. Lim"));
    assertTrue(Name.isValidName("Jose de la Cruz"));

    // valid - unicode and international names
    assertTrue(Name.isValidName("Nguyễn Tấn Dũng"));
    assertTrue(Name.isValidName("毛泽东"));
    assertTrue(Name.isValidName("José"));
    assertTrue(Name.isValidName("Björk Guðmundsdóttir"));
    assertTrue(Name.isValidName("Наина Ельцина"));
    assertTrue(Name.isValidName("محمد"));

    // valid - unicode with allowed separators
    assertTrue(Name.isValidName("Isa bin Osman"));
    assertTrue(Name.isValidName("José de la Cruz"));

    // invalid - unicode starting with non-letter
    assertFalse(Name.isValidName("123毛泽东"));

    // valid - boundary length
    assertTrue(Name.isValidName("A"));
    assertTrue(Name.isValidName("Abcdefghij Abcdefghij Abcdefghij Abcdefghij Abcdef"));
}
