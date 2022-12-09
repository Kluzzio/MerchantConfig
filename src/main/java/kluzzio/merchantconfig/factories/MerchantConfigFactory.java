package kluzzio.merchantconfig.factories;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public record MerchantConfigFactory(TradeOffer trade) implements TradeOffers.Factory {

    public static MerchantConfigFactory createTrade(
            Item buySlot1, int buyAmount1,
            Item sellSlot, int sellAmount,
            int maxUses, int merchantExperience, float priceMultiplier) {
        return new MerchantConfigFactory(new TradeOffer(
                // Buying
                new ItemStack(buySlot1, buyAmount1),
                // Selling
                new ItemStack(sellSlot, sellAmount),
                // Other Info
                maxUses, merchantExperience, priceMultiplier));
    }
    public static MerchantConfigFactory createTrade(
            Item buySlot1, int buyAmount1, Item buySlot2, int buyAmount2,
            Item sellSlot, int sellAmount,
            int maxUses, int merchantExperience, float priceMultiplier) {
        return new MerchantConfigFactory(new TradeOffer(
                // Buying
                new ItemStack(buySlot1, buyAmount1),
                new ItemStack(buySlot2, buyAmount2),
                // Selling
                new ItemStack(sellSlot, sellAmount),
                // Other Info
                maxUses, merchantExperience, priceMultiplier));
    }

    @Override
    public TradeOffer create(Entity entity, net.minecraft.util.math.random.Random random) {
        return new TradeOffer(this.trade.toNbt());
    }
}
