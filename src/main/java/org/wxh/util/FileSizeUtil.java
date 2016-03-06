package org.wxh.util;

public class FileSizeUtil {
	
	private static FileSizeUtil fsu;
	private FileSizeUtil() {}
	
	public static FileSizeUtil getInstance() {
		if( fsu == null ) {
			fsu = new FileSizeUtil();
		}
		return fsu;
	}
	/**
	 * 文件大小转换成可显示的Mb,Gb和kb方法
	 * @param size
	 * @return
	 */
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }
	
}
