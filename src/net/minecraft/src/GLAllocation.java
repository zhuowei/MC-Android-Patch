package net.minecraft.src;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
//MCAndroidPatch: changed imports
//import org.lwjgl.opengl.GL11;
import android.opengl.GLES11;
//MCAndroidPatch end

public class GLAllocation
{
    /**
     * An ArrayList that stores the first index and the length of each display list.
     */
    private static List displayLists = new ArrayList();

    /** An ArrayList that stores all the generated texture names. */
    private static List textureNames = new ArrayList();

    /**
     * Generates the specified number of display lists and returns the first index.
     */
    @Deprecated
    public static synchronized int generateDisplayLists(int par0)
    {
        //MCAndroidPatch start
        /*int var1 = GL11.glGenLists(par0);
        displayLists.add(Integer.valueOf(var1));
        displayLists.add(Integer.valueOf(par0));
        return var1;*/
        throw new UnsupportedOperationException("Display lists not supported");
        //MCAndroidPatch end
    }

    /**
     * Generates texture names and stores them in the specified buffer.
     */
    public static synchronized void generateTextureNames(IntBuffer par0IntBuffer)
    {
        GLES11.glGenTextures(par0IntBuffer.limit() - par0IntBuffer.position(), par0IntBuffer); //MCAndroidPatch

        for (int var1 = par0IntBuffer.position(); var1 < par0IntBuffer.limit(); ++var1)
        {
            textureNames.add(Integer.valueOf(par0IntBuffer.get(var1)));
        }
    }

    @Deprecated
    public static synchronized void deleteDisplayLists(int par0)
    {
        //MCAndroidPatch start
        /*int var1 = displayLists.indexOf(Integer.valueOf(par0));
        GL11.glDeleteLists(((Integer)displayLists.get(var1)).intValue(), ((Integer)displayLists.get(var1 + 1)).intValue());
        displayLists.remove(var1);
        displayLists.remove(var1);*/
        throw new UnsupportedOperationException("Display lists not supported");
        //MCAndroidPatch end
    }

    /**
     * Deletes all textures and display lists. Called when Minecraft is shutdown to free up resources.
     */
    public static synchronized void deleteTexturesAndDisplayLists()
    {
        //MCAndroidPatch: No display list support
        /*for (int var0 = 0; var0 < displayLists.size(); var0 += 2)
        {
            GLES11.glDeleteLists(((Integer)displayLists.get(var0)).intValue(), ((Integer)displayLists.get(var0 + 1)).intValue());
        }*/

        IntBuffer var2 = createDirectIntBuffer(textureNames.size());
        var2.flip();
        GLES11.glDeleteTextures(textureNames.size(), var2); //MCAndroidPatch

        for (int var1 = 0; var1 < textureNames.size(); ++var1)
        {
            var2.put(((Integer)textureNames.get(var1)).intValue());
        }

        var2.flip();
        GLES11.glDeleteTextures(textureNames.size(), var2); //MCAndroidPatch
        displayLists.clear();
        textureNames.clear();
    }

    /**
     * Creates and returns a direct byte buffer with the specified capacity. Applies native ordering to speed up access.
     */
    public static synchronized ByteBuffer createDirectByteBuffer(int par0)
    {
        ByteBuffer var1 = ByteBuffer.allocateDirect(par0).order(ByteOrder.nativeOrder());
        return var1;
    }

    /**
     * Creates and returns a direct int buffer with the specified capacity. Applies native ordering to speed up access.
     */
    public static IntBuffer createDirectIntBuffer(int par0)
    {
        return createDirectByteBuffer(par0 << 2).asIntBuffer();
    }

    /**
     * Creates and returns a direct float buffer with the specified capacity. Applies native ordering to speed up
     * access.
     */
    public static FloatBuffer createDirectFloatBuffer(int par0)
    {
        return createDirectByteBuffer(par0 << 2).asFloatBuffer();
    }
}
