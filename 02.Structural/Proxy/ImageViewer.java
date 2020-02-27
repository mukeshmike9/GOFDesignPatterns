package com.jpm.structural.Proxy;

public class ImageViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// assuming that the user selects a folder that has 3 images	
		//create the 3 images 	
		Image highResolutionImage1 = new ImageProxy("sample/veryHighResPhoto1.jpeg");
		Image highResolutionImage2 = new ImageProxy("sample/veryHighResPhoto2.jpeg");
		Image highResolutionImage3 = new ImageProxy("sample/veryHighResPhoto3.jpeg");
		
		// assume that the user clicks on Image one item in a list
		// this would cause the program to call showImage() for that image only
		// note that in this case only image one was loaded into memory
		highResolutionImage1.showImage();
		
		// consider using the high resolution image object directly
		Image highResolutionImageNoProxy1 = new HighResolutionImage("sample/veryHighResPhoto1.jpeg");
		Image highResolutionImageNoProxy2 = new HighResolutionImage("sample/veryHighResPhoto2.jpeg");
		Image highResolutionImageBoProxy3 = new HighResolutionImage("sample/veryHighResPhoto3.jpeg");
		
		
		// assume that the user selects image two item from images list
		highResolutionImageNoProxy2.showImage();
		
		// note that in this case all images have been loaded into memory 
		// and not all have been actually displayed
		// this is a waste of memory resources


	}

}

/**
 * Subject Interface
 */
interface Image {

	public void showImage();
	
}


/**
 * RealSubject
 */
class HighResolutionImage implements Image {

	public HighResolutionImage(String imageFilePath) {
		
		loadImage(imageFilePath);
	}

	private void loadImage(String imageFilePath) {

		// load Image from disk into memory
		// this is heavy and costly operation
	}

	@Override
	public void showImage() {

		// Actual Image rendering logic

	}

}

/**
 * Proxy
 */
class ImageProxy implements Image {

	/**
	 * Private Proxy data 
	 */
	private String imageFilePath;
	
	/**
	 * Reference to RealSubject
	 */
	private Image proxifiedImage;
	
	
	public ImageProxy(String imageFilePath) {
		this.imageFilePath= imageFilePath;	
	}
	
	@Override
	public void showImage() {

		// create the Image Object only when the image is required to be shown
		
		proxifiedImage = new HighResolutionImage(imageFilePath);
		
		// now call showImage on realSubject
		proxifiedImage.showImage();
		
	}

}
