// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityWaterMob, ItemStack, Item, AxisAlignedBB, 
//            Material, World, MathHelper, NBTTagCompound, 
//            EntityPlayer

public class EntitySquid extends EntityWaterMob
{

    public float field_21063_a;
    public float field_21062_b;
    public float field_21061_c;
    public float field_21059_f;
    public float field_21060_ak;
    public float field_21058_al;
    public float field_21057_am;
    public float field_21056_an;
    private float randomMotionSpeed;
    private float field_21054_ap;
    private float field_21053_aq;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public EntitySquid(World world)
    {
        super(world);
        field_21063_a = 0.0F;
        field_21062_b = 0.0F;
        field_21061_c = 0.0F;
        field_21059_f = 0.0F;
        field_21060_ak = 0.0F;
        field_21058_al = 0.0F;
        field_21057_am = 0.0F;
        field_21056_an = 0.0F;
        randomMotionSpeed = 0.0F;
        field_21054_ap = 0.0F;
        field_21053_aq = 0.0F;
        randomMotionVecX = 0.0F;
        randomMotionVecY = 0.0F;
        randomMotionVecZ = 0.0F;
        texture = "/mob/squid.png";
        setSize(0.95F, 0.95F);
        field_21054_ap = (1.0F / (rand.nextFloat() + 1.0F)) * 0.2F;
    }

    public int getMaxHealth()
    {
        return 10;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return null;
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return 0;
    }

    protected void dropFewItems(boolean flag, int i)
    {
        int j = rand.nextInt(3 + i) + 1;
        for(int k = 0; k < j; k++)
        {
            entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }

    }

    public boolean interact(EntityPlayer entityplayer)
    {
        return super.interact(entityplayer);
    }

    public boolean isInWater()
    {
        return worldObj.handleMaterialAcceleration(boundingBox.expand(0.0D, -0.60000002384185791D, 0.0D), Material.water, this);
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        field_21062_b = field_21063_a;
        field_21059_f = field_21061_c;
        field_21058_al = field_21060_ak;
        field_21056_an = field_21057_am;
        field_21060_ak += field_21054_ap;
        if(field_21060_ak > 6.283185F)
        {
            field_21060_ak -= 6.283185F;
            if(rand.nextInt(10) == 0)
            {
                field_21054_ap = (1.0F / (rand.nextFloat() + 1.0F)) * 0.2F;
            }
        }
        if(isInWater())
        {
            if(field_21060_ak < 3.141593F)
            {
                float f = field_21060_ak / 3.141593F;
                field_21057_am = MathHelper.sin(f * f * 3.141593F) * 3.141593F * 0.25F;
                if((double)f > 0.75D)
                {
                    randomMotionSpeed = 1.0F;
                    field_21053_aq = 1.0F;
                } else
                {
                    field_21053_aq = field_21053_aq * 0.8F;
                }
            } else
            {
                field_21057_am = 0.0F;
                randomMotionSpeed = randomMotionSpeed * 0.9F;
                field_21053_aq = field_21053_aq * 0.99F;
            }
            if(!isMultiplayerEntity)
            {
                motionX = randomMotionVecX * randomMotionSpeed;
                motionY = randomMotionVecY * randomMotionSpeed;
                motionZ = randomMotionVecZ * randomMotionSpeed;
            }
            float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            renderYawOffset += ((-(float)Math.atan2(motionX, motionZ) * 180F) / 3.141593F - renderYawOffset) * 0.1F;
            rotationYaw = renderYawOffset;
            field_21061_c = field_21061_c + 3.141593F * field_21053_aq * 1.5F;
            field_21063_a += ((-(float)Math.atan2(f1, motionY) * 180F) / 3.141593F - field_21063_a) * 0.1F;
        } else
        {
            field_21057_am = MathHelper.abs(MathHelper.sin(field_21060_ak)) * 3.141593F * 0.25F;
            if(!isMultiplayerEntity)
            {
                motionX = 0.0D;
                motionY -= 0.080000000000000002D;
                motionY *= 0.98000001907348633D;
                motionZ = 0.0D;
            }
            field_21063_a += (double)(-90F - field_21063_a) * 0.02D;
        }
    }

    public void moveEntityWithHeading(float f, float f1)
    {
        moveEntity(motionX, motionY, motionZ);
    }

    protected void updateEntityActionState()
    {
        entityAge++;
        if(entityAge > 100)
        {
            randomMotionVecX = randomMotionVecY = randomMotionVecZ = 0.0F;
        } else
        if(rand.nextInt(50) == 0 || !inWater || randomMotionVecX == 0.0F && randomMotionVecY == 0.0F && randomMotionVecZ == 0.0F)
        {
            float f = rand.nextFloat() * 3.141593F * 2.0F;
            randomMotionVecX = MathHelper.cos(f) * 0.2F;
            randomMotionVecY = -0.1F + rand.nextFloat() * 0.2F;
            randomMotionVecZ = MathHelper.sin(f) * 0.2F;
        }
        despawnEntity();
    }

    public boolean getCanSpawnHere()
    {
        return posY > 45D && posY < (double)worldObj.worldOceanHeight && super.getCanSpawnHere();
    }
}
