package com.buuz135.hotornot;

import com.buuz135.hotornot.object.item.ItemMetalTongsHead;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Metal.ItemType;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill.Type;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.buuz135.hotornot.HotOrNot.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public final class RegisterAnvilRecipe {

	@SubscribeEvent
	public static void onRegisterAnvilRecipe(final Register<AnvilRecipe> event) {
		final IForgeRegistry<AnvilRecipe> registry = event.getRegistry();
		for (final Metal metal : TFCRegistries.METALS.getValuesCollection()) {
			if (!metal.isToolMetal()) continue;

			registry.register(new AnvilRecipe(new ResourceLocation(MOD_ID, metal + "_tongs_head"), IIngredient.of(new ItemStack(
					ItemMetal.get(metal, ItemType.INGOT))),
					new ItemStack(ItemMetalTongsHead.get(metal)),
					metal.getTier(), Type.TOOLS,
					ForgeRule.PUNCH_LAST, ForgeRule.DRAW_SECOND_LAST, ForgeRule.DRAW_THIRD_LAST));
		}
	}
}