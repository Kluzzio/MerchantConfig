package kluzzio.merchantconfig.api;

import kluzzio.merchantconfig.config.FactoryMapConfigObject;
import kluzzio.merchantconfig.config.FactoryParametersConfigObject;
import net.minecraft.village.TradeOffers;

import java.util.List;
import java.util.Map;

public class ConfigInterpretationHelper {

    public static TradeOffers.Factory[] getFactories(Map<Integer, FactoryMapConfigObject> leveledTradesMap, int level) {
        List<TradeOffers.Factory> factories = new java.util.ArrayList<>(List.of());
        if (leveledTradesMap == null)
            return factories.toArray(TradeOffers.Factory[]::new);
        // For each factory, find the appropriate code and look for the correct inputs
        Map<String, FactoryParametersConfigObject[]> factoryMap = leveledTradesMap.get(level).getFactoryMap(); // config input
        for (String key : factoryMap.keySet())
            for (FactoryParametersConfigObject parameters : factoryMap.get(key))
                factories.add(getFactoryFromString(key, parameters.getFactoryParameters()));
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
