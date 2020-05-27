package reoseah.caelum.common.tab;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import reoseah.caelum.common.Caelum;
import reoseah.caelum.common.CaelumBlocks;

public class CreativeTab {
    public static final ItemGroup CAELUM_TAB = FabricItemGroupBuilder.create(
            new Identifier(Caelum.MOD_ID, "items"))
            .icon(() -> new ItemStack(CaelumBlocks.CAELUM_GRASS))
            .build();
}
