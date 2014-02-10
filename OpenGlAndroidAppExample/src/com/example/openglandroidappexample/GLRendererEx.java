package com.example.openglandroidappexample;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.SystemClock;



public class GLRendererEx implements Renderer{


	private GLTriangleEx tri;
	private GLCube cube;
	
	public GLRendererEx() {
		tri = new GLTriangleEx();
		cube = new GLCube();
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
		// TODO Auto-generated method stub
		
		// disabling this reduces quality 
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(.1f, 0f, 0.5f, 1f);
		gl.glClearDepthf(1f);
		
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -20, 0, 0, 0, 0, 2f, 0);
		
		long time = SystemClock.uptimeMillis() % 4000L;
		float angle = .09f * ((int) time);
		
		//gl.glRotatef(angle, 1, 2, 2);
		tri.draw(gl);
		cube.draw(gl);
		//cube.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		
		// 0,0  starts at the bottom left corner of the emulator
		// viewPort will be the whole emulator screen
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width/height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		// if objects are outside these specifications it doesn not load them
		// includes Z-direction
		gl.glFrustumf(-ratio, ratio, -1,  1, 1, 25);
	}
}









