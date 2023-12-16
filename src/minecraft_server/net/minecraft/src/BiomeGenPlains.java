package net.minecraft.src;

import java.util.List;
import java.util.Random;


public class BiomeGenPlains extends BiomeGenBase
{

    protected BiomeGenPlains(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 0;
        biomeDecorator.flowersPerChunk = 3;
        biomeDecorator.grassPerChunk = 1;
    }
	
}
