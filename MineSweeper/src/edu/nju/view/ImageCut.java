package edu.nju.view;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.prism.Image;

public class ImageCut {
 
 public void readUsingImageReader(String src, String dest ,int x,int y, int w, int h)
   throws Exception {
  // 取得图片读入器
  Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
  ImageReader reader = (ImageReader) readers.next();
  // 取得图片读入流
  InputStream source = new FileInputStream(src);
  ImageInputStream iis = ImageIO.createImageInputStream(source);
  reader.setInput(iis, true);
  // 图片参数
  ImageReadParam param = reader.getDefaultReadParam();
  // 100，200是左上起始位置，300就是取宽度为300的，就是从100开始取300宽，就是横向100~400，同理纵向200~350的区域就取高度150
  // Rectangle rect = new Rectangle(100, 200, 300, 150);//
  
  Rectangle rect = new Rectangle(x, y, w, h);
  param.setSourceRegion(rect);
  BufferedImage bi = reader.read(0, param);
  ImageIO.write(bi, "png", new File(dest));
 }
 public static void main(String[] args) throws Exception { // main方法测试用
  ImageCut t = new ImageCut();
  int index = 0;
  		for(int i=0;i<7;i++){
  			for(int j=0;j<5;j++){
  				t.readUsingImageReader("f://minesweeper/Fireworks2.png", "f://minesweeper/win fire work2/"+index+".png",j*375,i*287, 375, 287);
  				index++;
  			}
  		}
  
  			//	t.readUsingImageReader("f://-2.png", "f://minesweeper/3.jpg", 84, 84);
 }
}