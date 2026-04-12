package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.MatchRecord;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMatchRecord;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.entity.EntityReference;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.model.util.SampleEntityUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.EntityStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonEntityStorage;
import seedu.address.storage.JsonMatchRecordStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.MatchRecordStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing AddressBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getAddressBookFilePath());
        MatchRecordStorage matchRecordStorage = new JsonMatchRecordStorage(userPrefs.getMatchRecordFilePath());
        EntityStorage entityStorage = new JsonEntityStorage(userPrefs.getEntityFilePath());
        storage = new StorageManager(addressBookStorage, matchRecordStorage, userPrefsStorage, entityStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getAddressBookFilePath());

        ReadOnlyAddressBook initialData;
        ReadOnlyMatchRecord initialMatchRecord;
        EntityReference initialEntityReference;

        // Load entity reference separately so that an error here only resets the entity reference
        initialEntityReference = initEntityReference(storage);

        try {
            Optional<ReadOnlyAddressBook> addressBookOptional = storage.readAddressBook();
            Optional<ReadOnlyMatchRecord> matchRecordOptional = storage.readMatchRecord();
            if (addressBookOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getAddressBookFilePath()
                        + " populated with a sample AddressBook.");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
            initialMatchRecord = matchRecordOptional.orElseGet(SampleDataUtil::getSampleMatchRecord);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getAddressBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty AddressBook and Match Record.");
            initialData = new AddressBook();
            initialMatchRecord = new MatchRecord();
        }

        ModelManager modelManager = new ModelManager(initialData, initialMatchRecord, userPrefs);
        modelManager.setEntityReference(initialEntityReference);
        return modelManager;
    }

    /**
     * Returns an {@code EntityReference} with data from {@code storage}'s entity file.
     * If the file is not found, sample entities will be used. If errors occur when reading
     * the file, sample entities will be used instead.
     */
    private EntityReference initEntityReference(Storage storage) {
        Optional<EntityReference> entityReferenceOptional;
        EntityReference initialEntityReference;
        try {
            entityReferenceOptional = storage.readEntityReference();
            if (entityReferenceOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getEntityFilePath()
                        + " with sample entities.");
                initialEntityReference = new EntityReference(
                    SampleEntityUtil.getSampleEntities());
                storage.saveEntityReference(initialEntityReference, storage.getEntityFilePath());
            } else {
                initialEntityReference = entityReferenceOptional.get();
            }
            initialEntityReference.reload();
        } catch (DataLoadingException e) {
            logger.warning("Entity data file at " + storage.getEntityFilePath()
                    + " could not be loaded. Using sample entities."
                    + " Details: " + StringUtil.getDetails(e));
            initialEntityReference = new EntityReference(SampleEntityUtil.getSampleEntities());
        } catch (IOException e) {
            logger.warning("Failed to save sample entity reference. Using sample entities. Details: "
                    + StringUtil.getDetails(e));
            initialEntityReference = new EntityReference(SampleEntityUtil.getSampleEntities());
        }
        return initialEntityReference;
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping AddressBook ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
