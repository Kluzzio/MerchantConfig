package kluzzio.merchantconfig.mixins;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import kluzzio.merchantconfig.MerchantConfig;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;

import static kluzzio.merchantconfig.MerchantConfig.*;


@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin {

    @ModifyArgs(method = "fillRecipes", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/passive/VillagerEntity;fillRecipesFromPool(Lnet/minecraft/village/TradeOfferList;[Lnet/minecraft/village/TradeOffers$Factory;I)V"))
    public void ahhhhh(Args args) {
        if ((Object) this instanceof VillagerEntity villager) {
            currentVillagerData = villager.getVillagerData();
            currentVillagerProfession = currentVillagerData.getProfession();
            currentProfessionId = currentVillagerProfession.id();
            villager.getCustomName();
            villager.getEntityName();
            currentVillagerData.getLevel();
            currentVillagerData.getType(); // biome of villager. Can use to make biome villager specific trades.
        }

        Int2ObjectMap<TradeOffers.Factory[]> int2ObjectMap = PROFESSION_TO_LEVELED_TRADE.get(currentVillagerData.getProfession());
        if (int2ObjectMap == null || int2ObjectMap.isEmpty()) {
            return;
        }
        TradeOffers.Factory[] factorys = int2ObjectMap.get(currentVillagerData.getLevel());

        args.set(1, factorys);
        //Map<String, Map<Integer, Map<String, Map<String, String>>>>;
        //Map<Profession, Map<Level, Map<Factory, Map<ParamName, ParamValue>>>>

        for (TradeOffers.Factory factory : (TradeOffers.Factory[]) args.get(1)) {
            System.out.print("Found a factory pog");
        }
    }
}
