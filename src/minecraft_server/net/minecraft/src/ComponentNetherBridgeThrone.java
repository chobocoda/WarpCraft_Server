// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentNetherBridgePiece, StructureBoundingBox, StructureComponent, Block, 
//            World, TileEntityMobSpawner

public class ComponentNetherBridgeThrone extends ComponentNetherBridgePiece
{

    private boolean field_40305_a;

    public ComponentNetherBridgeThrone(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
    }

    public static ComponentNetherBridgeThrone func_40304_a(List list, Random random, int i, int j, int k, int l, int i1)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.func_35663_a(i, j, k, -2, 0, 0, 7, 8, 9, l);
        if(!func_40286_a(structureboundingbox) || StructureComponent.canFitInside(list, structureboundingbox) != null)
        {
            return null;
        } else
        {
            return new ComponentNetherBridgeThrone(i1, random, structureboundingbox, l);
        }
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        fillWithBlocks(world, structureboundingbox, 0, 2, 0, 6, 7, 7, 0, 0, false);
        fillWithBlocks(world, structureboundingbox, 1, 0, 0, 5, 1, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 2, 1, 5, 2, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 3, 2, 5, 3, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 4, 3, 5, 4, 7, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 2, 0, 1, 4, 2, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 5, 2, 0, 5, 4, 2, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 5, 2, 1, 5, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 5, 5, 2, 5, 5, 3, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 0, 5, 3, 0, 5, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 6, 5, 3, 6, 5, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 5, 8, 5, 5, 8, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        func_35309_a(world, Block.netherFence.blockID, 0, 1, 6, 3, structureboundingbox);
        func_35309_a(world, Block.netherFence.blockID, 0, 5, 6, 3, structureboundingbox);
        fillWithBlocks(world, structureboundingbox, 0, 6, 3, 0, 6, 8, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 6, 6, 3, 6, 6, 8, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 1, 6, 8, 5, 7, 8, Block.netherFence.blockID, Block.netherFence.blockID, false);
        fillWithBlocks(world, structureboundingbox, 2, 8, 8, 4, 8, 8, Block.netherFence.blockID, Block.netherFence.blockID, false);
        if(!field_40305_a)
        {
            int i = func_35300_a(5);
            int k = func_35306_a(3, 5);
            int i1 = func_35296_b(3, 5);
            if(structureboundingbox.isInBbVolume(k, i, i1))
            {
                field_40305_a = true;
                world.setBlockWithNotify(k, i, i1, Block.mobSpawner.blockID);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getBlockTileEntity(k, i, i1);
                if(tileentitymobspawner != null)
                {
                    tileentitymobspawner.setMobID("Blaze");
                }
            }
        }
        for(int j = 0; j <= 6; j++)
        {
            for(int l = 0; l <= 6; l++)
            {
                func_35303_b(world, Block.netherBrick.blockID, 0, j, -1, l, structureboundingbox);
            }

        }

        return true;
    }
}
