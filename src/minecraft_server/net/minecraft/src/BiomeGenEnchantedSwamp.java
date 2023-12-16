package net.minecraft.src;

import java.util.Random;

public class BiomeGenEnchantedSwamp extends BiomeGenBase
{

    protected BiomeGenEnchantedSwamp(int i)
    {
        super(i);
		spawnableCreatureList.clear();
		spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntitySwampkeeper.class, 1, 1, 1));
        biomeDecorator.treesPerChunk = 1;
        biomeDecorator.flowersPerChunk = 2;
        biomeDecorator.deadBushPerChunk = -99;
        biomeDecorator.mushroomsPerChunk = 5;
        biomeDecorator.reedsPerChunk = -99;
        biomeDecorator.clayPerChunk = -99;
        biomeDecorator.waterlilyPerChunk = 5;
        setWaterColor = 0x00ff50;
    }
	
	public float getSpawningChance()
    {
        return 0.099F;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        return worldGenEnchantedSwamp;
    }
}
