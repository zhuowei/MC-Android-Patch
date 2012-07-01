package net.minecraft.src;

import java.nio.FloatBuffer;
//import org.lwjgl.opengl.GL11;
import android.opengl.GLES11;
//MCAndroidPatch end

public class RenderHelper
{
    /** Float buffer used to set OpenGL material colors */
    private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);

    /**
     * Disables the OpenGL lighting properties enabled by enableStandardItemLighting
     */
    public static void disableStandardItemLighting()
    {
        GLES11.glDisable(GLES11.GL_LIGHTING);
        GLES11.glDisable(GLES11.GL_LIGHT0);
        GLES11.glDisable(GLES11.GL_LIGHT1);
        GLES11.glDisable(GLES11.GL_COLOR_MATERIAL);
    }

    /**
     * Sets the OpenGL lighting properties to the values used when rendering blocks as items
     */
    public static void enableStandardItemLighting()
    {
        GLES11.glEnable(GLES11.GL_LIGHTING);
        GLES11.glEnable(GLES11.GL_LIGHT0);
        GLES11.glEnable(GLES11.GL_LIGHT1);
        GLES11.glEnable(GLES11.GL_COLOR_MATERIAL);
        //GLES11.glColorMaterial(GLES11.GL_FRONT_AND_BACK, GLES11.GL_AMBIENT_AND_DIFFUSE); MCAndroidPatch - not supported; default behaviour on ES 1.1
        float var0 = 0.4F;
        float var1 = 0.6F;
        float var2 = 0.0F;
        Vec3D var3 = Vec3D.createVector(0.2D, 1.0D, -0.7D).normalize();
        GLES11.glLightfv(GLES11.GL_LIGHT0, GLES11.GL_POSITION, setColorBuffer(var3.xCoord, var3.yCoord, var3.zCoord, 0.0D));
        GLES11.glLightfv(GLES11.GL_LIGHT0, GLES11.GL_DIFFUSE, setColorBuffer(var1, var1, var1, 1.0F));
        GLES11.glLightfv(GLES11.GL_LIGHT0, GLES11.GL_AMBIENT, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GLES11.glLightfv(GLES11.GL_LIGHT0, GLES11.GL_SPECULAR, setColorBuffer(var2, var2, var2, 1.0F));
        var3 = Vec3D.createVector(-0.2D, 1.0D, 0.7D).normalize();
        GLES11.glLightfv(GLES11.GL_LIGHT1, GLES11.GL_POSITION, setColorBuffer(var3.xCoord, var3.yCoord, var3.zCoord, 0.0D));
        GLES11.glLightfv(GLES11.GL_LIGHT1, GLES11.GL_DIFFUSE, setColorBuffer(var1, var1, var1, 1.0F));
        GLES11.glLightfv(GLES11.GL_LIGHT1, GLES11.GL_AMBIENT, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GLES11.glLightfv(GLES11.GL_LIGHT1, GLES11.GL_SPECULAR, setColorBuffer(var2, var2, var2, 1.0F));
        GLES11.glShadeModel(GLES11.GL_FLAT);
        GLES11.glLightModelfv(GLES11.GL_LIGHT_MODEL_AMBIENT, setColorBuffer(var0, var0, var0, 1.0F));
    }

    /**
     * Update and return colorBuffer with the RGBA values passed as arguments
     */
    private static FloatBuffer setColorBuffer(double par0, double par2, double par4, double par6)
    {
        return setColorBuffer((float)par0, (float)par2, (float)par4, (float)par6);
    }

    /**
     * Update and return colorBuffer with the RGBA values passed as arguments
     */
    private static FloatBuffer setColorBuffer(float par0, float par1, float par2, float par3)
    {
        colorBuffer.clear();
        colorBuffer.put(par0).put(par1).put(par2).put(par3);
        colorBuffer.flip();
        return colorBuffer;
    }

    /**
     * Sets OpenGL lighting for rendering blocks as items inside GUI screens (such as containers).
     */
    public static void enableGUIStandardItemLighting()
    {
        GLES11.glPushMatrix();
        GLES11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
        GLES11.glRotatef(165.0F, 1.0F, 0.0F, 0.0F);
        enableStandardItemLighting();
        GLES11.glPopMatrix();
    }
}