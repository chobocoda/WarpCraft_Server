// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

public class MathHelper
{

    private static float SIN_TABLE[];

    public MathHelper()
    {
    }

    public static final float sin(float f)
    {
        return SIN_TABLE[(int)(f * 10430.38F) & 0xffff];
    }

    public static final float cos(float f)
    {
        return SIN_TABLE[(int)(f * 10430.38F + 16384F) & 0xffff];
    }

    public static final float sqrt_float(float f)
    {
        return (float)Math.sqrt(f);
    }

    public static final float sqrt_double(double d)
    {
        return (float)Math.sqrt(d);
    }

    public static int floor_float(float f)
    {
        int i = (int)f;
        return f >= (float)i ? i : i - 1;
    }

    public static int floor_double(double d)
    {
        int i = (int)d;
        return d >= (double)i ? i : i - 1;
    }

    public static long floor_double_long(double d)
    {
        long l = (long)d;
        return d >= (double)l ? l : l - 1L;
    }

    public static float abs(float f)
    {
        return f < 0.0F ? -f : f;
    }

    public static int getUnsigned(int i)
    {
        return i < 0 ? -i : i;
    }

    public static int func_41051_a(int i, int j, int k)
    {
        if(i < j)
        {
            return j;
        }
        if(i > k)
        {
            return k;
        } else
        {
            return i;
        }
    }

    public static double abs_max(double d, double d1)
    {
        if(d < 0.0D)
        {
            d = -d;
        }
        if(d1 < 0.0D)
        {
            d1 = -d1;
        }
        return d <= d1 ? d1 : d;
    }

    public static int func_35476_a(Random random, int i, int j)
    {
        if(i >= j)
        {
            return i;
        } else
        {
            return random.nextInt((j - i) + 1) + i;
        }
    }

    static 
    {
        SIN_TABLE = new float[0x10000];
        for(int i = 0; i < 0x10000; i++)
        {
            SIN_TABLE[i] = (float)Math.sin(((double)i * 3.1415926535897931D * 2D) / 65536D);
        }

    }
}
