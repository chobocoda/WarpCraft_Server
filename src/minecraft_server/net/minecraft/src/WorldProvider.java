// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            WorldChunkManager, ChunkProviderGenerate, World, WorldInfo, 
//            Block, BlockGrass, WorldProviderHell, WorldProviderSurface, 
//            WorldProviderEnd, IChunkProvider, ChunkCoordinates

public abstract class WorldProvider
{

    public World worldObj;
    public WorldChunkManager worldChunkMgr;
    public boolean canSleepInWorld;
    public boolean isHellWorld;
    public boolean hasNoSky;
    public float lightBrightnessTable[];
    public int worldType;
    private float colorsSunriseSunset[];

    public WorldProvider()
    {
        canSleepInWorld = false;
        isHellWorld = false;
        hasNoSky = false;
        lightBrightnessTable = new float[16];
        worldType = 0;
        colorsSunriseSunset = new float[4];
    }

    public final void registerWorld(World world)
    {
        worldObj = world;
        registerWorldChunkManager();
        generateLightBrightnessTable();
    }

    protected void generateLightBrightnessTable()
    {
        float f = 0.0F;
        for(int i = 0; i <= 15; i++)
        {
            float f1 = 1.0F - (float)i / 15F;
            lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
        }

    }

    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManager(worldObj);
    }

    public IChunkProvider getChunkProvider()
    {
        return new ChunkProviderGenerate(worldObj, worldObj.getRandomSeed(), worldObj.getWorldInfo().getMapFeaturesEnabled());
    }

    public boolean canCoordinateBeSpawn(int i, int j)
    {
        int k = worldObj.getFirstUncoveredBlock(i, j);
        return k == Block.grass.blockID;
    }

    public float calculateCelestialAngle(long l, float f)
    {
        int i = (int)(l % 24000L);
        float f1 = ((float)i + f) / 24000F - 0.25F;
        if(f1 < 0.0F)
        {
            f1++;
        }
        if(f1 > 1.0F)
        {
            f1--;
        }
        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * 3.1415926535897931D) + 1.0D) / 2D);
        f1 = f2 + (f1 - f2) / 3F;
        return f1;
    }

    public boolean canRespawnHere()
    {
        return true;
    }

    public static WorldProvider getProviderForDimension(int i)
    {
        if(i == -1)
        {
            return new WorldProviderHell();
        }
        if(i == 0)
        {
            return new WorldProviderSurface();
        }
        if(i == 1)
        {
            return new WorldProviderEnd();
        } else
        {
            return null;
        }
    }

    public ChunkCoordinates func_40545_d()
    {
        return null;
    }
}
