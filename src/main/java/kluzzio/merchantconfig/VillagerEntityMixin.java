package com.github.kluzzio.lootconfig.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(VillagerEntityMixin.class)
public class VillagerEntityMixin {

    @Inject(method = "fillRecipes")
}
