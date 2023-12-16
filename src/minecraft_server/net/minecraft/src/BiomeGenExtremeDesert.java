package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenExtremeDesert extends BiomeGenBase
{

    public BiomeGenExtremeDesert(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sandStone.blockID;
        biomeDecorator.treesPerChunk = -999;
        biomeDecorator.grassPerChunk = -999;
        biomeDecorator.deadBushPerChunk = 6;
        biomeDecorator.mushroomsPerChunk = -999;
        biomeDecorator.reedsPerChunk = -999;
        biomeDecorator.cactiPerChunk = 20;
		biomeDecorator.sandPerChunk = 99;
        biomeDecorator.sandPerChunk2 = 99;
        biomeDecorator.clayPerChunk = -999;
    }
	
}
