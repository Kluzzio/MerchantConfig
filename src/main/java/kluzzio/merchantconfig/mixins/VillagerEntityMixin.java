package kluzzio.merchantconfig.mixins;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import kluzzio.merchantconfig.config.ConfigManager;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
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
            currentData = villager.getVillagerData();
            currentProfession = currentData.getProfession();
            currentID = currentProfession.id();
            villager.getCustomName();
            villager.getEntityName();
            currentLevel = currentData.getLevel();
            currentData.getType(); // biome of villager. Can use to make biome villager specific trades.

            Int2ObjectMap<TradeOffers.Factory[]> int2ObjectMap = PROFESSION_TO_LEVELED_TRADE.get(new Identifier(currentID).toString());
            villager.sendMessage(Text.of(currentID));
            System.out.println(currentID);
            if (int2ObjectMap == null || int2ObjectMap.isEmpty())
                return; //If left unmodified by config, will use vanilla
            TradeOffers.Factory[] factories = int2ObjectMap.get(currentData.getLevel());

            args.set(1, factories);

            if (settingsProfessionMap.containsKey(currentID)) {
                Map<Integer, Integer> tradeAmountMap = settingsProfessionMap.get(currentID).getNumOfTradesGainedPerLevel();
                if (tradeAmountMap.containsKey(currentLevel))
                    args.set(2, tradeAmountMap.get(currentLevel));
            }

            for (TradeOffers.Factory factory : (TradeOffers.Factory[]) args.get(1)) {
                System.out.print("Found a factory pog");
            }
        }
    }
}
