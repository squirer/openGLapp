package com.example.openglandroidappexample;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx {
	
	private float vertices[] = {
		0f, 1f, 
		1f, -1f,
		-1f, -1f 
	};
	
	private float rgbaVals[] = {
			1,1,0,.5f,
			.25f, 0, .85f, 1,
			0 ,1 ,1,1
		};
	
	
	
	private FloatBuffer vertBuff, colourBuff;
	
	private short[] pIndex = {0,1,2};
	
	private ShortBuffer pBuff;
	
	public GLTriangleEx() {
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
		
		ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		cBuff.order(ByteOrder.nativeOrder());
		colourBuff = cBuff.asFloatBuffer();
		colourBuff.put(rgbaVals);
		colourBuff.position(0);
	}
	
	
	public void draw(GL10 gl) {
		// CW - clockwise connecting points
		// - front of triangle will be facing us!
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		// size is dimensons - in this case 2D
		// type is a float - vertBuff is a float
		// stride is 0 - stride is set if you want to skip over another variable (eg colour) 
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colourBuff);
		// mode 
		// count is how many points we have
		// type is the type of buffer we are using - Short
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}









