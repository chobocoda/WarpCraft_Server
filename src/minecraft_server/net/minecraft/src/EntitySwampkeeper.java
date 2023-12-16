package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class EntitySwampkeeper extends EntityCreature
{

    public EntitySwampkeeper(World world)
    {
        super(world);
        texture = "/mob/char.png";
        setSize(0.6F, 1.8F);
    }

    public int getMaxHealth()
    {
        return 10;
    }
	
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    protected boolean canDespawn()
    {
        return false;
    }

}
