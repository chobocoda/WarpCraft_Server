package net.minecraft.src;

import java.util.*;

public abstract class BiomeGenBase
{

    public static final BiomeGenBase biomeList[] = new BiomeGenBase[256];
	
    public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setGrassColor(112).setBiomeName("Ocean").setMinMaxHeight(-1F, 0.4F);
	
	public static final BiomeGenBase river = (new BiomeGenRiver(1)).setGrassColor(255).setBiomeName("River").setMinMaxHeight(-0.5F, 0.0F);
	
	
    public static final BiomeGenBase plains = (new BiomeGenPlains(2)).setGrassColor(0x8db360).setBiomeName("Plains").setTemperatureRainfall(0.8F, 0.4F);
    
    public static final BiomeGenBase mushroom_plains = (new BiomeGenMushroomPlains(3)).setGrassColor(0xaea441).setBiomeName("Mushroom Plains").setTemperatureRainfall(2.0F, 2.0F).setMinMaxHeight(-0.05F, 0.0F);
	
	
    public static final BiomeGenBase desert = (new BiomeGenDesert(4)).setGrassColor(0x34DF17).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.4F);
   
    public static final BiomeGenBase extremedesert = (new BiomeGenExtremeDesert(5)).setGrassColor(0xfa9218).setBiomeName("Extreme Desert").setDisableRain().setTemperatureRainfall(2.5F, 0.0F).setMinMaxHeight(0.2F, 1.2F);
	
	
    public static final BiomeGenBase extremehills = (new BiomeGenHills(6)).setGrassColor(0x43B243).setBiomeName("Extreme Hills").setMinMaxHeight(0.2F, 2.2F).setTemperatureRainfall(0.2F, 0.3F);
	
	
    public static final BiomeGenBase forest = (new BiomeGenForest(7)).setGrassColor(0x3DB029).setBiomeName("Forest").setTemperatureRainfall(0.7F, 0.8F);
	
	public static final BiomeGenBase extremeforest = (new BiomeGenExtremeForest(8)).setGrassColor(0x8852b).setBiomeName("Extreme Forest").setTemperatureRainfall(0.8F, 0.8F).setMinMaxHeight(0.3F, 1.1F);
	
	
	public static final BiomeGenBase frozen_taiga = (new BiomeGenFrozenTaiga(9)).setGrassColor(0xa5548).setBiomeName("Frozen Taiga").setTemperatureRainfall(0.1F, 0.6F).setMinMaxHeight(-0.2F, 0.8F);
	
    public static final BiomeGenBase taiga = (new BiomeGenTaiga(10)).setGrassColor(0xb6659).setBiomeName("Taiga").setTemperatureRainfall(0.3F, 0.8F).setMinMaxHeight(0.1F, 0.4F);
	
	
    public static final BiomeGenBase regular_swampland = (new BiomeGenSwamp(11)).setGrassColor(0x7f9b2).setBiomeName("Regular Swampland").setMinMaxHeight(-0.2F, 0.1F).setTemperatureRainfall(0.8F, 0.9F);
	
    public static final BiomeGenBase enchanted_swampland = (new BiomeGenEnchantedSwamp(12)).setGrassColor(0x10ff00).setBiomeName("Enchanted Swampland").setMinMaxHeight(-1F, 0.15F).setTemperatureRainfall(3.0F, 3.0F);
	
	
    public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13)).setGrassColor(0xa0a0a0).setBiomeName("Ice Mountains").setMinMaxHeight(0.2F, 2.2F).setTemperatureRainfall(0.0F, 0.5F);
	
	
	public static final BiomeGenBase hell = (new BiomeGenHell(14)).setGrassColor(0xff0000).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
	
    public static final BiomeGenBase sky = (new BiomeGenEnd(15)).setGrassColor(0x8080ff).setBiomeName("Sky").setDisableRain().setTemperatureRainfall(0.0F, 0.0F);

    public String biomeName;
    public int color;
    public byte topBlock;
    public byte fillerBlock;
    public float minHeight;
    public float maxHeight;
    public float temperature;
    public float rainfall;
    public int setWaterColor;
    public BiomeDecorator biomeDecorator;
    protected List spawnableMonsterList;
    protected List spawnableCreatureList;
    protected List spawnableWaterCreatureList;
    private boolean enableSnow;
    private boolean enableRain;
    public final int biomeID;
    protected WorldGenTrees worldGenTrees;
    protected WorldGenBigTree worldGenBigTree;
    protected WorldGenForest worldGenForest;
    protected WorldGenExtremeForest worldGenExtremeForest;
    protected WorldGenSwamp worldGenSwamp;
    protected WorldGenEnchantedSwamp worldGenEnchantedSwamp;
    protected WorldGenMossyStoneWall worldgenMossyStoneWall;

    protected BiomeGenBase(int i)
    {
        topBlock = (byte)Block.grass.blockID;
        fillerBlock = (byte)Block.dirt.blockID;
        minHeight = 0.1F;
        maxHeight = 0.3F;
        temperature = 0.5F;
        rainfall = 0.5F;
        setWaterColor = 0xffffff;
        spawnableMonsterList = new ArrayList();
        spawnableCreatureList = new ArrayList();
        spawnableWaterCreatureList = new ArrayList();
        enableRain = true;
        worldGenTrees = new WorldGenTrees(false);
        worldGenBigTree = new WorldGenBigTree(false);
        worldGenForest = new WorldGenForest(false);
        worldGenExtremeForest = new WorldGenExtremeForest(false);
        worldGenSwamp = new WorldGenSwamp();
        worldGenEnchantedSwamp = new WorldGenEnchantedSwamp();
        worldgenMossyStoneWall = new WorldGenMossyStoneWall(false);
        biomeID = i;
        biomeList[i] = this;
        biomeDecorator = createBiomeDecorator();
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntitySheep.class, 12, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityPig.class, 10, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityChicken.class, 10, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityCow.class, 8, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntitySpider.class, 10, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityZombie.class, 10, 4, 4));
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityGiantZombie.class, 10, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntitySkeleton.class, 10, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityCreeper.class, 10, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntitySlime.class, 10, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityEnderman.class, 1, 1, 4));
        spawnableWaterCreatureList.add(new SpawnListEntry(net.minecraft.src.EntitySquid.class, 10, 4, 4));
    }

    protected BiomeDecorator createBiomeDecorator()
    {
        return new BiomeDecorator(this);
    }

    private BiomeGenBase setTemperatureRainfall(float f, float f1)
    {
        if(f > 0.1F && f < 0.2F)
        {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        } else
        {
            temperature = f;
            rainfall = f1;
            return this;
        }
    }

    private BiomeGenBase setMinMaxHeight(float f, float f1)
    {
        minHeight = f;
        maxHeight = f1;
        return this;
    }

    private BiomeGenBase setDisableRain()
    {
        enableRain = false;
        return this;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(10) == 0)
        {
            return worldGenBigTree;
        } else
        {
            return worldGenTrees;
        }
    }

    protected BiomeGenBase setBiomeName(String s)
    {
        biomeName = s;
        return this;
    }

    protected BiomeGenBase setGrassColor(int i)
    {
        color = i;
        return this;
    }

    public List getSpawnableList(EnumCreatureType enumcreaturetype)
    {
        if(enumcreaturetype == EnumCreatureType.monster)
        {
            return spawnableMonsterList;
        }
        if(enumcreaturetype == EnumCreatureType.creature)
        {
            return spawnableCreatureList;
        }
        if(enumcreaturetype == EnumCreatureType.waterCreature)
        {
            return spawnableWaterCreatureList;
        } else
        {
            return null;
        }
    }

    public boolean getEnableSnow()
    {
        return enableSnow;
    }

    public boolean canSpawnLightningBolt()
    {
        if(enableSnow)
        {
            return false;
        } else
        {
            return enableRain;
        }
    }

    public float getSpawningChance()
    {
        return 0.1F;
    }

    public final int func_35476_e()
    {
        return (int)(rainfall * 65536F);
    }

    public final int func_35474_f()
    {
        return (int)(temperature * 65536F);
    }

    public void func_35477_a(World world, Random random, int i, int j)
    {
        biomeDecorator.decorate(world, random, i, j);
    }

}
