package org.szernex.usc.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import net.minecraftforge.common.config.Configuration;
import org.szernex.usc.reference.Reference;

import java.io.File;

public class ConfigHandler
{
	public static final String CATEGORY_HEALTH = "health";

	private static Configuration configuration;

	public static boolean alterRegeneration =           true;
	public static int minHungerForRegen =               16;
	public static int baseRegenRate =                   80;
	public static float regenModifierRate =             1.1F;
	public static boolean recipeFruitOfRejuvenation =   true;
	public static int glowingOrbTimeToLive =            180;


	public static void init(File file)
	{
		if (file != null)
		{
			configuration = new Configuration(file);

			loadConfig();
		}
	}

	public static void loadConfig()
	{
		if (configuration == null)
			return;

		String category = CATEGORY_HEALTH;

		alterRegeneration = configuration.getBoolean("alterRegeneration", category, alterRegeneration, "Set to true to override default health regeneration and use USC mode instead.");
		minHungerForRegen = configuration.getInt("minHungerForRegen", category, minHungerForRegen, 1, 19, "The number of 'food-haunches' you need for natural regeneration. Vanilla setting is 18 (1 haunch).");
		baseRegenRate = configuration.getInt("baseRegenRate", category, baseRegenRate, 10, Integer.MAX_VALUE, "The base rate (in ticks, 20 ticks = 1 second) at which health naturally regenerations. Vanilla setting is 80 (4 seconds)");
		regenModifierRate = configuration.getFloat("regenModifierRate", category, regenModifierRate, 0.5F, Float.MAX_VALUE, "The rate at which your regeneration rate gets modified after each death.\nStarts out with 1.0, then 1.1, 1.21 and so on. The higher the rate, the more severe teh punishment for deaths.");
		recipeFruitOfRejuvenation = configuration.getBoolean("recipeFruitOfRejuvenation", category, recipeFruitOfRejuvenation, "Enable the recipe for the Fruit of Rejuvenation to allow players to reset their health regeneration.");
		glowingOrbTimeToLive = configuration.getInt("glowingOrbTimeToLive", category, glowingOrbTimeToLive, 1, Integer.MAX_VALUE, "The time Glowing Orbs will stay before they despawn (in seconds).\nDon't set this to a too high value, otherwise it might lag the game.");

		if (configuration.hasChanged())
			configuration.save();
	}

	@Mod.EventHandler
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfig();
		}
	}
}
