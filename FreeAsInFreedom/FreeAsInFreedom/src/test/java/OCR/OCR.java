package OCR;

import Image.FastOCR;

public class OCR {
	public static void main(String[]args) throws Exception {
		FastOCR ocr = new FastOCR();
		System.out.println(ocr.ocr());
	}
}
