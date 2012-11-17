package org.anurag.bookview;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Flippage  extends Animation {
private final float mFromDegrees;
private final float mToDegrees;
private Camera mCamera;

	public Flippage(float fromDegrees, float toDegrees) {
		mFromDegrees = fromDegrees;
		mToDegrees = toDegrees;
	}
	
	@Override
	public void initialize(int width, int height, int parentWidth, int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		float degrees = mFromDegrees + ((mToDegrees - mFromDegrees) * interpolatedTime);
		
		final Camera camera = mCamera;		
		final Matrix matrix = t.getMatrix();		
		camera.save();		
		camera.rotateY(degrees);		
		camera.getMatrix(matrix);
		camera.restore();
	}

}

