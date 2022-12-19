package kluzzio.merchantconfig.config;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import kluzzio.merchantconfig.MerchantConfig;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {

    public static final Path FABRIC_CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
    public static SettingsConfigurationObject SETTINGS_CONFIG;
    public static boolean SUCCESSFULLY_LOADED_SETTINGS;
    public static TradesConfigObject TRADES_CONFIG;
    public static boolean SUCCESSFULLY_LOADED_TRADES;

    public static void interpretConfigFile(String configInQuestion) {
        //Make config if it doesn't exist
        createConfigFile(configInQuestion);
        //Read config and store into appropriate configuration object
        readConfigFile(configInQuestion);
    }

    public static void createConfigFile(String configToCreate) {
        // Create the file in memory
        File configFile = FABRIC_CONFIG_DIR.resolve(configToCreate).toFile();
        // If it doesn't exist in disk
        if (!configFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            configFile.getParentFile().mkdirs();
            try {
                String configFileText =
                """
                {
                  "ProfessionMap": {
                  }
                }
                """;

                FileWriter configFileWriter = new FileWriter(configFile);

                configFileWriter.write(configFileText);
                configFileWriter.close();

                //noinspection ResultOfMethodCallIgnored
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readConfigFile(String configToRead) {
        // Create the variable to contain the config file in memory
        File configFile = FABRIC_CONFIG_DIR.resolve(configToRead).toFile();
        if (configFile.exists()) {
            // Create the Gson instance
            try {
                // Create a reader for the configuration file
                Reader configReader = Files.newBufferedReader(configFile.toPath());
                // Create an object from the config file
                if (configToRead.equals(MerchantConfig.SETTINGS_CONFIG_FILE)) {
                    SETTINGS_CONFIG = new Gson().fromJson(configReader, SettingsConfigurationObject.class);
                    if (SETTINGS_CONFIG.getProfessionMap() == null) {
                        SUCCESSFULLY_LOADED_SETTINGS = false;
                        MerchantConfig.LOGGER.error(MerchantConfig.MOD_NAME_LOG_ID +
                                " Profession Map does not exist or is malformed in the configuration file: " + configFile.getName());
                    } else SUCCESSFULLY_LOADED_SETTINGS = true;
                } else {
                    TRADES_CONFIG = new Gson().fromJson(configReader, TradesConfigObject.class);
                    if (TRADES_CONFIG.getProfessionMap() == null) {
                        SUCCESSFULLY_LOADED_TRADES = false;
                        MerchantConfig.LOGGER.error(MerchantConfig.MOD_NAME_LOG_ID +
                                " Profession Map does not exist or is malformed in the configuration file: " + configFile.getName());
                    } else SUCCESSFULLY_LOADED_TRADES = true;
                }
            } catch (IOException e) {
                MerchantConfig.LOGGER.error(MerchantConfig.MOD_NAME_LOG_ID + " IO error when reading configuration file:");
                e.printStackTrace();
                System.exit(-1);
            } catch (JsonSyntaxException jsonSyntaxException) {
                MerchantConfig.LOGGER.error(MerchantConfig.MOD_NAME_LOG_ID + " JSON syntax error when reading configuration file (" + configFile.getName() + "):");
                jsonSyntaxException.printStackTrace();
                System.exit(-1);
            } catch (JsonParseException jsonParseException) {
                MerchantConfig.LOGGER.error(MerchantConfig.MOD_NAME_LOG_ID + " JSON parse error when reading configuration file (" + configFile.getName() + "):");
                jsonParseException.printStackTrace();
                System.exit(-1);
            }
        } else MerchantConfig.LOGGER.error("No configuration file found for Merchant Config Mod in: " + configFile.getPath());
    }
}
