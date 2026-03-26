package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MembershipIdContainsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
      public FindCommand parse(String args) throws ParseException {
        Prefix[] allPrefixes = new Prefix[] {
            PREFIX_NAME,
            PREFIX_ID
        };
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        // Check that exactly one prefix is present
        Prefix usedPrefix = null;
        for (Prefix prefix : allPrefixes) {
            if (argMultimap.getValue(prefix).isPresent()) {
                if (usedPrefix != null) {
                    throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                }
                usedPrefix = prefix;
            }
        }

        if (usedPrefix == null) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String value = argMultimap.getValue(usedPrefix).get().trim().replaceAll("\\s+", " ");
        if (value.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        List<String> keywordList = Arrays.asList(value.split(" "));

        switch (usedPrefix.getPrefix()) {
        case "n/":
            return new FindCommand(new NameContainsKeywordsPredicate(keywordList));
        case "id/":
            return new FindCommand(new MembershipIdContainsPredicate(keywordList));
        default:
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
