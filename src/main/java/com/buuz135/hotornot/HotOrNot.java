/*
 * This file is part of Hot or Not.
 *
 * Copyright 2018, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.buuz135.hotornot;

import com.buuz135.hotornot.config.HotLists;
import com.buuz135.hotornot.network.SyncClientLists;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HotOrNot.MOD_ID, name = HotOrNot.MOD_NAME, version = HotOrNot.VERSION, useMetadata = true)
public class HotOrNot {

	public static final String MOD_ID = "hotornot";
	public static final String MOD_NAME = "Hot Or Not - TFC";
	public static final String VERSION = "1.1.6";

	public static final CreativeTabs HOTORNOT_TAB = new HotOrNotTab();

	@Instance
	private static HotOrNot INSTANCE = null;
	private static SimpleNetworkWrapper network;
	private final Logger log = LogManager.getLogger(MOD_ID);

	public static SimpleNetworkWrapper getNetwork() {
		return network;
	}

	@SuppressWarnings("unused")
	public static Logger getLog() {
		return INSTANCE.log;
	}

	@EventHandler
	public void onPreInit(final FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		network.registerMessage(new SyncClientLists.Handler(), SyncClientLists.class, 1, Side.CLIENT);
	}

	@EventHandler
	public void onInit(final FMLInitializationEvent event) {
		HotLists.init();
	}
}