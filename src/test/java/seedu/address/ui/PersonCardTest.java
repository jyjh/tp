package seedu.address.ui;

import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * Contains tests for PersonCard.
 * Tests for entity button creation have been moved to {@link EntityButtonFactoryTest}.
 * These tests are disabled on Linux because they require JavaFX which needs a display server.
 */
@DisabledOnOs(OS.LINUX)
public class PersonCardTest {
}
