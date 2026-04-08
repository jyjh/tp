package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.ResultCommand.MESSAGE_FIELD_QUANTITY_MISMATCH;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityReference;
import seedu.address.model.match.PlayerInMatch;
import seedu.address.model.match.PlayersInMatch;
import seedu.address.model.match.Result;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InGameName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Role;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_IDENTIFIER =
        "Invalid identifier. Must be either a positive integer index or an IGN prefixed with 'i/'.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code identifier} which can be either an index (numeric) or an IGN (prefixed with i/).
     * Returns a string representation of the identifier.
     *
     * @throws ParseException if the identifier format is invalid.
     */
    public static String parseIdentifier(String identifier) throws ParseException {
        String trimmed = identifier.trim();

        // Check if it's a numeric index
        if (StringUtil.isNonZeroUnsignedInteger(trimmed)) {
            return trimmed;
        }

        // Check if it's an IGN with prefix
        if (trimmed.startsWith("i/")) {
            String ign = trimmed.substring(2);
            if (!ign.isEmpty()) {
                return ign;
            }
        }

        throw new ParseException(MESSAGE_INVALID_IDENTIFIER);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String ign} into an {@code InGameName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ign} is invalid.
     */
    public static InGameName parseIgn(String ign) throws ParseException {
        requireNonNull(ign);
        String trimmedIgn = ign.trim();
        if (!InGameName.isValidIgn(trimmedIgn)) {
            throw new ParseException(InGameName.MESSAGE_CONSTRAINTS);
        }
        return new InGameName(trimmedIgn);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim().toUpperCase();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    /**
     * Parses a {@code String rank} into a {@code Rank}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code rank} is invalid.
     */
    public static Rank parseRank(String rank) throws ParseException {
        requireNonNull(rank);
        String trimmedRank = rank.trim().toUpperCase();
        if (!Rank.isValidRank(trimmedRank)) {
            throw new ParseException(Rank.MESSAGE_CONSTRAINTS);
        }
        return new Rank(trimmedRank);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String kills} into a {@code Kills}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code kills} is invalid.
     */
    public static Kills parseKills(String kills) throws ParseException {
        requireNonNull(kills);
        String trimmedKills = kills.trim();
        if (!Kills.isValidKills(trimmedKills)) {
            throw new ParseException(Kills.MESSAGE_CONSTRAINTS);
        }
        return new Kills(trimmedKills);
    }

    /**
     * Parses a {@code String deaths} into a {@code Deaths}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deaths} is invalid.
     */
    public static Deaths parseDeaths(String deaths) throws ParseException {
        requireNonNull(deaths);
        String trimmedDeaths = deaths.trim();
        if (!Deaths.isValidDeaths(trimmedDeaths)) {
            throw new ParseException(Deaths.MESSAGE_CONSTRAINTS);
        }
        return new Deaths(trimmedDeaths);
    }

    /**
     * Parses a {@code String assists} into an {@code Assists}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code assists} is invalid.
     */
    public static Assists parseAssists(String assists) throws ParseException {
        requireNonNull(assists);
        String trimmedAssists = assists.trim();
        if (!Assists.isValidAssists(trimmedAssists)) {
            throw new ParseException(Assists.MESSAGE_CONSTRAINTS);
        }
        return new Assists(trimmedAssists);
    }

    /**
     * Parses a {@code String result} into a {@code Result}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code result} is invalid.
     */
    public static Result parseResult(String result) throws ParseException {
        requireNonNull(result);
        String trimmedResult = result.trim().toUpperCase();
        if (!Result.isValidResult(trimmedResult)) {
            throw new ParseException(Result.MESSAGE_CONSTRAINTS);
        }
        return new Result(trimmedResult);
    }

    /**
     * Parses {@code argIgn, argKills, argDeaths, argAssists} into a {@code PlayersInMatch}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code argIgn, argKills, argDeaths, argAssists} are invalid.
     */
    public static PlayersInMatch parsePlayers(List<String> argIgn, List<String> argEntities, List<String> argKills,
            List<String> argDeaths, List<String> argAssists) throws ParseException {
        int noPlayers = argIgn.size();

        if (noPlayers != argKills.size() || noPlayers != argDeaths.size()
                || noPlayers != argAssists.size() || noPlayers != argEntities.size()) {
            throw new ParseException(String.format(MESSAGE_FIELD_QUANTITY_MISMATCH));
        }
        List<PlayerInMatch> players = new ArrayList<>(noPlayers);
        for (int i = 0; i < noPlayers; i++) {
            InGameName ign = ParserUtil.parseIgn(argIgn.get(i));
            Entity entity = ParserUtil.parseEntity(argEntities.get(i));
            Kills kills = ParserUtil.parseKills(argKills.get(i));
            Deaths deaths = ParserUtil.parseDeaths(argDeaths.get(i));
            Assists assists = ParserUtil.parseAssists(argAssists.get(i));

            Statistics statistics = new Statistics.Builder()
                    .withKills(kills)
                    .withDeaths(deaths)
                    .withAssists(assists)
                    .build();
            players.add(new PlayerInMatch(ign, statistics, entity));
        }

        if (!PlayersInMatch.isValidPlayerList(players)) {
            throw new ParseException(PlayersInMatch.MESSAGE_CONSTRAINTS);
        }

        return new PlayersInMatch(players);

    }

    /**
     * Parses a {@code String entity} into a {@code Entity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code entity} is invalid.
     */
    public static Entity parseEntity(String entity) throws ParseException {
        requireNonNull(entity);
        String trimmedEntity = entity.trim().toUpperCase();
        return EntityReference.findByName(trimmedEntity)
            .orElseThrow(() -> new ParseException(String.format(Entity.NOT_FOUND, trimmedEntity)));
    }

}
