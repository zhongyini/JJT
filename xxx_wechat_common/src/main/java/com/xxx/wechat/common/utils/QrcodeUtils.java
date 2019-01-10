package com.xxx.wechat.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xxx.wechat.common.constant.Constant;

/**
 * 生成二维码
 * @author Administrator
 *
 */
public class QrcodeUtils {
	
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 
	 * @param text 扫描后显示的内容
	 * @param path 文件路径
	 * @param filename 文件名
	 * @param format 二维码图片格式
	 * @return filename.format
	 * @throws Exception
	 */
	public static String createQrcode(String text, String path, String filename, String format) throws Exception {
		int width = 900;
		int height = 900;
		// 设置编码，防止中文乱码
		Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object>();
		ht.put(EncodeHintType.CHARACTER_SET, Constant.Str.BIG_UTF8);
		// 设置二维码参数(编码内容，编码类型，图片宽度，图片高度, 编码格式)
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, ht);
		// 生成二维码(定义二维码输出服务器路径)
		File outputFile = new File(path);
		if (!outputFile.exists()) {
			// 创建文件夹
			outputFile.mkdir();
		}
		int b_width = bitMatrix.getWidth();
		int b_height = bitMatrix.getHeight();
		// 建立图像缓冲器
		BufferedImage image = new BufferedImage(b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
		for (int x = 0; x < b_width; x++) {
			for (int y = 0; y < b_height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
			}
		}
		// 生成二维码
		ImageIO.write(image, format, new File(path + File.separator + filename + Constant.Str.POINT + format));
		return filename + Constant.Str.POINT + format;
	}
	
}
