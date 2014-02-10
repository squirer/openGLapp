package com.example.openglandroidappexample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {

	private float vertices[] = {
		1,1,-1,
		1,-1,-1,
		-1,-1,-1,
		-1,1,-1,
		
		1,1,1,
		1,-1,1,
		-1,-1,1,
		-1,1,1,
	};
	
	private FloatBuffer vertBuff;
	
	private short[] pIndex = {
			3,4,0,  0,4,1,  3,0,1,
			3,7,4,  7,6,4,  7,3,6,
			3,1,2,  1,6,2,  6,3,1,
			1,4,5,  5,6,1,  6,5,4
	};
	
	private ShortBuffer pBuff;
	
	public GLCube() {
		// number of vertices * 4 to get bytes required
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		//shorts are 2 bytes get length index list * 2
		ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);
	}
	
	
	public void draw(GL10 gl) {
		// CW - clockwise connecting points
		// - front of triangle will be facing us!
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_CULL_FACE);
		// cull ignores the inside of the cube faces
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// size is dimensons - in this case 3D -- 
		// type is a float - vertBuff is a float
		// stride is 0 - stride is set if you want to skip over another variable (eg colour) 
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		
		// mode 
		// count is how many points we have
		// type is the type of buffer we are using - Short
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
}
