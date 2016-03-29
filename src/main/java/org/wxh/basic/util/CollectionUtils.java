package org.wxh.basic.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CollectionUtils {
	
	/**
	 * 获取集合长度
	 * @param collection
	 * @return
	 */
	public static int length( Collection collection ) {
		return collection != null ? collection.size( ) : 0;
	}

	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty( Collection collection ) {
		return ( collection == null ) || ( collection.isEmpty( ) );
	}

	/**
	 * 获取数组长度
	 * @param array
	 * @return
	 */
	public static int length( Object[] array ) {
		return array != null ? array.length : 0;
	}

	/**
	 * 判断数组是否为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty( Object[] array ) {
		return length( array ) == 0;
	}

	/**
	 * 获取map长度
	 * @param map
	 * @return
	 */
	public static int length( Map map ) {
		return map != null ? map.size( ) : 0;
	}

	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty( Map map ) {
		return ( map == null ) || ( map.isEmpty( ) );
	}

	/**
	 * 判断list 是否相等
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean equals( List x, List y ) {
		if ( x == y ) {
			return true;
		}
		if ( x.size( ) != y.size( ) ) {
			return false;
		}
		int index = 0;
		for ( int len = x.size( ); index < len; index++ ) {
			Object valX = x.get( index );
			Object valY = y.get( index );
			if ( !valX.equals( valY ) ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断数组是否相等
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean deepEquals( Object[] x, Object[] y ) {
		return Arrays.deepEquals( x, y );
	}

	/**
	 * 判断数据是否相等
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean equals( Object[] x, Object[] y ) {
		if ( x == y ) {
			return true;
		}
		if ( x.length != y.length ) {
			return false;
		}
		int index = 0;
		for ( int len = x.length; index < len; index++ ) {
			Object valX = x[ index ];
			Object valY = y[ index ];
			if ( !valX.equals( valY ) ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断常量数组是否相等
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean equals( int[] x, int[] y ) {
		if ( x == y ) {
			return true;
		}
		if ( x.length != y.length ) {
			return false;
		}
		int index = 0;
		for ( int len = x.length; index < len; index++ ) {
			int valX = x[ index ];
			int valY = y[ index ];
			if ( valX != valY ) {
				return false;
			}
		}
		return true;
	}


}
