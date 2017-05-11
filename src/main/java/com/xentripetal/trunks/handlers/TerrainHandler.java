package com.xentripetal.trunks.handlers;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainHandler {

	@SubscribeEvent
	public void saplingGrowTreeEvent(SaplingGrowTreeEvent e) {
		System.out.println(e.getResult());
		if (e.getResult() != Result.DENY) {
			System.out.println("Sapling at " + e.getPos());
		}
	}

	@SubscribeEvent
	public void decorateBiomeEvent(DecorateBiomeEvent.Decorate e) {
		if (e.getType().equals(DecorateBiomeEvent.Decorate.EventType.TREE)) {
			Random random = e.getRand();
			World worldIn = e.getWorld();
			BlockPos chunkPos = e.getPos();
			Biome biomeIn = worldIn.getBiome(chunkPos);
			int k1 = biomeIn.theBiomeDecorator.treesPerChunk;
			for (int j2 = 0; j2 < k1; ++j2) {
				int k6 = random.nextInt(16) + 8;
				int l = random.nextInt(16) + 8;
				WorldGenAbstractTree worldgenabstracttree = biomeIn.genBigTreeChance(random);
				worldgenabstracttree.setDecorationDefaults();
				BlockPos blockpos = worldIn.getHeight(chunkPos.add(k6, 0, l));

				if (worldgenabstracttree.generate(worldIn, random, blockpos)) {
					System.out.println("Tree at " + blockpos.toString());
					worldgenabstracttree.generateSaplings(worldIn, random, blockpos);
				}
			}
			e.setResult(Result.DENY);
		}
	}
}
