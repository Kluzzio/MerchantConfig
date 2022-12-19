package kluzzio.merchantconfig;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import kluzzio.merchantconfig.api.ConfigInterpretationHelper;
import kluzzio.merchantconfig.config.ConfigManager;
import kluzzio.merchantconfig.config.Int2ObjectMapConfigObject;
import kluzzio.merchantconfig.config.SettingsConfigurationObject;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnstableApiUsage")
public class MerchantConfig implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("merchantconfig");
    public static final String SETTINGS_CONFIG_FILE = "merchant_config/settings.json";
    public static final String TRADES_CONFIG_FILE = "merchant_config/trades.json";
    public static final String MOD_NAME_LOG_ID = "[Merchant Config Mod]";
    public static VillagerData currentData;
    public static VillagerProfession currentProfession;
    public static String currentID;
    public static int currentLevel;

    public static HashMap<String, Int2ObjectMap<TradeOffers.Factory[]>> PROFESSION_TO_LEVELED_TRADE = new HashMap<>();

    public static Map<String, Integer> PROFESSION_MAX_LEVEL = Collections.emptyMap();
    public static Map<String, SettingsConfigurationObject.ProfessionSettings> settingsProfessionMap;
    public static Map<String, Int2ObjectMapConfigObject> tradesProfessionMap;

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }

    @Override
    public void onInitialize() {
        ConfigManager.interpretConfigFile(TRADES_CONFIG_FILE);
        ConfigManager.interpretConfigFile(SETTINGS_CONFIG_FILE);

        if (ConfigManager.SUCCESSFULLY_LOADED_SETTINGS && ConfigManager.SUCCESSFULLY_LOADED_TRADES) {
            settingsProfessionMap = ConfigManager.SETTINGS_CONFIG.getProfessionMap();
            for (String key : settingsProfessionMap.keySet())
                PROFESSION_MAX_LEVEL.put(key, settingsProfessionMap.get(key).getMaxLevel());
            tradesProfessionMap = ConfigManager.TRADES_CONFIG.getProfessionMap();
            for (String key : tradesProfessionMap.keySet()) {
                int mLvlOfProf = PROFESSION_MAX_LEVEL.getOrDefault(key, 5);
                ImmutableMap.Builder<Integer, TradeOffers.Factory[]> map =
                        ImmutableMap.builderWithExpectedSize(mLvlOfProf);
                for (int level = 1; level <= mLvlOfProf; level++)
                    map.put(level, ConfigInterpretationHelper.getFactories(
                            ConfigManager.TRADES_CONFIG.getProfessionMap().get(key).getLeveledTradesMap(), level));
                PROFESSION_TO_LEVELED_TRADE.putIfAbsent(key, copyToFastUtilMap(map.build()));
                System.out.println("put a thing");
            }
        }
    }
}
