package kluzzio.merchantconfig;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import kluzzio.merchantconfig.api.ConfigInterpretationHelper;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Util;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;

import java.util.Map;

public class MerchantConfig implements ModInitializer {

    public static VillagerData currentVillagerData;
    public static VillagerProfession currentVillagerProfession;
    public static String currentProfessionId;

    public static final Map<VillagerProfession, Int2ObjectMap<TradeOffers.Factory[]>> PROFESSION_TO_LEVELED_TRADE = Util.make(Maps.newHashMap(), map -> {
        map.put(VillagerProfession.WEAPONSMITH, copyToFastUtilMap(ImmutableMap.of( )));
    });

    private static Int2ObjectMap<TradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, TradeOffers.Factory[]> map) {
        return new Int2ObjectOpenHashMap<TradeOffers.Factory[]>(map);
    }

    @Override
    public void onInitialize() {
        for(VillagerProfession villagerProfession : VillagerProfession.class.getEnumConstants()) {
            if (!villagerProfession.equals(VillagerProfession.NONE)) {
                int villagerProfessionMaxLevel = 5;
                ImmutableMap.Builder<Integer, TradeOffers.Factory[]> map =
                        ImmutableMap.builderWithExpectedSize(villagerProfessionMaxLevel);
                for (int level = 1 ; level <= villagerProfessionMaxLevel ; level++)
                    map.put(level, ConfigInterpretationHelper.getFactories(villagerProfession, level));
                PROFESSION_TO_LEVELED_TRADE.put(villagerProfession, copyToFastUtilMap(map.build()));
            }
        }
    }
}
