package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenExtremeForest extends BiomeGenBase
{

    public BiomeGenExtremeForest(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 10, 8, 8));
        biomeDecorator.treesPerChunk = 15;
		biomeDecorator.flowersPerChunk = 1;
        biomeDecorator.grassPerChunk = 6;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
		if (random.nextInt(25) == 0)
		{
			return worldgenMossyStoneWall;
		}
		
        if(random.nextInt(5) == 0)
        {
            return worldGenBigTree;
        }
		
		if(random.nextInt(5) == 0)
        {
            return worldGenTrees;
        } else
        {
            return worldGenExtremeForest;
        }
    }
}
