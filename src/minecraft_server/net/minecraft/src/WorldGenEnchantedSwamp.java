// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Material, Block, 
//            BlockLeaves, BlockGrass

public class WorldGenEnchantedSwamp extends WorldGenerator
{

    public WorldGenEnchantedSwamp()
    {
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(4) + 5;
		
        for(; world.getBlockMaterial(i, j - 1, k) == Material.water; j--) { }
        boolean flag = true;
        if(j < 1 || j + l + 1 > world.worldYMax)
        {
            return false;
        }
        for(int i1 = j; i1 <= j + 1 + l; i1++)
        {
            byte byte0 = 1;
            if(i1 == j)
            {
                byte0 = 0;
            }
            if(i1 >= (j + 1 + l) - 2)
            {
                byte0 = 3;
            }
            for(int j2 = i - byte0; j2 <= i + byte0 && flag; j2++)
            {
                for(int j3 = k - byte0; j3 <= k + byte0 && flag; j3++)
                {
                    if(i1 >= 0 && i1 < world.worldYMax)
                    {
                        int i4 = world.getBlockId(j2, i1, j3);
                        if(i4 == 0 || i4 == Block.leaves.blockID)
                        {
                            continue;
                        }
                        if(i4 == Block.waterStill.blockID || i4 == Block.waterMoving.blockID)
                        {
                            if(i1 > j)
                            {
                                flag = false;
                            }
                        } else
                        {
                            flag = false;
                        }
                    } else
                    {
                        flag = false;
                    }
                }

            }

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
        for(int k1 = (j - 3) + l; k1 <= j + l; k1++)
        {
            int k2 = k1 - (j + l);
            int k3 = 2 - k2 / 2;
            for(int j4 = i - k3; j4 <= i + k3; j4++)
            {
                int l4 = j4 - i;
                for(int j5 = k - k3; j5 <= k + k3; j5++)
                {
                    int k5 = j5 - k;
                    if((Math.abs(l4) != k3 || Math.abs(k5) != k3 || random.nextInt(2) != 0 && k2 != 0) && !Block.opaqueCubeLookup[world.getBlockId(j4, k1, j5)])
                    {
                        world.setBlock(j4, k1, j5, Block.leaves.blockID);
                    }
                }

            }

        }

        for(int l1 = 0; l1 < l; l1++)
        {
            int l2 = world.getBlockId(i, j + l1, k);
            if(l2 == 0 || l2 == Block.leaves.blockID || l2 == Block.waterMoving.blockID || l2 == Block.waterStill.blockID)
            {
                world.setBlock(i, j + l1, k, Block.wood.blockID);
            }
        }

        for(int i2 = (j - 3) + l; i2 <= j + l; i2++)
        {
            int i3 = i2 - (j + l);
            int l3 = 2 - i3 / 2;
            for(int k4 = i - l3; k4 <= i + l3; k4++)
            {
                for(int i5 = k - l3; i5 <= k + l3; i5++)
                {
                    if(world.getBlockId(k4, i2, i5) != Block.leaves.blockID)
                    {
                        continue;
                    }
                    if(random.nextInt(4) == 0 && world.getBlockId(k4 - 1, i2, i5) == 0)
                    {
                        func_35265_a(world, k4 - 1, i2, i5, 8);
                    }
                    if(random.nextInt(4) == 0 && world.getBlockId(k4 + 1, i2, i5) == 0)
                    {
                        func_35265_a(world, k4 + 1, i2, i5, 2);
                    }
                    if(random.nextInt(4) == 0 && world.getBlockId(k4, i2, i5 - 1) == 0)
                    {
                        func_35265_a(world, k4, i2, i5 - 1, 1);
                    }
                    if(random.nextInt(4) == 0 && world.getBlockId(k4, i2, i5 + 1) == 0)
                    {
                        func_35265_a(world, k4, i2, i5 + 1, 4);
                    }
                }

            }

        }
		
		
		for(int i2 = 0; i2 < 2; i2++)
        {
		label0:
            for(int l2 = 0; l2 < 3; l2++)
            {
                int k3 = (i + random.nextInt(l * 2 + 1)) - l;
                int l3 = j;
                int i4 = (k + random.nextInt(l * 2 + 1)) - l;
                if(!world.isAirBlock(k3, l3, i4))
                {
                    continue;
                }
                int j4 = 0;
                if(world.getBlockMaterial(k3 - 1, l3, i4).isSolid())
                {
                    j4++;
                }
                if(world.getBlockMaterial(k3 + 1, l3, i4).isSolid())
                {
                    j4++;
                }
                if(world.getBlockMaterial(k3, l3, i4 - 1).isSolid())
                {
                    j4++;
                }
                if(world.getBlockMaterial(k3, l3, i4 + 1).isSolid())
                {
                    j4++;
                }
                if(j4 != 1)
                {
                    continue;
                }
                world.setBlockWithNotify(k3, l3, i4, Block.chest.blockID);
                TileEntityChest tileentitychest = (TileEntityChest)world.getBlockTileEntity(k3, l3, i4);
                if(tileentitychest == null)
                {
                    break;
                }
                int k4 = 0;
                do
                {
                    if(k4 >= 8)
                    {
                        break label0;
                    }
                    ItemStack itemstack = pickCheckLootItem(random);
                    if(itemstack != null)
                    {
                        tileentitychest.setInventorySlotContents(random.nextInt(tileentitychest.getSizeInventory()), itemstack);
                    }
                    k4++;
                } while(true);
            }

        }

        return true;
    }

    private void func_35265_a(World world, int i, int j, int k, int l)
    {
        world.setBlockAndMetadataWithNotify(i, j, k, Block.vine.blockID, l);
        for(int i1 = 4; world.getBlockId(i, --j, k) == 0 && i1 > 0; i1--)
        {
            world.setBlockAndMetadataWithNotify(i, j, k, Block.vine.blockID, l);
        }

    }
	
	private ItemStack pickCheckLootItem(Random random)
	{
		int i = random.nextInt(11);
		if(i == 0)
		{
			return new ItemStack(Item.saddle);
		}
		if(i == 1)
		{
			return new ItemStack(Item.ingotIron, random.nextInt(4) + 1);
		}
		if(i == 2)
		{
			return new ItemStack(Item.bread);
		}
		if(i == 3)
		{
			return new ItemStack(Item.wheat, random.nextInt(4) + 1);
		}
		if(i == 4)
		{
			return new ItemStack(Item.gunpowder, random.nextInt(4) + 1);
		}
		if(i == 5)
		{
			return new ItemStack(Item.silk, random.nextInt(4) + 1);
		}
		if(i == 6)
		{
			return new ItemStack(Item.bucketEmpty);
		}
       	if(i == 7 && random.nextInt(100) == 0)
        {
            return new ItemStack(Item.appleGold);
        }
        if(i == 8 && random.nextInt(2) == 0)
        {
            return new ItemStack(Item.redstone, random.nextInt(4) + 1);
        }
        if(i == 9 && random.nextInt(10) == 0)
        {
            return new ItemStack(Item.itemsList[Item.record13.shiftedIndex + random.nextInt(2)]);
        }
        if(i == 10)
        {
            return new ItemStack(Item.dyePowder, 1, 3);
        } else
        {
            return null;
        }
	}
}
