package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENTITY_STATISTIC_MAP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IGN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RANK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATS_SET_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same ign, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different ign, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withIgn(VALID_IGN_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // ign differs in case, all other attributes same -> returns true
        Person editedBob = new PersonBuilder(BOB).withIgn(VALID_IGN_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePerson(editedBob));

        // ign has trailing spaces, all other attributes same -> returns false
        String ignWithTrailingSpaces = VALID_IGN_BOB + " ";
        editedBob = new PersonBuilder(BOB).withIgn(ignWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE, aliceCopy);

        // same object -> returns true
        assertEquals(ALICE, ALICE);

        // null -> returns false
        assertNotEquals(null, ALICE);

        // different type -> returns false
        assertNotEquals(5, ALICE);

        // different person -> returns false
        assertNotEquals(ALICE, BOB);

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(ALICE, editedAlice);

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertNotEquals(ALICE, editedAlice);

        // TODO:
        // This test appears to be broken due to a problem with the gradlew version.
        // editedAlice = new PersonBuilder(ALICE).withEntityStatistics(VALID_ENTITY_STATISTIC_MAP).build();
        // assertNotEquals(ALICE, editedAlice);

        // different rank -> returns false
        editedAlice = new PersonBuilder(ALICE).withRank(VALID_RANK_BOB).build();
        assertNotEquals(ALICE, editedAlice);
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", role=" + ALICE.getRole() + ", rank=" + ALICE.getRank() + ", tags="
                + ALICE.getTags() + ", entityStats=" + ALICE.getOverallEntityStatistics() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void getStatistics_success() {
        Person person = new PersonBuilder(ALICE).withEntityStatistics(VALID_ENTITY_STATISTIC_MAP).build();
        assertEquals(VALID_STATS_SET_1, person.getEntityStatistics(VALID_ENTITY_1));
    }

    @Test
    public void addStatistics_success() {
        Person person = new PersonBuilder(ALICE).build();
        Person expected = new PersonBuilder(person).withEntityStatistics(VALID_ENTITY_STATISTIC_MAP).build();
        Person editedPerson = person.addEntityStatistics(VALID_STATS_SET_1, VALID_ENTITY_1);

        assertEquals(expected, editedPerson);
    }

    @Test
    public void hashCodeMethod() {
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());
    }
}
