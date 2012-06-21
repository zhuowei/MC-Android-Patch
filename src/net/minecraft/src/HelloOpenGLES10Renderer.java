package net.minecraft.src;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES11;
import android.opengl.GLSurfaceView;

public class HelloOpenGLES10Renderer implements GLSurfaceView.Renderer {

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        GLES11.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    }
    
    public void onDrawFrame(GL10 gl) {
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
    }
    
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES11.glViewport(0, 0, width, height);
    }
}
