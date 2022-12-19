package kluzzio.merchantconfig.mixins;

import kluzzio.merchantconfig.MerchantConfig;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Set;

@Mixin(MerchantEntity.class)
public class MerchantEntityMixin {

    @Inject(method = "fillRecipesFromPool", at = @At(value = "INVOKE", target = "Lnet/minecraft/village/TradeOfferList;add(Ljava/lang/Object;)Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void test(TradeOfferList recipeList, TradeOffers.Factory[] pool, int count, CallbackInfo ci, Set<Integer> set, Iterator<Integer> var5, Integer integer,
                      TradeOffers.Factory factory, TradeOffer tradeOffer) {
        tradeOffer.getOriginalFirstBuyItem();
        tradeOffer.getSellItem();
        tradeOffer.getPriceMultiplier();
        tradeOffer.getMaxUses();
        tradeOffer.getMerchantExperience();
        tradeOffer.getUses();
        tradeOffer.getSecondBuyItem();

        System.out.print(MerchantConfig.currentID);

        MerchantConfig.currentID = "";
    }
}
