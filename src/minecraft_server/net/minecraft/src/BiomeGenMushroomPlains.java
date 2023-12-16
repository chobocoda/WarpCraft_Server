package net.minecraft.src;

import java.util.List;
import java.util.Random;


public class BiomeGenMushroomPlains extends BiomeGenBase
{

    protected BiomeGenMushroomPlains(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        biomeDecorator.treesPerChunk = -99;
        biomeDecorator.flowersPerChunk = 1;
        biomeDecorator.grassPerChunk = 1;
        biomeDecorator.mushroomsPerChunk = 15;
        biomeDecorator.bigmushroomPerChunk = 2;
    }
	
	public float getSpawningChance()
    {
        return 0.099F;
    }
	
}
