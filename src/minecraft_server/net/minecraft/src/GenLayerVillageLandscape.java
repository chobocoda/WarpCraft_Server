package net.minecraft.src;

public class GenLayerVillageLandscape extends GenLayer
{

    private BiomeGenBase allowedBiomes[];

    public GenLayerVillageLandscape(long l, GenLayer genlayer)
    {
        super(l);
        allowedBiomes = (new BiomeGenBase[] {
			BiomeGenBase.ocean,
			BiomeGenBase.river,
			BiomeGenBase.plains,
			BiomeGenBase.mushroom_plains,
			BiomeGenBase.desert,
			BiomeGenBase.extremedesert,
			BiomeGenBase.extremehills,
			BiomeGenBase.forest,
			BiomeGenBase.extremeforest,
			BiomeGenBase.taiga,
			BiomeGenBase.frozen_taiga,
			BiomeGenBase.regular_swampland,
			BiomeGenBase.enchanted_swampland,
			BiomeGenBase.iceMountains,
        });
        parent = genlayer;
    }

    public int[] func_35500_a(int i, int j, int k, int l)
    {
        int ai[] = parent.func_35500_a(i, j, k, l);
        int ai1[] = IntCache.getIntCache(k * l);
        for(int i1 = 0; i1 < l; i1++)
        {
            for(int j1 = 0; j1 < k; j1++)
            {
                func_35499_a(j1 + i, i1 + j);
                int k1 = ai[j1 + i1 * k];
                if(k1 == 0)
                {
                    ai1[j1 + i1 * k] = 0;
                    continue;
                }
                if(k1 == 1)
                {
                    ai1[j1 + i1 * k] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
                }
            }

        }

        return ai1;
    }
}
