package com.bbsuper.nev.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * 图片验证码工具
 * @author liwei
 * @date: 2018年5月14日 下午2:31:49
 *
 */
public class ImageCodeUtil {
	
	/**
	 * 图像宽度
	 */
	public static final int WIDEH = 90;
	
	/**
	 * 图像高度
	 */
	public static final int HEIGHT = 35;
	
	/**
	 * 字符数
	 */
	private static final int NUM = 4;
	
	/**
	 * 干扰线数量
	 */
	private static final int LINE_NUM = 6;
	
	public static String imageCode(BufferedImage image){
        // 获取图形上下文
        Graphics2D g = image.createGraphics();
        // 设定背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDEH, HEIGHT);
        // 设定字体
        g.setFont(new Font("黑体" , Font.BOLD , 24));
        // 绘制干扰线
        drawRandomLines(g,LINE_NUM);
        
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < NUM; i++) {
        	//生成随机字符
        	String temp = randomChar();
            builder.append(temp);
            //设置文字颜色
            g.setColor(getRandColor(50, 150));
            //设置旋转
			AffineTransform trans = new AffineTransform();  
			trans.rotate((random.nextInt(60)-30)*3.14/180, 22*i+15, 15) ;  
			//缩放文字  
			float scaleSize = random.nextFloat() + 0.8f ;  
			if(scaleSize>1f) {
				scaleSize = 1f ; 
			}
			trans.scale(scaleSize, scaleSize) ;  
			g.setTransform(trans) ;  
			g.drawString(temp, 22*i+5, 25) ;  
        }
        //图像生效
        g.dispose(); 
        return builder.toString();
    }

	private static String randomChar() {
		Random random = new Random();
		switch (random.nextInt(3)) {
		//大写字母
		case 0:
			return String.valueOf((char)(random.nextInt(26)+65));
		//小写字母
		case 1:
			return String.valueOf((char)(random.nextInt(26)+97));
		//数字
		case 2:
			return String.valueOf(random.nextInt(10));
		default:
			break;
		}
		return "";
	}
	/**
	 * 绘制干扰线
	 * @param g
	 * @param nums
	 */
	private static void drawRandomLines(Graphics2D g ,int num ){
		g.setColor(getRandColor(50, 150));
		Random random = new Random();  
		for(int i=0 ; i<num ; i++){  
			int x1 = random.nextInt(WIDEH) ;  
			int y1 = random.nextInt(HEIGHT);  
			int x2 = random.nextInt(WIDEH) ;  
			int y2 = random.nextInt(HEIGHT) ;  
			g.drawLine(x1, y1, x2, y2) ;  
		}  
	}
	/**
	 * 生成随机颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
