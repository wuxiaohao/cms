package org.wxh.basic.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtils {
/*	public static final String EMPTY_STRING = "";
	public static final int INDEX_NOT_FOUND = -1;
	public static final char DEFAULT_SEPARATOR = ',';
	public static final String DEFAULT_SEPARATOR_STRING = ",";
	public static final char MAP_KEY_SEPARATOR = ':';
	public static final char MAP_KEY_SEPARATOR2 = '=';
	public static final String MAP_KEY_SEPARATOR_STRING = ":";
	public static final char ARRAY_BOUND_START = '[';
	public static final char ARRAY_BOUND_END = ']';
	public static final char MAP_BOUND_START = '{';
	public static final char MAP_BOUND_END = '}';
	public static final char LF = '\n';
	public static final char CR = '\r';*/

	/**
	 * 判断是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty( String s ) {
		return length( s ) == 0;
	}

	/**
	 * 是否不为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean notEmpty( String s ) {
		return length( s ) > 0;
	}

	/**
	 * 长度
	 * 
	 * @param s
	 * @return
	 */
	public static int length( String s ) {
		return s != null ? s.length( ) : 0;
	}

	/**
	 * 是否含有文本内容
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasText( String s ) {
		if ( isEmpty( s ) ) {
			return false;
		}
		int i = s.length( );
		while ( i > 0 ) {
			i--;
			if ( s.charAt( i ) > ' ' ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否是16进制数
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public static boolean isHexPrefix( String s, int index ) {
		return ( s.charAt( index ) == '0' ) && ( s.charAt( index + 1 ) == 'x' );
	}

	/**
	 * s是否是数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumeric( String s ) {
		int sz = length( s );
		if ( sz == 0 ) {
			return false;
		}
		for ( int i = 0; i < sz; i++ ) {
			if ( !Character.isDigit( s.charAt( i ) ) ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 字符串是否由数字和字母组成
	 * @param s
	 * @return
	 */
	public static boolean isLettersAndNumbers(String s){
		//a-z，A-Z，0-9
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{1,}$");
		return pattern.matcher(s).matches() ;
	}
	
	/**
	 * 从s1中查找s2字符串
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static String select( String s1, String s2 ) {
		if ( notEmpty( s1 ) )
			return s1;
		return s2;
	}

	/**
	 * 
	 * @param array
	 * @return
	 */
	public static String select( String[] array ) {
		if ( array != null ) {
			String[] arrayOfString = array;
			int j = array.length;
			for ( int i = 0; i < j; i++ ) {
				String e = arrayOfString[ i ];
				if ( notEmpty( e ) )
					return e;
			}
		}
		return null;
	}

	/**
	 * 去除两边空格字符
	 * 
	 * @param s
	 * @return
	 */
	public static String trim( String s ) {
		return isEmpty( s ) ? s : s.trim( );
	}

	/**
	 * 去除字符串中所有的空格字符
	 * 
	 * @param s
	 * @return
	 */
	public static String deleteWhitespace( String s ) {
		if ( isEmpty( s ) ) {
			return s;
		}
		int length = s.length( );
		char[] chs = new char[ length ];
		int count = 0;
		for ( int i = 0; i < length; i++ ) {
			char ch = s.charAt( i );
			if ( !Character.isWhitespace( ch ) ) {
				chs[ ( count++ ) ] = ch;
			}
		}
		if ( count == length ) {
			return s;
		}
		return new String( chs, 0, count );
	}

	/**
	 * 从字符串中删除指定字符
	 * 
	 * @param s
	 * @param ch
	 * @return
	 */
	public static String deleteCharacter( String s, char ch ) {
		int length = length( s );
		if ( length == 0 ) {
			return s;
		}
		char[] chs = new char[ length ];
		int count = 0;
		for ( int i = 0; i < length; i++ ) {
			char c = s.charAt( i );
			if ( c != ch ) {
				chs[ ( count++ ) ] = c;
			}
		}
		if ( count == length ) {
			return s;
		}
		return new String( chs, 0, count );
	}

	/**
	 * 从字符串中删除字符数组
	 * 
	 * @param s
	 * @param charArray
	 * @return
	 */
	public static String deleteCharacter( String s, char[] charArray ) {
		int length = length( s );
		if ( length == 0 ) {
			return s;
		}
		Arrays.sort( charArray );
		char[] chs = new char[ length ];
		int count = 0;
		for ( int i = 0; i < length; i++ ) {
			char c = s.charAt( i );
			if ( Arrays.binarySearch( charArray, c ) < 0 ) {
				chs[ ( count++ ) ] = c;
			}
		}
		if ( count == length ) {
			return s;
		}
		return new String( chs, 0, count );
	}

	/**
	 * 获取首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalFirstLetter( String s ) {
		int len = length( s );
		if ( len == 0 ) {
			return s;
		}
		if ( len == 1 ) {
			return String.valueOf( Character.toUpperCase( s.charAt( 0 ) ) );
		}
		return Character.toUpperCase( s.charAt( 0 ) ) + s.substring( 1 );
	}
	
	/**
	 * 将unicode转成中文
	 * @param utfString
	 * @return
	 */
	public static String convert( String utfString ) {
		if ( isEmpty( utfString ) ) {
			return utfString;
		}
		StringBuilder sb = new StringBuilder( );
		int i = -1;
		int pos = 0;

		while ( ( i = utfString.indexOf( "\\u", pos ) ) != -1 ) {
			sb.append( utfString.substring( pos, i ) );
			if ( i + 5 < utfString.length( ) ) {
				pos = i + 6;
				sb.append( ( char ) Integer.parseInt( utfString.substring( i + 2, i + 6 ), 16 ) );
			}
		}

		return sb.toString( );
	} 

	/**
	 * 左边补空
	 * 
	 * @param s
	 * @param size
	 * @return
	 */
	public static String leftPad( String s, int size ) {
		return leftPad( s, size, ' ' );
	}

	/**
	 * 右边补空
	 * 
	 * @param s
	 * @param size
	 * @return
	 */
	public static String rightPad( String s, int size ) {
		return rightPad( s, size, ' ' );
	}

	/**
	 * 左边补空
	 * 
	 * @param s
	 * @param size
	 * @param padChar
	 * @return
	 */
	public static String leftPad( String s, int size, char padChar ) {
		int length = length( s );
		if ( length == 0 ) {
			return s;
		}
		int pads = size - length;
		if ( pads <= 0 ) {
			return s;
		}
		return padding( pads, padChar ).concat( s );
	}

	/**
	 * 右边补空
	 * 
	 * @param s
	 * @param size
	 * @param padChar
	 * @return
	 */
	public static String rightPad( String s, int size, char padChar ) {
		int length = length( s );
		if ( length == 0 ) {
			return s;
		}
		int pads = size - length;
		if ( pads <= 0 ) {
			return s;
		}
		return s.concat( padding( pads, padChar ) );
	}

	/**
	 * 缩写
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String abbr( String s, int len ) {
		int length = length( s );
		if ( length <= len ) {
			return s;
		}
		return s.substring( 0, len - 3 ) + "...";
	}

	/**
	 * 填充
	 * 
	 * @param repeat
	 * @param padChar
	 * @return
	 */
	private static String padding( int repeat, char padChar ) {
		if ( repeat < 0 ) {
			throw new IndexOutOfBoundsException( "Cannot pad a negative amount: " + repeat );
		}
		char[] buf = new char[ repeat ];
		for ( int i = 0; i < buf.length; i++ ) {
			buf[ i ] = padChar;
		}
		return new String( buf );
	}

	/**
	 * 左起截取字符串
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String left( String s, int len ) {
		int length = length( s );
		if ( length <= len ) {
			return s;
		}
		return s.substring( 0, len );
	}

	/**
	 * 右起截取字符串
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String right( String s, int len ) {
		int length = length( s );
		if ( length <= len ) {
			return s;
		}
		return s.substring( length - len );
	}

	/**
	 * 根据起点和长度截取字符串
	 * 
	 * @param s
	 * @param startIndex
	 * @param len
	 * @return
	 */
	public static String mid( String s, int startIndex, int len ) {
		int length = length( s );
		if ( ( length == 0 ) || ( len < 0 ) || ( startIndex > length ) ) {
			return s;
		}
		if ( startIndex < 0 ) {
			startIndex = 0;
		}
		if ( s.length( ) <= startIndex + len ) {
			return s.substring( startIndex );
		}
		return s.substring( startIndex, startIndex + len );
	}

	/**
	 * 在S中截取open和close之前的字符串
	 * 
	 * @param s
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetween( String s, String open, String close ) {
		if ( ( s == null ) || ( open == null ) || ( close == null ) ) {
			return null;
		}
		int start = s.indexOf( open );
		if ( start != -1 ) {
			int end = s.indexOf( close, start + open.length( ) );
			if ( end != -1 ) {
				return s.substring( start + open.length( ), end );
			}
		}
		return null;
	}

	/**
	 * 转成字符串数组
	 * @param commaDelim
	 * @return
	 */
	public static String[] toArray( String commaDelim ) {
		if ( isEmpty( commaDelim ) ) {
			return new String[ 0 ];
		}
		List list = toList( commaDelim );
		return ( String[] ) list.toArray( new String[ list.size( ) ] );
	}

	/**
	 * 
	 * @param commaDelim
	 * @param separator
	 * @return
	 */
	public static String[] toArray( String commaDelim, String separator ) {
		if ( isEmpty( commaDelim ) ) {
			return new String[ 0 ];
		}
		List list = toList( commaDelim, separator );
		return ( String[] ) list.toArray( new String[ list.size( ) ] );
	}

	/**
	 * 将制定字符串分离出来并转成字符串数组
	 * @param commaDelim
	 * @param separator
	 * @return
	 */
	public static String[] toArray( String commaDelim, char separator ) {
		if ( isEmpty( commaDelim ) ) {
			return new String[ 0 ];
		}
		List list = toList( commaDelim, separator );
		return ( String[] ) list.toArray( new String[ list.size( ) ] );
	}
	

	/**
	 * 根据","逗号分隔并转化成字符串集合
	 * @param commaDelim
	 * @return
	 */
	public static List< String > toList( String commaDelim ) {
		return toList( commaDelim, ',' );
	}
	
	/**
	 * 将制定字符串分离并转换成字符串集合
	 * @param commaDelim
	 * @param separator
	 * @return
	 */
	public static List< String > toList( String commaDelim, String separator ) {
		int len = length( commaDelim );
		if ( len == 0 ) {
			return Collections.emptyList( );
		}
		List list = new ArrayList( );
		parseCollection( list, commaDelim, separator );
		return list;
	}
	
	/**
	 * 将制定字符分离然后转化成字符串集合
	 * @param commaDelim
	 * @param separator
	 * @return
	 */
	public static List< String > toList( String commaDelim, char separator ) {
		int len = length( commaDelim );
		if ( len == 0 ) {
			return Collections.emptyList( );
		}
		List list = new ArrayList( );
		parseCollection( list, commaDelim, separator );
		return list;
	}
	
	/**
	 * 转成SET集合
	 * @param commaDelim
	 * @return
	 */
	public static Set< String > toSet( String commaDelim ) {
		return toSet( commaDelim, ',' );
	}

	/**
	 * 将制定字符串分裂并转成set集合
	 * @param commaDelim
	 * @param separator
	 * @return
	 */
	public static Set< String > toSet( String commaDelim, String separator ) {
		int len = length( commaDelim );
		if ( len == 0 ) {
			return Collections.emptySet( );
		}
		Set set = new LinkedHashSet( );
		parseCollection( set, commaDelim, separator );
		return set;
	}

	/**
	 * 将制定字符分离并转换成set集合
	 * @param commaDelim
	 * @param separator
	 * @return
	 */
	public static Set< String > toSet( String commaDelim, char separator ) {
		int len = length( commaDelim );
		if ( len == 0 ) {
			return Collections.emptySet( );
		}
		Set set = new LinkedHashSet( );
		parseCollection( set, commaDelim, separator );
		return set;
	}

	/**
	 * 集合分析器
	 * @param collection
	 * @param commaDelim 
	 * @param separator 分离字符串
	 */
	private static void parseCollection( Collection< String > collection, String commaDelim, String separator ) {
		int len = length( commaDelim );
		if ( len == 0 )
			return;
		String s = null;
		if ( ( commaDelim.charAt( 0 ) == '[' ) && ( commaDelim.charAt( len - 1 ) == ']' ) )
			s = commaDelim.substring( 1, len - 1 );
		else {
			s = commaDelim;
		}
		StringTokenizer token = new StringTokenizer( s, separator );
		while ( token.hasMoreTokens( ) ) {
			String item = token.nextToken( );
			String trimmed = item.trim( );
			collection.add( trimmed );
		}
	}

	private static void parseCollection( Collection< String > collection, String commaDelim, char separator ) {
		int len = length( commaDelim );
		if ( len == 0 )
			return;
		String s = null;
		if ( ( commaDelim.charAt( 0 ) == '[' ) && ( commaDelim.charAt( len - 1 ) == ']' ) ) {
			s = commaDelim.substring( 1, len - 1 );
			len = s.length( );
			if ( len == 0 )
				return;
		} else {
			s = commaDelim;
		}
		int index = 0;
		int start = 0;
		while ( index < len ) {
			char ch = s.charAt( index );
			if ( ch == separator ) {
				String block = s.substring( start, index );
				start = index + 1;
				collection.add( block.trim( ) );
			}
			index++;
		}

		if ( start < index ) {
			String block = s.substring( start, index );
			collection.add( block.trim( ) );
		} else if ( start == index ) {
			collection.add( "" );
		}
	}

	/**
	 * 转成map
	 * @param commaDelim
	 * @return
	 */
	public static Map< String, String > toMap( String commaDelim ) {
		if ( ( commaDelim == null ) || ( commaDelim.trim( ).length( ) == 0 ) ) {
			return Collections.emptyMap( );
		}
		String s = null;
		int len = commaDelim.length( );
		if ( ( commaDelim.charAt( 0 ) == '{' ) && ( commaDelim.charAt( len - 1 ) == '}' ) )
			s = commaDelim.substring( 1, len - 1 );
		else {
			s = commaDelim;
		}
		Map map = new LinkedHashMap( );
		StringTokenizer token = new StringTokenizer( s, "," );
		while ( token.hasMoreTokens( ) ) {
			String item = token.nextToken( );
			int index = item.indexOf( ':' );
			if ( index <= 0 ) {
				index = item.indexOf( '=' );
			}
			if ( index <= 0 ) {
				continue;
			}
			String key = item.substring( 0, index ).trim( );
			String val = item.substring( index + 1 ).trim( );
			map.put( key, val );
		}
		return map;
	}

	/**
	 * 将布尔值数组转成字符串
	 * @param array
	 * @return
	 */
	public static String toString( boolean[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}
	
	/**
	 * 将字节数组转成字符串
	 * @param array
	 * @return
	 */
	public static String toString( byte[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	/**
	 * 将短整型数组转成字符串
	 * @param array
	 * @return
	 */
	public static String toString( short[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	public static String toString( int[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	public static String toString( long[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	public static String toString( char[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	public static String toString( float[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	public static String toString( double[] array ) {
		return array != null ? Arrays.toString( array ) : "";
	}

	public static String toString( Collection collection ) {
		return toString( collection, ",", true );
	}

	public static String toString( Collection collection, boolean includeBound ) {
		return toString( collection, ",", includeBound );
	}

	/*
	 * public static String toString(Object[] array) { return toString(array,
	 * true); }
	 * 
	 * public static String toString(Object[] array, boolean includeBound) {
	 * return toString(array, ",", 0, CollectionUtils.length(array),
	 * includeBound); }
	 * 
	 * public static String toString(Object[] array, String separator, int
	 * startIndex, int endIndex, boolean includeBound) { int length =
	 * CollectionUtils.length(array); if (length == 0) { return ""; } int start
	 * = startIndex >= 0 ? Math.min(startIndex, length - 1) : 0; int end =
	 * endIndex >= 0 ? Math.min(endIndex, length) : 0;
	 * 
	 * int bufferSize = end - start; bufferSize *= ((array[start] == null ? 16 :
	 * array[start].toString().length()) + separator.length()); StringBuilder sb
	 * = new StringBuilder(bufferSize + 2); if (includeBound) sb.append('[');
	 * for (int i = start; i < end; i++) { if (i != start) {
	 * sb.append(separator); } sb.append(array[i]); } if (includeBound)
	 * sb.append(']'); return sb.toString(); }
	 */

	/**
	 * 将集合通过分隔符转成字符串
	 * 
	 * @param collection
	 *            集合
	 * @param separator
	 *            分隔符
	 * @param includeBound
	 *            是否添加[]
	 * @return
	 */
	public static String toString( Collection collection, String separator, boolean includeBound ) {
		int length = CollectionUtils.length( collection );
		if ( length == 0 ) {
			return "";
		}
		int bufferSize = length * ( 16 + separator.length( ) );
		StringBuilder sb = new StringBuilder( bufferSize + 2 );
		if ( includeBound )
			sb.append( '[' );
		Iterator iter = collection.iterator( );
		for ( boolean hasNext = iter.hasNext( ); hasNext; ) {
			Object element = iter.next( );
			sb.append( element );
			hasNext = iter.hasNext( );
			if ( hasNext ) {
				sb.append( separator );
			}
		}
		if ( includeBound )
			sb.append( ']' );
		return sb.toString( );
	}

	/*
	 * public static String toString(Map map) { return toString(map, ",", ":",
	 * true); }
	 * 
	 * public static String toString(Map map, boolean includeBound) { return
	 * toString(map, ",", ":", includeBound); }
	 */

	/*
	 * public static String toString(Map map, String separator, String
	 * equalSymbol, boolean includeBound) { int length =
	 * CollectionUtils.length(map); if (length == 0) { return ""; } int
	 * bufferSize = length * (16 + separator.length() + equalSymbol.length());
	 * StringBuffer sb = new StringBuffer(bufferSize + 2); if (includeBound)
	 * sb.append('{'); Iterator iter = map.entrySet().iterator(); for (boolean
	 * hasNext = iter.hasNext(); hasNext; ) { Map.Entry entry =
	 * (Map.Entry)iter.next();
	 * sb.append(entry.getKey()).append(equalSymbol).append
	 * (toString(entry.getValue())); hasNext = iter.hasNext(); if (hasNext) {
	 * sb.append(separator); } } if (includeBound) sb.append('}'); return
	 * sb.toString(); }
	 */

	public static String toString( Object obj ) {
		if ( obj == null ) {
			return "null";
		}
		if ( ( obj instanceof String ) ) {
			return ( String ) obj;
		}
		if ( ( obj instanceof Map ) ) {
			return toString( ( Map ) obj );
		}
		if ( ( obj instanceof Collection ) ) {
			return toString( ( Collection ) obj );
		}
		if ( ( obj instanceof Date ) ) {
			SimpleDateFormat rfc3399 = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );
			return rfc3399.format( ( Date ) obj );
		}
		if ( ( obj.getClass( ).isPrimitive( ) ) || ( obj.getClass( ).getName( ).startsWith( "java" ) ) ) {
			return String.valueOf( obj );
		}
		if ( ( obj instanceof Object[] ) ) {
			return toString( ( Object[] ) obj );
		}
		if ( ( obj instanceof boolean[] ) ) {
			return toString( ( boolean[] ) obj );
		}
		if ( ( obj instanceof byte[] ) ) {
			return toString( ( byte[] ) obj );
		}
		if ( ( obj instanceof short[] ) ) {
			return toString( ( short[] ) obj );
		}
		if ( ( obj instanceof int[] ) ) {
			return toString( ( int[] ) obj );
		}
		if ( ( obj instanceof long[] ) ) {
			return toString( ( long[] ) obj );
		}
		if ( ( obj instanceof char[] ) ) {
			return toString( ( char[] ) obj );
		}
		if ( ( obj instanceof float[] ) ) {
			return toString( ( float[] ) obj );
		}
		if ( ( obj instanceof double[] ) ) {
			return toString( ( double[] ) obj );
		}
		return obj.toString( );
	}

	public static String toObjectString( Object obj ) {
		return obj.getClass( ).getName( ) + "@" + Integer.toHexString( obj.hashCode( ) );
	}

	/**
	 * 清除多余的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String cleanUpRedundantBlank( String str ) {
		if ( null == str ) {
			return str;
		}

		Pattern pattern = Pattern.compile( "\\s+" );
		Matcher matcher = pattern.matcher( str );
		return matcher.replaceAll( " " );
	}

	/**
	 * HTML 特殊字符转义
	 * 
	 * @param html
	 * @return
	 */
	public static String htmlEscape( String html ) {
		if ( null == html ) {
			return "";
		}

		String temp = html;

		/*
		 * temp = temp.replace( "\t", "&nbsp;&nbsp;");// 替换跳格 temp =
		 * temp.replace( " ", "&nbsp;");// 替换空格
		 */

		temp = temp.replace( "'", "&apos;" );
		temp = temp.replaceAll( "&", "&amp;" );
		temp = temp.replace( "\"", "&quot;" ); // "
		temp = temp.replace( "\\/", "&#47;" ); // 反斜杠
		temp = temp.replace( "<", "&lt;" );
		temp = temp.replaceAll( ">", "&gt;" );

		return temp;
	}

	/**
	 * 清除脚本 一般后台使用
	 * 
	 * @param html
	 * @return
	 */
	public static String clearScript( String html ) {
		if ( null == html ) {
			return "";
		}

		Pattern p = Pattern.compile( "(?is)<script[^>]*?>.*?<\\/script>" );
		Matcher m = p.matcher( html );
		return m.replaceAll( "" );

	}

	/**
	 * 把字符串的第一个字母转为大写
	 * 
	 * @param newStr
	 * @return
	 */
	public static String makeFirstLetterUpperCase(String newStr) {
		if (newStr.length() == 0)
			return newStr;

		char[] oneChar = new char[1];
		oneChar[0] = newStr.charAt(0);
		String firstChar = new String(oneChar);
		return (firstChar.toUpperCase() + newStr.substring(1));
	}
}