package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class BiomeGenFrozenTaiga extends BiomeGenBase
{

    public BiomeGenFrozenTaiga(int i)
    {
        super(i);
        spawnableCreatureList.clear();
        biomeDecorator.treesPerChunk = 1;
        biomeDecorator.grassPerChunk = -99;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(5) == 0)
        {
            return new WorldGenTaiga1();
        } else
        {
            return new WorldGenTaiga2(false);
        }
    }
}
