// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, Block, BlockBed, EntityPlayer, 
//            MathHelper, World, ItemStack

public class ItemBed extends Item
{

    public ItemBed(int i)
    {
        super(i);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(l != 1)
        {
            return false;
        }
        j++;
        BlockBed blockbed = (BlockBed)Block.bed;
        int i1 = MathHelper.floor_double((double)((entityplayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        byte byte0 = 0;
        byte byte1 = 0;
        if(i1 == 0)
        {
            byte1 = 1;
        }
        if(i1 == 1)
        {
            byte0 = -1;
        }
        if(i1 == 2)
        {
            byte1 = -1;
        }
        if(i1 == 3)
        {
            byte0 = 1;
        }
        if(!entityplayer.func_35200_c(i, j, k) || !entityplayer.func_35200_c(i + byte0, j, k + byte1))
        {
            return false;
        }
        if(world.isAirBlock(i, j, k) && world.isAirBlock(i + byte0, j, k + byte1) && world.isBlockNormalCube(i, j - 1, k) && world.isBlockNormalCube(i + byte0, j - 1, k + byte1))
        {
            world.setBlockAndMetadataWithNotify(i, j, k, blockbed.blockID, i1);
            if(world.getBlockId(i, j, k) == blockbed.blockID)
            {
                world.setBlockAndMetadataWithNotify(i + byte0, j, k + byte1, blockbed.blockID, i1 + 8);
            }
            itemstack.stackSize--;
            return true;
        } else
        {
            return false;
        }
    }
}
