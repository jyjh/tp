package seedu.address.testutil;


import seedu.address.logic.commands.StatsCommand.EditStatsDescriptor;
import seedu.address.model.entity.Entity;
import seedu.address.model.person.statistics.Assists;
import seedu.address.model.person.statistics.Deaths;
import seedu.address.model.person.statistics.Kills;
import seedu.address.model.person.statistics.Statistics;

/**
 * A utility class to help with building EditStatsDescriptor objects.
 */
public class EditStatsDescriptorBuilder {

    private EditStatsDescriptor descriptor;

    public EditStatsDescriptorBuilder() {
        descriptor = new EditStatsDescriptor();
    }

    public EditStatsDescriptorBuilder(EditStatsDescriptor descriptor) {
        this.descriptor = new EditStatsDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStatsDescriptor} with fields containing {@code person}'s statistics.
     */
    public EditStatsDescriptorBuilder(Statistics statistics) {
        descriptor = new EditStatsDescriptor();
        descriptor.setKills(statistics.getKills());
        descriptor.setDeaths(statistics.getDeaths());
        descriptor.setAssists(statistics.getAssists());
    }

    /**
     * Sets the {@code Entity} of the {@code EditStatsDescriptor} that we are building.
     */
    public EditStatsDescriptorBuilder withEntity(Entity entity) {
        descriptor.setEntity(entity);
        return this;
    }

    /**
     * Sets the {@code Kills} of the {@code EditStatsDescriptor} that we are building.
     */
    public EditStatsDescriptorBuilder withKills(String kills) {
        descriptor.setKills(new Kills(kills));
        return this;
    }

    /**
     * Sets the {@code Deaths} of the {@code EditStatsDescriptor} that we are building.
     */
    public EditStatsDescriptorBuilder withDeaths(String deaths) {
        descriptor.setDeaths(new Deaths(deaths));
        return this;
    }

    /**
     * Sets the {@code Assists} of the {@code EditStatsDescriptor} that we are building.
     */
    public EditStatsDescriptorBuilder withAssists(String assists) {
        descriptor.setAssists(new Assists(assists));
        return this;
    }

    /**
     * Builds and returns the {@code EditStatsDescriptor} that we have been building.
     * @return
     */
    public EditStatsDescriptor build() {
        assert(descriptor.getEntity() != null);
        return descriptor;
    }
}
