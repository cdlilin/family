package org.swz.com.family.common.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgCutUtil {

	public static void cut(String srcImageFile, String outFile, int w, int h, int x1,

			int y1, int sw, int sh) {
		
		FileOutputStream fos = null;
		
		Image img;

		ImageFilter cropFilter; 

		try { 
 
			// 读取源图像

			BufferedImage bi = ImageIO.read(new File(srcImageFile));

			if (sw >= w && sh >= h) {

				Image image = bi.getScaledInstance(sw, sh, Image.SCALE_DEFAULT);

				// 剪切起始坐标点

				int x = x1;

				int y = y1;

				int destWidth = w; // 切片宽度

				int destHeight = h; // 切片高度

				// 图片比例

				double pw = sw;

				double ph = sh;

				double m = (double) sw / pw;

				double n = (double) sh / ph;

				int wth = (int) (destWidth * m);

				int hth = (int) (destHeight * n);

				int xx = (int) (x * m);

				int yy = (int) (y * n);
			

				// 四个参数分别为图像起点坐标和宽高

				// 即: CropImageFilter(int x,int y,int width,int height)
				
				cropFilter = new CropImageFilter(xx, yy, wth, hth);

				img = Toolkit.getDefaultToolkit().createImage(

				new FilteredImageSource(image.getSource(), cropFilter));

				BufferedImage tag = new BufferedImage(w, h,

				BufferedImage.TYPE_INT_RGB);

				Graphics g = tag.getGraphics();

				g.drawImage(img, 0, 0, null); // 绘制缩小后的图

				g.dispose();

				// 输出为文件
				fos = new FileOutputStream(new File(outFile));

				ImageIO.write(tag, "JPEG", new FileOutputStream(new File(outFile)));

			}

		} catch (Exception e) {

			e.printStackTrace();

		}finally{
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	}
	
	public static void main(String[] args) {
		cut("c:\\a.png", "c:\\b.png",100, 100, 100, 100,728, 601);
	} 
	 
}
