// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, World, Material, EntityPlayer, 
//            Block, MathHelper, ItemStack, TileEntitySign

public class ItemSign extends Item
{

    public ItemSign(int i)
    {
        super(i);
        maxStackSize = 1;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(l == 0)
        {
            return false;
        }
        if(!world.getBlockMaterial(i, j, k).isSolid())
        {
            return false;
        }
        if(l == 1)
        {
            j++;
        }
        if(l == 2)
        {
            k--;
        }
        if(l == 3)
        {
            k++;
        }
        if(l == 4)
        {
            i--;
        }
        if(l == 5)
        {
            i++;
        }
        if(!entityplayer.func_35200_c(i, j, k))
        {
            return false;
        }
        if(!Block.signPost.canPlaceBlockAt(world, i, j, k))
        {
            return false;
        }
        if(l == 1)
        {
            int i1 = MathHelper.floor_double((double)(((entityplayer.rotationYaw + 180F) * 16F) / 360F) + 0.5D) & 0xf;
            world.setBlockAndMetadataWithNotify(i, j, k, Block.signPost.blockID, i1);
        } else
        {
            world.setBlockAndMetadataWithNotify(i, j, k, Block.signWall.blockID, l);
        }
        itemstack.stackSize--;
        TileEntitySign tileentitysign = (TileEntitySign)world.getBlockTileEntity(i, j, k);
        if(tileentitysign != null)
        {
            entityplayer.displayGUIEditSign(tileentitysign);
        }
        return true;
    }
}
