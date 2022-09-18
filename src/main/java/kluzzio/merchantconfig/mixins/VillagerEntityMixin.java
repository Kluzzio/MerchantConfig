package kluzzio.merchantconfig.mixins;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

    @Inject(method = "fillRecipes", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/passive/VillagerEntity;fillRecipesFromPool(Lnet/minecraft/village/TradeOfferList;[Lnet/minecraft/village/TradeOffers$Factory;I)V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    public void ahhhhh(CallbackInfo ci, VillagerData villagerData, Int2ObjectMap int2ObjectMap, TradeOffers.Factory[] factorys, TradeOfferList tradeOfferList) {
    }
}
