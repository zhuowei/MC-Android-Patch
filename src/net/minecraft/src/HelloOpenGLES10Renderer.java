package net.minecraft.src;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES11;
import android.opengl.GLSurfaceView;

public class HelloOpenGLES10Renderer implements GLSurfaceView.Renderer {

    public ModelBiped bipedModel = new ModelBiped();

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        GLES11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    }
    
    public void onDrawFrame(GL10 gl) {
        Vec3D.initialize();
        GLES11.glLoadIdentity();
        // Redraw background color
        GLES11.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        GLES11.glColor4f(0.63671875f, (float)((System.currentTimeMillis() % 4000) / 4000f), 0.22265625f, 0.0f);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertex(0D, -0.5D, 0D);
        tessellator.addVertex(0D, 0D, 0D);
        tessellator.addVertex(0.5D, 0D, 0D);
        tessellator.addVertex(0.5D, -0.5D, 0D);
        tessellator.draw();
        GLES11.glColor4f(0.63671875f, 0.0f, (float)((System.currentTimeMillis() % 4000) / 4000f), 0.0f);
        GLES11.glScalef(0.15f, 0.15f, 0.15f);
        //GLES11.glRotatef((float)((System.currentTimeMillis() % 36000) / 100f), 0, 1.0F, 0);
        GLES11.glRotatef((float)((System.currentTimeMillis() % 18000) / 50f), 0, 0, 1.0f);
        //GLES11.glRotatef((float)((System.currentTimeMillis() % 36000) / 100f), 1.0f, 0F, 0f);
        //GLES11.glEnable(32826);
        GLES11.glEnable(GLES11.GL_COLOR_MATERIAL);
        //RenderHelper.enableStandardItemLighting();
        GLES11.glPushMatrix();
        GLES11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        //GLES11.glRotatef(entity.prevRotationYaw, 0, 1.0F, 0);
        //RenderHelper.enableStandardItemLighting();
        //RenderManager.instance.playerViewY = 180F;
        //RenderManager.instance.renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        bipedModel.render(0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f);
        GLES11.glPopMatrix();
        //RenderHelper.disableStandardItemLighting();
        //GLES11.glDisable(32826);

    }
    
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);

        /*
        * Set our projection matrix. This doesn't have to be done
        * each time we draw, but usually a new projection needs to
        * be set when the viewport is resized.
        */

        float ratio = (float) w / h;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);

    }
}
