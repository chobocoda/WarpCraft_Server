package net.minecraft.src;

import java.util.Random;

public class WorldGenMossyStoneWall extends WorldGenerator
{

    public WorldGenMossyStoneWall(boolean flag)
    {
        super(flag);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(6) + 8;
        boolean flag = true;
        if(j < 1 || j + l + 1 > world.worldYMax)
        {
            return false;
        }

        if(!flag)
        {
            return false;
        }
        int j1 = world.getBlockId(i, j - 1, k);
        if(j1 != Block.grass.blockID && j1 != Block.dirt.blockID || j >= world.worldYMax - l - 1)
        {
            return false;
        }
        world.setBlock(i, j - 1, k, Block.dirt.blockID);

        for(int l1 = 0; l1 < 2; l1++)
        {
            int k2 = world.getBlockId(i, j + l1, k);
            if(k2 == 0)
            {
				for(int k3 = 0; k3 < 3; k3++)
				{
					func_41060_a(world, i, j + l1, k + k3, Block.cobblestoneMossy.blockID, 0);
				}
				
				func_41060_a(world, i, (j + l1) - 1, k + 3, Block.cobblestoneMossy.blockID, 0);
				
            }
        }

        return true;
    }
}
