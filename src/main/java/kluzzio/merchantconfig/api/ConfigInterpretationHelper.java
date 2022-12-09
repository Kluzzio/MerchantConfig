package kluzzio.merchantconfig.api;

import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ConfigInterpretationHelper {

    public static TradeOffers.Factory[] getFactories(VillagerProfession profession, int level) {
        List<TradeOffers.Factory> factories = new java.util.ArrayList<>(List.of());
        // For each factory, find the appropriate code and look for the correct inputs
        Map<String, Map<String, String>> h = null; // config input
        for (String key : h.keySet())
            factories.add(getFactoryFromString(key, h.get(key)));
        return factories.toArray(TradeOffers.Factory[]::new);
    }

    public static TradeOffers.Factory getFactoryFromString(String factoryName, Map<String, String> factoryParams) {
        return switch (factoryName) {
            case "BuyForOneEmerald" -> FactoryHelper.buyForOneEmerald(factoryParams);
            case "SellItem" -> FactoryHelper.sellItem(factoryParams);
            case "SellSuspiciousStew" -> FactoryHelper.sellSuspiciousStew(factoryParams);
            case "ProcessItem" -> FactoryHelper.processItem(factoryParams);
            case "SellEnchantedTool" -> FactoryHelper.sellEnchantedTool(factoryParams);
            case "TypeAwareBuyForOneEmerald" -> FactoryHelper.typeAwareBuyForOneEmerald(factoryParams);
            case "SellPotionHoldingItem" -> FactoryHelper.sellPotionHoldingItem(factoryParams);
            case "EnchantBook" -> FactoryHelper.enchantBook(factoryParams);
            case "SellMap" -> FactoryHelper.sellMap(factoryParams);
            case "SellDyedArmor" -> FactoryHelper.sellDyedArmor(factoryParams);
            default -> throw new IllegalStateException("Unexpected value: " + factoryName + ". No such factory.");
        };
    }
}
