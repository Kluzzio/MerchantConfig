package kluzzio.merchantconfig.api;

import kluzzio.merchantconfig.factories.VanillaFactoryHolder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapIcon;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerType;
import net.minecraft.world.gen.structure.Structure;

import java.util.Collections;
import java.util.Map;

public class FactoryHelper {

    public static TradeOffers.Factory buyForOneEmerald(Map<String, String> factoryParams) {
        ItemConvertible item = Registry.ITEM.get(new Identifier(factoryParams.get("item")));
        int price = Integer.parseInt(factoryParams.get("price"));
        int maxUses = Integer.parseInt(factoryParams.get("maxUses"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        return new VanillaFactoryHolder.BuyForOneEmeraldFactory(item, price, maxUses, experience);
    }

    public static TradeOffers.Factory sellItem(Map<String, String> factoryParams) {
        ItemStack stack = new ItemStack(Registry.ITEM.get(new Identifier(factoryParams.get("item"))));
        int price = Integer.parseInt(factoryParams.get("price"));
        int count = Integer.parseInt(factoryParams.get("count"));
        int maxUses = factoryParams.containsKey("maxUses") ? Integer.parseInt(factoryParams.get("maxUses")) : 12;
        int experience = Integer.parseInt(factoryParams.get("experience"));
        float multiplier = factoryParams.containsKey("multiplier") ? Float.parseFloat(factoryParams.get("multiplier")) : 0.05F;
        return new VanillaFactoryHolder.SellItemFactory(stack, price, count, maxUses, experience, multiplier);
    }

    public static TradeOffers.Factory sellSuspiciousStew(Map<String, String> factoryParams) {
        StatusEffect effect = Registry.STATUS_EFFECT.get(new Identifier(factoryParams.get("effect")));
        int duration = Integer.parseInt(factoryParams.get("duration"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        return new VanillaFactoryHolder.SellSuspiciousStewFactory(effect, duration, experience);
    }

    public static TradeOffers.Factory processItem(Map<String, String> factoryParams) {
        ItemConvertible item = Registry.ITEM.get(new Identifier(factoryParams.get("item")));
        int secondCount = Integer.parseInt(factoryParams.get("secondCount"));
        int price = factoryParams.containsKey("price") ? Integer.parseInt(factoryParams.get("price")) : 1;
        Item sellItem = Registry.ITEM.get(new Identifier(factoryParams.get("sellItem")));
        int sellCount = Integer.parseInt(factoryParams.get("sellCount"));
        int maxUses = Integer.parseInt(factoryParams.get("maxUses"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        return new VanillaFactoryHolder.ProcessItemFactory(item, secondCount, price, sellItem, sellCount, maxUses, experience);
    }

    public static TradeOffers.Factory sellEnchantedTool(Map<String, String> factoryParams) {
        Item item = Registry.ITEM.get(new Identifier(factoryParams.get("item")));
        int basePrice = Integer.parseInt(factoryParams.get("basePrice"));
        int maxUses = Integer.parseInt(factoryParams.get("maxUses"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        float multiplier = factoryParams.containsKey("multiplier") ? Float.parseFloat(factoryParams.get("multiplier")) : 0.05F;
        return new VanillaFactoryHolder.SellEnchantedToolFactory(item, basePrice, maxUses, experience, multiplier);
    }

    public static TradeOffers.Factory typeAwareBuyForOneEmerald(Map<String, String> factoryParams) {
        int count = Integer.parseInt(factoryParams.get("count"));
        int maxUses = Integer.parseInt(factoryParams.get("maxUses"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        Map<VillagerType, Item> map = new java.util.HashMap<>(Collections.emptyMap());
        for (VillagerType type : VillagerType.BIOME_TO_TYPE.values()) {
            if (factoryParams.containsKey(type.toString())) {
                map.put(type, Registry.ITEM.get(new Identifier(factoryParams.get(type.toString()))));
            }
        }
        return new VanillaFactoryHolder.TypeAwareBuyForOneEmeraldFactory(count, maxUses, experience, map);
    }

    public static TradeOffers.Factory sellPotionHoldingItem(Map<String, String> factoryParams) {
        Item arrow = Registry.ITEM.get(new Identifier(factoryParams.get("arrow")));
        int secondCount = Integer.parseInt(factoryParams.get("secondCount"));
        Item tippedArrow = Registry.ITEM.get(new Identifier(factoryParams.get("tippedArrow")));
        int sellCount = Integer.parseInt(factoryParams.get("sellCount"));
        int price = Integer.parseInt(factoryParams.get("price"));
        int maxUses = Integer.parseInt(factoryParams.get("maxUses"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        return new VanillaFactoryHolder.SellPotionHoldingItemFactory(arrow, secondCount, tippedArrow, sellCount, price, maxUses, experience);
    }

    public static TradeOffers.Factory enchantBook(Map<String, String> factoryParams) {
        int experience = Integer.parseInt(factoryParams.get("experience"));
        return new VanillaFactoryHolder.EnchantBookFactory(experience);
    }

    public static TradeOffers.Factory sellMap(Map<String, String> factoryParams) {
        int price = Integer.parseInt(factoryParams.get("price"));
        TagKey<Structure> structure = TagKey.of(Registry.STRUCTURE_KEY, new Identifier(factoryParams.get("structure")));
        String nameKey = factoryParams.get("nameKey");
        //Must be exact match including case to the enum names
        MapIcon.Type iconType = MapIcon.Type.valueOf(factoryParams.get("iconType"));
        int maxUses = Integer.parseInt(factoryParams.get("maxUses"));
        int experience = Integer.parseInt(factoryParams.get("experience"));
        return new VanillaFactoryHolder.SellMapFactory(price, structure, nameKey, iconType, maxUses, experience);
    }

    public static TradeOffers.Factory sellDyedArmor(Map<String, String> factoryParams) {
        Item item = Registry.ITEM.get(new Identifier(factoryParams.get("item")));
        int price = Integer.parseInt(factoryParams.get("price"));
        int maxUses = factoryParams.containsKey("maxUses") ? Integer.parseInt(factoryParams.get("maxUses")) : 12;
        int experience = factoryParams.containsKey("experience") ? Integer.parseInt(factoryParams.get("experience")) : 1;
        return new VanillaFactoryHolder.SellDyedArmorFactory(item, price, maxUses, experience);
    }
}
